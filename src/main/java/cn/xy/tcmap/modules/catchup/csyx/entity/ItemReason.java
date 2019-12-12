/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 项目问题原因Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ItemReason extends DataEntity<ItemReason> {
	
	private static final long serialVersionUID = 1L;
	private String reason;		// 原因
	private String num;		// 数量
	private String year;		// 年
	
	public ItemReason() {
		super();
	}

	public ItemReason(String id){
		super(id);
	}

	@Length(min=0, max=255, message="原因长度必须介于 0 和 255 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
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