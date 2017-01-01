<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/bootstrap.min.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<body class="homebody">
	<c:choose>
		<c:when test="${new_company}">
			<h1>Register</h1>
		</c:when>
		<c:otherwise>
			<h1>Update</h1>
		</c:otherwise>
	</c:choose>
	<br />

<c:choose>
		<c:when test="${new_company}">
			<spring:url value="companies" var="actionURL" />
		</c:when>
		<c:otherwise>
			<spring:url value="/reg/company/companies" var="actionURL" />
		</c:otherwise>
	</c:choose>

	<form:form method="post" modelAttribute="companyForm"
		action="${actionURL}">

		<spring:bind path="company">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Company Name</label>
				<div class="col-sm-10">
					<form:input path="company" type="text" class="form-control"
						id="company" placeholder="Company Name" />
					<form:errors path="company" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="loginID">
			<label>Login ID</label>
			<form:input path="loginID" id="loginID" placeholder="Login ID" />
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

		<c:if test="${new_company}">
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
		<c:choose>
		<c:when test="${new_company}">
			<button type="submit" name="create">Register</button>
		</c:when>
		<c:otherwise>
			<button type="submit" name="update">Update</button>
		</c:otherwise>
	</c:choose>
		
	</form:form>
</body>
</html>