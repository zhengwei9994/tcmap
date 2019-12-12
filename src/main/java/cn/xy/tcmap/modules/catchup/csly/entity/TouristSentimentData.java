/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 舆情数据Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristSentimentData extends DataEntity<TouristSentimentData> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String num;		// 数据

	private String area;
	private String nyear;
	private String nmonth;
	
	public TouristSentimentData() {
		super();
	}

	public TouristSentimentData(String id){
		super(id);
	}

	@Length(min=0, max=255, message="名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="数据长度必须介于 0 和 20 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}

	public String getNmonth() {
		return nmonth;
	}

	public void setNmonth(String nmonth) {
		this.nmonth = nmonth;
	}
}