package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestMenuVO;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;

@Mapper
public interface RestMapper {
	List<RestDMI> selRestList(RestPARAM param);
	RestDMI selRest(RestPARAM param);
	List<RestRecMenuVO> selRestRecMenus(RestPARAM param);
	List<RestRecMenuVO> selRestMenus(RestPARAM param);
	int selRestChkUser(int i_rest);
	
	int insRest(RestPARAM param);
	int insRestMenu(RestRecMenuVO param);
	int insRestRecMenu(RestRecMenuVO param);
	
	int updAddHits(RestPARAM param);
	
	int delRest(RestPARAM param);
	int delRestMenu(RestPARAM param);
	int delRestRecMenu(RestPARAM param);
}
