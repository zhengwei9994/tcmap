/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 特色项目Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchSpecialProject extends DataEntity<CatchSpecialProject> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String projectname;		// 项目名称
	private String details;		// 明细
	private String sort;		// 图片地址
	
	public CatchSpecialProject() {
		super();
	}

	public CatchSpecialProject(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=50, message="项目名称长度必须介于 1 和 50 之间")
	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	@Length(min=1, max=50, message="明细长度必须介于 1 和 50 之间")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}