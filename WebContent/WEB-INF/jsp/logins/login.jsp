<%@ include file="/WEB-INF/jsp/head.jsp"%>

<html>
<head>
<spring:url value="/resources/css/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
<title>MSD Project</title>
</head>
<body class="homebody">

	<h2>${principal}&nbsp;Login</h2>
	<form:form method="POST" modelAttribute="command"
		action="${action_url}">
		<table class="form-area">
			<tr>
				<td><form:label path="username" class="input-label">${principal}</form:label></td>
				<td><form:input path="username" class="input-field" /></td>
			</tr>
			<tr>
				<td><form:label path="password" class="input-label">Password</form:label></td>
				<td><form:input path="password" type="password"
						class="input-field" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="login" value="Login"
					class="midbutton positivebtn"/></td>
			</tr>
			<c:if test="${not admin}">
				<tr>
					<td colspan="2"><input type="submit" name="register"
						value="Register" class="midbutton positivebtn" /></td>
				</tr>
			</c:if>
		</table>
	</form:form>

	<p class="error">
		<c:if test="${not empty error}">
			<c:out value="${error}" />
		</c:if>
	</p>
</body>
</html>