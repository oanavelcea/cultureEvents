<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html>
<html>
<head>
 	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
   	<meta charset="utf-8" />
   
    <title>Lille Culture Events / Espace Client</title>
    
    <base href="<%=request.getScheme()+"://"+request.getServerName()+":" + request.getServerPort()+request.getContextPath()+"/" %>" />
		
    <link href="resources/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css" />
		 
		<link href='resources/css/fullcalendar.min.css' rel='stylesheet' />
		<link href='resources/css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
    
    <style>
		body {
		  padding-top: 54px;
		    margin: 40px 10px;
		    padding: 0;
		    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		    font-size: 14px;
		  }
		
		  #calendar {
		    max-width: 900px;
		    margin: 0 auto;
		  }
		
		@media (min-width: 992px) {
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
        <a class="navbar-brand" href="#">Lille Culture Events / Espace Client</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="client/account">Dashboard <span class="sr-only">(current)</span></a>
            </li>
            <li>
            	<a class="nav-link" href="client/coordonnees-utilisateur?id=${sessionScope.user_id}">Mes coordonnées</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="client/disconnect">Déconnexion</a>
            </li>
           
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
        <p class="m-0 text-center text-white">Copyright &copy; 2018 : Cindy, José, Ioana</p>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src='resources/js/moment.min.js'></script>
	<script src='resources/js/fullcalendar.min.js'></script>
	<script src='resources/js/locale-all.js'></script>	
	<script src="resources/js/bootstrap.min.js"></script>



  </body>
</html>