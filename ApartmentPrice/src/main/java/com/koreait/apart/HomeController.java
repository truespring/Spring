package com.koreait.apart;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Controller
public class HomeController {
	
	@Autowired
	private HomeMapper mapper;
	
	@RequestMapping
	public String home(Model model) {
		
		model.addAttribute("locationList", mapper.selLocationCodeList());
		return "index";
	}
	
	@RequestMapping(value = "/result", method=RequestMethod.POST)
	public String result(SearchParam param, Model model) {
		List<ApartInfoVO> data = mapper.selApartmentInfoList(param);
		if(data.size() > 0) {
			model.addAttribute("data", data);
			return "result";
		}
		String lawd_cd = param.getLocationCd();
		//02는 무조건 2자리 차지한다는 의미,
		//primative 변수, 레퍼런스 변수는 null 가질 수 있음
		//mon에 null이 안 넘어옴, int형이라서 0이 넘어옴
		String deal_ym = String.format("%s%02d", param.getYear(), param.getMon());
		
		String serviceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX%2BNgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA%3D%3D";
		String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
		
		String decodeServiceKey = null;
		try {
			decodeServiceKey = URLDecoder.decode(serviceKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		
		final HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("LAWD_CD", lawd_cd)
				
				.queryParam("DEAL_YMD", deal_ym).queryParam("serviceKey", decodeServiceKey).queryParam("numOfRows", 100).build(false);
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		System.out.println("builder.toString() : "+builder.toUriString());
		
		ResponseEntity<String> respEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		String result = respEntity.getBody();
		System.out.println("xml : "+result);
		ObjectMapper om = new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ResponseDTO dto = null;
		
		try {
			dto = om.readValue(result, ResponseDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ItemDTO> list = dto.getBody().getItems();
		
		if(list.size() > 0) {
			for (ItemDTO item : list) {
				String s = item.getDeal_amount();
				item.setDeal_amount(s.replace(",", ""));
				mapper.insApartmentInfo(item);
			}
		}
		
		System.out.println("result : "+respEntity.getBody());
		model.addAttribute("data", mapper.selApartmentInfoList(param));
		return "result";
	}
}
