<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>point of subway</title>
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
<header>
	<nav class="navbar navbar-default navbar-custom">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">POORIT</a>
		</div>
		<div class="collapse navbar-collapse">
		
		<div class="navbar-right">
			<button type="button" class="btn btn-primary navbar-btn" id="side_menu_btn">
				<i class="glyphicon glyphicon-menu-hamburger"></i>
			</button>
		</div>
		</div>
	</div>
	</nav>
</header>
<div class="container-fluid" id="side_menu">
	<div class="row login_box">
		<div class="col-md-12 col-xs-12" align="center">
			<div class="outter">
				<img src="<%=request.getContextPath() %>/resources/img/Penguins.jpg" class="image-circle"/>
			</div>
			<h1>Hi, Guest</h1>
		</div>
		<div class="col-md-12 col-xs-12">
			<div class="login_box">
				<div class="form-group">
					<label for="id_input">ID</label>
					<input type="text" class="form-control" id="id_input" placeholder="ID"/>
				</div>
				<div class="form-group">
					<label for="password_input">PASSWORD</label>
					<input type="password" class="form-control" id="password_input" placeholder="PASSWORD"/>
				</div>
			</div>
			<div align="center">
				<button class="btn btn-primary" id="login_btn">LOGIN</button>
			</div>
			<div align="center">
				<p>회원이 아니신가요? 
				<span>
				<a href="#myModal" role="button"
				data-toggle="modal" data-target="#myModal"
				style="color:#fff; text-decoration:underline;"
				id="join_btn">
				회원가입</a>
				</span>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid" id="main_contents">
	<div class="contents_top">
		<h1>POORIT TEMPLATE</h1>
	</div>
	<div class="contents_bottom">
		<div class="main_btn">
			<div class="col-xs-4 col-md-2 menu_btn" id="menu1">
				<a href="#" class="thumbnail">
					<img src="<%=request.getContextPath() %>/resources/img/Penguins.jpg" alt="Penguins">
				</a>
			</div>
			<div class="col-xs-4 col-md-2 menu_btn">
				<a href="#" class="thumbnail">
					<img src="<%=request.getContextPath() %>/resources/img/Penguins.jpg" alt="Penguins">
				</a>
			</div>
			<div class="col-xs-4 col-md-2 menu_btn">
				<a href="#" class="thumbnail">
					<img src="<%=request.getContextPath() %>/resources/img/Penguins.jpg" alt="Penguins">
				</a>
			</div>
			<div class="col-xs-4 col-md-2 menu_btn">
				<a href="#" class="thumbnail">
					<img src="<%=request.getContextPath() %>/resources/img/Penguins.jpg" alt="Penguins">
				</a>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">회원가입</h4>
      </div>
      <div class="modal-body">
			<div class="join_box">
				<div class="form-group">
					<label for="join_id_input">아이디</label>
					<input type="text" class="form-control" id="join_id_input" placeholder="아이디"/>
				</div>
				<div class="form-group">
					<label for="join_name_input">닉네임</label>
					<input type="text" class="form-control" id="join_name_input" placeholder="닉네임"/>
				</div>
				<div class="form-group">
					<label for="join_password_input">비밀번호</label>
					<input type="password" class="form-control" id="join_password_input" placeholder="비밀번호"/>
				</div>
				<div class="form-group">
					<label for="join_password_check_input">비밀번호 확인</label>
					<input type="password" class="form-control" id="join_password_check_input" placeholder="비밀번호 확인"/>
				</div>
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary">가입</button>
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>
