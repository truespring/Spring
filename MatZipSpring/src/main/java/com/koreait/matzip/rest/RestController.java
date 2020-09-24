package com.koreait.matzip.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.rest.model.RestDMI;
import com.koreait.matzip.rest.model.RestFile;
import com.koreait.matzip.rest.model.RestMenuVO;
import com.koreait.matzip.rest.model.RestPARAM;

@Controller
@RequestMapping("/rest")
public class RestController {
	
	@Autowired
	private RestService service;
	
	@RequestMapping("/map")
	public String restMap(Model model) {
		model.addAttribute(Const.TITLE, "지도");
		model.addAttribute(Const.VIEW, "rest/restMap");
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	@RequestMapping(value = "/restReg", method = RequestMethod.GET)
	public String restReg(Model model) {
		model.addAttribute("categoryList", service.selCategoryList());
		model.addAttribute(Const.TITLE, "식당 등록");
		model.addAttribute(Const.VIEW, "rest/restReg");
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	@RequestMapping(value = "/restReg", method = RequestMethod.POST)
	public String restReg(RestPARAM param, HttpSession hs) {		
		param.setI_user(SecurityUtils.getLoginUserPK(hs));
		int result = service.insRest(param);
		
		return "redirect:/";
	}	
	
	@RequestMapping(value = "/ajaxGetList", produces = {"application/json; charset=UTF-8"})
	@ResponseBody public List<RestDMI> ajaxGetList(RestPARAM param) {
		System.out.println("sw_lat : " + param.getSw_lat());
		System.out.println("sw_lng : " + param.getSw_lng());
		System.out.println("ne_lat : " + param.getNe_lat());
		System.out.println("ne_lng : " + param.getNe_lng());
		return service.selRestList(param);
	}
	
	@RequestMapping(value = "/detail")
	public String detail(RestPARAM param, Model model) {
		RestDMI data = service.selRest(param);
//		List<RestRecMenuVO> selRestRecMenus = service.selRestRecMenus(param);
		model.addAttribute(Const.TITLE, data.getNm());
		model.addAttribute(Const.VIEW, "rest/restDetail");
		model.addAttribute("css", new String[] {"restaurant"});
		model.addAttribute("data", data);
		model.addAttribute("menuList", service.selRestMenus(param));
		model.addAttribute("recMenuList", service.selRestRecMenus(param));
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	@RequestMapping(value = "/del")
	public String del(RestPARAM param, HttpSession hs, Model model) {
		param.setI_user(SecurityUtils.getLoginUserPK(hs));
		int result = 1;
		try { // 트라이 캐치문이 없다면 쿼리문이 노출될 수 있다.
			service.delRestTran(param);
		} catch(Exception e) {
			result = 0;
		}
		System.out.println("result : " + result);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/recMenus", method = RequestMethod.POST)
	public String recMenus(MultipartHttpServletRequest mReq, RedirectAttributes ra) {
		System.out.println("/recMenus");
		int i_rest = service.insRecMenus(mReq);
		
		ra.addAttribute("i_rest", i_rest);
		return "redirect:/rest/detail";
	}
	
	@RequestMapping("/ajaxDelRecMenu")
	@ResponseBody public int ajaxDelRecMenu(RestPARAM param, HttpSession hs) {
		String path = "/resources/img/rest/" + param.getI_rest() + "/rec_menu/";
		String realPath = hs.getServletContext().getRealPath(path);
		param.setI_user(SecurityUtils.getLoginUserPK(hs));
		return service.delRecMenu(param, realPath);
	}
	
	@RequestMapping("/menus")
	public String menus(@ModelAttribute RestFile param, HttpSession hs, RedirectAttributes ra) {
		int i_user = SecurityUtils.getLoginUserPK(hs);
		int result = service.insRestMenu(param, i_user);
		ra.addAttribute("i_rest", param.getI_rest());
		
		return "redirect:/rest/detail";
	}
}
