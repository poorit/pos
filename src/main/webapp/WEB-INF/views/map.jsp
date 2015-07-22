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
	<div class="form-group" id="map_search_one">
			<input type="text" class="form-control" id="map_search_input_one" placeholder="SEARCH"/>
			<button type="button" class="btn btn-default" id="map_search_one_btn" onclick="SearchMap('one')">검색</button>
	</div>
	<div class="form-group" id="map_search_two">
			<input type="text" class="form-control" id="map_search_input_two" placeholder="SEARCH"/>
			<button type="button" class="btn btn-default" id="map_search_two_btn" onclick="SearchMap('two')">검색</button>
	</div>
	<div class="form-group" id="map_search_three">
			<input type="text" class="form-control" id="map_search_input_three" placeholder="SEARCH"/>
			<button type="button" class="btn btn-default" id="map_search_three_btn" onclick="SearchMap('three')">검색</button>
	</div>
		<button type="button" class="btn btn-default" id="map_search_btn" onclick="SearchMap('result')">검색</button>

	<!-- 지도를 표시할 div 입니다 -->
	<div id="map" style="width:100%;height:1024px"></div>
</div>
<script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=b2e0efeb7f0e5d5853cb1778210d6854&libraries=services"></script>
<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new daum.maps.InfoWindow({zIndex:1});
var map_one = {x : 0 , y : 0};
var map_two = {x : 0 , y : 0};
var map_three = {x : 0 , y : 0};
var map_result = {x : 0 , y : 0};

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 장소 검색 객체를 생성합니다
var ps = new daum.maps.services.Places(); 

// 키워드로 장소를 검색합니다
ps.keywordSearch('용산 맛집', placesSearchCB); 

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
function SearchMap(value){
	
	if(value != "result")
	{
		var data = document.getElementById('map_search_input_'+value).value;
		ps.keywordSearch(data, placesSearchCB);
	}else{
		alert(map_three.x);
		map_result.x = (map_one.x + map_two.x + map_three.x) / 3;
		map_result.y = (map_one.y + map_two.y + map_three.y) / 3;
		
		alert("result : " + map_result.x + ", " + map_result.y);
		
		mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new daum.maps.LatLng(map_result.x.toFixed(6), map_result.y.toFixed(6)), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    }; 
		
		map = new daum.maps.Map(mapContainer, mapOption); 
	}
	
	
	if(value == "one")
	{
		map_one.x = map.getCenter().getLat();
		map_one.y = map.getCenter().getLng();
		alert("one : " + map_one.x + ", " + map_one.y);
	}else if(value == "two"){
		map_two.x = map.getCenter().getLat();
		map_two.y = map.getCenter().getLng();
		alert("two : " + map_two.x + ", " + map_two.y);
	}else if(value == "three"){
		map_three.x = map.getCenter().getLat();
		map_three.y = map.getCenter().getLng();
		alert("three : " + map_three.x + ", " + map_three.y);
	}
}
</script>
</body>
</html>
