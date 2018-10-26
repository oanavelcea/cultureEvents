<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>

<h1>Authentication</h1>

<div class="alert alert-danger">
	<spring:hasBindErrors name="login-form">
		<c:forEach var="err" items="${errors.allErrors}">
			<c:out value="${err.field}" /> :
			<c:out value="${err.defaultMessage}" />
			<br />
		</c:forEach>
	</spring:hasBindErrors>
</div>

<form:form method="post" action="check-login"
	modelAttribute="login-form">
	<form:label path="username">Email :</form:label>
	<form:input path="username" />

	<br />

	<form:label path="password">Mot de passe :</form:label>
	<form:password path="password" />
	<br />
	<input type="submit" value="Connect" />
</form:form>

<div>${msg}</div>

</body>
</html>
