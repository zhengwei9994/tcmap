/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 空气质量Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchAqiparam extends DataEntity<CatchAqiparam> {
	
	private static final long serialVersionUID = 1L;
	private String areaName;		// 区域名称
	private String aqi;		// AQI
	private String aqilevel;		// 空气质量级别
	private Integer rank;		// 排名
	
	public CatchAqiparam() {
		super();
	}

	public CatchAqiparam(String id){
		super(id);
	}

	@Length(min=0, max=10, message="区域名称长度必须介于 0 和 10 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Length(min=0, max=5, message="AQI长度必须介于 0 和 5 之间")
	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	
	@Length(min=0, max=5, message="空气质量级别长度必须介于 0 和 5 之间")
	public String getAqilevel() {
		return aqilevel;
	}

	public void setAqilevel(String aqilevel) {
		this.aqilevel = aqilevel;
	}
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
}