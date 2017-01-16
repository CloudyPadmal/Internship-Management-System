<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">All Users</a>
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
	<div class="user-list">
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
					<th>Index</th>
					<th>Name & GPA</th>
					<th>Email</th>
					<th>Telephone</th>
					<th>About</th>
					<th>Preferences</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.indexNumber}</td>
					<td>${user.name}&nbsp;${user.surname}<br />${user.gradedPoint}</td>
					<td>${user.emailAddress}</td>
					<td>${user.telephone}</td>
					<td id="description">${user.aboutMe}</td>
					<td><c:forEach var="preference" items="${user.preferences}">
							<li>${preference}</li>
						</c:forEach></td>
					<td>
						<form type="submit"
							action="user/view/${user.indexNumber}"
							method="POST" class="button">
							<button class="btn btn-info">Query</button>
						</form>
						<form type="submit"
							action="user/delete/${user.indexNumber}"
							method="POST" class="button">
							<button class="btn btn-danger">Delete</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
