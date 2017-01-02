<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<head>
<spring:url value="/resources/css/bootstrap.min.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">${company.company}</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/MSDProject/vacancy/add/${company.company}">Add
						Vacancy</a></li>
				<li><a
					href="/MSDProject/reg/company/company/${company.loginID}/update">Edit</a></li>
				<li><a href="/MSDProject/">Logout</a></li>
			</ul>
		</div>
	</div>
</nav>
<br/>
<br/>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<c:if test="${not empty msg}">
				<strong>${msg}</strong>
			</c:if>
			<div class="section-one">
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
								href="vacancy/${vacancy.id}/update" class="button" type="submit">Edit
									${vacancy.title}</a> <i><b>[${vacancy.applicant}]</b></i></td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</html>