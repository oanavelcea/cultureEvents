<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="header.jsp"/>

<article>

	<h1>Bonjour <c:out value="${sessionScope.firstName }"/></h1>
		
</article>

<jsp:include page="footer.jsp"/>
