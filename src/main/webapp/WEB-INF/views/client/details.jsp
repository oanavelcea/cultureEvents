<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coordonnées</title>
</head>
<body>
	<h1>Mes coordonnées</h1>

<div class="alert alert-danger">
	<spring:hasBindErrors name="login-form">
		<c:forEach var="err" items="${errors.allErrors}">
			<c:out value="${err.field}" /> :
			<c:out value="${err.defaultMessage}" />
			<br />
		</c:forEach>
	</spring:hasBindErrors>
</div>

	<form:form method="post" action="client/sauvegarde-coordonnees" modelAttribute="user-form">
		<form:label path="gender">Votre civilité :</form:label>
		<form:select path="gender">
			<form:option value="M">M</form:option>
			<form:option value="MME">MME</form:option>
		</form:select>
		<br />
		<form:label path="name">Votre nom :</form:label>
		<form:input path="name" />
		<br />

		<form:label path="address">Votre adresse :</form:label>
		<form:input path="address" />
		<br />

		<form:label path="dateOfBirth">Votre année de naissance :</form:label>
		<form:input path="dateOfBirth" />
		<br />

		<form:label path="email">Email :</form:label>
		<form:input path="email" />
		<br />

		<form:label path="password">Mot de passe :</form:label>
		<form:password path="password" />
		<br />

		<input type="submit" value="Modifier" />
	</form:form>
</body>
</html>