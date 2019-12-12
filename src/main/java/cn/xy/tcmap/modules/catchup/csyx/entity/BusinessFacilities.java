/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 营商设施完成度Entity
 * @author wufan
 * @version 2019-07-31
 */
public class BusinessFacilities extends DataEntity<BusinessFacilities> {
	
	private static final long serialVersionUID = 1L;
	private String item;		// 设施
	private String completion;		// 完成度
	private String year;		// 年
	private String month;//月
	
	public BusinessFacilities() {
		super();
	}

	public BusinessFacilities(String id){
		super(id);
	}

	@Length(min=0, max=255, message="设施长度必须介于 0 和 255 之间")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	@Length(min=0, max=255, message="完成度长度必须介于 0 和 255 之间")
	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
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
}