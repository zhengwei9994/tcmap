/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 电子证照类别管理Entity
 * @author gxq
 * @version 2018-10-19
 */
public class CatchElectronicCategory extends DataEntity<CatchElectronicCategory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 服务类别及行业名称
	private String value;		// 使用次数
	private String type;		// 1：服务类型 2：行业
	
	public CatchElectronicCategory() {
		super();
	}

	public CatchElectronicCategory(String id){
		super(id);
	}

	@Length(min=0, max=100, message="服务类别及行业名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="使用次数长度必须介于 0 和 11 之间")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Length(min=0, max=11, message="1：服务类型 2：行业长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}