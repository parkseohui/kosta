<%@page import="kosta.model.BoardDao2"%>
<%@page import="kosta.model.Board"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h3>�ۼ�����</h3>
	<form action="realupdateAction.do" method="post">
	<input type="hidden" name="seq" value="${board.getSeq()}">
	�ۼ��� : <input type="text" name="writer" value="${board.getWriter()}"><br>
	���� : <input type="text" name="title" value="${board.getTitle()}"><br>
	���� <br>
	<textarea rows="6" cols="70" name="contents">${board.getContents()}</textarea>
	<br>
	<input type="submit" value="����">
</form>
</body>
</html>