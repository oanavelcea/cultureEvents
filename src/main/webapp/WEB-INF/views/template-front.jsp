<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />

<link href='resources/css/fullcalendar.min.css' rel='stylesheet' />

<meta name="viewport"
	content="width=device-width, initial-scale=1 shrink-to-fit=no ">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Lille Culture Events</title>

<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />

<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css" />

<link href='resources/css/style.css' rel='stylesheet' />
<link href='resources/fronts/glyphicons-halflings-regular.eot'
	rel='stylesheet' />

<style>
body {
	padding-top: 54px;
}

footer {
	height: 70px;
}

.copyright {
	margin-top: 20px;
}

@media ( min-width : 992px) {
	body {
		padding-top: 56px;
	}
}

.navbar {
	height: 70px;
}

.titre-menu {
	font-family: "Serif (serif red), Times New Roman", Times;
	font-size: 30px;
}

.portfolio-item {
	margin-bottom: 30px;
}

.pagination {
	margin-bottom: 30px;
}
</style>

</head>

<body>

	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand titre-menu" href="#">Lille Culture Events</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#"><span
							class="glyphicon glyphicon-home"></span>&nbsp; Accueil <span
							class="sr-only">(current)</span> </a></li>
				</ul>
			</div>
		</div>


		<!-- Menu déroulaant pour les catégories -->
		<div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle categories1" type="button"
				data-toggle="dropdown">
				Catégories <span class="caret"></span>
			</button>
			<ul class=" dropdown-menu" id="navbarSupportedContent">

				<li><a class="nav-link dropdown" href="evenements?tag=exposition">Expositions</a></li>

				<li><a class="nav-link dropdown" href="evenements?tag=concert">Concerts</a></li>

				<li class="nav-item dropdown"><a class="nav-link"
					href="evenements?tag=SPECTACLE">Spectacles</a></li>

				<li><a class="nav-link dropdown" href="evenements?tag=sport">Sport</a></li>

				<li><a class="nav-link dropdown" href="evenements?tag=loisirs">Loisirs</a></li>

				<li><a class="nav-link dropdown" href="evenements">Tous</a></li>
			</ul>
		</div>
		<!-- Fin menu déroulant -->



		<div class="collapse navbar-collapse coordonnees" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="authenticate?contact=false"><span
						class="glyphicon glyphicon-user"></span>&nbsp Login</a></li>
						
				<li class="nav-item"><a class="nav-link" href="signup">
				<span class="glyphicon glyphicon-pencil"></span>&nbsp S'inscrire</a></li>

				<li class="nav-item"><a class="nav-link"
					href="authenticate?contact=true">
					<span class="glyphicon glyphicon-envelope"></span>&nbsp Contact</a></li>
			</ul>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<decorator:body />
	</div>

	<!-- Footer -->
	<footer class="bg-dark fixed-bottom">
		<div>
			<p class="text-center text-white copyright">Copyright &copy; 2018
				: Cindy, José, Ioana</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js">
		
	</script>



</body>

</html>