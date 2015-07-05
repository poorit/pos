<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/lib/bootstrap-3.3.2/css/bootstrap.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/lib/bootstrap-3.3.2/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/index.css"/>
<script src="<%=request.getContextPath() %>/resources/lib/jquery-2.1.3/jquery-2.1.3.min.js"></script>
<script src="<%=request.getContextPath() %>/resources/lib/jquery-2.1.3/jquery.js"></script>
<script src="<%=request.getContextPath() %>/resources/lib/jquery-2.1.3/jquery-ui.js"></script>
<script src="<%=request.getContextPath() %>/resources/lib/bootstrap-3.3.2/js/bootstrap.js"></script>
<script src="<%=request.getContextPath() %>/resources/js/index.js"></script>
</head>
<body class="index">
<%@ include file="navbar.jsp"%>
<%@ include file="login.jsp"%>

<div class="container-fluid" id="main_contents">
	<div class="contents_top">
		<h1>LOGO</h1>
			<form action="search" method="get">
				<div class="form-group" id="home_search">
						<input type="text" class="form-control" id="home_search_input" name="stationName" placeholder="SEARCH"/>
				</div>
				<button type="submit" class="btn btn-default" id="home_search_btn">찾기</button>
			</form>
	</div>
	<div class="contents_bottom">
		<div class="text_area">
			<div id="notice">
				<h4 id="title">NOTICE</h4>
				<hr>
				<h5>안녕하세요 공지사항입니다.</h5>
				<h5>안녕하세요 공지사항입니다.</h5>
				<h5>안녕하세요 공지사항입니다.</h5>
				<h5>안녕하세요 공지사항입니다.</h5>
			</div>
			<div id="rank">
				<h4 id="title">RANK</h4>
				<hr>
				<h5>안녕하세요 랭크입니다.</h5>
			</div>
		</div>
	</div>
</div>
</body>
</html>