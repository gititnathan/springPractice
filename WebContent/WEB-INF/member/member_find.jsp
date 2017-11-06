<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*, member.*"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- member_find.jsp -->
<html>
<head>
	<title>mvc���</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>	
	<div align="center">
		<hr color="green" width="300">
		<h2>ȸ �� ã ��</h2>
		<form name="f" method="post" action="find_member.do">
			<select name="search">
				<option value="id">���̵�</option>
				<option value="name">�̸�</option>
			</select>
			<input type="text" name="searchString" class="box">
			<input type="submit" value="�˻�">
		</form>
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
			
		<c:if test="${memberFind==null}">
			<tr>
					<td colspan="7">ã���ô� ȸ���� �����ϴ�.</td>
			</tr>
		</c:if>
		<c:if test="${memberFind!=null}">
			<c:forEach items= "${memberFind}" var="list" >
				<tr>
				<td>${list.no}</td>
				<td>${list.name }</td>
				<td>${list.id}</td>
				<td>${list.email}</td>
				<td>${list.allHp}</td>
				<td>${list.joindate }</td>
				<td>
				<a href="edit_member.do?no=${list.no}">����</a> | 
				<a href="delete_member.do?no=${list.no}">����</a></td>
			</tr>	
			</c:forEach>
		</c:if>
		</table>
		<br>
		<a href="index_member.do">ȸ�������������� �̵�</a>
	</div>
</body>
</html>