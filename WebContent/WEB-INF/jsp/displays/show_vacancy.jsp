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

	<h1 class="display-header">${vacancy.id}</h1>
	<div class="section-one">
		<table class="display-user">
			<tr>				
				<td><div class="column-head">Title</div></td>
				<td><div class="column-body">${vacancy.title}</div></td>
				
			</tr>
			<tr>
				<td><div class="column-head">Company</div></td>
				<td><div class="column-body">${vacancy.company}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Salary</div></td>
				<td><div class="column-body">${vacancy.salary}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Description</div></td>
				<td><div class="column-body">${vacancy.description_1}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Other...</div></td>
				<td><div class="column-body">${vacancy.description_2}</div></td>
			</tr>
			<tr>
				<td colspan="2"><div class="column-head">Currently ${vacancy.applicantCount} applied for this!</div></td>
			</tr>
		</table>
		
		<table class="display-user">
			<tr>
				<td><div class="column-head">Preferences</div></td>
				<td><c:forEach var="pref" items="${vacancy.preferences}">
						<div class="column-item"><li>${pref}</li></div>
					</c:forEach></td>
			</tr>
		</table>
	</div>
</body>
</html>