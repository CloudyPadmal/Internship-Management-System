<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><b>${user.indexNumber}</b>&nbsp;-&nbsp;${user.name}&nbsp;${user.surname}</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><form:form
							action="/MSDProject/reg/user/update/${user.indexNumber}"
							method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Edit</button>
						</form:form></li>
					<li><form:form
							action="/MSDProject/user/logout"
							method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Logout</button>
						</form:form></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="user-container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible fade in"
				role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<c:if test="${user.awarded}">
			<div class="congratulations">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<span class="blink_me">CONGRATULATIONS!!!</span>
					</div>
					<div class="panel-body">Your application had been selected
						and you have got the internship at ${congrats.companyName} as a
						${congrats.title} for a monthly allowance of
						Rs.&nbsp;${congrats.salary}!</div>
					<div class="panel-body">
						<cite>Please note that you cannot cancel this opportunity
							and apply for any more vacancies. Wish you all the very best!
							Happy Intern!!!</cite>
					</div>
				</div>
			</div>
		</c:if>

		<div class="user-header">
			<div class="col-sm-4">
				<div class="panel panel-primary">
					<div class="panel-heading">Email</div>
					<div class="panel-body">${user.emailAddress}</div>
				</div>
			</div>
			<div class="col-sm-2">
				<div class="panel panel-primary">
					<div class="panel-heading">Gender</div>
					<div class="panel-body">${user.gender}</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-primary">
					<div class="panel-heading">Phone</div>
					<div class="panel-body">${user.telephone}</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-primary">
					<div class="panel-heading">GPA</div>
					<div class="panel-body">${user.gradedPoint}</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="panel panel-primary">
				<div class="panel-heading">About Me</div>
				<div class="panel-body">${user.aboutMe}</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="panel panel-primary">
				<div class="panel-heading">Preferences</div>
				<div class="panel-body">
					<c:forEach var="pref" items="${user.preferences}">
						<div class="column-item">
							<li>${pref}</li>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>

	</div>

	<c:if test="${not user.awarded}">
		<h1 class="vacancy-header">Available Vacancies</h1>
		<div class="vacancy-container">
			<c:forEach var="vacancy" items="${vacancies}">
				<c:if test="${not vacancy.awarded}">
					<div class="panel panel-display">
						<div class="panel-heading">
							<h3 class="panel-title">${vacancy.companyName}&nbsp;-&nbsp;<b>${vacancy.title}</b>&nbsp;<i>(Rs.${vacancy.salary})</i>
							</h3>
						</div>
						<div class="panel-body vacancy">
							<div class="col-md-6">${vacancy.description_1}&nbsp;${vacancy.description_2}</div>
							<div class="col-md-4 preferences">
								<c:forEach var="pref" items="${vacancy.preferences}">
									<li>${pref}</li>
								</c:forEach>
							</div>
							<div class="button col-md-2">
								<br />
								<c:choose>
									<c:when test="${not vacancy.open}">
										<c:if test="${not user.applied1}">
											<form:form
												action="/MSDProject/user/apply/${vacancy.id}/${user.indexNumber}/1"
												method="POST">
												<button type="submit" name="1st" value="edit"
													class="btn btn-primary btn-lg">
													Apply as <b>1st</b> Choice
												</button>
											</form:form>
										</c:if>
										<c:if test="${not user.applied2}">
											<form:form
												action="/MSDProject/user/apply/${vacancy.id}/${user.indexNumber}/2"
												method="POST">
												<button type="submit" name="2nd" value="edit"
													class="btn btn-success btn-lg">
													Apply as <b>2nd</b> Choice
												</button>
											</form:form>
										</c:if>
										<c:if test="${not user.applied3}">
											<form:form
												action="/MSDProject/user/apply/${vacancy.id}/${user.indexNumber}/3"
												method="POST">
												<button type="submit" name="3rd" value="edit"
													class="btn btn-info btn-lg">
													Apply as <b>3rd</b> Choice
												</button>
											</form:form>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${vacancy.applicant == user.indexNumber}">
												<c:if
													test="${(user.applied1) && user.vacancy1 == vacancy.id}">
													<form:form
														action="/MSDProject/user/cancel/${vacancy.id}/${user.indexNumber}/1"
														method="POST">
														<button type="submit" name="edit" value="edit"
															class="btn btn-danger btn-lg">
															Cancel <b>1st</b> Choice
														</button>
													</form:form>
												</c:if>
												<c:if
													test="${(user.applied2) && user.vacancy2 == vacancy.id}">
													<form:form
														action="/MSDProject/user/cancel/${vacancy.id}/${user.indexNumber}/2"
														method="POST">
														<button type="submit" name="edit" value="edit"
															class="btn btn-danger btn-lg">
															Cancel <b>2nd</b> Choice
														</button>
													</form:form>
												</c:if>
												<c:if
													test="${(user.applied3) && user.vacancy3 == vacancy.id}">
													<form:form
														action="/MSDProject/user/cancel/${vacancy.id}/${user.indexNumber}/3"
														method="POST">
														<button type="submit" name="edit" value="edit"
															class="btn btn-danger btn-lg">
															Cancel <b>3rd</b> Choice
														</button>
													</form:form>
												</c:if>
											</c:when>

											<c:otherwise>
												<c:choose>
													<c:when test="${user.appeal == vacancy.id}">
														<form:form
															action="/MSDProject/user/request/cancel/${user.indexNumber}"
															method="POST">
															<button type="submit" name="edit" value="edit"
																class="btn btn-info btn-lg">Requested! Cancel it!!
															</button>
														</form:form>
													</c:when>
													<c:otherwise>
														<button type="submit" name="edit" value="edit"
															class="btn btn-warning btn-lg">Someone applied for it!
														</button>
													</c:otherwise>
												</c:choose>
												<c:if
													test="${(not user.applied1) || (not user.applied2) || (not user.applied3)}">
													<c:if test="${not user.appealStatus}">
														<form:form
															action="/MSDProject/user/request/${vacancy.id}/${user.indexNumber}"
															method="POST">
															<button type="submit" name="edit" value="edit"
																class="btn btn-primary btn-lg">Make a request!</button>
														</form:form>
													</c:if>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
</body>
</html>