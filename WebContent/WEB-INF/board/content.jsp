<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
			<td align="center" width="35%">${getBoard.num}</td>
			<th bgcolor="yellow" width="15%">��ȸ��</th>
			<td align="center" width="35%">${getBoard.readcount}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">�ۼ���</th>
			<td align="center" width="35%">${getBoard.writer}</td>
			<th bgcolor="yellow" width="15%">�ۼ���</th>
			<td align="center" width="35%">${getBoard.reg_date}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">������</th>
			<td align="center" colspan="3">${getBoard.subject}</td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="15%">�۳���</th>
			<td colspan="3">${getBoard.content}</td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="4" align="right">
				<input type="button" value="��۾���"
					onclick="window.location='board_writeForm.do?num=${getBoard.num}&re_group=${getBoard.re_group}&re_step=${getBoard.re_step}&re_level=${getBoard.re_level}'">
				<input type="button" value="�ۼ���"
					onclick="window.location='board_updateForm.do?num=${getBoard.num}'">
				<input type="button" value="�ۻ���"
					onclick="window.location='board_deleteForm.do?num=${getBoard.num}'">
				<input type="button" value="�۸��" 
					onclick="window.location='board_list.do'">
			</td>
		</tr>
	</table>
</div>
</body>
</html>



















