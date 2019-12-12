/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 敏感指数Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchSensitivityIndex extends DataEntity<CatchSensitivityIndex> {
	
	private static final long serialVersionUID = 1L;
	private String sensitiveIndex;		// 敏感指数
	private String nonSensitiveIndex;		// 非敏感指数
	
	public CatchSensitivityIndex() {
		super();
	}

	public CatchSensitivityIndex(String id){
		super(id);
	}

	@Length(min=0, max=10, message="敏感指数长度必须介于 0 和 10 之间")
	public String getSensitiveIndex() {
		return sensitiveIndex;
	}

	public void setSensitiveIndex(String sensitiveIndex) {
		this.sensitiveIndex = sensitiveIndex;
	}
	
	@Length(min=0, max=10, message="非敏感指数长度必须介于 0 和 10 之间")
	public String getNonSensitiveIndex() {
		return nonSensitiveIndex;
	}

	public void setNonSensitiveIndex(String nonSensitiveIndex) {
		this.nonSensitiveIndex = nonSensitiveIndex;
	}
	
}