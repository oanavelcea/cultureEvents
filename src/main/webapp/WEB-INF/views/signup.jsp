<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<title>Inscription</title>
</head>
<body>
	<h1>Créer un compte</h1>

	<form:form method="post" action="validate-signup"
		modelAttribute="signup-form">

		<p>Vos coordonnées</p>

		<p>
			<form:label path="gender">Civilité :</form:label>
			<form:select path="gender" class="custom-select col-sm-1">
				<form:option value="M">M</form:option>
				<form:option value="MME">MME</form:option>
			</form:select>
		</p>

		<form:label path="name">Votre nom :</form:label>
		<form:input path="name" />
		<br />

		<form:label path="address">Votre adresse :</form:label>
		<form:input path="address" />
		<br />

		<fieldset >
			<legend>Date de naissance</legend>
			
			<form:label path="day" class="mr-sm-2" for="inlineFormCustomSelect"> Jour : </form:label>
			<form:select path="day" items="${days }" class="custom-select col-sm-1" id="inlineFormCustomSelect"/>
			
			<form:label path="month" class="mr-sm-2" for="inlineFormCustomSelect"> Mois : </form:label>
			<form:select path="month" items="${months }" class="custom-select col-sm-1" id="inlineFormCustomSelect"/>
			
			<form:label path="year" class="mr-sm-2" for="inlineFormCustomSelect"> Année : </form:label>
			<form:select path="year" items="${years }" class="custom-select col-sm-1" id="inlineFormCustomSelect"/>
			
		</fieldset>
		<br />


		<form:label path="email">Email :</form:label>
		<form:input path="email" />
		<br />

		<form:label path="password">Mot de passe :</form:label>
		<form:password path="password" />
		<br />

		<input type="submit" value="S'inscrire" />
	</form:form>
</body>
</html>
