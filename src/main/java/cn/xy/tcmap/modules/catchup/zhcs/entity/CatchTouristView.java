/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 旅游资产Entity
 * @author zzc
 * @version 2018-11-07
 */
public class CatchTouristView extends DataEntity<CatchTouristView> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 景区名称
	private String lon;		// 景区经度
	private String lat;		// 景区纬度
	private String rank;		// 景区等级
	private String areaName;//地区
	
	public CatchTouristView() {
		super();
	}

	public CatchTouristView(String id){
		super(id);
	}

	@Length(min=0, max=100, message="景区名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Length(min=0, max=10, message="景区等级长度必须介于 0 和 10 之间")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}