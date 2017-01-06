<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<c:choose>
					<c:when test="${new_company}">
						<a class="navbar-brand">Register</a>
					</c:when>
					<c:otherwise>
						<a class="navbar-brand">Update</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</nav>

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

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
				<label class="col-sm-2 control-label">Company</label>
				<div class="col-sm-10">
					<form:input path="company" type="text" id="company"
						class="form-control" placeholder="Company" />
					<form:errors path="company" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<c:if test="${not new_company}">
			<form:hidden path="loginID" />
		</c:if>

		<c:if test="${new_company}">
			<spring:bind path="loginID">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Login ID</label>
					<div class="col-sm-10">
						<form:input path="loginID" type="text" id="loginID"
							class="form-control" placeholder="Login ID" />
						<form:errors path="loginID" class="control-label" />
					</div>
				</div>
			</spring:bind>
			<br />
		</c:if>

		<spring:bind path="address">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Address</label>
				<div class="col-sm-10">
					<form:input path="address" type="text" id="address"
						class="form-control" placeholder="Address" />
					<form:errors path="address" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="emailAddress">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<form:input path="emailAddress" type="text" id="emailAddress"
						class="form-control" placeholder="Email Address" />
					<form:errors path="emailAddress" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<c:if test="${new_company}">
			<spring:bind path="password">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<form:input path="password" type="password" id="password"
							placeholder="Password" class="form-control" />
						<form:errors path="password" class="control-label" />
					</div>
				</div>
			</spring:bind>
			<br />

			<spring:bind path="confirmPassword">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<label class="col-sm-2 control-label">Confirm</label>
					<div class="col-sm-10">
						<form:input path="confirmPassword" type="password"
							id="confirmPassword" placeholder="Confirm Password"
							class="form-control" />
						<form:errors path="confirmPassword" class="control-label" />
					</div>
				</div>
			</spring:bind>
			<br />
		</c:if>

		<spring:bind path="telephone">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Telephone</label>
				<div class="col-sm-10">
					<form:input path="telephone" type="text" id="telephone"
						class="form-control" placeholder="Telephone" />
					<form:errors path="telephone" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="aboutUs">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">About Us</label>
				<div class="col-sm-10">
					<form:textarea path="aboutUs" id="aboutUs" placeholder="About Us"
						class="form-control" />
					<form:errors path="aboutUs" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />
		<br />
		<br />

		<div class="register-buttons">
			<c:choose>
				<c:when test="${new_company}">
					<button type="submit" name="create" class="btn-lg btn-primary">Register</button>
				</c:when>
				<c:otherwise>
					<button type="submit" name="update" class="btn-lg btn-primary">Update</button>
				</c:otherwise>
			</c:choose>
		</div>
	</form:form>
</body>