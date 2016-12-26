<%@ include file="/WEB-INF/jsp/head.jsp"%>

<html>
<head>
<title>MSD Project</title>
</head>
<body>

	<h2>${principal}Login</h2>
	<form:form method="POST" modelAttribute="command" action="${action_url}">
		<table>
			<tr>
				<td><form:label path="username">${principal}</form:label></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="login" value="Login" /></td>
			</tr>
			<c:if test="${not admin}">
				<tr>
					<td colspan="2"><input type="submit" name="register"
						value="Register" /></td>
				</tr>
			</c:if>
		</table>
	</form:form>

	<p>
		<c:if test="${not empty error}">
			<c:out value="${error}" />
		</c:if>
	</p>
</body>
</html>