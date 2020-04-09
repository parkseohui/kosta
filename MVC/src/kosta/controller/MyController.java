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
//컨트롤러는 오지랖만 많은시키!!! 걔 일잘해!! 해서 액션한테 넘겨버림
//경로가 겹치면 500에러 뜨기때문에 컨트롤러 경로를 명확하게 해야한다.
//list.do->controller->action->service->dao->mybatis	
//위의 구조는 의존이 되니까 Spring DI가 생김
@WebServlet("/board/*" )
//board가 끼어있으면 내가 알아서 다처리할게 ㅇㅋ?
//.do는 컨트롤러인 내가 맡는다 크흐-
public class MyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MyController() {
        super();
    }
    
    public void doProcess(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    	//경로 식별
    	//insertForm.do, insertAction.do, listAction.do, detailAction.do
    	//http://localhost:8081/MVC/board/insertForm.do 
    	System.out.println(request);
    	System.out.println(response);
    	String requestURI=request.getRequestURI(); //현재 uri를 구해주는 메소드
    	System.out.println(requestURI);
    	//	/MVC/board/insertFrom.do
    	String contextPath=request.getContextPath(); //컨텍스트의 경로를 찾아주는 메소드
    	System.out.println(contextPath);
    	String command=requestURI.substring(contextPath.length()+7); //insertFrom.do를 뽑기위한 작업입니다.
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
    			//redirct면 리다이렉트 아니면 디스패처 시키면된당
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
		/*
		 * 히잉..똑같은게 반복되네? 만들때마다 추가해야하네..? 데이터가 자동으로 변환되고 만들어지고 그랬으면 좋겠다.. -->>>interface
		 */
    }
    //그냥 jsp를 가는 경우는 dispatcher 
    //또다른 do를 가는 경우는 redirect로!!!!!!
    
    
    //get이든 post든 mvc구조 할거야!!! 그래서 doget과 dopost에 doprocess작업을 할거야
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
