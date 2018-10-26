<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="col-md-12">
	<h4>Liste des utilisateurs</h4>
	<div class="table-responsive">
		<table id="mytable" class="table table-bordred table-striped">
			<tr>
				<th>Nom</th>
				<th>Email</th>
				<th>Admin ?</th>
				<th>Modifier</th>
				<th>Supprimer</th>
			</tr>
			<c:forEach var="u" items="${users}">
				<tr>
					<td>${u.name}</td>
					<td>${u.email}</td>
					<td><c:choose>
							<c:when test="${u.admin}">Oui</c:when>
							<c:otherwise>Non</c:otherwise>
						</c:choose></td>
					<td><a href="admin/modifier-utilisateur?id=${u.id}">Modifier</a></td>
					<td><a href="admin/supprimer-utilisateur?id=${u.id}">Supprimer</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="clearfix"></div>

		<ul class="pagination pull-center">
			<c:if test="${page>1}">
				<li><a href="admin/liste-utilisateurs?page=${page-1}&max=15"><span
						class="glyphicon glyphicon-chevron-left"></span></a></li>
			</c:if>
			<li class="active"><a href="#">${page}</a></li>
			<c:if test="${suivExist}">
				<li><a href="admin/liste-utilisateurs?page=${page+1}&max=15"><span
						class="glyphicon glyphicon-chevron-right"></span></a></li>
			</c:if>
		</ul>
	</div>
</div>


