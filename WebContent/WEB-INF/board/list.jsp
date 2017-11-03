<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>mvc게시판</title>
</head>
<body>
<div align="center">
	<b>글 목 록</b>
	<table border="0" width="600">
		<tr bgcolor="yellow">
			<td align="right"><a href="board_writeForm.do">글쓰기</a></td>
		</tr>
	</table>
		<table border="1" width="600">
		<tr bgcolor="green">
			<th>번호</th>
			<th width="30%">제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
			<th>IP</th>
			<th>파일</th>
		</tr>
		
		
		<c:choose>
		<c:when test = "${boardList == null}" >
		<tr>
			<td colspan="6">등록된 게시글이 없습니다.</td>
		</tr>	
		</c:when>
		<c:otherwise>
			<c:forEach items="${boardList}" var="list">
				<tr>
			<td>${list.num}</td>
			<td>
		<c:if test ="${list.re_level > 0}">
				<img src="img/level.gif" width="${list.re_level*10}">
				<img src="img/re.gif">
		</c:if>	
				<a href="board_content.do?num=${list.num}">
					${list.subject}
				</a>
				
		<c:if test = "${list.readcount>10}">
			<img src="img/hot.gif">
		</c:if>
			</td>
			<td>${list.writer}</td>
			<td>${list.reg_date}</td>
			<td>${list.readcount}</td>
			<td>${list.ip}</td>
		</tr>						
			</c:forEach>
		</c:otherwise>
		</c:choose>
	
	</table>
</div>
</body>
</html>




