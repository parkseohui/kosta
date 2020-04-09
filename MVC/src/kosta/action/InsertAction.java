package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;

public class InsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		BoardService service=BoardService.getInstance();
		
		service.insertBoardService(request);
		//List<Board> list=service.listBoardService();
		
		forward.setRedirct(true);
		forward.setPath("listAction.do");
		return forward;
		
		
	}

}
