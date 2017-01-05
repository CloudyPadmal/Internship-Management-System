<%@ include file="/WEB-INF/jsp/head.jsp"%>
<html>
	<body>	
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand">${principal}&nbsp;Login</a>
				</div>
				<c:if test="${not admin}">
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="${register_url}">Register</a></li>
						</ul>
					</div>
				</c:if>
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
	
		<div class="login-page">
			<div class="form">
				<form:form method="POST" modelAttribute="info" action="${action_url}">
					<form:input type="text" placeholder="${principal}" path="username" />
					<form:input type="password" placeholder="Password" path="password"/>
					<form:hidden path="company"/>
					<button type="submit" class="midbutton positivebtn" name="login">Login</button>
				</form:form>
			</div>
		</div>		
	</body>
</html>
<!-- © Padmal -->