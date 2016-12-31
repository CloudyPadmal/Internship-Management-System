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
					<td><a href="" class="button" type="submit">Disqualify
							${vacancy.applicant}!</a><a href="" class="button" type="submit">Delete
							${vacancy.title}</a></td>
				</tr>
			</table>
		</c:forEach>
	</div>
</body>
</html>