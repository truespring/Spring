package com.koreait.apart;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Alias("ItemDTO")
@JacksonXmlRootElement(localName="item")
public class ItemDTO {
	
	@JacksonXmlProperty(localName="거래금액")
	private String deal_amount;
	
	@JacksonXmlProperty(localName="건축년도")
	private String build_year;
	
	@JacksonXmlProperty(localName="년")
	private String deal_year;
	
	@JacksonXmlProperty(localName="월")
	private String deal_month;
	
	@JacksonXmlProperty(localName="일")
	private String deal_day;
	
	@JacksonXmlProperty(localName="법정동")
	private String dong;
	
	@JacksonXmlProperty(localName="아파트")
	private String apartment_name;
	
	@JacksonXmlProperty(localName="전용면적")
	private double area_for_exclusive_use;
	
	@JacksonXmlProperty(localName="지번")
	private String jibun;
	
	@JacksonXmlProperty(localName="층")
	private int flr;
	
	@JacksonXmlProperty(localName="지역코드")
	private int location_cd;

	public String getDeal_amount() {
		return deal_amount;
	}

	public void setDeal_amount(String deal_amount) {
		this.deal_amount = deal_amount;
	}

	public String getBuild_year() {
		return build_year;
	}

	public void setBuild_year(String build_year) {
		this.build_year = build_year;
	}

	public String getDeal_year() {
		return deal_year;
	}

	public void setDeal_year(String deal_year) {
		this.deal_year = deal_year;
	}

	public String getDeal_month() {
		return deal_month;
	}

	public void setDeal_month(String deal_month) {
		this.deal_month = deal_month;
	}

	public String getDeal_day() {
		return deal_day;
	}

	public void setDeal_day(String deal_day) {
		this.deal_day = deal_day;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getApartment_name() {
		return apartment_name;
	}

	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}

	public double getArea_for_exclusive_use() {
		return area_for_exclusive_use;
	}

	public void setArea_for_exclusive_use(double area_for_exclusive_use) {
		this.area_for_exclusive_use = area_for_exclusive_use;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public int getFlr() {
		return flr;
	}

	public void setFlr(int flr) {
		this.flr = flr;
	}

	public int getLocation_cd() {
		return location_cd;
	}

	public void setLocation_cd(int location_cd) {
		this.location_cd = location_cd;
	}
}
