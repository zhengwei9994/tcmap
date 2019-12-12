/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 区域经济增长Entity
 * @author gxq
 * @version 2018-11-05
 */
public class CatchEconomicGrowth extends DataEntity<CatchEconomicGrowth> {
	
	private static final long serialVersionUID = 1L;
	private String indexId;		// 经济指标
	private String nyear;		// 年份
	private String areaName;		// 区域
	private String indicators;		// 额度
	private String indicatorsUnit;		// 单位
	private String rate;		// 占比
	
	private String indexName;
	private String nameEn;
	private double lat;
	private double lon;
	
	
	public CatchEconomicGrowth() {
		super();
	}

	public CatchEconomicGrowth(String id){
		super(id);
	}

	@Length(min=1, max=64, message="经济指标长度必须介于 1 和 64 之间")
	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	
	@Length(min=0, max=10, message="年份长度必须介于 0 和 10 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=100, message="区域长度必须介于 0 和 100 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getIndicators() {
		return indicators;
	}

	public void setIndicators(String indicators) {
		this.indicators = indicators;
	}
	
	@Length(min=0, max=10, message="单位长度必须介于 0 和 10 之间")
	public String getIndicatorsUnit() {
		return indicatorsUnit;
	}

	public void setIndicatorsUnit(String indicatorsUnit) {
		this.indicatorsUnit = indicatorsUnit;
	}
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}
	
}