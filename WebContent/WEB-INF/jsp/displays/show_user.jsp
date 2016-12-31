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
						<div class="column-item">
							<li>${pref}</li>
						</div>
					</c:forEach></td>
			</tr>
		</table>
	</div>
	<div>
		<c:forEach var="vacancy" items="${vacancies}">
			<table class="display-vacancies">
				<tr>
					<td><div class="column-head">Title</div></td>
					<td><div class="column-body">${vacancy.title}&nbsp;-&nbsp;(Rs.
							${vacancy.salary})&nbsp;${vacancy.company}</div></td>
				</tr>
				<tr>
					<td><div class="column-head">Description</div></td>
					<td><div class="column-body">${vacancy.description_1}</div></td>
				</tr>
				<tr>
					<td><div class="column-head">Preferences</div></td>
					<td><div class="column-body">${vacancy.preferences}</div></td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${not vacancy.open}">
							<td />
							<td><a href="apply/${vacancy.id}/${user.indexNumber}" class="button"
								type="submit">Apply for ${vacancy.title}!</a></td>
						</c:when>
						<c:otherwise>
							<td />
							<td><i>Someone applied for it!</i></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
</html>