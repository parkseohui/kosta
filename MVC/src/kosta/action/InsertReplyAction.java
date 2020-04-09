package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.BoardService;
import kosta.model.Reply;

public class InsertReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		BoardService service=BoardService.getInstance();
		ActionForward forward= new ActionForward();
		
		Reply reply =new Reply();
		reply.setR_title(request.getParameter("r_title"));
		reply.setR_writer(request.getParameter("r_writer"));
		reply.setR_contents(request.getParameter("r_contents"));
		reply.setSeq(Integer.parseInt(request.getParameter("seq")));
		
		service.insertReplyService(reply);
		
		forward.setRedirct(true);
		forward.setPath("detailAction.do?seq="+request.getParameter("seq"));
		return forward;
	}

}
