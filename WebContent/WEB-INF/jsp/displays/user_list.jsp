<%@ include file="/WEB-INF/jsp/head.jsp"%>

<html>
<head>
<spring:url value="/resources/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>

<body class="homebody">
	<table>
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="info" items="${list}">
			<tr>
				<td>${info.username}</td>
				<td>${info.password}</td>
				<td><a href="editpw/${info.username}">Edit</a></td>
				<td><a href="deletepw/${info.username}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>