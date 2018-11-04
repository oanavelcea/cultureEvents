<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>



<div class="container">

	<!-- pour l'affichage des erreurs -->
	<c:if test="${errors != null }">
		<div class="alert alert-danger">
			<spring:hasBindErrors name="login-form">
				<c:forEach var="err" items="${errors.allErrors}">
					<c:out value="${err.field}" /> :
			<c:out value="${err.defaultMessage}" />
					<br />
				</c:forEach>
			</spring:hasBindErrors>
		</div>
	</c:if>


	<div class="row main ">
		<h1 class="col-lg-9 col-lg-offset-1 titre-page">Authentification</h1>

		<div class="row col-lg-9 form-coordonnees">
			<form:form method="post" action="check-login"
				modelAttribute="login-form">

				<p>Identifiez-vous</p>


				<div class="form-control form-element">
					<form:label path="username" class="form-label">Email :</form:label>
					<form:input path="username" class="form-control" />
				</div>

				<div class="form-control form-element">
					<form:label path="password">Mot de passe :</form:label>
					<form:password path="password" class="form-control" />
				</div>

				<form:hidden path="contact" />

				<div class="form-submit">
					<input type="submit" value="Se connecter" class="btn btn-secondary" />
				</div>
			</form:form>

			<div>${msg}</div>
		</div>
	</div>
</div>

</body>
</html>