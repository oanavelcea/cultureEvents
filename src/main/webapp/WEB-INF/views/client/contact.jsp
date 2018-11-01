<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css"
	integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt"
	crossorigin="anonymous">
<div class="container">
	<h2 class="text-center">Formulaire de contact</h2>
	<div class="row justify-content-center">
		<div class="col-12 col-md-8 col-lg-6 pb-5">


			<!--Form with header-->

			<form:form method="post" action="client/envoyer-message"
				modelAttribute="contact-form">
				<div class="card border-primary rounded-0">
					<div class="card-header p-0">
						<div class="bg-info text-white text-center py-2">
							<h3>
								<i class="fa fa-envelope"></i> Contactez-nous
							</h3>
							<p class="m-0">Je vous aiderai volontiers</p>
						</div>
					</div>
					<div class="card-body p-3">

						<!--Body-->

						<div class="form-group">
							<div class="input-group mb-2">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-envelope text-info"></i>
									</div>
								</div>
								<form:input  class="form-control" id="nombre"
									path="email" placeholder="Entrez votre adresse mail" />
							</div>
						</div>

						<div class="form-group">
							<div class="input-group mb-2">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-question text-info"></i>
									</div>
								</div>
								<form:input class="form-control" id="nombre"
									path="subject" placeholder="Sujet" />
							</div>
						</div>


						<div class="form-group">
							<div class="input-group mb-2">
								<div class="input-group-prepend">
									<div class="input-group-text">
										<i class="fa fa-comment text-info"></i>
									</div>
								</div>
								<form:textarea path="message" class="form-control"
									placeholder="Entrez votre message"/>
							</div>
						</div>

						<div class="text-center">
							<input type="submit" value="Enviar"
								class="btn btn-info btn-block rounded-0 py-2">
						</div>
					</div>

				</div>
			</form:form>
			<!--Form with header-->
		</div>
	</div>
</div>