/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济指标名称Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchIndexName extends DataEntity<CatchIndexName> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 指标名称
	private String nameEn;      //指标英文缩写
	public CatchIndexName() {
		super();
	}

	public CatchIndexName(String id){
		super(id);
	}

	@Length(min=1, max=30, message="指标名称长度必须介于 1 和 30 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	
}