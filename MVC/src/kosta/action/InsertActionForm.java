package kosta.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//사실 이친구는 insertform이고 싶었던거임
public class InsertActionForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		forward.setRedirct(false);
		forward.setPath("/insert_form.jsp");
		return forward;
	}
}
