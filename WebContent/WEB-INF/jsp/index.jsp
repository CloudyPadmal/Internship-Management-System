<%@ include file="/WEB-INF/jsp/head.jsp"%>

<html>
<head>
<spring:url value="/resources/msd_styles.css" var="msdCSS" />
<link href="${msdCSS}" rel="stylesheet" />
</head>

<body class="homebody">
	<div class="homepage" align="center">
		<a href=user_login>
			<button class="bigbutton positivebtn">User Login</button>
		</a><br /> <a href="company_login">
			<button class="bigbutton positivebtn">Company Login</button>
		</a><br />
	</div>
</body>
</html>