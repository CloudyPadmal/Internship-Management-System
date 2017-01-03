<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">${company.company}</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/MSDProject/vacancy/add/${company.company}">+
							Add Vacancy</a></li>
					<li><a
						href="/MSDProject/reg/company/company/${company.loginID}/update">Edit</a></li>
					<li><a href="/MSDProject/">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div
				class="col-ssm-9 col-ssm-offset-3 col-mmd-10 col-mmd-offset-2 main">


				<c:if test="${not empty msg}">
					<div class="alert alert-${css} alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>${msg}</strong>
					</div>
				</c:if>

				<div class="col-sm-3 col-md-2 sidebar">
					<table class="display-user">
						<tr>
							<td><div class="column-head">Login ID</div></td>
							<td><div class="column-body">${company.loginID}</div></td>

						</tr>
						<tr>
							<td><div class="column-head">Email</div></td>
							<td><div class="column-body">${company.emailAddress}</div></td>
						</tr>
						<tr>
							<td><div class="column-head">Address</div></td>
							<td><div class="column-body">${company.address}</div></td>
						</tr>
						<tr>
							<td><div class="column-head">Phone</div></td>
							<td><div class="column-body">${company.telephone}</div></td>
						</tr>
						<tr>
							<td><div class="column-head">About Us</div></td>
							<td><div class="column-body">${company.aboutUs}</div></td>
						</tr>
					</table>

					<table class="display-user">
						<tr>
							<td><div class="column-head">Positions Posted</div></td>
							<td><div class="column-body">${company.positions}</div></td>
						</tr>
					</table>
				</div>
				<div></div>
				<div class="section-three">
					<c:forEach var="vacancy" items="${vacancies}">
						<table class="display-vacancies">
							<tr>
								<td><div class="column-head">Title</div></td>
								<td><div class="column-body">
										${vacancy.id}.&nbsp;<b>${vacancy.title} (Rs.
											${vacancy.salary})</b>
									</div></td>
							</tr>
							<tr>
								<td><div class="column-head">Description</div></td>
								<td><div class="column-body">${vacancy.description_1}&nbsp;${vacancy.description_2}</div></td>
							</tr>
							<tr>
								<td><div class="column-head">Preferences</div></td>
								<td><div class="column-body">${vacancy.preferences}</div></td>
							</tr>
							<tr>
								<td />
								<td><a href="vacancy/${vacancy.id}/delete" class="button"
									type="submit">Delete ${vacancy.title}</a> <a
									href="vacancy/${vacancy.id}/update" class="button"
									type="submit">Edit ${vacancy.title}</a> <i><b>[${vacancy.applicant}]</b></i></td>
							</tr>
						</table>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>