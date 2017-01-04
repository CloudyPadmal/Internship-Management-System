<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand">All Users</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="/MSDProject/admin/">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="user-list">
			<c:if test="${not empty msg}">
				<div class="alert alert-${css} alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<strong>${msg}</strong>
				</div>
			</c:if>
	
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Index</th>
						<th>Name & GPA</th>
						<th>Email</th>
						<th>Telephone</th>
						<th>About</th>
						<th>Preferences</th>
						<th>Action</th>
					</tr>
				</thead>
	
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.indexNumber}</td>
						<td>${user.name}&nbsp;${user.surname}<br />${user.gradedPoint}</td>
						<td>${user.emailAddress}</td>
						<td>${user.telephone}</td>
						<td id="description">${user.aboutMe}</td>
						<td><c:forEach var="preference" items="${user.preferences}">
								<li>${preference}</li>
							</c:forEach>
						</td>
						<td>
							<spring:url value="/user/${user.indexNumber}" var="userUrl" />
							<spring:url	value="/reg/user/users/${user.indexNumber}/update" var="updateUrl" />	
							<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button><br />
							<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button><br />
							<form type="submit" action="/MSDProject/reg/user/users/${user.indexNumber}/delete" method="POST"><button class="btn btn-danger">Delete</button></form>
						</td>
					</tr>
				</c:forEach>
			</table>	
		</div>
	</body>
</html>