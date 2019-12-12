/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 劳动就业占比Entity
 * @author gxq
 * @version 2018-10-18
 */
public class CatchPractitioners extends DataEntity<CatchPractitioners> {
	
	private static final long serialVersionUID = 1L;
	private String address;		// 地址
	private String proportion;		// 从业人员占比
	
	public CatchPractitioners() {
		super();
	}

	public CatchPractitioners(String id){
		super(id);
	}

	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
}