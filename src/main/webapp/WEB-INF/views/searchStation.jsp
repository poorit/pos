<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8">
		<title>POS :: search</title>
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
			<form action="search" method="get">
				<div class="form-group" id="station_search">
						<input type="text" class="form-control" id="station_search_input" name="stationName" placeholder="SEARCH"/>
				</div>
				<button type="submit" class="btn btn-default" id="station_search_btn">찾기</button>
			</form>
			
			<!-- 역정보 -->
			<div id="station_info">
				찾는 역 이름 : ${stationName} <br>
				찾는 역 정보 : 
				
				<table>
				<c:forEach var="info" items="${stationInfo}">
					<tr>
						<td>${info}</td>
					</tr>
				</c:forEach>
				</table>
			</div>
		</div>
		
	</body>
</html>