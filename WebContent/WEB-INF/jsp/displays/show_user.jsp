<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<head>
<spring:url value="/resources/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>

<body>
	<c:if test="${not empty msg}">
		<strong>${msg}</strong>
	</c:if>

	<h1 class="display-header">${user.indexNumber}</h1>
	<div class="section-one">
		<table class="display-user">
			<tr>				
				<td><div class="column-head">Name</div></td>
				<td><div class="column-body">${user.name}&nbsp;${user.surname}</div></td>
				
			</tr>
			<tr>
				<td><div class="column-head">Email</div></td>
				<td><div class="column-body">${user.emailAddress}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Gender</div></td>
				<td><div class="column-body">${user.gender}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Phone</div></td>
				<td><div class="column-body">${user.telephone}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">About Me</div></td>
				<td><div class="column-body">${user.aboutMe}</div></td>
			</tr>
		</table>
		
		<table class="display-user">
			<tr>
				<td><div class="column-head">GPA</div></td>
				<td><div class="column-body">${user.gradedPoint}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Preferences</div></td>
				<td><c:forEach var="pref" items="${user.preferences}">
						<div class="column-item"><li>${pref}</li></div>
					</c:forEach></td>
			</tr>
		</table>
	</div>
</body>
</html>