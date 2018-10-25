<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp">
	<jsp:param value="Authentification" name="title"/>
</jsp:include>

<article>
	<h1>Authentification</h1>
		
	<c:if test="${ errors != null }">
		<p class="alert alert-danger"><c:out value="${ errors }" /></p>
	</c:if>

<form method="post" action="Authentification">
	
	<div class="form-group row">
		<label class="col-2 col-form-label">Login:</label>
		<div class="col-10">
			<input type="text" name="login" class="form-control" value="" />
		</div>
	</div>
	
	<div class="form-group row">
		<label class="col-2 col-form-label">Password:</label>
		<div class="col-10">
			<input type="password" name="password" class="form-control" />
		</div>
	</div>
	
	<div class="form-group row">
		<div class="col-10 offset-2">
			<input type="submit" name="envoyer" value="Se Connecter" class="btn btn-outline-primary" />
		</div>	
	</div>
	
</form>
</article>

<jsp:include page="footer.jsp"/>
