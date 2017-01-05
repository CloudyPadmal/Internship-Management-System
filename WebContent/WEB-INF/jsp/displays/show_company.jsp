<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">${company.company}</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><form:form
							action="/MSDProject/vacancy/add/${company.loginID}" method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Add
								Vacancy</button>
						</form:form></li>
					<li><form:form
							action="/MSDProject/reg/company/company/${company.loginID}/update"
							method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Edit</button>
						</form:form></li>
					<li><form:form action="/MSDProject/" method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Logout</button>
						</form:form></li>
				</ul>
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

	<div class="company-container">
		<div class="company-header">
			<div class="col-sm-2">
				<div class="panel panel-info">
					<div class="panel-heading">Login ID</div>
					<div class="panel-body">${company.loginID}</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-info">
					<div class="panel-heading">Email</div>
					<div class="panel-body">${company.emailAddress}</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-info">
					<div class="panel-heading">Telephone</div>
					<div class="panel-body">${company.telephone}</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="panel panel-info">
					<div class="panel-heading">Positions Posted</div>
					<div class="panel-body">${company.positions}</div>
				</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="panel panel-info">
				<div class="panel-heading">Address</div>
				<div class="panel-body">${company.address}</div>
			</div>
		</div>
		<div class="col-sm-12">
			<div class="panel panel-info">
				<div class="panel-heading">About Us</div>
				<div class="panel-body">${company.aboutUs}</div>
			</div>
		</div>
	</div>

	<h1 class="vacancy-header">Vacancies</h1>
	<div class="vacancy-container">
		<c:forEach var="vacancy" items="${vacancies}">
			<div class="panel panel-display">
				<div class="panel-heading">
					<h3 class="panel-title">${vacancy.id}&nbsp;-&nbsp;<b>${vacancy.title}</b>&nbsp;<i>(Rs.${vacancy.salary})</i>
					</h3>
				</div>
				<div class="panel-body vacancy">
					<div class="col-md-6">${vacancy.description_1}&nbsp;${vacancy.description_2}
						adasdasda sdandad ghadghaf dahgsd adf aghdf ahgdf aghdf adf aghdfa
						ghdfa ghdf ahgdf ahgsdfad ghadghaf dahgsd adf aghdf ahgdf aghdf
						adf aghdfa ghdfa ga ghdf ahgdf ahgsdfad ghadghaf dahgsd adf aghdf
						ahgdf aghdf adf aghdfa ghdfa ghdf ahgdf ahgsdf</div>
					<div class="col-md-4 preferences">
						<c:forEach var="pref" items="${vacancy.preferences}">
							<li>${pref}</li>
						</c:forEach>
					</div>
					<div class="button col-md-2">
						<button type="submit" name="edit" value="edit"
							class="btn btn-warning btn-lg">${vacancy.applicant}</button><br />
						<form:form action="vacancy/${vacancy.id}/delete" method="POST">
							<button type="submit" name="1st" value="edit"
								class="btn btn-danger btn-lg">Delete</button>
						</form:form>
						<form:form action="vacancy/${vacancy.id}/update" method="POST">
							<button type="submit" name="1st" value="edit"
								class="btn btn-info btn-lg">Update</button>
						</form:form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>