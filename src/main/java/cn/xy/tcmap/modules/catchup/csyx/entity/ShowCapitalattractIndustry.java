/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济三产占比Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowCapitalattractIndustry extends DataEntity<ShowCapitalattractIndustry> {
	
	private static final long serialVersionUID = 1L;
	private String industry;		// 产业
	private String proportion;		// 数值
	private String year;		// 年
	private String month;       // 月


	public ShowCapitalattractIndustry() {
		super();
	}

	public ShowCapitalattractIndustry(String id){
		super(id);
	}

	@Length(min=0, max=255, message="产业长度必须介于 0 和 255 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Length(min=0, max=255, message="数值长度必须介于 0 和 255 之间")
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
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