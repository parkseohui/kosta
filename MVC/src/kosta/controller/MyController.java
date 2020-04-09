package kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.action.Action;
import kosta.action.ActionForward;
import kosta.action.DeleteAction;
import kosta.action.DetailAction;
import kosta.action.InsertAction;
import kosta.action.InsertActionForm;
import kosta.action.ListAction;
import kosta.action.UpdateAction;
//��Ʈ�ѷ��� �������� ������Ű!!! �� ������!! �ؼ� �׼����� �Ѱܹ���
//��ΰ� ��ġ�� 500���� �߱⶧���� ��Ʈ�ѷ� ��θ� ��Ȯ�ϰ� �ؾ��Ѵ�.
//list.do->controller->action->service->dao->mybatis	
//���� ������ ������ �Ǵϱ� Spring DI�� ����
@WebServlet("/board/*" )
//board�� ���������� ���� �˾Ƽ� ��ó���Ұ� ����?
//.do�� ��Ʈ�ѷ��� ���� �ô´� ũ��-
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MyController() {
        super();
    }
    
    public void doProcess(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//��� �ĺ�
    	//insertForm.do, insertAction.do, listAction.do, detailAction.do
    	//http://localhost:8081/MVC/board/insertForm.do 
    	System.out.println(request);
    	System.out.println(response);
    	String requestURI=request.getRequestURI(); //���� uri�� �����ִ� �޼ҵ�
    	System.out.println(requestURI);
    	//	/MVC/board/insertFrom.do
    	String contextPath=request.getContextPath(); //���ؽ�Ʈ�� ��θ� ã���ִ� �޼ҵ�
    	System.out.println(contextPath);
    	String command=requestURI.substring(contextPath.length()+7); //insertFrom.do�� �̱����� �۾��Դϴ�.
    	System.out.println(command);
    	
    	Action action =null;
    	ActionForward forward=null;
    	
    	
    	if(command.equals("insertForm.do")) {
    		action=new InsertActionForm();
    		try {
    			forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}else if(command.equals("insertAction.do")) {
    		action=new InsertAction();
    		try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}else if(command.equals("listAction.do")) {
    		action=new ListAction();
    		try {
    			forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}else if(command.equals("detailAction.do")) {
    		action=new DetailAction();
    		try {
    			forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}else if(command.equals("deleteAction.do")) {
    		System.out.println("gkgkgk");
    		action=new DeleteAction();
    		try {
    			forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("updateAction.do")) {
    		System.out.println("gkgkgk");
    		action=new UpdateAction();
    		try {
    			forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	if(forward!=null) {
    		if(forward.isRedirct()) {
    			//redirct�� �����̷�Ʈ �ƴϸ� ����ó ��Ű��ȴ�
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
		/*
		 * ����..�Ȱ����� �ݺ��ǳ�? ���鶧���� �߰��ؾ��ϳ�..? �����Ͱ� �ڵ����� ��ȯ�ǰ� ��������� �׷����� ���ڴ�.. -->>>interface
		 */
    }
    //�׳� jsp�� ���� ���� dispatcher 
    //�Ǵٸ� do�� ���� ���� redirect��!!!!!!
    
    
    //get�̵� post�� mvc���� �Ұž�!!! �׷��� doget�� dopost�� doprocess�۾��� �Ұž�
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
