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


	<div class="container">

		<c:if test="${msg !=null }">
			<div class="alert alert-block alert-danger messageMail">
				<p>${msg }</p>
			</div>
		</c:if>

		<div class="row main ">
			<h1 class="col-lg-9 col-lg-offset-1 titre-page">Créer un compte</h1>

			<div class="row col-lg-9 form-coordonnees">
				<form:form method="post" action="validate-signup"
					modelAttribute="signup-form" class="col-lg-10">

					<h3>Vos coordonnées</h3>

					<div class="form-control form-element">
						<form:label path="gender">Civilité </form:label>
						<div>
							<form:select path="gender" class="custom-select col-lg-2">
								<form:option value="M">M</form:option>
								<form:option value="MME">MME</form:option>
							</form:select>
						</div>
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

							<form:label path="day" class="col-lg-1"
								for="inlineFormCustomSelect"> Jour</form:label>
							<form:select path="day" class="custom-select col-sm-2"
								id="inlineFormCustomSelect">
								<c:forEach var="i" begin="1" end="31">
									<form:option value="${i }"></form:option>
								</c:forEach>
							</form:select>


							<form:label path="month" class="col-lg-1"
								for="inlineFormCustomSelect"> Mois     </form:label>
							<form:select path="month" class="custom-select col-sm-2"
								id="inlineFormCustomSelect">
								<c:forEach var="i" begin="1" end="12">
									<form:option value="${i }"></form:option>
								</c:forEach>
							</form:select>

							<form:label path="year" class="col-lg-1"
								for="inlineFormCustomSelect"> Année     </form:label>
							<form:select path="year"
								class="custom-select col-lg-2" id="inlineFormCustomSelect">
								<c:forEach var="i" begin="1900" end="2018">
									<form:option value="${i }"></form:option>
								</c:forEach>
							</form:select>

						</fieldset>
					</div>

					<div class="form-control form-element">
						<form:label path="email">Email :</form:label>
						<form:input path="email" class="form-control" />
					</div>
					<c:if test="${msgMail !=null }">
						<div class="alert alert-block alert-danger">
							<p>${msgMail }</p>
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

	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</body>
</html>
