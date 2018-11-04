<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${messageSuccess !=null }">
	<div class="alert alert-success messageMail" role="alert">
		<c:out value="${messageSuccess }"></c:out>
	</div>

</c:if>

<!-- <a href="test-data">Insérer des données de test</a> -->
<!-- <br /> -->

<h1 class="my-4 titre-page">Evénements de la métropole de Lille</h1>
<a href="client/ajouter-event-agenda?eventId=${evt.uid }">Ajouter à
	l'agenda</a>
<!-- <a href="client/ajouter-event-agenda?eventId=20081963">Ajouter à l'agenda</a> -->



<div class="row">
	<c:forEach var="evt" items="${events}" varStatus="st">

		<div class="col-sm-6 col-md-4 col-lg-3 portfolio-item">
			<div class="card h-100">
				<a href="#"><img class="card-img-top" src="${evt.image}" alt=""></a>
				<div class="card-body">
					<h4 class="card-title">
						<a href="${evt.link}" target="blank" title="Lien de l'événement">${evt.title}</a>
					</h4>
					<p class="card-text">
						<span>${evt.description}</span> <br /> <strong> <fmt:formatDate
								value="${evt.dateStart}" pattern="dd/MM/yyyy" /> - <fmt:formatDate
								value="${evt.dateEnd}" pattern="dd/MM/yyyy" />
						</strong> <br /> <span>${evt.spaceTimeInfo}</span> <br /> <span>${pricingInfo}</span>
					</p>
					<p>
						<span>${evt.placename}</span> - <span>${evt.city}</span> <br /> <span>${evt.address}</span>
					</p>
					<p>
						<span> <c:forEach var="tg" items="${evt.tags}">
								<c:out value="${tg}" />
								<br />
							</c:forEach>
						</span> <br /> <span> <c:forEach var="tt"
								items="${evt.timeTable}">
								<c:out value="${tt}" />
								<br />
							</c:forEach>
						</span>
					</p>
					<a href="client/ajouter-event-agenda?eventId=${evt.uid }">Ajouter
						à l'agenda</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<!-- 		<ul class="pagination justify-content-center"> -->
<!--         <li class="page-item"> -->
<!--           <a class="page-link" href="#" aria-label="Previous"> -->
<!--             <span aria-hidden="true">«</span> -->
<!--             <span class="sr-only">Previous</span> -->
<!--           </a> -->
<!--         </li> -->
<!--         <li class="page-item"> -->
<!--           <a class="page-link" href="#">1</a> -->
<!--         </li> -->

<!--         <li class="page-item"> -->
<!--           <a class="page-link" href="#" aria-label="Next"> -->
<!--             <span aria-hidden="true">»</span> -->
<!--             <span class="sr-only">Next</span> -->
<!--           </a> -->
<!--         </li> -->
<!--       </ul>  -->