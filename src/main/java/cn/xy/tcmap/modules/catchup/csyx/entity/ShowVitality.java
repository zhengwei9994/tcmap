/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济活力指数Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowVitality extends DataEntity<ShowVitality> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年
	private String vitality;		// 活力值
	private String month;         //月



	public ShowVitality() {
		super();
	}

	public ShowVitality(String id){
		super(id);
	}

	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="活力值长度必须介于 0 和 255 之间")
	public String getVitality() {
		return vitality;
	}

	public void setVitality(String vitality) {
		this.vitality = vitality;
	}

	@Length(min=0, max=20, message="月长度必须介于 0 和 20 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}