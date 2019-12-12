/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 营商高等院校数量Entity
 * @author wufan
 * @version 2019-07-31
 */
public class BusinessHighschool extends DataEntity<BusinessHighschool> {
	
	private static final long serialVersionUID = 1L;
	private String mechanism;		// 机构
	private String num;		// 数量
	private String year;		// 时间
	private String month;//月份
	
	public BusinessHighschool() {
		super();
	}

	public BusinessHighschool(String id){
		super(id);
	}

	@Length(min=0, max=255, message="机构长度必须介于 0 和 255 之间")
	public String getMechanism() {
		return mechanism;
	}

	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=255, message="时间长度必须介于 0 和 255 之间")
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