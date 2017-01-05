<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">All Requests</a>
			</div>
		</div>
	</nav>
	<div class="request-list">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Vacancy</th>
					<th>Applicant</th>
					<th>GPA</th>
					<th>Preferences</th>					
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="request" items="${requests}">
				<tr>
					<td>${request.id}</td>
					<td>${request.vacancy}</td>
					<td>${request.applicant}</td>
					<td>${request.GPA}</td>
					<td><c:forEach var="preference" items="${request.preferences}">
							<li>${preference}</li>
						</c:forEach></td>
					<td id="buttons">
						<form type="submit" action="accept"
							method="POST">
							<button class="btn btn-info">Accept</button>
						</form>
						<form type="submit" action="decline"
							method="POST">
							<button class="btn btn-danger">Decline</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>