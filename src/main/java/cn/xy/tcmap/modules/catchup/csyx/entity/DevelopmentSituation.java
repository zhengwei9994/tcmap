/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 产业现状Entity
 * @author wufan
 * @version 2019-07-31
 */
public class DevelopmentSituation extends DataEntity<DevelopmentSituation> {
	
	private static final long serialVersionUID = 1L;
	private String industry;		// 行业
	private String households;		// 户数
	private String capital;		// 资本
	private String size;		// 大小
	private String year;		// 年
	private String month;//月
	
	public DevelopmentSituation() {
		super();
	}

	public DevelopmentSituation(String id){
		super(id);
	}

	@Length(min=0, max=255, message="行业长度必须介于 0 和 255 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Length(min=0, max=255, message="户数长度必须介于 0 和 255 之间")
	public String getHouseholds() {
		return households;
	}

	public void setHouseholds(String households) {
		this.households = households;
	}
	
	@Length(min=0, max=255, message="资本长度必须介于 0 和 255 之间")
	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}