<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">All Requests</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
				<li><form:form action="/MSDProject/logout" method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Logout</button>
						</form:form></li>
				</ul>
			</div>
		</div>
	</nav>

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<div class="request-list">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Vacancy</th>
					<th id="applicant">Applicant</th>
					<th id="gpa">GPA</th>
					<th id="applicant">Current Applicant</th>
					<th id="gpa">GPA</th>
					<th id="buttons">Action</th>
				</tr>
			</thead>

			<c:forEach var="request" items="${requests}">
				<c:if test="${not request.attended}">
					<tr>
						<td id="request-row">${request.id}</td>
						<td><b>${request.vacancyID}</b>&nbsp;-&nbsp;${request.vacancyName}</td>
						<td>${request.indexNumber}</td>
						<td>${request.gradedPoint}</td>
						<td>${request.currentNumber}</td>
						<td>${request.currentGradedPoint}</td>
						<td id="buttons">
							<form type="submit"
								action="request/accept/${request.indexNumber}/${request.vacancyID}/${request.id}"
								method="POST">
								<button class="btn btn-info">Accept</button>
							</form>
							<form type="submit"
								action="request/decline/${request.indexNumber}/${request.id}"
								method="POST">
								<button class="btn btn-danger">Decline</button>
							</form>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>