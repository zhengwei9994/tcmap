/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 废气排放指标来源构成分析Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchGasResource extends DataEntity<CatchGasResource> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 废气来源类型
	private String powderValue;		// 烟（粉）尘
	private String oxynitrideValue;		// 氮氧化物
	private String coValue;		// 二氧化碳
	
	public CatchGasResource() {
		super();
	}

	public CatchGasResource(String id){
		super(id);
	}

	@Length(min=0, max=10, message="废气来源类型长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=10, message="烟（粉）尘长度必须介于 0 和 10 之间")
	public String getPowderValue() {
		return powderValue;
	}

	public void setPowderValue(String powderValue) {
		this.powderValue = powderValue;
	}
	
	@Length(min=0, max=10, message="氮氧化物长度必须介于 0 和 10 之间")
	public String getOxynitrideValue() {
		return oxynitrideValue;
	}

	public void setOxynitrideValue(String oxynitrideValue) {
		this.oxynitrideValue = oxynitrideValue;
	}
	
	@Length(min=0, max=10, message="二氧化碳长度必须介于 0 和 10 之间")
	public String getCoValue() {
		return coValue;
	}

	public void setCoValue(String coValue) {
		this.coValue = coValue;
	}
	
}