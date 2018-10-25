<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/views/front/header.jsp">
	<jsp:param value="Detail" name="title"/>
</jsp:include>

<a href="livre"> &lt;&lt; Retour</a>
<article>
	<h1><c:out value="${ livre.nom }" /></h1>
		
	<div>
		<c:out value="${ livre.description }" escapeXml="false" />
	</div>
	
	<p> Paru le : <fmt:formatDate value="${ livre.parution }"/></p>
</article>

<jsp:include page="/WEB-INF/views/front/footer.jsp"/>
