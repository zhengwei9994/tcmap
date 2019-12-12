/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 旅游趋势分析Entity
 * @author wl
 * @version 2018-09-28
 */
public class CatchTourCountAnalysis extends DataEntity<CatchTourCountAnalysis> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String tourCount;		// 旅游人数
	private String tourDay;		// 日期
	
	public CatchTourCountAnalysis() {
		super();
	}

	public CatchTourCountAnalysis(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=255, message="旅游人数长度必须介于 0 和 255 之间")
	public String getTourCount() {
		return tourCount;
	}

	public void setTourCount(String tourCount) {
		this.tourCount = tourCount;
	}
	
	@Length(min=0, max=255, message="日期长度必须介于 0 和 255 之间")
	public String getTourDay() {
		return tourDay;
	}

	public void setTourDay(String tourDay) {
		this.tourDay = tourDay;
	}
	
}