package kosta.model;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class BoardService {
	// ���񽺰� �������� Action�κ��� ���������Եȴ�
	// ���񽺿��� �������� ������ ���ÿ� �����Ű������ dao�� ������!
	// �Խ��ǿ� �۸����°Ծƴ϶� ��۵������ϰ� ��ȸ���� �������Ѿ��ϰ�.. �������� ������ ���ƾ��ϴϱ��!
	private static BoardService service = new BoardService();
	private static BoardDao2 dao;
	private static final int PAGE_SIZE = 2;

	public static BoardService getInstance() {
		dao = BoardDao2.getInstance();
		return service;
	}

	public int insertBoardService(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		//���� ���ε� (���,����ũ��, ���ڵ�, �����̸� ��ø ��å)
		String uploadPath=request.getRealPath("upload");
		System.out.println(uploadPath);
		//���� �׾����°� ��������ϻ� ����� �����ϴ�
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
		
		//���Ͼ��ε�� db(�����̸�����)
		if(multi.getFilesystemName("fname")!=null) {
			String fname=(String)multi.getFilesystemName("fname");
			board.setFname(fname);
			
			//����� �̹���(gif,jpg) =>aa.gif, aa.jpg
			String pattern = fname .substring(fname.indexOf(".")+1);//gif
			String head=fname.substring(0, fname.indexOf("."));//aa
			
			//���� file��ü
			String imagePath=uploadPath + "\\" + fname;
			System.out.println(imagePath);
			File src=new File(imagePath);
			
			//����� file��ü
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
		//�˻��Ұ��(�˻���ư Ŭ��)
		if(request.getParameterValues("area")!=null) {
			session.removeAttribute("search");
			search.setArea(request.getParameterValues("area"));
			search.setSearchKey("%" + request.getParameter("searchKey") + "%");
			session.setAttribute("search", search);
			
			//�˻��� �������� Ŭ���Ұ��
		}else if(session.getAttribute("search")!=null) {
			search=(Search)session.getAttribute("search");
		}
		// ����¡ ó�� ����
		// �������� �۰���, �ѱ۰���, ����������, ����������
		// �� �۰���
		int totalCount = dao.countBoard(search);
		// ����������
		int totalPageCount = totalCount / PAGE_SIZE;
		if (totalCount % PAGE_SIZE > 0) {
			totalPageCount++;
		}

		// ����������
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int requestPage = Integer.parseInt(pageNum);
		// startpage
		// startpage=����������-(����������-1)%5 --> �� ģ���� �����Դϴ�.
		int startPage = requestPage - (requestPage - 1) % 5;

		// endpage
		int endPage = startPage + 4;
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		// startRow
		// startRow=(����������-1)*�������� �۰���
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
