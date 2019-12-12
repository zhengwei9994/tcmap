/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 媒体活跃度Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchMediaHyd extends DataEntity<CatchMediaHyd> {
	
	private static final long serialVersionUID = 1L;
	private String legend;		// 媒体
	private String number;		// 活跃度
	
	public CatchMediaHyd() {
		super();
	}

	public CatchMediaHyd(String id){
		super(id);
	}

	@Length(min=0, max=50, message="媒体长度必须介于 0 和 50 之间")
	public String getLegend() {
		return legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}
	
	@Length(min=0, max=10, message="活跃度长度必须介于 0 和 10 之间")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}