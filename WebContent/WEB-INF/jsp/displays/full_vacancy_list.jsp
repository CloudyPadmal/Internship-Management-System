<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">All Vacancies</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><form:form
							action="view_requests"
							method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Requests</button>
						</form:form></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="vacancy-list">
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
					<th>Title</th>
					<th>Salary</th>
					<th>Description</th>
					<th>Applicant</th>
					<th>Preferences</th>					
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="vacancy" items="${vacancies}">
				<tr>
					<td>${vacancy.id}</td>
					<td>${vacancy.title}</td>
					<td>${vacancy.salary}</td>
					<td id="description">${vacancy.description_1}&nbsp;${vacancy.description_2} asdasdgaj sdhjkah djkah djkahgsdkj askjd khagd hagd agdg asdg asg dag sdjagsdh asdg hj</td>
					<td>${vacancy.applicant}</td>
					<td><c:forEach var="preference" items="${vacancy.preferences}">
							<li>${preference}</li>
						</c:forEach></td>
					<td id="buttons"><spring:url value="/usesr/${user.indexNumber}ss"
							var="userUrl" /> <spring:url
							value="/reg/user/users/${user.indexNumber}/update"
							var="updateUrl" />
						<button class="btn btn-primary"
							onclick="location.href='${updateUrl}'">Disqualify</button> <br />
						<form type="submit" action="vacancy/${vacancy.id}/deletee"
							method="POST">
							<button class="btn btn-danger">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>