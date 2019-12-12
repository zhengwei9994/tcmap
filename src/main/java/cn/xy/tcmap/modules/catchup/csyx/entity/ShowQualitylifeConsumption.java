/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济消费状况Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowQualitylifeConsumption extends DataEntity<ShowQualitylifeConsumption> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年
	private String month;		// 月
	private String consumption;		// 消费状况
	
	public ShowQualitylifeConsumption() {
		super();
	}

	public ShowQualitylifeConsumption(String id){
		super(id);
	}

	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=255, message="消费状况长度必须介于 0 和 255 之间")
	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	
}