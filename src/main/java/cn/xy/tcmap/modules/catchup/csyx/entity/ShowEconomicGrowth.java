/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济成长性Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowEconomicGrowth extends DataEntity<ShowEconomicGrowth> {
	
	private static final long serialVersionUID = 1L;
	private String population;		// 常住人口
	private String populationIncrease;		// 常住人口增长率
	private String capita;		// 人均gdp
	private String capitaIncrease;		// 人均gdp增长率
	private String revenue;		// 财政收入
	private String revenueIncrease;		// 财政收入增长率
	private String year;		// 年
	private String month;       // 月

	public ShowEconomicGrowth() {
		super();
	}

	public ShowEconomicGrowth(String id){
		super(id);
	}

	@Length(min=0, max=255, message="常住人口长度必须介于 0 和 255 之间")
	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
	
	@Length(min=0, max=255, message="常住人口增长率长度必须介于 0 和 255 之间")
	public String getPopulationIncrease() {
		return populationIncrease;
	}

	public void setPopulationIncrease(String populationIncrease) {
		this.populationIncrease = populationIncrease;
	}
	
	@Length(min=0, max=255, message="人均gdp长度必须介于 0 和 255 之间")
	public String getCapita() {
		return capita;
	}

	public void setCapita(String capita) {
		this.capita = capita;
	}
	
	@Length(min=0, max=255, message="人均gdp增长率长度必须介于 0 和 255 之间")
	public String getCapitaIncrease() {
		return capitaIncrease;
	}

	public void setCapitaIncrease(String capitaIncrease) {
		this.capitaIncrease = capitaIncrease;
	}
	
	@Length(min=1, max=255, message="财政收入长度必须介于 1 和 255 之间")
	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	
	@Length(min=0, max=255, message="财政收入增长率长度必须介于 0 和 255 之间")
	public String getRevenueIncrease() {
		return revenueIncrease;
	}

	public void setRevenueIncrease(String revenueIncrease) {
		this.revenueIncrease = revenueIncrease;
	}
	
	@Length(min=1, max=255, message="年长度必须介于 1 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Length(min=1, max=255, message="月长度必须介于 1 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}