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
		
		//���Ƹ�����µ� REQUEST���þ��ϸ� �������� �ФФ�
		
		forward.setRedirct(true);
		forward.setPath("listAction.do");
		return forward;
	}

}
