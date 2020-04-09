package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.BoardService;

public class UpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		BoardService service = BoardService.getInstance();
		System.out.println("111111111");
		service.deleteBoardService(request);
		//실컷만들었는데 REQUEST셋팅안하면 못가져가 ㅠㅠㅠ
		
		forward.setRedirct(true);
		forward.setPath("listAction.do");
		return forward;
	}

}
