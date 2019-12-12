/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 万元GDP能耗降低率Entity
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchEnergyConsumption extends DataEntity<CatchEnergyConsumption> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年份
	private String quarter;		// 季度
	private Double energyConsumption;		// 单位GDP能耗
	private Double energyConsumptionRate;		// GDP能耗增速
	
	public CatchEnergyConsumption() {
		super();
	}

	public CatchEnergyConsumption(String id){
		super(id);
	}

	@Length(min=1, max=20, message="年份长度必须介于 1 和 20 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=1, max=10, message="季度长度必须介于 1 和 10 之间")
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	@NotNull(message="单位GDP能耗不能为空")
	public Double getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(Double energyConsumption) {
		this.energyConsumption = energyConsumption;
	}
	
	@NotNull(message="GDP能耗增速不能为空")
	public Double getEnergyConsumptionRate() {
		return energyConsumptionRate;
	}

	public void setEnergyConsumptionRate(Double energyConsumptionRate) {
		this.energyConsumptionRate = energyConsumptionRate;
	}

}