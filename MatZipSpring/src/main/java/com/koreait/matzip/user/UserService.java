package com.koreait.matzip.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserDTO;
import com.koreait.matzip.user.model.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	// 1번 로그인 성공, 2번 아이디 없음, 3번 비번 다름
	public int login(UserDTO param) {
		if(param.getUser_id().equals("")) {
			return Const.NO_ID;
		}
		UserDMI dbUser = mapper.selUser(param);
		System.out.println("pw : " + dbUser.getUser_pw());
		return Const.SUCCESS;
	}
	
	public int join(UserVO param) { // 이미 param에는 이름, 아이디, 비밀번호가 담겨있다
		String pw = param.getUser_pw();
		String salt = SecurityUtils.generateSalt();
		String cryptPw = SecurityUtils.getEncrypt(pw, salt);
		
		param.setSalt(salt);
		param.setUser_pw(cryptPw);
		// 파람에는 아이디, 암호화된 비밀번호, 솔트, 이름이 담겨있다
		return mapper.insUser(param);
	}
}
