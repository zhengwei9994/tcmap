/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济资本吸引力Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowCapitalattract extends DataEntity<ShowCapitalattract> {
	
	private static final long serialVersionUID = 1L;
	private String practitioners;		// 从业人员
	private String education;		// 教育支出
	private String educationProportion;		// 教育支出比重
	private String foreign;		// 利用外资
	private String investment;		// 固定投资
	private String year;		// 年
	private String month;       //月

	public ShowCapitalattract() {
		super();
	}

	public ShowCapitalattract(String id){
		super(id);
	}

	@Length(min=0, max=255, message="从业人员长度必须介于 0 和 255 之间")
	public String getPractitioners() {
		return practitioners;
	}

	public void setPractitioners(String practitioners) {
		this.practitioners = practitioners;
	}
	
	@Length(min=0, max=255, message="教育支出长度必须介于 0 和 255 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=255, message="教育支出比重长度必须介于 0 和 255 之间")
	public String getEducationProportion() {
		return educationProportion;
	}

	public void setEducationProportion(String educationProportion) {
		this.educationProportion = educationProportion;
	}
	
	@Length(min=0, max=255, message="利用外资长度必须介于 0 和 255 之间")
	public String getForeign() {
		return foreign;
	}

	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	
	@Length(min=0, max=255, message="固定投资长度必须介于 0 和 255 之间")
	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
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