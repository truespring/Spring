package com.koreait.matzip.rest;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestPARAM;
import com.koreait.matzip.rest.model.RestRecMenuVO;

@Mapper
public interface RestMapper {
	List<RestDMI> selRestList(RestPARAM param);
	int insRest(RestPARAM param);
	RestDMI selRest(RestPARAM param);
	int delRestRecMenu(RestPARAM param);
	int delRestMenu(RestPARAM param);
	int delRest(RestPARAM param);
	int insRestRecMenu(RestRecMenuVO param);
	List<RestRecMenuVO> selRestRecMenus(RestPARAM param);
	int delRecMenu(RestPARAM param);
}
