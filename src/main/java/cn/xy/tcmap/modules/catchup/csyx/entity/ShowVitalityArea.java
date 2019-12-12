/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济活力地图Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowVitalityArea extends DataEntity<ShowVitalityArea> {
	
	private static final long serialVersionUID = 1L;
	private String area;		// 区域
	private String year;		// 年
	private String vitality;		// 活力
	private String month;
	
	public ShowVitalityArea() {
		super();
	}

	public ShowVitalityArea(String id){
		super(id);
	}

	@Length(min=0, max=255, message="区域长度必须介于 0 和 255 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="活力长度必须介于 0 和 255 之间")
	public String getVitality() {
		return vitality;
	}

	public void setVitality(String vitality) {
		this.vitality = vitality;
	}

	@Length(min=0, max=255, message="月必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}