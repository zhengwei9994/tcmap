/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济财政收入Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowEconomicIncome extends DataEntity<ShowEconomicIncome> {
	
	private static final long serialVersionUID = 1L;
	private String income;		// 财政收入
	private String year;		// 年
	private String month;		// 月
	
	public ShowEconomicIncome() {
		super();
	}

	public ShowEconomicIncome(String id){
		super(id);
	}

	@Length(min=0, max=255, message="财政收入长度必须介于 0 和 255 之间")
	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
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