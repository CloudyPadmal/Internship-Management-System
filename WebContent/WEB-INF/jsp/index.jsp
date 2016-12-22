<%@ page contentType="text/html; charset=UTF-8"%>

<html>
	<head>
		<style>
		.button {
			background-color: #4CAF50; /* Green */
			border: none;
			color: white;
			padding: 16px 10px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			width: 350px;
			margin: 10px;
			-webkit-transition-duration: 0.4s; /* Safari */
			transition-duration: 0.4s;
			cursor: pointer;
		}
		
		.userbtn {
			background-color: white; 
			color: black; 
			border: 2px solid #4CAF50;
		}
		.userbtn:hover {
			background-color: #000000;
			color: white;
		}
		
		.companybtn {
			background-color: white; 
			color: black; 
			border: 2px solid #4CAF50;
		}
		.companybtn:hover {
			background-color: #000000;
			color: white;
		}
		
		.adminbtn {
			background-color: white; 
			color: black; 
			border: 2px solid #4CAF50;
		}

		.adminbtn:hover {
			background-color: #000000;
			color: white;
		}
		
		.homepage {
			margin: 0;
    		position: absolute;
    		top: 50%;
    		left: 50%;
    		transform: translate(-50%, -50%);
		}
		
		.homebody {
			background-color: #4CAC81;
		}
		</style>
	</head>
	
	<body class = "homebody">
	<div class = "homepage" align = "center">
		<button class="button userbtn">User Login</button><br />
		<button class="button companybtn">Company Login</button><br />
		<button class="button adminbtn">Admin Login</button><br />
	</div>
	</body>

</html>