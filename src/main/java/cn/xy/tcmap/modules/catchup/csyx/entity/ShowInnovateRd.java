/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济rd变化Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowInnovateRd extends DataEntity<ShowInnovateRd> {
	
	private static final long serialVersionUID = 1L;
	private String rdnum;		// RD值
	private String month;		// 月
	private String year;		// 年
	
	public ShowInnovateRd() {
		super();
	}

	public ShowInnovateRd(String id){
		super(id);
	}

	@Length(min=0, max=11, message="RD值长度必须介于 0 和 11 之间")
	public String getRdnum() {
		return rdnum;
	}

	public void setRdnum(String rdnum) {
		this.rdnum = rdnum;
	}
	
	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}