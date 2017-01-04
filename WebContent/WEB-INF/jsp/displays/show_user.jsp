<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">${user.indexNumber}</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><form:form
							action="/MSDProject/reg/user/users/${user.indexNumber}/update"
							method="POST">
							<button type="submit" name="edit" value="edit" class="edit">Edit</button>
						</form:form></li>
					<li><a href="/MSDProject/">Logout</a></li>
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

	<div class="section-one">
		<table class="display-user">
			<tr>
				<td><div class="column-head">Name</div></td>
				<td><div class="column-body">${user.name}&nbsp;${user.surname}</div></td>

			</tr>
			<tr>
				<td><div class="column-head">Email</div></td>
				<td><div class="column-body">${user.emailAddress}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Gender</div></td>
				<td><div class="column-body">${user.gender}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Phone</div></td>
				<td><div class="column-body">${user.telephone}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">About Me</div></td>
				<td><div class="column-body">${user.aboutMe}</div></td>
			</tr>
		</table>

		<table class="display-user">
			<tr>
				<td><div class="column-head">GPA</div></td>
				<td><div class="column-body">${user.gradedPoint}</div></td>
			</tr>
			<tr>
				<td><div class="column-head">Preferences</div></td>
				<td><c:forEach var="pref" items="${user.preferences}">
						<div class="column-item">
							<li>${pref}</li>
						</div>
					</c:forEach></td>
			</tr>
		</table>
	</div>
	<div>
		<c:forEach var="vacancy" items="${vacancies}">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">${vacancy.company}&nbsp;-&nbsp;
						${vacancy.title}&nbsp;(Rs.${vacancy.salary})</h3>
				</div>
				<div class="panel-body">
					<table class="table table-striped">
						<tr>
							<td>${vacancy.description_1}&nbsp;${vacancy.description_2}
								adasdasda sdandad ghadghaf dahgsd adf aghdf ahgdf aghdf adf
								aghdfa ghdfa ghdf ahgdf ahgsdfad ghadghaf dahgsd adf aghdf ahgdf
								aghdf adf aghdfa ghdfa ga ghdf ahgdf ahgsdfad ghadghaf dahgsd
								adf aghdf ahgdf aghdf adf aghdfa ghdfa ghdf ahgdf ahgsdf</td>
							<td><c:choose>
									<c:when test="${not vacancy.open}">
										<a href="apply/${vacancy.id}/${user.indexNumber}"
											class="button" type="submit">Apply for ${vacancy.title}!</a>
									</c:when>
									<c:otherwise>
										<i>Someone applied for it!</i>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<tr>
							<td>${vacancy.preferences}</td>
						</tr>
					</table>
				</div>

			</div>

		</c:forEach>
	</div>

</body>
</html>