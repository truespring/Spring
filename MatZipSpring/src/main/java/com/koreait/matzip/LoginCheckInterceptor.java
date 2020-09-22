package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 메소드를 추가함으로 인해 부픔을 갈아끼우는 것처럼 사용이 가능하다
		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String[] uriArr = uri.split("/");

		System.out.println("uriArr.length : " + uriArr.length);
		if(uriArr[1].equals("res")) {
			return true;
		} else if(uriArr.length < 3) { // 주소가 이상한 경우
			return false;
		}
		
		System.out.println("인터셉터!");
		boolean isLogout = SecurityUtils.isLogout(request);
		
		switch(uriArr[1]) {
		case ViewRef.URI_USER:
			switch(uriArr[2]) { // 1차 주소값
			case "login": case "join": // 2차 주소값
				if(!isLogout) { // 로그인이 되어 있는 상태
					response.sendRedirect("/rest/map");
					return false;
				}
			}
			break;
		case ViewRef.URI_REST :
			switch(uriArr[2]) {
			case "restReg" :	
				if(isLogout) { // 로그아웃 상태
					response.sendRedirect("/user/login");
					return false;
				}
			}
		}
		return true;
	}
}
