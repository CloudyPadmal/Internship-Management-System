<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<body>
	<c:if test="${not empty msg}">
		<strong>${msg}</strong>
	</c:if>

	<h1>All Users</h1>

	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Email</th>
				<th>Prefs</th>
				<th>Action</th>
			</tr>
		</thead>

		<c:forEach var="user" items="${users}">
			<tr>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.emailAddress}</td>
				<td><c:forEach var="pref" items="${user.preferences}"
						varStatus="loop">
							${pref} <c:if test="${not loop.last}">,</c:if>
					</c:forEach></td>
				<td><a href="users/${user.indexNumber}" class="button">Show</a>
					<a href="users/${user.indexNumber}/delete" class="button">Delete</a>
					<a href="users/${user.indexNumber}/update" class="button">Update</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>