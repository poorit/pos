<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/lib/bootstrap-3.3.2/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/lib/bootstrap-3.3.2/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/index.css"/>
<script src="<%=request.getContextPath() %>/resources/lib/jquery-2.1.3/jquery-2.1.3.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/lib/jquery-2.1.3/jquery.js"></script>
<script src="<%=request.getContextPath() %>/resources/lib/jquery-2.1.3/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/resources/lib/bootstrap-3.3.2/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/index.js"></script>
</head>
<body>
<header>
	<nav class="navbar navbar-default navbar-custom">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="home">HOME</a>
		</div>
		<div class="collapse navbar-collapse">
		<ul class="nav navbar-nav">
			<li class="">
				<a href="#">SUBWAY</a>
			</li>
			<li class="">
				<a href="map">MAP</a>
			</li>
			<li class="">
				<a href="search">SEARCH</a>
			</li>
			<li class="">
				<a href="#">RANK</a>
			</li>
		</ul>
		 <div class="navbar-right">
			<ul class="nav navbar-nav">
			<li class="">
				<a href="#">Sign In</a>
			</li>
			<li class="">
				<a href="#">Sign Up</a>
			</li>
		</ul>
		</div> 
		</div>
	</div>
	</nav>
</header>
</body>
</html>