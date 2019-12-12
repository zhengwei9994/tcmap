/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 停留时长分布Entity
 * @author wl
 * @version 2018-09-29
 */
public class CatchStayTime extends DataEntity<CatchStayTime> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String stayPlace;		// 地点
	private String stayTime;		// 停留时长
	private String stayCount;		// 停留人数
	
	public CatchStayTime() {
		super();
	}

	public CatchStayTime(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=255, message="地点长度必须介于 0 和 255 之间")
	public String getStayPlace() {
		return stayPlace;
	}

	public void setStayPlace(String stayPlace) {
		this.stayPlace = stayPlace;
	}
	
	@Length(min=0, max=255, message="停留时长长度必须介于 0 和 255 之间")
	public String getStayTime() {
		return stayTime;
	}

	public void setStayTime(String stayTime) {
		this.stayTime = stayTime;
	}
	
	@Length(min=0, max=255, message="停留人数长度必须介于 0 和 255 之间")
	public String getStayCount() {
		return stayCount;
	}

	public void setStayCount(String stayCount) {
		this.stayCount = stayCount;
	}
	
}