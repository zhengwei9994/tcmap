/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 行政审批事项统计Entity
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchApprovalMatters extends DataEntity<CatchApprovalMatters> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String department;		// 部门
	private Integer mattersCount;		// 审批事项数目
	
	public CatchApprovalMatters() {
		super();
	}

	public CatchApprovalMatters(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=20, message="部门长度必须介于 1 和 20 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@NotNull(message="审批事项数目不能为空")
	public Integer getMattersCount() {
		return mattersCount;
	}

	public void setMattersCount(Integer mattersCount) {
		this.mattersCount = mattersCount;
	}
	
}