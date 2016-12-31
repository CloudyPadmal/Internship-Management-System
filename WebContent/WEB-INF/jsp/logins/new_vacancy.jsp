<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<body class="homebody">

	<h1>New Vacancy</h1>
	<br />

	<form:form method="post" modelAttribute="vacancyForm"
		action="vacancies">
		
		<spring:bind path="title">
			<label>Title</label>
			<form:input path="title" type="text" id="title" placeholder="Title" />
			<form:errors path="title" />
		</spring:bind>
		<br />

		<spring:bind path="company">
			<label>Company</label>
			<form:input path="company" type="text" id="company"
				placeholder="Company" />
			<form:errors path="company" />
		</spring:bind>
		<br />

		<spring:bind path="salary">
			<label>Salary</label>
			<form:input path="salary" id="salary" placeholder="Salary" />
			<form:errors path="salary" />
		</spring:bind>
		<br />

		<spring:bind path="description_1">
			<label>Description</label>
			<form:input path="description_1" id="description_1"
				placeholder="Description" />
			<form:errors path="description_1" />
		</spring:bind>
		<br />

		<spring:bind path="description_2">
			<label>Sub Description</label>
			<form:input path="description_2" id="description_2"
				placeholder="Sub Description" />
			<form:errors path="description_2" />
		</spring:bind>
		<br />

		<spring:bind path="preferences">
			<label>Preferences</label>
			<form:checkboxes path="preferences" items="${preferences}" />
			<br />
			<form:errors path="preferences" />
		</spring:bind>
		<br />

		<button type="submit">Create</button>
	</form:form>
</body>
</html>