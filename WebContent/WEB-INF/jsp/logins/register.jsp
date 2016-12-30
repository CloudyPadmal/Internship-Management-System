<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<body class="homebody">

	<h1>Register</h1>
	<br />

	<form:form method="post" modelAttribute="userForm"
		action="${actionURL}">

		<spring:bind path="name">
			<label>First Name</label>
			<form:input path="name" type="text" id="name"
				placeholder="First Name" />
			<form:errors path="name" />
		</spring:bind>
		<br />

		<spring:bind path="surname">
			<label>Surname</label>
			<form:input path="surname" type="text" id="surname"
				placeholder="Surname" />
			<form:errors path="surname" />
		</spring:bind>
		<br />

		<spring:bind path="emailAddress">
			<label>Email</label>
			<form:input path="emailAddress" id="emailAddress" placeholder="Email" />
			<form:errors path="emailAddress" />
		</spring:bind>
		<br />

		<c:if test="${register}">
			<spring:bind path="password">
				<label>Password</label>
				<form:password path="password" id="password" placeholder="Password" />
				<form:errors path="password" />
			</spring:bind>
			<br />

			<spring:bind path="confirmPassword">
				<label>Confirm Password</label>
				<form:password path="confirmPassword" id="password"
					placeholder="Confirm" />
				<form:errors path="confirmPassword" />
			</spring:bind>
			<br />
		</c:if>

		<spring:bind path="indexNumber">
			<label>Index</label>
			<form:input path="indexNumber" id="indexNumber"
				placeholder="Index Number" />
			<form:errors path="indexNumber" />
		</spring:bind>
		<br />

		<spring:bind path="telephone">
			<label>Telephone</label>
			<form:input path="telephone" id="telephone" placeholder="Telephone" />
			<form:errors path="telephone" />
		</spring:bind>
		<br />

		<spring:bind path="gradedPoint">
			<label>GPA</label>
			<form:input path="gradedPoint" id="gradedPoint" placeholder="GPA" />
			<form:errors path="gradedPoint" />
		</spring:bind>
		<br />

		<spring:bind path="aboutMe">
			<label>aboutMe</label>
			<form:input path="aboutMe" id="aboutMe" placeholder="About Me" />
			<form:errors path="aboutMe" />
		</spring:bind>
		<br />

		<spring:bind path="preferences">
			<label>Preferences</label>
			<form:checkboxes path="preferences" items="${preferences}" />
			<br />
			<form:errors path="preferences" />
		</spring:bind>
		<br />

		<spring:bind path="gender">
			<label>Gender</label>
			<label><form:radiobutton path="gender" value="M" />Male</label>
			<label><form:radiobutton path="gender" value="F" />Female</label>
			<br />
			<form:errors path="gender" />
		</spring:bind>
		<br />
		<c:if test="${register}">
			<button type="submit" name="register">Register</button>
		</c:if>
		<c:if test="${not register}">
			<button type="submit" name="update">Update</button>
		</c:if>
	</form:form>
</body>
</html>