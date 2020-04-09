package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;

public class DeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		service.deleteBoardService(request);
		
		//실컷만들었는데 REQUEST셋팅안하면 못가져가 ㅠㅠㅠ
		
		forward.setRedirct(true);
		forward.setPath("listAction.do");
		return forward;
	}

}
