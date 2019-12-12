/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 景区旅游数据分析Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchSceniArea extends DataEntity<CatchSceniArea> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String scenicArea;		// 景区名称
	private Integer totalRevenue;		// 累计收入
	private String growth;		// 去年同比
	private Integer reception;		// 接待人次
	
	public CatchSceniArea() {
		super();
	}

	public CatchSceniArea(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=20, message="景区名称长度必须介于 1 和 20 之间")
	public String getScenicArea() {
		return scenicArea;
	}

	public void setScenicArea(String scenicArea) {
		this.scenicArea = scenicArea;
	}
	
	@NotNull(message="累计收入不能为空")
	public Integer getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Integer totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	@Length(min=1, max=10, message="去年同比长度必须介于 1 和 10 之间")
	public String getGrowth() {
		return growth;
	}

	public void setGrowth(String growth) {
		this.growth = growth;
	}
	
	@NotNull(message="接待人次不能为空")
	public Integer getReception() {
		return reception;
	}

	public void setReception(Integer reception) {
		this.reception = reception;
	}
	
}