<%@page language="java" isELIgnored="false" isErrorPage="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="header.jsp">
	<jsp:param value="Authentification" name="title" />
</jsp:include>

<style>
section {

}

body {
	width: 1200px;
}
</style>

<body>
	<h2 style="text-align: center">EVENEMENTS A LILLE</h2>
	<section>
		<div>

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
						<td><fmt:formatDate value="${evt.dateStart}"
								pattern="dd/MM/yyyy" /></td>
						<td><fmt:formatDate value="${evt.dateEnd}"
								pattern="dd/MM/yyyy" /></td>
						<td>${evt.placename}</td>
						<td>${evt.uid}</td>
						<td>${evt.image}</td>
						<td>${evt.spaceTimeInfo}</td>
						<td>${evt.department}</td>
						<td>${evt.link}</td>
						<td>${evt.address}</td>
						<td>${evt.region}</td>
						<td>${evt.imageThumb}</td>
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
		</div>
		<jsp:include page="footer.jsp" />