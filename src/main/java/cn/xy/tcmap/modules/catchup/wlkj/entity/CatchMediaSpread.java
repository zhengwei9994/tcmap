/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 媒体分布Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchMediaSpread extends DataEntity<CatchMediaSpread> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// nyear
	private String projectType;		// 媒体类型
	private String totalInvestment;		// 占比
	
	public CatchMediaSpread() {
		super();
	}

	public CatchMediaSpread(String id){
		super(id);
	}

	@Length(min=0, max=10, message="nyear长度必须介于 0 和 10 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=50, message="媒体类型长度必须介于 0 和 50 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Length(min=0, max=10, message="占比长度必须介于 0 和 10 之间")
	public String getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(String totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	
}