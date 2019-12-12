/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 人口总量Entity
 * @author tuo
 * @version 2019-08-22
 */
public class ShowTotalPopulation extends DataEntity<ShowTotalPopulation> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年
	private String totalNum;		// 总量
	private String oldNum;		// 老年人口数量
	private String laborPercent;		// 劳动人口占比
	private String raisePercent;		// 总人口抚养比
	
	public ShowTotalPopulation() {
		super();
	}

	public ShowTotalPopulation(String id){
		super(id);
	}

	@Length(min=0, max=10, message="年长度必须介于 0 和 10 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	
	public String getOldNum() {
		return oldNum;
	}

	public void setOldNum(String oldNum) {
		this.oldNum = oldNum;
	}
	
	public String getLaborPercent() {
		return laborPercent;
	}

	public void setLaborPercent(String laborPercent) {
		this.laborPercent = laborPercent;
	}
	
	public String getRaisePercent() {
		return raisePercent;
	}

	public void setRaisePercent(String raisePercent) {
		this.raisePercent = raisePercent;
	}
	
}