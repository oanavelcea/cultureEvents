<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Coordonnées</title>
</head>
<body>
	<h1>Mes coordonnées</h1>

	<form:form method="post" action="modify-changes" modelAttribute="user">
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