<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>

<body>
	<div class="container">
		<div class="jumbotron">
			<h1>Project MSD</h1>
			<p class="lead">Where companies meet students!!!</p>
			<p>
				<form:form action="view_users" method="POST">
					<button type="submit" name="view_users" value="users"
						class="bigbutton positivebtn">View Users</button>
				</form:form>
				<form:form action="view_companies" method="POST">
					<button type="submit" name="view_users" value="users"
						class="bigbutton positivebtn">View Companies</button>
				</form:form>
				<form:form action="view_vacancies" method="POST">
					<button type="submit" name="view_users" value="users"
						class="bigbutton positivebtn">View Vacancies</button>
				</form:form>
			</p>
		</div>

		<footer class="footer">
			<p>© 2016 MSDProject, Inc.</p>
		</footer>
	</div>
</body>
</html>