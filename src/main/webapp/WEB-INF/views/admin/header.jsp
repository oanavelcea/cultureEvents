<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<title> Administration Biblio </title>
<meta charset="utf-8" />
<base href="<%=request.getScheme()+"://"+request.getServerName()+":" + request.getServerPort()+request.getContextPath()+"/" %>" />
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
 
</head>
<body>
<nav>
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<a class="nav-link" href="admin/dashboard">Dashboard</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="admin/liste-utilisateurs?page=1&max=15">Gestion des utilisateurs</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="deconnexion">Déconnexion</a>
		</li>
	</ul>
</nav>
	
