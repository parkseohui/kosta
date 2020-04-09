
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h3>글 세부보기</h3>
   
   <!-- JSTL 사용하기 -->
   <ul>
      <li>${ board.title }</li>
      <li>${ board.writer }</li>
      <li>
         <!-- •2020-04-06 14:11:45 -->
         <fmt:parseDate var="date" value="${board.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
         <fmt:formatDate value="${ date}" pattern="yyyy.MM.dd. HH:mm:ss"/>
      
      </li>
      <li>${ board.hitcount }</li>
      <li>${ board.contents }</li>
   </ul>
   <a href="updateForm.jsp?seq=${ board.seq }">수정</a>
   <a href="deleteAction.do?seq=${ board.seq }">삭제</a>
   <!-- onclick="return deleteBtn();"  -->
   <h4><a href="listAction.do">글 목록 가기</a></h4>
<script>
function deleteBtn(){
   return confirm("삭제하시겠습니까?");
};
</script>
</body>
</html>