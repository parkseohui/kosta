package kosta.model;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BoardService {
	// 서비스가 없어지면 Action부분이 복잡해지게된다
	// 서비스에서 여러개의 쿼리를 동시에 실행시키기위해 dao를 여러개!
	// 게시판에 글만보는게아니라 댓글도봐야하고 조회수도 증가시켜야하고.. 여러개의 쿼리가 돌아야하니까요!
	private static BoardService service = new BoardService();
	private static BoardDao2 dao;
	private static final int PAGE_SIZE = 2;

	public static BoardService getInstance() {
		dao = BoardDao2.getInstance();
		return service;
	}

	public int insertBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		//파일 업로드 (경로,파일크기, 인코딩, 파일이름 중첩 정책)
		String uploadPath=request.getRealPath("upload");
		System.out.println(uploadPath);
		//줄이 그어지는건 사용비권장일뿐 사용은 가능하다
		int size=20*1024*1024;//20mb
		MultipartRequest multi=
				new MultipartRequest(request,uploadPath,size,
						"utf-8",new DefaultFileRenamePolicy());
		System.out.println(uploadPath);
		Board board = new Board();

		board.setContents(multi.getParameter("contents"));
		board.setTitle(multi.getParameter("title"));
		board.setWriter(multi.getParameter("writer"));
		board.setFname("");
		
		//파일업로드시 db(파일이름저장)
		if(multi.getFilesystemName("fname")!=null) {
			String fname=(String)multi.getFilesystemName("fname");
			board.setFname(fname);
			
			//썸네일 이미지(gif,jpg) =>aa.gif, aa.jpg
			String pattern = fname .substring(fname.indexOf(".")+1);//gif
			String head=fname.substring(0, fname.indexOf("."));//aa
			
			//원본 file객체
			String imagePath=uploadPath + "\\" + fname;
			System.out.println(imagePath);
			File src=new File(imagePath);
			
			//썸네일 file객체
			String thumPath=uploadPath+"\\"+ head +"_small."+pattern;
			File dest=new File(thumPath);
			
			if(pattern.equals("gif")||pattern.equals("jpg")) {
				ImageUtil.resize(src,dest,100,ImageUtil.RATIO);
			}
		}
		
		return dao.insertBoard(board);
	}

	public ListModel listBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		Search search = new Search();
		HttpSession session=request.getSession();
		//검색할경우(검색버튼 클릭)
		if(request.getParameterValues("area")!=null) {
			session.removeAttribute("search");
			search.setArea(request.getParameterValues("area"));
			search.setSearchKey("%" + request.getParameter("searchKey") + "%");
			session.setAttribute("search", search);
			
			//검색후 페이지를 클릭할경우
		}else if(session.getAttribute("search")!=null) {
			search=(Search)session.getAttribute("search");
		}
		// 페이징 처리 로직
		// 페이지당 글갯수, 총글갯수, 총페이지수, 현재페이지
		// 총 글갯수
		int totalCount = dao.countBoard(search);
		// 총페이지수
		int totalPageCount = totalCount / PAGE_SIZE;
		if (totalCount % PAGE_SIZE > 0) {
			totalPageCount++;
		}

		// 현재페이지
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int requestPage = Integer.parseInt(pageNum);
		// startpage
		// startpage=현재페이지-(현재페이지-1)%5 --> 이 친구는 공식입니다.
		int startPage = requestPage - (requestPage - 1) % 5;

		// endpage
		int endPage = startPage + 4;
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		// startRow
		// startRow=(현재페이지-1)*페이지당 글갯수
		int startRow = (requestPage - 1) * PAGE_SIZE;

		List<Board> list = dao.listBoard(search, startRow);
		ListModel listModel = new ListModel(list, requestPage, totalPageCount, startPage, endPage);

		return listModel;
	}

	public Board detailBoardService(HttpServletRequest request) throws Exception {
		String x = request.getParameter("seq");
		return dao.detailBoard(Integer.parseInt(x));
	}
	
	public void deleteBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String x=request.getParameter("seq");
		System.out.println(x);
		dao.deleteBoard(Integer.parseInt(x));
	}
	
	public int insertReplyService(Reply reply) {
		return dao.insertReply(reply);
	}
	public List<Reply> listReplyService(int seq){
		return dao.listReply(seq);
	}
	public int updateBoardService(Board board) {
		return dao.updateBoard(board);
	}
}
