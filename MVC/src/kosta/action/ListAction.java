package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;
import kosta.model.ListModel;

public class ListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		BoardService service=BoardService.getInstance();
		
		//service.insertBoardService(request);
		ListModel listModel=service.listBoardService(request);
		request.setAttribute("listModel",listModel);
		
		forward.setRedirct(false);
		forward.setPath("/list.jsp");
		return forward;
			}

}
