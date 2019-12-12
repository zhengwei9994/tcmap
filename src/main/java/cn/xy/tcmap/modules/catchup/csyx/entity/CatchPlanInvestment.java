/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;


/**
 * 年计划总投资Entity
 * @author xuzhou
 * @version 2018-05-02
 */
public class CatchPlanInvestment extends DataEntity<CatchPlanInvestment> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String plannedInvestment;		// 计划投资
	private String totalInvestment;		// 累计完成投资
	private String unit;		// 单位
	
	public CatchPlanInvestment() {
		super();
	}

	public CatchPlanInvestment(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=10, message="计划投资长度必须介于 0 和 10 之间")
	public String getPlannedInvestment() {
		return plannedInvestment;
	}

	public void setPlannedInvestment(String plannedInvestment) {
		this.plannedInvestment = plannedInvestment;
	}
	
	@Length(min=0, max=10, message="累计完成投资长度必须介于 0 和 10 之间")
	public String getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(String totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	
	@Length(min=0, max=1, message="单位长度必须介于 0 和 1 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}