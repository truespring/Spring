<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.label {margin-bottom: 96px;}
.label * {display: inline-block;vertical-align: top;}
.label .left {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;display: inline-block;height: 24px;overflow: hidden;vertical-align: top;width: 7px;}
.label .center {background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;display: inline-block;height: 24px;font-size: 12px;line-height: 24px;}
.label .right {background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0  no-repeat;display: inline-block;height: 24px;overflow: hidden;width: 6px;}
</style>
<div id="sectionContainerCenter">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fd39d5d86e6a76d14302b439f50a0c81"></script>
	<div id="mapContainer" style="width:100%;height:100%;"></div>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
		var markerList = [] // 마커 리스트
	
		const options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(35.8641294, 128.5942331), //지도의 중심좌표.
			level: 5  //지도의 레벨(확대, 축소 정도)
		}
		
		const map = new kakao.maps.Map(mapContainer, options); //지도 생성 및 객체 리턴
		
		function getRestaurantList() {
			// 마커 모두 지우기
			markerList.forEach(function(marker) {
				marker.setMap(null)
			})
			
			const bounds = map.getBounds()
			const southWest = bounds.getSouthWest()
			const northEast = bounds.getNorthEast()
			
			const sw_lat = southWest.getLat()
			const sw_lng = southWest.getLng()
			const ne_lat = northEast.getLat()
			const ne_lng = northEast.getLng()
			
			console.log('southWest: ' + southWest)
			console.log('northEast: ' + northEast)
			
			axios.get('/rest/ajaxGetList', {
				params: {
					sw_lat, sw_lng, ne_lat, ne_lng
				}
			}).then(function(res) {
				console.log(res.data)
				
				res.data.forEach(function(item) {
					createMarker(item)
				})
			})
		}
		
		kakao.maps.event.addListener(map, 'tilesloaded', getRestaurantList)

		function createMarker(item) {
			var content = document.createElement('div')
			content.className = 'label'
			
			var leftSpan = document.createElement('span')
			leftSpan.className = 'left'
			
			var rightSpan = document.createElement('span')
			rightSpan.className = 'right'
			
			var centerSpan = document.createElement('span')
			centerSpan.className = 'center'
			centerSpan.innerText = item.nm
			
			content.appendChild(leftSpan)
			content.appendChild(centerSpan)
			content.appendChild(rightSpan)
			
			var mPos = new kakao.maps.LatLng(item.lat, item.lng)
			var marker = new kakao.maps.CustomOverlay({
				position: mPos,
				content: content
			})
			
			addEvent(content, 'click', function() {
				console.log('마커클릭 : ' + item.i_rest)
				moveToDetail(item.i_rest)
			})
			marker.setMap(map)
			
			markerList.push(marker)
		}
		
		function moveToDetail(i_rest) {
			location.href = '/rest/detail?i_rest=' + i_rest
		}
		
		function addEvent(target, type, callback) {
			if(target.addEventListener) {
				target.addEventListener(type, callback)
			} else {
				target.attachEvent('on' + type, callback)
			}
		}

		if (navigator.geolocation) {
			console.log('Geolocation is supported!')
			var startPos;
			navigator.geolocation.getCurrentPosition(function(pos) {
				startPos = pos
				console.log('lat : ' + startPos.coords.latitude)
				console.log('lng : ' + startPos.coords.longitude)
				
				if(map) {
					var moveLatLon = new kakao.maps.LatLng(startPos.coords.latitude, startPos.coords.longitude)
					map.panTo(moveLatLon)					
				}
				
			})
		} else {
			console.log('Geolocation is not supported this browserOS')
		}
	</script>
</div>
