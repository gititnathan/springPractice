<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
<head>
	<title>mvc게시판</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center">
	<b>글내용 보기</b>
	<table border="1" width="500">
		<tr>
			<th bgcolor="yellow" width="15%">글번호</th>
			<td align="center" width="35%">${getTest.num}</td>
			<th bgcolor="yellow" width="15%">조회수</th>
			<td align="center" width="35%">${getTest.readcount}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">작성자</th>
			<td align="center" width="35%">${getTest.writer}</td>
			<th bgcolor="yellow" width="15%">작성일</th>
			<td align="center" width="35%">${getTest.reg_date}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">글제목</th>
			<td align="center" colspan="3">${getTest.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">글내용</th>
			<td colspan="3">${getTest.content}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">파일명</th>
			<td colspan="3">${getTest.filename}</td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="답글쓰기"
					onclick="window.location='test_write.do?num=${getTest.num}&re_step=${getTest.re_step}&re_level=${getTest.re_level}'">
				<input type="button" value="글수정"
					onclick="window.location='test_update.do?num=${getTest.num}'">
				<input type="button" value="글삭제"
					onclick="window.location='test_delete.do?num=${getTest.num}'">
				<input type="button" value="글목록" 
					onclick="window.location='test_list.do'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>



















