package com.koreait.apart;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="body")
public class BodyDTO {
	@JacksonXmlProperty(localName="items")
	private List<ItemDTO> items;

	@JacksonXmlProperty(localName="numberOfRaws")
	private int numberOfRaws;
	
	@JacksonXmlProperty(localName="pageNo")
	private int pageNo;

	@JacksonXmlProperty(localName="totalCount")
	private int totalCount;

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}

	public int getNumberOfRaws() {
		return numberOfRaws;
	}

	public void setNumberOfRaws(int numberOfRaws) {
		this.numberOfRaws = numberOfRaws;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
