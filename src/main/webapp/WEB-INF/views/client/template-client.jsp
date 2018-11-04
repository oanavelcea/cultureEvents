<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1 shrink-to-fit=no ">
<meta charset="utf-8" />

<title>Lille Culture Events</title>

<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />

<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css" />

<link href='resources/css/fullcalendar.min.css' rel='stylesheet' />
<link href='resources/css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />



<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href='resources/fronts/glyphicons-halflings-regular.eot'
	rel='stylesheet' />

<link href='resources/css/style.css' rel='stylesheet' />

<style>
body {
	margin-top: -50px;
	padding-top: 54px;
}



@media ( min-width : 992px) {
	body {
		padding-top: 56px;
	}
}

.navbar {
	height: 70px;
	
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
			<a class="navbar-brand titre-menu" href="client/accueil">Lille
				Culture Events</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="client/accueil"><span class="glyphicon glyphicon-home"></span>&nbsp;
							Accueil <span class="sr-only">(current)</span> </a></li>
				</ul>
			</div>
		</div>


		<!-- Menu déroulaant pour les catégories -->
		<div class="dropdown">
			<button class="btn btn-secondary dropdown-toggle categories" type="button"
				data-toggle="dropdown">
				Catégories <span class="caret"></span>
			</button>
			<ul class=" dropdown-menu" id="navbarSupportedContent">

				<li class="dropdown"><a class="nav-link"
					href="client/evenements?tag=exposition">Expositions</a></li>

				<li class="dropdown"><a class="nav-link"
					href="client/evenements?tag=concert">Concerts</a></li>

				<li class="dropdown" class="nav-item"><a class="nav-link"
					href="client/evenements?tag=SPECTACLE">Spectacles</a></li>

				<li class="dropdown"><a class="nav-link"
					href="client/evenements?tag=sport">Sport</a></li>

				<li class="dropdown"><a class="nav-link"
					href="client/evenements?tag=loisirs">Loisirs</a></li>

				<li class="dropdown"><a class="nav-link"
					href="client/accueil">Tous</a></li>
			</ul>
		</div>
		<!-- Fin menu déroulant -->

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item coordonnees"><a class="nav-link"
					href="client/coordonnees-utilisateur?id=${sessionScope.user_id}">
						<span class="glyphicon glyphicon-edit"></span>&nbsp Mes
						coordonnées
				</a></li>

				<li class="nav-item"><a class="nav-link"
					href="client/disconnect"><span
						class="glyphicon glyphicon-log-out"></span>&nbsp Déconnexion</a></li>

				<li class="nav-item"><a class="nav-link"
					href="client/contact?contact=false"><span
						class="glyphicon glyphicon-envelope"></span>&nbsp Contact</a></li>

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

	<!-- Bootstrap core JavaScript -->
	<script src='resources/js/moment.min.js'></script>
	<script src='resources/js/fullcalendar.min.js'></script>
	<script src='resources/js/locale-all.js'></script>
	<!-- 		<script src="resources/js/bootstrap.min.js"></script> -->

</body>
</html>