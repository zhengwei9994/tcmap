/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 地区景点排名Entity
 * @author wl
 * @version 2018-09-27
 */
public class CatchAreaRanking extends DataEntity<CatchAreaRanking> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String areaType;		// 类型-地区,景点
	private String areaName;		// 名称
	private String areaCount;		// 数量
	private String areaSort;		// 排序
	
	public CatchAreaRanking() {
		super();
	}

	public CatchAreaRanking(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=20, message="类型-地区,景点长度必须介于 0 和 20 之间")
	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Length(min=0, max=255, message="数量长度必须介于 0 和 255 之间")
	public String getAreaCount() {
		return areaCount;
	}

	public void setAreaCount(String areaCount) {
		this.areaCount = areaCount;
	}
	
	@Length(min=0, max=255, message="排序长度必须介于 0 和 255 之间")
	public String getAreaSort() {
		return areaSort;
	}

	public void setAreaSort(String areaSort) {
		this.areaSort = areaSort;
	}
	
}