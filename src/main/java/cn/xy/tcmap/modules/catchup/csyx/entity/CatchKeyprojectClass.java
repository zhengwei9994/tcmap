/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;



/**
 * 月进度Entity
 * @author xuzhou
 * @version 2018-05-02
 */
public class CatchKeyprojectClass extends DataEntity<CatchKeyprojectClass> {
	
	private static final long serialVersionUID = 1L;
	private CatchKeyproject keyprojectId;		// keyproject_id
	private String month;		// 月份
	private String amountCompleted;		// 已完成金额
	private String unit;		// 单位
	private String completionRatio;		// 已完成占比
	private String projectStatus;		// 完成状态
	
	public CatchKeyprojectClass() {
		super();
	}

	public CatchKeyprojectClass(String id){
		super(id);
	}


	public CatchKeyproject getKeyprojectId() {
		return keyprojectId;
	}

	public void setKeyprojectId(CatchKeyproject keyprojectId) {
		this.keyprojectId = keyprojectId;
	}
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=10, message="已完成金额长度必须介于 0 和 10 之间")
	public String getAmountCompleted() {
		return amountCompleted;
	}

	public void setAmountCompleted(String amountCompleted) {
		this.amountCompleted = amountCompleted;
	}
	
	@Length(min=0, max=1, message="单位长度必须介于 0 和 1 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getCompletionRatio() {
		return completionRatio;
	}

	public void setCompletionRatio(String completionRatio) {
		this.completionRatio = completionRatio;
	}
	
	@Length(min=0, max=10, message="完成状态长度必须介于 0 和 10 之间")
	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	
}