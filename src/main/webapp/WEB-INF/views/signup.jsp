<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<title>Inscription</title>

<!-- Google Fonts -->
<!-- <link href='https://fonts.googleapis.com/css?family=Passion+One' -->
<!-- 	rel='stylesheet' type='text/css'> -->
<!-- <link href='https://fonts.googleapis.com/css?family=Oxygen' -->
<!-- 	rel='stylesheet' type='text/css'> -->


<!-- <script -->
<!-- 	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script> -->
<!-- <script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->

</head>
<body>


	<div class="container">
		<div class="row main ">
			<h1 class="col-lg-9 col-lg-offset-1">Créer un compte</h1>

			<div class="row col-lg-9 form-coordonnees">
				<form:form method="post" action="validate-signup"
					modelAttribute="signup-form" class="col-lg-10">

					<p>Vos coordonnées</p>

					<div class="form-control form-element">
						<form:label path="gender">Civilité :</form:label>
						<form:select path="gender"
							class="custom-select col-sm-3 form-control">
							<form:option value="M">M</form:option>
							<form:option value="MME">MME</form:option>
						</form:select>
					</div>


					<div class="form-control form-element">
						<form:label path="name">Votre nom :</form:label>
						<form:input path="name" class="form-control" />
					</div>

					<div class="form-control form-element">
						<form:label path="address">Votre adresse :</form:label>
						<form:input path="address" class="form-control" />
					</div>

					<div class="form-control form-element">
						<fieldset>
							<legend>Date de naissance</legend>

							<form:label path="day" class="mr-sm-2"
								for="inlineFormCustomSelect"> Jour : </form:label>
							<form:select path="day" items="${days }"
								class="custom-select col-sm-2" id="inlineFormCustomSelect" />

							<form:label path="month" class="mr-sm-2"
								for="inlineFormCustomSelect"> Mois : </form:label>
							<form:select path="month" items="${months }"
								class="custom-select col-sm-2" id="inlineFormCustomSelect" />

							<form:label path="year" class="mr-sm-2"
								for="inlineFormCustomSelect"> Année : </form:label>
							<form:select path="year" items="${years }"
								class="custom-select col-sm-3" id="inlineFormCustomSelect" />

						</fieldset>
					</div>

					<div class="form-control form-element">
						<form:label path="email">Email :</form:label>
						<form:input path="email" class="form-control" />
					</div>
					<c:if test="${msg !=null }">
						<div class="alert alert-block alert-danger">
							<p>${msg }</p>
						</div>
					</c:if>

					<div class="form-control form-element">
						<form:label path="password">Mot de passe :</form:label>
						<form:password path="password" class="form-control" />
					</div>

					<div class="form-submit">
						<input type="submit" value="S'inscrire" class="btn btn-secondary" />
					</div>

				</form:form>

			</div>
		</div>
	</div>
	<br />
	<br />
	<br />
	<br />





</body>
</html>
