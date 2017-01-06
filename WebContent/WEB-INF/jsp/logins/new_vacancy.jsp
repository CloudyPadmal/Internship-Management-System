<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<c:choose>
					<c:when test="${status}">
						<a class="navbar-brand">New Vacancy for ${company}</a>
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

	<form:form method="post" modelAttribute="vacancyForm"
		action="/MSDProject/vacancy/vacancies">

		<form:hidden path="id" />
		<form:hidden path="open" />
		<form:hidden path="applicant" />
		<form:hidden path="companyID" />

		<spring:bind path="title">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Title</label>
				<div class="col-sm-10">
					<form:input path="title" type="text" id="title"
						class="form-control" placeholder="Title" />
					<form:errors path="title" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="salary">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Salary</label>
				<div class="col-sm-10">
					<form:input path="salary" type="text" id="salary"
						placeholder="Salary" class="form-control" />
					<form:errors path="salary" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="description_1">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Description</label>
				<div class="col-sm-10">
					<form:textarea path="description_1" id="description_1"
						placeholder="Description" class="form-control" />
					<form:errors path="description_1" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />
		<br />

		<spring:bind path="description_2">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">More..</label>
				<div class="col-sm-10">
					<form:textarea path="description_2" id="description_2"
						placeholder="Sub Description" class="form-control" />
					<form:errors path="description_2" class="control-label" />
				</div>
			</div>
		</spring:bind>
		<br />

		<spring:bind path="preferences">
			<div
				class="form-group ${status.error ? 'has-error' : ''} check-box-block">
				<label class="col-sm-2 control-label">Preferences</label>
				<div class="col-sm-10">
					<form:checkboxes path="preferences" items="${preferences}"
						element="label class='checkbox' id='register-boxes'" />
					<br />
					<form:errors path="preferences" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<div class="register-buttons">
			<c:choose>
				<c:when test="${status}">
					<button type="submit" name="create" class="btn-lg btn-primary">Create</button>
				</c:when>
				<c:otherwise>
					<button type="submit" name="update" class="btn-lg btn-primary">Update</button>
				</c:otherwise>
			</c:choose>
		</div>
	</form:form>
</body>