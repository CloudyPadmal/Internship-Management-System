<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
	<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
	<body>
		<div class="container">
			<div class="jumbotron">
				<h1>Project MSD</h1>
				<p class="lead">Where companies meet students!!!</p>
				<p>				
					<form:form action="user_login"><button class="bigbutton positivebtn">User Login</button></form:form>
					<form:form action="company_login"><button class="bigbutton positivebtn">Company Login</button></form:form>
				</p>
			</div>
	
			<footer class="footer">
				<p>© 2016 MSDProject, Inc.</p>
			</footer>
		</div>
	</body>
</html>
<!-- © Padmal -->