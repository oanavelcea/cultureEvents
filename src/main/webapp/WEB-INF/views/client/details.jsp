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

	<form:form method="post" action="client/sauvegarde-coordonnees"
		modelAttribute="user-form">
		
		<form:label path="gender" class="lbel-control">Votre civilité :</form:label>
		<form:select path="gender" class="custom-select col-sm-1">
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

		<fieldset>
			<legend>Date de naissance</legend>

			<form:label path="day" class="mr-sm-2" for="inlineFormCustomSelect"> Jour : </form:label>
			<form:select path="day" items="${days }"
				class="custom-select col-sm-1" id="inlineFormCustomSelect" />

			<form:label path="month" class="mr-sm-2" for="inlineFormCustomSelect"> Mois : </form:label>
			<form:select path="month" items="${months }"
				class="custom-select col-sm-1" id="inlineFormCustomSelect" />

			<form:label path="year" class="mr-sm-2" for="inlineFormCustomSelect"> Année : </form:label>
			<form:select path="year" items="${years }"
				class="custom-select col-sm-1" id="inlineFormCustomSelect" />

		</fieldset>
		<br />

<%-- 		<form:label path="dateOfBirth">Votre année de naissance :</form:label> --%>
<%-- 		<form:input path="dateOfBirth" /> --%>
<!-- 		<br /> -->

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