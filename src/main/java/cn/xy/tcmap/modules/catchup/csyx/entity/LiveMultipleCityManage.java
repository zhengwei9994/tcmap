/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 城市综合治理Entity
 * @author tuo
 * @version 2019-08-14
 */
public class LiveMultipleCityManage extends DataEntity<LiveMultipleCityManage> {
	
	private static final long serialVersionUID = 1L;
	private String eventType;		// 事件类型
	private Integer done;		// 已办
	private Integer doing;		// 办理中
	private Integer extension;		// extension
	private Integer agency;		// agency
	private String disposalRate;		// 处置率
	private String year;		// 年
	
	public LiveMultipleCityManage() {
		super();
	}

	public LiveMultipleCityManage(String id){
		super(id);
	}

	@Length(min=0, max=255, message="事件类型长度必须介于 0 和 255 之间")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public Integer getDone() {
		return done;
	}

	public void setDone(Integer done) {
		this.done = done;
	}
	
	public Integer getDoing() {
		return doing;
	}

	public void setDoing(Integer doing) {
		this.doing = doing;
	}
	
	public Integer getExtension() {
		return extension;
	}

	public void setExtension(Integer extension) {
		this.extension = extension;
	}
	
	public Integer getAgency() {
		return agency;
	}

	public void setAgency(Integer agency) {
		this.agency = agency;
	}
	
	public String  getDisposalRate() {
		return disposalRate;
	}

	public void setDisposalRate(String disposalRate) {
		this.disposalRate = disposalRate;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}