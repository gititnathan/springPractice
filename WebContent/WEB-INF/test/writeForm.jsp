<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
	<title>mvc게시판</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<script type="text/javascript">
		function check(){
			if (f.writer.value==""){
				alert("이름을 입력해 주세요!!")
				f.writer.focus()
				return false
			}
			if (f.subject.value==""){
				alert("제목을 입력해 주세요!!")
				f.subject.focus()
				return false
			}
			if (f.content.value==""){
				alert("내용을 입력해 주세요!!")
				f.content.focus()
				return false
			}
			if (f.passwd.value==""){
				alert("비밀번호를 입력해 주세요!!")
				f.passwd.focus()
				return false
			}
			return true
		}
	</script>
</head>	
<body>

<div align="center">
	<form name="f" action="test_writepro.do" method="post" enctype= "multipart/form-data" onsubmit="return check()">

	<input type="hidden" name="num" value="${param.num}"/>
	<input type="hidden" name="re_group" value="${param.re_group}"/>
	<input type="hidden" name="re_step" value="${param.re_step}"/>
	<input type="hidden" name="re_level" value="${param.re_level}"/>
	<table border="1" width="500">
		<tr bgcolor="yellow">
			<th colspan="2">글 쓰 기</th>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">이 름</th>
			<td><input type="text" name="writer" class="box"></td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">제 목</th>
			<td><input type="text" name="subject" class="box" size="50"></td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">Email</th>
			<td><input type="text" name="email" class="box" size="50"></td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">파일명</th>
			<td><input type="file" name="filename" class="box" size="50"></td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">내 용</th>
			<td><textarea name="content" rows="13" cols="50" class="box"></textarea></td>
		</tr>
		<tr>
			<th bgcolor="yellow" width="20%">비밀번호</th>
			<td><input type="password" name="passwd" class="box"></td>
		</tr>
		<tr bgcolor="yellow">
			<td colspan="2" align="center">
				<input type="submit" value="글쓰기">
				<input type="reset" value="다시작성">
				<input type="button" value="목록보기" 	onclick="window.location='test_list.do'">
			</td>	
		</tr>
	</table>
	</form>
</div>
</body>
</html>












