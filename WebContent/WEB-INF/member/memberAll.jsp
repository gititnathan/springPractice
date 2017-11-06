<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, member.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- memberAll.jsp -->
<html>
<head>
	<title>mvc���</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>	
	<div align="center">
		<hr color="green" width="300">
		<h2>ȸ �� �� �� �� ��</h2>
		<hr color="green" width="300">
		<table width="100%" class="outline">
			<tr>
				<th class="m3">��ȣ</th>
				<th class="m3">�̸�</th>
				<th class="m3">���̵�</th>
				<th class="m3">�̸���</th>
				<th class="m3">��ȭ��ȣ</th>
				<th class="m3">������</th>
				<th class="m3">���� | ����</th>
			</tr>
			
		<c:if test="${memberList==null}">
			<tr>
					<td colspan="7">��ϵ� ȸ���� �����ϴ�.</td>
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
				<a href="edit.member?no=${list.no}">����</a> | 
				<a href="delete.member?no=${list.no}">����</a></td>
			</tr>	
			</c:forEach>
		</c:if>
		

		</table>
	</div>
</body>
</html>














