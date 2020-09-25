package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.koreait.matzip.rest.RestMapper;

public class RestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RestMapper mapper;

	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) throws Exception {	
		System.out.println("rest - interceptor");

		String uri = request.getRequestURI();
		System.out.println("uri : " + uri);
		String[] uriArr = uri.split("/");
// 주소값안에 체크키워드가 포함되어 있다면 글쓴이와 로그인한 사람이 동일한지 다른지 체크하는 것
		String[] checkKeywords = {"del", "Del", "upd", "Upd"};
		for(String keyword: checkKeywords) {
			if(uriArr[2].contains(keyword)) {
				int i_rest = CommonUtils.getIntParameter("i_rest", request);
				if(i_rest == 0) {
					return false;
				}

				int i_user = SecurityUtils.getLoginUserPK(request); // 로그인 한 사람의 정보

				boolean result = _authSuccess(i_rest, i_user); // 식당을 등록한 사람과 로그인 사람이 동일인인지 체크(true = 동일인)
				System.out.println("=== auth result : " + result);
				return result;
			}
		}
		return true;
	}

	// 로그인 정보 비교
	private boolean _authSuccess(int i_rest, int i_user) {
		System.out.println("i_rest : " + i_rest);
		return i_user == mapper.selRestChkUser(i_rest);
	}
}
