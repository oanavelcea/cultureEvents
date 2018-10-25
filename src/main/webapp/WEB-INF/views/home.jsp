<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>Home</title>
	<link rel="stylesheet" href="" />
	<script type="text/javascript" src="resources/jquery-ui/external/jquery/jquery.js"></script>
</head>
<body>
	<h1>Culture Events</h1>	

	<a href="test-data">Insérer des données de test</a>
	<br />
	<a href="authenticate">Login</a>
	<br />
	
	<table class="table table-striped table-grey" border="">
				<tr>

					<th>Latitude-Longitude</th>

					<th>Title</th>
					<th>Lang</th>
					<th>City</th>
					<th>Date start</th>
					<th>Date end</th>
					<th>Placename</th>
					<th>Uid</th>
					<th>Image</th>
					<th>Space time info</th>
					<th>Department</th>
					<th>Link</th>
					<th>Address</th>
					<th>Region</th>
					<th>Image thumb</th>
					<th>Description</th>
					<th>Tags</th>
					<th>Updated at</th>
					<th>Timetable</th>
				</tr>
				<c:forEach var="evt" items="${events}">
					<tr>
						<td>${evt.latitude}-${evt.longitude}</td>
						<td width="50%">${evt.title}</td>
						<td>${evt.lang}</td>
						<td>${evt.city}</td>
						<td><fmt:formatDate value="${evt.dateStart}" pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${evt.dateEnd}" pattern="dd/MM/yyyy" /></td>
						<td>${evt.placename}</td>
						<td>${evt.uid}</td>
						
						<td>
							<img src="${evt.image}" alt="Image" />
						</td>
						
						<td>${evt.spaceTimeInfo}</td>
						
						<td>${evt.department}</td>
						
						<td><a href="${evt.link}" title="Lien event" target="blank">Lien de l'événement</a></td>
						<td>${evt.address}</td>
						<td>${evt.region}</td>
						<td><img src="${evt.imageThumb}" alt="Image thumb" /></td>
						<td>${evt.description}</td>
						<td><c:forEach var="tg" items="${evt.tags}">
								<c:out value="${tg}" />
								<br />
							</c:forEach></td>
						<td>${evt.updatedAt}</td>
						<td><c:forEach var="tt" items="${evt.timeTable}">
								<c:out value="${tt}" />
								<br />
							</c:forEach></td>
					</tr>
				</c:forEach>
			</table>
	
	
</body>
</html>
