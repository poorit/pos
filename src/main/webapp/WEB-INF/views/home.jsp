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
<style>
	#home_search{
		display:inline-block;
		margin:0 auto;
		width:30%;
		align:center;
		text-align:center;
		margin-bottom:20px;
		border-radious:0px;
		border:solid 3px #6ed746;
	}
	#home_search_input{
		height:100%;
		font-size:24px;
		border-radius:0px;
	}
	#home_search_btn{
		width:100px;
		height:54px;
		margin-bottom:10px;
		border-radius:0px;
		background:#6ed746;
		color:#fff;
		font-size:16px;
	}
	
</style>
</head>
<body class="index">
<%@ include file="navbar.jsp"%>
<%@ include file="login.jsp"%>

<div class="container-fluid" id="main_contents">
	<div class="contents_top">
		<h1>LOGO</h1>
			<div class="form-group" id="home_search">
					<input type="text" class="form-control" id="home_search_input" placeholder="SEARCH"/>
			</div>
			<button type="button" class="btn btn-default" id="home_search_btn">검색</button>
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