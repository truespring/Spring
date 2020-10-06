package com.koreait.matzip.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.user.model.UserDMI;
import com.koreait.matzip.user.model.UserPARAM;
import com.koreait.matzip.user.model.UserVO;

@Mapper // xml 파일과 같이 DAO를 만들게 된다
public interface UserMapper {
	public UserDMI selUser(UserPARAM param);
	public List<UserDMI> selFavoriteList(UserPARAM param);
	
	public int insUser(UserVO param);
	int insFavorite(UserPARAM param);

	int delFavorite(UserPARAM param);
}
