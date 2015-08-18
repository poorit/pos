<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
 .map_wrap {position:relative;width:100%;height:600px;}
 #centerAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background: rgba(255,255,255,0.8);z-index: 1;padding:5px;font-weight: bold;font-size:14px;}
</style>
<body class="index">
<%@ include file="navbar.jsp"%>
<%@ include file="login.jsp"%>

<div class="container-fluid" id="main_contents">
  <form action = "map" method="get" id="mapForm">
	<div class="form-group has-success" id="map_search_one">
      <input type="text" class="form-control" name="one" id="map_search_input_one" placeholder="SEARCH"/>
        
      <button type="button" onclick="SearchMap('one')" 
            id="map_search_btn" class="btn btn-default">
         <span class="glyphicon glyphicon-ok"></span>
      </button>
   </div>
   
   <div class="form-group has-success" id="map_search_two">
      <input type="text" class="form-control" name="two" id="map_search_input_two" placeholder="SEARCH"/>
        
      <button type="button" onclick="SearchMap('two')" 
            id="map_search_btn" class="btn btn-default">
         <span class="glyphicon glyphicon-ok"></span>
      </button>
   </div>
   
   <div class="form-group has-success" id="map_search_three">
      <input type="text" class="form-control" name="three" id="map_search_input_three" placeholder="SEARCH"/>
        
      <button type="button" onclick="SearchMap('three')" 
            id="map_search_btn" class="btn btn-default">
         <span class="glyphicon glyphicon-ok"></span>
      </button>
   </div>
   <input type="text" id="city" name="city" value="" style="display:none">
   <input type="text" id="map_result_x" name="map_result_x" value="${map_result_x}" style="display:">
   <input type="text" id="map_result_y" name="map_result_y" value="${map_result_y}" style="display:">
   <button type="button" class="btn btn-default" id="map_search_result_btn" onclick="SearchMap('result')">검색</button>
   </form>
	<!-- 지도를 표시할 div 입니다 -->
	<div class="map_wrap">
    	<div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
    	<div id="centerAddr"></div>
	</div>
</div>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=b2e0efeb7f0e5d5853cb1778210d6854&libraries=services"></script>
<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});

var map_one = {x : 0 , y : 0};
var map_two = {x : 0 , y : 0};
var map_three = {x : 0 , y : 0};
var map_result = {x : 0 , y : 0};

var x=37.566826;
var y=126.9786567;

var value = "";

var first_flag = true;
var test_flag = true;

if(document.getElementById('map_result_x').value != "")
{
	x=document.getElementById('map_result_x').value;
	y=document.getElementById('map_result_y').value;
	first_flag=false;
} 
	
	
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(x, y), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

//주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

//현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// 장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places(); 

// 키워드로 장소를 검색합니다
if(first_flag){
	//ps.keywordSearch('용산 맛집', placesSearchCB);
}
 
//현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB (status, data, pagination) {
    if (status === daum.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        var bounds = new daum.maps.LatLngBounds();

        for (var i=0; i<data.places.length; i++) {
            displayMarker(data.places[i]);    
            bounds.extend(new daum.maps.LatLng(data.places[i].latitude, data.places[i].longitude));
        }       

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
        
        if(value == "one"){
        	map_one.x = map.getCenter().getLat();
    		map_one.y = map.getCenter().getLng();
        }else if(value == "two"){
        	map_two.x = map.getCenter().getLat();
    		map_two.y = map.getCenter().getLng();
        }else if(value == "three"){
        	map_three.x = map.getCenter().getLat();
    		map_three.y = map.getCenter().getLng();
        }
    } 
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    
    // 마커를 생성하고 지도에 표시합니다
    var marker = new daum.maps.Marker({
        map: map,
        position: new daum.maps.LatLng(place.latitude, place.longitude) 
    });

    // 마커에 클릭이벤트를 등록합니다
    daum.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.title + '</div>');
        infowindow.open(map, marker);
    });
}

function SearchMap(tmp){
	
	if(tmp != "result")
	{
		var data = document.getElementById('map_search_input_'+tmp).value;
		ps.keywordSearch(data, placesSearchCB);
		
		value = tmp;
	}else{
		map_result.x = (map_one.x + map_two.x + map_three.x) / 3;
		map_result.y = (map_one.y + map_two.y + map_three.y) / 3;
		
		alert("x : " + map_result.x + "y : " + map_result.y);
		
		mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new daum.maps.LatLng(map_result.x.toFixed(6), map_result.y.toFixed(6)), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    }; 
		
		map = new daum.maps.Map(mapContainer, mapOption); 
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		
		var infoDiv = document.getElementById('centerAddr');
		var city = document.getElementById('city');
		var form = document.forms["mapForm"];
		
		city.value = infoDiv.value;
		document.getElementById('map_result_x').value = map_result.x.toFixed(6);
		document.getElementById('map_result_y').value = map_result.y.toFixed(6);

		form.submit();
	}
	
	
/* 	if(value == "one" && test_flag == false)
	{
		map_one.x = map.getCenter().getLat();
		map_one.y = map.getCenter().getLng();
		
		alert("one x : " + map_one.x + " y : " + map_one.y);
		
	}else if(value == "two"){
		map_two.x = map.getCenter().getLat();
		map_two.y = map.getCenter().getLng();
		
		alert("two x : " + map_two.x + " y : " + map_two.y);
	}else if(value == "three"){
		map_three.x = map.getCenter().getLat();
		map_three.y = map.getCenter().getLng();
		
		alert("three x : " + map_three.x + " y : " + map_three.y);
	} */
}
//중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
daum.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    // 좌표로 주소 정보를 요청합니다
    geocoder.coord2addr(coords, callback);         
}

// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayCenterInfo(status, result) {
    if (status === daum.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');
        infoDiv.innerHTML = result[0].fullName;
        infoDiv.value = result[0].fullName;
    }    
}
</script>
</body>
</html>
