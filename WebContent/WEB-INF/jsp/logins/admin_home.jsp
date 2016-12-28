<%@ include file="/WEB-INF/jsp/head.jsp"%>

<html>
<head>
<spring:url value="/resources/css/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>

<body class="homebody">
	<div class="homepage" align="center">
		<a href=view_users>
			<button class="bigbutton positivebtn">View Users</button>
		</a> <a href=user_login>
			<button class="bigbutton positivebtn">View Companies</button>
		</a> <a href=user_login>
			<button class="bigbutton positivebtn">View Vacancies</button>
		</a> <br />
	</div>
</body>
</html>