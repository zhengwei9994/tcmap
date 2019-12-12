/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 营商环境变量Entity
 * @author wufan
 * @version 2019-07-31
 */
public class BusinessEnvironmental extends DataEntity<BusinessEnvironmental> {
	
	private static final long serialVersionUID = 1L;
	private String index;		// 指数
	private String num;		// 数值
	private String max;		// 最大值
	private String year;		// 年
	private String month; //月
	
	public BusinessEnvironmental() {
		super();
	}

	public BusinessEnvironmental(String id){
		super(id);
	}

	@Length(min=0, max=255, message="指数长度必须介于 0 和 255 之间")
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
	@Length(min=0, max=11, message="数值长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=255, message="最大值长度必须介于 0 和 255 之间")
	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
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