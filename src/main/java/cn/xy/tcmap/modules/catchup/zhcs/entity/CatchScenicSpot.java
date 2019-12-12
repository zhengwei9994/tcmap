/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 旅游资产Entity
 * @author liuyang
 * @version 2018-06-11
 */
public class CatchScenicSpot extends DataEntity<CatchScenicSpot> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String spotType;		// 景点等级
	private String spotName;		// 景点名称
	private String displayPosition;		// 展示位置
	
	public CatchScenicSpot() {
		super();
	}

	public CatchScenicSpot(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="景点等级长度必须介于 1 和 255 之间")
	public String getSpotType() {
		return spotType;
	}

	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}
	
	@Length(min=1, max=255, message="景点名称长度必须介于 1 和 255 之间")
	public String getSpotName() {
		return spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	
	@Length(min=1, max=255, message="展示位置长度必须介于 1 和 255 之间")
	public String getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}
	
}