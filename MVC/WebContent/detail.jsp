
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
   <div>
   <h3>
   댓글목록
   </h3>
   <table border="1">
   <tr>
   <td>댓글번호</td>
   <td>댓글제목</td>
   <td>댓글작성자</td>
   <td>댓글내용</td>
   <td>댓글날짜</td>
   </tr>
   <c:forEach var="reply" items="${replys}">
   <tr>
   <td>${reply.r_no }</td>
   <td>${reply.r_title }</td>
   <td>${reply.r_writer }</td>
   <td>${reply.r_contents }</td>
   <td>${reply.r_regdate }</td>
   </tr>
   </c:forEach>
   
   </table>
   
   </div>
   <br>
   <form action="insertReplyAction.do" method="post">
   <input type="hidden" name="seq" value="${board.seq}">
   댓글제목:<input type="text" name="r_title"><br>
   댓글작성자:<input type="text" name="r_writer"><br>
   댓글내용:<input type="text" name="r_contents"><br>
   <input type="submit" value="댓글쓰기">
   
   </form>
   
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