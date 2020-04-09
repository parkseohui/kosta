package kosta.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.model.Board;
import kosta.model.BoardService;

public class DetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward=new ActionForward();
		BoardService service=BoardService.getInstance();
		
		Board b = service.detailBoardService(request);
		request.setAttribute("board",b);
		//���Ƹ�����µ� REQUEST���þ��ϸ� �������� �ФФ�
		
		forward.setRedirct(false);
		forward.setPath("/detail.jsp");
		return forward;
	}

}
