/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济创新能力Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowInnovate extends DataEntity<ShowInnovate> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 专利数量
	private String year;		// 年
	private String proportion;		// 科学技术占比
	private String month;       //月
	
	public ShowInnovate() {
		super();
	}

	public ShowInnovate(String id){
		super(id);
	}

	@Length(min=0, max=11, message="专利数量长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=255, message="科学技术占比长度必须介于 0 和 255 之间")
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}