/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 厕所声量Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristWcSound extends DataEntity<TouristWcSound> {
	
	private static final long serialVersionUID = 1L;
	private String month;		// 月份
	private String num;		// 声量
	private String year;		// 年
	
	public TouristWcSound() {
		super();
	}

	public TouristWcSound(String id){
		super(id);
	}

	@Length(min=0, max=255, message="月份长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=11, message="声量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}