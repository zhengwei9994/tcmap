/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 变化率Entity
 * @author 变化率
 * @version 2019-08-22
 */
public class ShowChangeRate extends DataEntity<ShowChangeRate> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年份
	private String gdpGrowth;		// GDP增长率
	private String investmentGrowthRate;		// 投资增长率
	private String saveRate;		// 储蓄率
	private String consumptionExpenditureRate;		// 消费支出增加率
	
	public ShowChangeRate() {
		super();
	}

	public ShowChangeRate(String id){
		super(id);
	}

	@Length(min=0, max=10, message="年份长度必须介于 0 和 10 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Length(min=0, max=6, message="GDP增长率长度必须介于 0 和 6 之间")
	public String getGdpGrowth() {
		return gdpGrowth;
	}

	public void setGdpGrowth(String gdpGrowth) {
		this.gdpGrowth = gdpGrowth;
	}

	@Length(min=0, max=6, message="投资增长率长度必须介于 0 和 6 之间")
	public String getInvestmentGrowthRate() {
		return investmentGrowthRate;
	}

	public void setInvestmentGrowthRate(String investmentGrowthRate) {
		this.investmentGrowthRate = investmentGrowthRate;
	}

	@Length(min=0, max=6, message="储蓄率长度必须介于 0 和 10 之间")
	public String getSaveRate() {
		return saveRate;
	}

	public void setSaveRate(String saveRate) {
		this.saveRate = saveRate;
	}

	@Length(min=0, max=6, message="消费支出增加率长度必须介于 0 和 6之间")
	public String getConsumptionExpenditureRate() {
		return consumptionExpenditureRate;
	}

	public void setConsumptionExpenditureRate(String consumptionExpenditureRate) {
		this.consumptionExpenditureRate = consumptionExpenditureRate;
	}
	
}