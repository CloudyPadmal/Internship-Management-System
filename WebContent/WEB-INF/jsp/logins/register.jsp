<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<spring:url value="/resources/css/bootstrap.min.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>
<body class="homebody">

	<h1>Register</h1>
	<br />

	<form:form method="post" modelAttribute="userForm"
		action="${actionURL}">

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">First Name</label>
				<div class="col-sm-10">
					<form:input path="name" type="text" id="name" class="form-control"
						placeholder="First Name" />
					<form:errors path="name" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="surname">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Surname</label>
				<div class="col-sm-10">
					<form:input path="surname" type="text" id="surname"
						placeholder="Surname" class="form-control" />
					<form:errors path="surname" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="emailAddress">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="emailAddress" id="emailAddress"
						placeholder="Email" class="form-control" />
					<form:errors path="emailAddress" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<c:if test="${register}">
			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:password path="password" id="password"
							placeholder="Password" class="form-control" />
						<form:errors path="password" class="control-label" />
					</div>
				</div>
			</spring:bind>
			<br />

			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Confirm Password</label>
					<div class="col-sm-10">
						<form:password path="confirmPassword" id="password"
							placeholder="Confirm" class="form-control" />
						<form:errors path="confirmPassword" class="control-label" />
					</div>
				</div>
			</spring:bind>
			<br />
		</c:if>

		<spring:bind path="indexNumber">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Index</label>
				<div class="col-sm-10">
					<form:input path="indexNumber" id="indexNumber"
						placeholder="Index Number" class="form-control" />
					<form:errors path="indexNumber" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="telephone">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Telephone</label>
				<div class="col-sm-10">
					<form:input path="telephone" id="telephone" placeholder="Telephone"
						class="form-control" />
					<form:errors path="telephone" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="gradedPoint">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">GPA</label>
				<div class="col-sm-10">
					<form:input path="gradedPoint" id="gradedPoint" placeholder="GPA"
						class="form-control" />
					<form:errors path="gradedPoint" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="aboutMe">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">About Me</label>
				<div class="col-sm-10">
					<form:textarea path="aboutMe" id="aboutMe" placeholder="About Me"
						class="form-control" />
					<form:errors path="aboutMe" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="preferences">
			<div class="form-group ${status.error ? 'has-error' : ''} check-box-block">
				<label class="col-sm-2 control-label">Preferences</label>
				<div class="col-sm-10">
					<form:checkboxes path="preferences" items="${preferences}"
						element="label class='checkbox-inline'" />
					<br />
					<form:errors path="preferences" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="gender">
			<div class="form-group ${status.error ? 'has-error' : ''} check-box-block">
				<label class="col-sm-2 control-label">Gender</label>
				<div class="col-sm-10">
					<label class="radio-inline"><form:radiobutton path="gender"
							value="M" />Male</label> <label class="radio-inline"><form:radiobutton
							path="gender" value="F" />Female</label> <br />
					<form:errors path="gender" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />
		<div class="col-sm-offset-2 col-sm-10">
		<c:if test="${register}">
			<button type="submit" name="register"
				class="btn-lg btn-primary pull-right">Register</button>
		</c:if>
		<c:if test="${not register}">
			<button type="submit" name="update"
				class="btn-lg btn-primary pull-right">Update</button>
		</c:if>
		</div>
	</form:form>
</body>
</html>