/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 企业结构基础数据分析Entity
 * @author wl
 * @version 2018-09-29
 */
public class CacheEnterpriseBasicData extends DataEntity<CacheEnterpriseBasicData> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String enterpriseType;		// 企业类型
	private String enterpriseCount;		// 企业数量
	private String enterpriseMoney;		// 营业额
	private String personCount;		// 人数
	
	public CacheEnterpriseBasicData() {
		super();
	}

	public CacheEnterpriseBasicData(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=255, message="企业类型长度必须介于 0 和 255 之间")
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	
	@Length(min=0, max=255, message="企业数量长度必须介于 0 和 255 之间")
	public String getEnterpriseCount() {
		return enterpriseCount;
	}

	public void setEnterpriseCount(String enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}
	
	@Length(min=0, max=255, message="营业额长度必须介于 0 和 255 之间")
	public String getEnterpriseMoney() {
		return enterpriseMoney;
	}

	public void setEnterpriseMoney(String enterpriseMoney) {
		this.enterpriseMoney = enterpriseMoney;
	}
	
	@Length(min=0, max=255, message="人数长度必须介于 0 和 255 之间")
	public String getPersonCount() {
		return personCount;
	}

	public void setPersonCount(String personCount) {
		this.personCount = personCount;
	}
	
}