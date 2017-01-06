<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">All Companies</a>
			</div>
		</div>
	</nav>
	<div class="company-list">
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
					<th>Company</th>
					<th>Email</th>
					<th>Address</th>
					<th>Telephone</th>
					<th>About Us</th>
					<th>Positions</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="company" items="${companies}">
				<tr>
					<td>${company.id}</td>
					<td>${company.company}</td>
					<td>${company.emailAddress}</td>
					<td>${company.address}</td>
					<td>${company.telephone}</td>
					<td id="description">${company.aboutUs}</td>
					<td id="positions">${company.positions}</td>
					<td>
						<form type="submit" action="/MSDProject/company/${company.id}"
							method="POST" class="button">
							<button class="btn btn-primary">Show</button>
						</form>
						<form type="submit"
							action="/MSDProject/company/${company.id}/delete" method="POST" class="button">
							<button class="btn btn-danger">Delete</button>
						</form>
						<form type="submit"
							action="/MSDProject/company/${company.id}/update" method="POST" class="button">
							<button class="btn btn-info">Update</button>
						</form>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>