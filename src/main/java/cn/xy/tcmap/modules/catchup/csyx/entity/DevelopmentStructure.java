/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import com.drew.lang.annotations.NotNull;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 产业结构Entity
 * @author wufan
 * @version 2019-07-31
 */
public class DevelopmentStructure extends DataEntity<DevelopmentStructure> {
	
	private static final long serialVersionUID = 1L;
	private String industry;		// 行业
	private Integer first;		// 第一产业
	private Integer second;		// 第二产业
	private Integer third;		// 第三产业
	private String year;		// 年
	private String month;      //月
	
	public DevelopmentStructure() {
		super();
	}

	public DevelopmentStructure(String id){
		super(id);
	}

	@Length(min=0, max=255, message="行业长度必须介于 0 和 255 之间")
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@NotNull
	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	@NotNull
	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	@NotNull
	public Integer getThird() {
		return third;
	}

	public void setThird(Integer third) {
		this.third = third;
	}

	@NotNull
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Length(min=0, max=255, message="行业长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}