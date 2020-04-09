package kosta.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		Board board = new Board();

		board.setContents(request.getParameter("contents"));
		board.setTitle(request.getParameter("title"));
		board.setWriter(request.getParameter("writer"));
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
		System.out.println("22222222222");
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
}
