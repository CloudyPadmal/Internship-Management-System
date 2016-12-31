<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<head>
<spring:url value="/resources/css/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>

<body>
	<c:if test="${not empty msg}">
		<strong>${msg}</strong>
	</c:if>

	<h1 class="display-header">${company.company}</h1>
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
	<div>
		<a href="/MSDProject/vacancy/add/${company.company}">Add Vacancy</a>
	</div>
	<div>
		<c:forEach var="vacancy" items="${vacancies}">
			<table class="display-vacancies">
				<tr>
					<td><div class="column-head">ID</div></td>
					<td><div class="column-body">${vacancy.id}</div></td>
				</tr>
				<tr>
					<td><div class="column-head">Title</div></td>
					<td><div class="column-body">${vacancy.title}</div></td>
				</tr>
				<tr>
					<td><div class="column-head">Description</div></td>
					<td><div class="column-body">${vacancy.description_1}</div></td>
				</tr>
				<tr>
					<td><div class="column-head">Preferences</div></td>
					<td><div class="column-body">${vacancy.preferences}</div></td>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
</html>