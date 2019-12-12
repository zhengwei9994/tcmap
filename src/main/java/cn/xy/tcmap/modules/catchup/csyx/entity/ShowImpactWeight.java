/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.springframework.format.annotation.NumberFormat;

/**
 * 影响权重Entity
 * @author tuo
 * @version 2019-08-22
 */
	public class ShowImpactWeight extends DataEntity<ShowImpactWeight> {
	
	private static final long serialVersionUID = 1L;
	private String industry;		// 行业
	private String populationRate;		// 人口老龄化权重
	private String gdpRate;		// GDP影响比重
	private String year;
	
	public ShowImpactWeight() {
		super();
	}

	public ShowImpactWeight(String id){
		super(id);
	}

	@Length(min=0, max=50, message="行业长度必须介于 0 和 50 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Length(min=0, max=50, message="年必须介于 0 和 50 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@NumberFormat
	public String getPopulationRate() {
		return populationRate;
	}

	public void setPopulationRate(String populationRate) {
		this.populationRate = populationRate;
	}
	
	public String getGdpRate() {
		return gdpRate;
	}

	public void setGdpRate(String gdpRate) {
		this.gdpRate = gdpRate;
	}
	
}