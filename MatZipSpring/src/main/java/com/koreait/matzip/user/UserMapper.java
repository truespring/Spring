package com.koreait.matzip.user;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserPARAM;
import com.koreait.matzip.user.model.UserVO;

@Mapper // xml 파일과 같이 DAO를 만들게 된다
public interface UserMapper {
	public int insUser(UserVO p);
	public UserDMI selUser(UserPARAM p);
}
