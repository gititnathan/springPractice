<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, member.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- memberAll.jsp -->
<html>
<head>
	<title>mvc멤버</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>	
	<div align="center">
		<hr color="green" width="300">
		<h2>회 원 목 록 보 기</h2>
		<hr color="green" width="300">
		<table width="100%" class="outline">
			<tr>
				<th class="m3">번호</th>
				<th class="m3">이름</th>
				<th class="m3">아이디</th>
				<th class="m3">이메일</th>
				<th class="m3">전화번호</th>
				<th class="m3">가입일</th>
				<th class="m3">수정 | 삭제</th>
			</tr>
			
		<c:if test="${memberList==null}">
			<tr>
					<td colspan="7">등록된 회원이 없습니다.</td>
			</tr>
		</c:if>
		<c:if test="${memberList!=null}">
			<c:forEach items ="${memberList}" var="list" >
				<tr>
				<td>${list.no}</td>
				<td>${list.name }</td>
				<td>${list.id}</td>
				<td>${list.email}</td>
				<td>${list.allHp}</td>
				<td>${list.joindate }</td>
				<td>
				<a href="edit.member?no=${list.no}">수정</a> | 
				<a href="delete.member?no=${list.no}">삭제</a></td>
			</tr>	
			</c:forEach>
		</c:if>
		

		</table>
	</div>
</body>
</html>














