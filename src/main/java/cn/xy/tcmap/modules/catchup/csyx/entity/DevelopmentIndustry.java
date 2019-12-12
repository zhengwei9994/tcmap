/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 产业发展Entity
 * @author wufan
 * @version 2019-07-31
 */
public class DevelopmentIndustry extends DataEntity<DevelopmentIndustry> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 种类
	private String industry;		// 产业
	private String num;		// 数值
	private String year;		// 年
	
	public DevelopmentIndustry() {
		super();
	}

	public DevelopmentIndustry(String id){
		super(id);
	}

	@Length(min=0, max=255, message="种类长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="产业长度必须介于 0 和 255 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Length(min=0, max=11, message="数值长度必须介于 0 和 11 之间")
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
	
}