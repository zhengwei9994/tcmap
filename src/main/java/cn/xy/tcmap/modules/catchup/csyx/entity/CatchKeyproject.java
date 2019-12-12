/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;



/**
 * 重点项目Entity
 * @author xuzhou
 * @version 2018-05-02
 */
public class CatchKeyproject extends DataEntity<CatchKeyproject> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String entryName;		// 项目名称
	private String projectType;		// 项目类型
	private String totalInvestment;		// 总投资
	private String unit;		// 单位
	
	public CatchKeyproject() {
		super();
	}

	public CatchKeyproject(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=100, message="项目名称长度必须介于 0 和 100 之间")
	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	
	@Length(min=0, max=10, message="项目类型长度必须介于 0 和 10 之间")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	@Length(min=0, max=10, message="总投资长度必须介于 0 和 10 之间")
	public String getTotalInvestment() {
		return totalInvestment;
	}

	public void setTotalInvestment(String totalInvestment) {
		this.totalInvestment = totalInvestment;
	}
	
	@Length(min=0, max=1, message="单位长度必须介于 0 和 1 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}