/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 劳动就业检索Entity
 * @author gxq
 * @version 2018-10-18
 */
public class CatchLaborEmployment extends DataEntity<CatchLaborEmployment> {
	
	private static final long serialVersionUID = 1L;
	private String kind;		// 产业类型
	private String number;		// 人数
	private String proportion;		// 占比
	private String imagepath;		// 头像
	
	public CatchLaborEmployment() {
		super();
	}

	public CatchLaborEmployment(String id){
		super(id);
	}

	@Length(min=0, max=50, message="产业类型长度必须介于 0 和 50 之间")
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Length(min=0, max=11, message="人数长度必须介于 0 和 11 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
	@Length(min=0, max=200, message="头像长度必须介于 0 和 200 之间")
	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
}