<%@ include file="/WEB-INF/jsp/head.jsp" %>

<h1>Add New User</h1>
<form:form method="post" action="${action_url}">
	<table>
		<tr>
			<td>Name :</td>
			<td><form:input path="username" /></td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><form:input path="password" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
