<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav>
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<a class="nav-link" href="./">Accueil</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="OpenDataController">Liste des événements</a>
		</li>
		
		<c:choose>
			<c:when test="${ sessionScope.auth == null }">
				<li class="nav-item">
					<a class="nav-link" href="Login">Connexion</a>
				</li>
			</c:when>
			<c:otherwise>
				<li class="nav-item">
					<a class="nav-link">Bonjour <c:out value="${ sessionScope.username }"></c:out></a>
				</li>
				<c:if test="${ sessionScope.role == 'ADMIN' }">
					<li class="nav-item">
						<a class="nav-link" href="admin/dashboard">Dashboard</a>
					</li>
				</c:if>
				<li class="nav-item">
					<a class="nav-link" href="deconnexion">Déconnexion</a>
				</li>
				
			</c:otherwise>
		</c:choose>
	</ul>
</nav>