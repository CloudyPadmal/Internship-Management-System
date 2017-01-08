<%@ include file="/WEB-INF/jsp/head.jsp"%>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"><b>${user.indexNumber}</b>&nbsp;-&nbsp;${user.name}&nbsp;${user.surname}&nbsp;${user.awarded ? '-- Awarded!' : '-- Pending!'}</a>
			</div>
		</div>
	</nav>

	<div class="user-container">
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

	<h1 class="vacancy-header">Available Vacancies</h1>
	<div class="vacancy-container">
		<c:forEach var="vacancy" items="${vacancies}">
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
				</div>
			</div>
		</c:forEach>
	</div>
</body>