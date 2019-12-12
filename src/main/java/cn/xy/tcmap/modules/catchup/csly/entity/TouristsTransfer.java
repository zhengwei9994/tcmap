/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 客流迁移Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristsTransfer extends DataEntity<TouristsTransfer> {
	
	private static final long serialVersionUID = 1L;
	private String route;		// 路线
	private String hot;		// 热度
	private String car;		// 汽车
	private String train;		// 火车
	private String aircraft;		// 飞机
	private String date;//日期
	
	public TouristsTransfer() {
		super();
	}

	public TouristsTransfer(String id){
		super(id);
	}

	@Length(min=0, max=255, message="路线长度必须介于 0 和 255 之间")
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	@Length(min=0, max=255, message="热度长度必须介于 0 和 255 之间")
	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}
	
	@Length(min=0, max=255, message="汽车长度必须介于 0 和 255 之间")
	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}
	
	@Length(min=0, max=255, message="火车长度必须介于 0 和 255 之间")
	public String getTrain() {
		return train;
	}

	public void setTrain(String train) {
		this.train = train;
	}
	
	@Length(min=0, max=255, message="飞机长度必须介于 0 和 255 之间")
	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}