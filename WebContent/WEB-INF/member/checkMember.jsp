<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${isMember == true}" var="true" >
		<script type="text/javascript">
			alert("ȸ���̽ʴϴ�. �α����� �� �ּ���")
			self.close()
		</script>
</c:if>

<c:if test ="${isMember == false}">
		<form name="f" action="join_member.do" method="post">
			<input type="hidden" name="name" value="${param.name}"/>
			<input type="hidden" name="ssn1" value="${param.ssn1}"/>
			<input type="hidden" name="ssn2" value="${param.ssn2}"/>
		</form>
		<script type="text/javascript">
			alert("ȸ�������������� �̵��մϴ�")
			document.f.submit()
		</script>
</c:if>









