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
   <a href="insertForm.do">글쓰기</a>
   <table border="1">
      <tr>
         <td>글번호</td>
         <td>글제목</td>
         <td>작성자</td>
         <td>작성일자</td>
         <td>조회수</td>
      </tr>

      <c:forEach var="b" items="${listModel.list}">
      <tr>
         <td>${b.seq }</td> <!-- detailAction.do 로 가는 이유는 데이터를 넘겨주기위해서 바로 jsp로 안가는 이유는 단순한 표현이기때문 -->
         <td><a href="detailAction.do?seq=${b.seq}">${b.title}</a></td> 
         <td>${b.writer}</td>
         <td>
         <!-- "2020-04-02 14:57:47" -->
         <fmt:parseDate var="dt" value="${b.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
         <fmt:formatDate value="${dt}" pattern="yyyy/MM/dd"/>
         </td>
         <td>${b.hitcount}</td>
      </tr>
      
      </c:forEach>
   </table>
   <br><br><br>
   <!-- 페이지처리 영역 -->
   <!-- 이전 -->
   
   <c:if test="${listModel.startPage>5}">
   <a href="listAction.do?pageNum=${listModel.startPage-1}">[이전]</a>
   </c:if>
   
<%--    <c:choose>
<c:when test="${fruit=='애플'}">
<c:out value="apple"/></c:when>
<c:when test="${fruit=='오렌지'}">
<c:out value="orange"/></c:when>
<c:otherwise>
 <c:out value="기타과일"/>
</c:otherwise>
</c:choose> --%>
   
   <!-- 페이지 목록출력 -->
   <c:forEach var="pageNo" begin="${listModel.startPage }" end="${listModel.endPage }">
   <c:choose>
    <c:when test="${listModel.requestPage== pageNo}">
    <a href="listAction.do?pageNum=${pageNo}">[<b>${pageNo}</b>]</a>
    </c:when>
    <c:otherwise>
   <a href="listAction.do?pageNum=${pageNo}">[${pageNo}]</a>
  </c:otherwise>
   </c:choose>
   
   </c:forEach>
   
   <!-- 이후 -->
   <c:if test="${listModel.endPage<listModel.totalPageCount }">
   <a href="listAction.do?pageNum=${listModel.endPage+1}">[이후]</a>
   </c:if>
   
   <form action="listAction.do" method="post">
      <input type="checkbox" name="area" value="title">제목
      <input type="checkbox" name="area" value="writer">작성자
      <input type="text" name="searchKey" size="10">
      <input type="submit" value="검색">
   </form>
</body>
</html>






