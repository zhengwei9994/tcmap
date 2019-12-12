/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 铜川区域管理Entity
 * @author gxq
 * @version 2018-11-08
 */
public class CatchAreaPosition extends DataEntity<CatchAreaPosition> {
	
	private static final long serialVersionUID = 1L;
	private String areaName;		// 地区名字
	private String lon;		// 经度
	private String lat;		// 纬度
	
	public CatchAreaPosition() {
		super();
	}

	public CatchAreaPosition(String id){
		super(id);
	}

	@Length(min=0, max=100, message="地区名字长度必须介于 0 和 100 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
}