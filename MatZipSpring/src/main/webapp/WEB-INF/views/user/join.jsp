<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
	<div>
		<div class="msg">${msg}</div>
		<form action="join" method="post" id="frm" class="frm">
			<div id="idChkResult" class="msg"></div>
			<div><input type="text" name="user_id" placeholder="아이디">
				<button type="button" onclick="chkId()">아이디 중복 체크</button>
			</div>
			<div><input type="password" name="user_pw" placeholder="비밀번호"></div>
			<div><input type="password" name="user_pwre" placeholder="비밀번호 확인"></div>
			<div><input type="text" name="nm" placeholder="이름"></div>
			<div><input type="submit" value="회원가입"></div>
		</form>
		<div><a href="/user/login">로그인</a></div>
	</div>
	<!-- ajax통신을 사용하는 이유는 값을 보내고 받을때 새로고침을 하지 않고 통신하기 위해서 -->
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
	function chkId() {												
		const user_id = frm.user_id.value									
		var text;									
		axios.post('/user/ajaxIdChk', {					
				user_id: user_id								
		}).then(function(res) {									
			console.log(res)											
			if(res.data == 2) { // 아이디 사용 가능								
				idChkResult.innerText = '사용 가능한 아이디 입니다.'							
			} else if(res.data == 3) { // 아이디 중복 됨								
				idChkResult.innerText = '중복된 아이디 입니다.'							
			}								
		})									
	}
	

	</script>
</div>