/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 营商义务教育Entity
 * @author wufan
 * @version 2019-07-31
 */
public class BusinessEducation extends DataEntity<BusinessEducation> {
	
	private static final long serialVersionUID = 1L;
	private String school;		// 学校
	private String num;		// 数量
	private String year;		// 年
	
	public BusinessEducation() {
		super();
	}

	public BusinessEducation(String id){
		super(id);
	}

	@Length(min=0, max=255, message="学校长度必须介于 0 和 255 之间")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
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