/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 按月流客量Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristVolumeMonth extends DataEntity<TouristVolumeMonth> {
	
	private static final long serialVersionUID = 1L;
	private String month;		// 月份
	private String year;		// year
	private String num;		// num
	
	public TouristVolumeMonth() {
		super();
	}

	public TouristVolumeMonth(String id){
		super(id);
	}

	@Length(min=0, max=255, message="月份长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=255, message="year长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="num长度必须介于 0 和 255 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}