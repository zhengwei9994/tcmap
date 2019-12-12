/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 办理深度Entity
 * @author wufan
 * @version 2019-10-14
 */
public class LiveGovementDeep extends DataEntity<LiveGovementDeep> {
	
	private static final long serialVersionUID = 1L;
	private String deep;		// 深度
	private Integer value;		// 数值
	
	public LiveGovementDeep() {
		super();
	}

	public LiveGovementDeep(String id){
		super(id);
	}

	@Length(min=0, max=255, message="深度长度必须介于 0 和 255 之间")
	public String getDeep() {
		return deep;
	}

	public void setDeep(String deep) {
		this.deep = deep;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}