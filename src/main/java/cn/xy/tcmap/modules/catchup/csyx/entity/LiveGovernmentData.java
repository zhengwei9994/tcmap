/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 政务服务Entity
 * @author wufan
 * @version 2019-10-14
 */
public class LiveGovernmentData extends DataEntity<LiveGovernmentData> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private Integer value;		// 数据
	
	public LiveGovernmentData() {
		super();
	}

	public LiveGovernmentData(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}