/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 区县综合政府服务指标Entity
 * @author gxq
 * @version 2018-10-22
 */
public class CatchServiceIndicators extends DataEntity<CatchServiceIndicators> {
	
	private static final long serialVersionUID = 1L;
	private String areaName;		// 区县
	private String fiber;		// 光纤到户渗透率
	private String broadband;		// 宽带家庭普及率
	private String hospital;		// 医院预约诊疗率
	private String security;		// 社保自助开通率
	private String uniform;		// 统一入口率
	private String processing;		// 一站式办理率
	
	public CatchServiceIndicators() {
		super();
	}

	public CatchServiceIndicators(String id){
		super(id);
	}

	@Length(min=0, max=100, message="区县长度必须介于 0 和 100 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getFiber() {
		return fiber;
	}

	public void setFiber(String fiber) {
		this.fiber = fiber;
	}
	
	public String getBroadband() {
		return broadband;
	}

	public void setBroadband(String broadband) {
		this.broadband = broadband;
	}
	
	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}
	
	public String getUniform() {
		return uniform;
	}

	public void setUniform(String uniform) {
		this.uniform = uniform;
	}
	
	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}
	
}