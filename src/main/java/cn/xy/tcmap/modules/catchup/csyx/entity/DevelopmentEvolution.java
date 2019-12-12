/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 产业演进Entity
 * @author wufan
 * @version 2019-07-31
 */
public class DevelopmentEvolution extends DataEntity<DevelopmentEvolution> {
	
	private static final long serialVersionUID = 1L;
	private String industry;		// 行业
	private String industryNum;		// 行业数量
	private String industryMoney;		// 行业金额
	private String year;		// 年
	
	public DevelopmentEvolution() {
		super();
	}

	public DevelopmentEvolution(String id){
		super(id);
	}

	@Length(min=0, max=255, message="行业长度必须介于 0 和 255 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Length(min=0, max=255, message="行业数量长度必须介于 0 和 255 之间")
	public String getIndustryNum() {
		return industryNum;
	}

	public void setIndustryNum(String industryNum) {
		this.industryNum = industryNum;
	}
	
	@Length(min=0, max=255, message="行业金额长度必须介于 0 和 255 之间")
	public String getIndustryMoney() {
		return industryMoney;
	}

	public void setIndustryMoney(String industryMoney) {
		this.industryMoney = industryMoney;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}