<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
<head>
	<title>mvc�Խ���</title>
	<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center">
	<b>�۳��� ����</b>
	<table border="1" width="500">
		<tr>
			<th bgcolor="yellow" width="15%">�۹�ȣ</th>
			<td align="center" width="35%">${getTest.num}</td>
			<th bgcolor="yellow" width="15%">��ȸ��</th>
			<td align="center" width="35%">${getTest.readcount}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">�ۼ���</th>
			<td align="center" width="35%">${getTest.writer}</td>
			<th bgcolor="yellow" width="15%">�ۼ���</th>
			<td align="center" width="35%">${getTest.reg_date}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">������</th>
			<td align="center" colspan="3">${getTest.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">�۳���</th>
			<td colspan="3">${getTest.content}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">���ϸ�</th>
			<td colspan="3">${getTest.filename}</td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="��۾���"
					onclick="window.location='test_write.do?num=${getTest.num}&re_step=${getTest.re_step}&re_level=${getTest.re_level}'">
				<input type="button" value="�ۼ���"
					onclick="window.location='test_update.do?num=${getTest.num}'">
				<input type="button" value="�ۻ���"
					onclick="window.location='test_delete.do?num=${getTest.num}'">
				<input type="button" value="�۸��" 
					onclick="window.location='test_list.do'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>



















