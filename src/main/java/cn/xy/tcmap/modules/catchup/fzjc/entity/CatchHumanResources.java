/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 人力资源统计Entity
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchHumanResources extends DataEntity<CatchHumanResources> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String companyType;		// 单位类型
	private Double numberPeople;		// 从业人数（万）
	
	public CatchHumanResources() {
		super();
	}

	public CatchHumanResources(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="单位类型长度必须介于 1 和 255 之间")
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	@NotNull(message="从业人数（万）不能为空")
	public Double getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(Double numberPeople) {
		this.numberPeople = numberPeople;
	}
	
}