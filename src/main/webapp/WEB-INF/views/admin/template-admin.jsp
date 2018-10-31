<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta charset="utf-8" />

<title>Lille Culture Events / Admin</title>

<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />

<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	id="bootstrap-css" />

<link href='resources/css/style.css' rel='stylesheet' />

<style>
body {
	padding-top: 54px;
}

@media ( min-width : 992px) {
	body {
		padding-top: 56px;
	}
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
			<a class="navbar-brand" href="#">Lille Culture Events / Admin</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link"
						href="admin/dashboard">Accueil <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="admin/liste-utilisateurs?page=1&max=15">Gestion des
							utilisateurs</a></li>
					<li class="nav-item"><a class="nav-link" href="exposition">Expositions</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="concert">Concerts</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="admin/disconnect">Déconnexion</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<decorator:body />
	</div>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; 2018 :
				Cindy, José, Ioana</p>
		</div>
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>

</body>

</html>