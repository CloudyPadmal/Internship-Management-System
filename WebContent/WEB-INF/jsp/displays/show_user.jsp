<%@ include file="/WEB-INF/jsp/head.jsp"%>

<!DOCTYPE html>
<html lang="en">


<c:if test="${not empty msg}">
	<strong>${msg}</strong>
</c:if>

<h1>User Detail</h1>
<table>
	<tr>
		<td>Login</td>
		<td>${user.indexNumber}</td>
	</tr>
	<tr>
		<td>Name</td>
		<td>${user.surname}</td>
	</tr>
	<tr>
		<td>Email</td>
		<td>${user.emailAddress}</td>
	</tr>
	<tr>
		<td>Gender</td>
		<td>${user.gender}</td>
	</tr>
	<tr>
		<td>Phone</td>
		<td>${user.telephone}</td>
	</tr>
	<tr>
		<td>GPA</td>
		<td>${user.gradedPoint}</td>
	</tr>
	<tr>
		<td>About Me</td>
		<td>${user.aboutMe}</td>
	</tr>
	<tr>
		<td>Preferences</td>
		<td>${user.preferences}</td>
	</tr>
</table>
</body>
</html>