/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 行业主管部门事项分布Entity
 * @author wufan
 * @version 2019-10-14
 */
public class LiveGovementDepartment extends DataEntity<LiveGovementDepartment> {
	
	private static final long serialVersionUID = 1L;
	private String department;		// 部门
	private Integer num;		// 数量
	
	public LiveGovementDepartment() {
		super();
	}

	public LiveGovementDepartment(String id){
		super(id);
	}

	@Length(min=0, max=255, message="部门长度必须介于 0 和 255 之间")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}