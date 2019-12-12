/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 办理类型Entity
 * @author wufan
 * @version 2019-10-14
 */
public class LiveGovermentType extends DataEntity<LiveGovermentType> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 办理类型
	private Integer value;		// 数值
	
	public LiveGovermentType() {
		super();
	}

	public LiveGovermentType(String id){
		super(id);
	}

	@Length(min=0, max=255, message="办理类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}