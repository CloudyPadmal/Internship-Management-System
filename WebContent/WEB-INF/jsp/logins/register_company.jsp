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

	<form:form method="post" modelAttribute="companyForm" action="companies">

		<spring:bind path="company">
			<label>Company Name</label>
			<form:input path="company" type="text" id="company"
				placeholder="Company Name" />
			<form:errors path="company" />
		</spring:bind>
		<br />
		
		<spring:bind path="loginID">
			<label>Login ID</label>
			<form:input path="loginID" id="loginID"
				placeholder="Login ID" />
			<form:errors path="loginID" />
		</spring:bind>
		<br />

		<spring:bind path="address">
			<label>Address</label>
			<form:input path="address" type="text" id="address"
				placeholder="Address" />
			<form:errors path="address" />
		</spring:bind>
		<br />

		<spring:bind path="emailAddress">
			<label>Email</label>
			<form:input path="emailAddress" id="emailAddress" placeholder="Email" />
			<form:errors path="emailAddress" />
		</spring:bind>
		<br />

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

		<spring:bind path="telephone">
			<label>Telephone</label>
			<form:input path="telephone" id="telephone" placeholder="Telephone" />
			<form:errors path="telephone" />
		</spring:bind>
		<br />

		<spring:bind path="aboutUs">
			<label>About Us</label>
			<form:input path="aboutUs" id="aboutUs" placeholder="About Us" />
			<form:errors path="aboutUs" />
		</spring:bind>
		<br />
		<button type="submit">Register</button>
	</form:form>
</body>
</html>