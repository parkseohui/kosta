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
	<h3>글수정폼</h3>
	<form action="realupdateAction.do" method="post">
	<input type="hidden" name="seq" value="${board.getSeq()}">
	작성자 : <input type="text" name="writer" value="${board.getWriter()}"><br>
	제목 : <input type="text" name="title" value="${board.getTitle()}"><br>
	내용 <br>
	<textarea rows="6" cols="70" name="contents">${board.getContents()}</textarea>
	<br>
	<input type="submit" value="수정">
</form>
</body>
</html>