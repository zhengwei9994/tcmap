/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 游客分析Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchProvincesStatistical extends DataEntity<CatchProvincesStatistical> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private Integer touristsSum;		// 游客总数
	private Integer otherProvinces;		// 外省游客人数
	private Integer thisProvinces;		// 本省游客数
	private Integer foreigns;		// 外国游客数
	
	public CatchProvincesStatistical() {
		super();
	}

	public CatchProvincesStatistical(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	
	public Integer getTouristsSum() {
		return touristsSum;
	}

	public void setTouristsSum(Integer touristsSum) {
		this.touristsSum = touristsSum;
	}
	
	@NotNull(message="外省游客人数不能为空")
	public Integer getOtherProvinces() {
		return otherProvinces;
	}

	public void setOtherProvinces(Integer otherProvinces) {
		this.otherProvinces = otherProvinces;
	}
	
	@NotNull(message="本省游客数不能为空")
	public Integer getThisProvinces() {
		return thisProvinces;
	}

	public void setThisProvinces(Integer thisProvinces) {
		this.thisProvinces = thisProvinces;
	}
	
	@NotNull(message="外国游客数不能为空")
	public Integer getForeigns() {
		return foreigns;
	}

	public void setForeigns(Integer foreigns) {
		this.foreigns = foreigns;
	}
	
}