package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;
import kosta.model.Reply;

public class DetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		BoardService service=BoardService.getInstance();
		
		Board b = service.detailBoardService(request);
		request.setAttribute("board",b);
		int seq=Integer.parseInt(request.getParameter("seq"));
		
		List<Reply> replys=service.listReplyService(seq);
		//실컷만들었는데 REQUEST셋팅안하면 못가져가 ㅠㅠㅠ
		
		request.setAttribute("board", b);
		request.setAttribute("replys", replys);
		
		forward.setRedirct(false);
		forward.setPath("/detail.jsp");
		return forward;
	}

}
