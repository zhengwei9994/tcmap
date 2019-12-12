/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 污水排放量趋势分析Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchWasteWater extends DataEntity<CatchWasteWater> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// nyear
	private String industryValue;		// 工业氨氮
	private String cityValue;		// 城镇生活氨氮
	private String farmValue;		// 农业氨氮
	private String ammoniaValue;		// 集中式氨氮
	
	public CatchWasteWater() {
		super();
	}

	public CatchWasteWater(String id){
		super(id);
	}

	@Length(min=0, max=10, message="nyear长度必须介于 0 和 10 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=50, message="工业氨氮长度必须介于 0 和 50 之间")
	public String getIndustryValue() {
		return industryValue;
	}

	public void setIndustryValue(String industryValue) {
		this.industryValue = industryValue;
	}
	
	@Length(min=0, max=10, message="城镇生活氨氮长度必须介于 0 和 10 之间")
	public String getCityValue() {
		return cityValue;
	}

	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}
	
	@Length(min=0, max=10, message="农业氨氮长度必须介于 0 和 10 之间")
	public String getFarmValue() {
		return farmValue;
	}

	public void setFarmValue(String farmValue) {
		this.farmValue = farmValue;
	}
	
	@Length(min=0, max=10, message="集中式氨氮长度必须介于 0 和 10 之间")
	public String getAmmoniaValue() {
		return ammoniaValue;
	}

	public void setAmmoniaValue(String ammoniaValue) {
		this.ammoniaValue = ammoniaValue;
	}
	
}