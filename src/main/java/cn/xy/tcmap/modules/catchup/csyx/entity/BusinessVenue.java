/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 营商文化场馆Entity
 * @author wufan
 * @version 2019-07-31
 */
public class BusinessVenue extends DataEntity<BusinessVenue> {
	
	private static final long serialVersionUID = 1L;
	private String venue;		// 场馆
	private String num;		// 数量
	private String year;		// 年
	private String month;//月
	
	public BusinessVenue() {
		super();
	}

	public BusinessVenue(String id){
		super(id);
	}

	@Length(min=0, max=255, message="场馆长度必须介于 0 和 255 之间")
	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}