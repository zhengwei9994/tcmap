/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 污水处置能力趋势分析Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchWaterTrade extends DataEntity<CatchWaterTrade> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String processMax;		// 处理量
	private String speedMax;		// 增速
	
	public CatchWaterTrade() {
		super();
	}

	public CatchWaterTrade(String id){
		super(id);
	}

	@Length(min=0, max=10, message="年份长度必须介于 0 和 10 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=11, message="处理量长度必须介于 0 和 11 之间")
	public String getProcessMax() {
		return processMax;
	}

	public void setProcessMax(String processMax) {
		this.processMax = processMax;
	}
	
	@Length(min=0, max=11, message="增速长度必须介于 0 和 11 之间")
	public String getSpeedMax() {
		return speedMax;
	}

	public void setSpeedMax(String speedMax) {
		this.speedMax = speedMax;
	}
	
}