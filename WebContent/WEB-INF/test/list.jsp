<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
	<title>mvc�Խ���</title>
</head>
<body>
<div align="center">
	<b>�� �� ��</b>
	<table border="0" width="600">
		<tr bgcolor="yellow">
			<td align="right"><a href="test_write.do">�۾���</a></td>
		</tr>
	</table>
		<table border="1" width="600">
		<tr bgcolor="green">
			<th>��ȣ</th>
			<th width="30%">����</th>
			<th>�ۼ���</th>
			<th>�ۼ���</th>
			<th>��ȸ</th>
			<th>IP</th>
			<th>����</th>
		</tr>
		
		
		<c:choose>
		<c:when test = "${TestList == null}" >
		<tr>
			<td colspan="6">��ϵ� �Խñ��� �����ϴ�.</td>
		</tr>	
		</c:when>
		<c:otherwise>
			<c:forEach items="${TestList}" var="list">
				<tr>
			<td>${list.num}</td>
			<td>
				<a href="test_content.do?num=${list.num}">
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




