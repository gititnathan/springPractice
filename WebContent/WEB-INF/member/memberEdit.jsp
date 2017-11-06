<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import = "member.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test = "${getMember.no == null}" >
	<c:redirect url="memberAll.jsp"/>
</c:if>


<script type="text/javascript">
	function check(){
		if (f.passwd.value==""){
			alert("비밀번호를 입력하세요")
			f.passwd.focus()
			return
		}
		document.f.submit()
	}
</script>


<html>
<head>
	<title>회원수정 </title>
	<link rel="stylesheet" type="text/css" href="style.css">
		
</head>
	<body onload="f.name.focus()">
		<form name="f" method="POST" action="editOk.member">
			<input type="hidden" name="no" value="${getMember.no}">
			<table width="550" align="center" class="outline">
  				<tr>
					<td colspan="2" align=center class="m2">회원수정</td>
 				</tr>
				<tr>
					<td width="150" class="m3">이름</td>
					<td class="m3">
						<input type="text" name="name" class="box"
							value="${getMember.name }" disabled>
						<input type="hidden" name="name" value="${getMember.name}"/>	
					</td>
				</tr>
				<tr>
					<td width="150" class="m3">아이디</td>
					<td class="m3">
						<input type="text" name="id" class="box"
							value="${getMember.id}" readOnly>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">비밀번호</td>
					<td class="m3">
						<input type="password" name="passwd" 
							value="${getMember.passwd}" class="box">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">주민번호</td>
					<td class="m3">
						<input type="text" name="ssn1" class="box"
							value="${getMember.ssn1}" readOnly> -
						<input type="password" name="ssn2" class="box"
							value="${getMember.ssn2}" readOnly>
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">이메일</td>
					<td class="m3">
						<input type="text" name="email" class="box"
							value="${getMember.email}">
					</td>
  				</tr>
  				<tr>
					<td width="150" class="m3">연락처</td>
					<td class="m3">
						<input type="text" name="hp1" class="box" 
							size="3" maxlength="3" value="${getMember.hp1 }"> -
						<input type="text" name="hp2" class="box" 
							size="4" maxlength="4" value="${getMember.hp2 }"> -
						<input type="text" name="hp3" class="box" 
							size="4" maxlength="4" value="${getMember.hp3 }">
					</td>
  				</tr>
  				<tr>
					<td colspan="2" align="center">
						<a href="javascript:check()">[수정]</a>
						<a href="#">[취소]</a>
					</td>
  				</tr>
  			</table>
		</form>
	</body>
</html>
