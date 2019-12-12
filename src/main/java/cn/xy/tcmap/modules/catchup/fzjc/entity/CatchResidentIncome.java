/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 居民收入Entity
 * @author FSH
 * @version 2018-10-18
 */
public class CatchResidentIncome extends DataEntity<CatchResidentIncome> {
	
	private static final long serialVersionUID = 1L;
	private String residentClusters;		// 居民类型
	private String wageIncome;		// 工资收入
	private String operatingIncome;		// 经营净收入
	private String ownershipIncome;		// 财产净收入
	private String transferIncome;		// 转移净收入
	private String rate;		// 比率
	
	public CatchResidentIncome() {
		super();
	}

	public CatchResidentIncome(String id){
		super(id);
	}

	@Length(min=1, max=255, message="居民类型长度必须介于 1 和 255 之间")
	public String getResidentClusters() {
		return residentClusters;
	}

	public void setResidentClusters(String residentClusters) {
		this.residentClusters = residentClusters;
	}
	
	@Length(min=0, max=255, message="工资收入长度必须介于 0 和 255 之间")
	public String getWageIncome() {
		return wageIncome;
	}

	public void setWageIncome(String wageIncome) {
		this.wageIncome = wageIncome;
	}
	
	@Length(min=0, max=255, message="经营净收入长度必须介于 0 和 255 之间")
	public String getOperatingIncome() {
		return operatingIncome;
	}

	public void setOperatingIncome(String operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
	
	@Length(min=0, max=255, message="财产净收入长度必须介于 0 和 255 之间")
	public String getOwnershipIncome() {
		return ownershipIncome;
	}

	public void setOwnershipIncome(String ownershipIncome) {
		this.ownershipIncome = ownershipIncome;
	}
	
	@Length(min=0, max=255, message="转移净收入长度必须介于 0 和 255 之间")
	public String getTransferIncome() {
		return transferIncome;
	}

	public void setTransferIncome(String transferIncome) {
		this.transferIncome = transferIncome;
	}
	
	@Length(min=0, max=255, message="比率长度必须介于 0 和 255 之间")
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
}