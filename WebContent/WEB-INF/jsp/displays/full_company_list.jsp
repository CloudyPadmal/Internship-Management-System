<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<body>
	<c:if test="${not empty msg}">
		<strong>${msg}</strong>
	</c:if>

	<h1>All Companies</h1>

	<table>
		<thead>
			<tr>
				<th>Company</th>
				<th>Email</th>
				<th>Address</th>
				<th>Telephone</th>
				<th>Vacancies</th>
				<th>About</th>
				<th>Action</th>
			</tr>
		</thead>

		<c:forEach var="company" items="${companies}">
			<tr>
				<td>${company.company}</td>
				<td>${company.emailAddress}</td>
				<td>${company.address}</td>
				<td>${company.telephone}</td>
				<td>${company.positions}</td>
				<td>${company.aboutUs}</td>
				<td><a href="company/${company.id}" class="button">Show</a>
					<a href="company/${company.id}/delete" class="button">Delete</a>
					<a href="company/${company.id}/update" class="button">Update</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>