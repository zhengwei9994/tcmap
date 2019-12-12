/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 事件数据管理Entity
 * @author wh
 * @version 2019-12-05
 */
public class TcEventManager extends DataEntity<TcEventManager> {
	
	private static final long serialVersionUID = 1L;
	private String eventType;		// 事件类型
	private String eventNumber;		// 事件数量
	private String eventRate;		// 事件占比
	private String eventPersonal;		// 相关责任人
	
	public TcEventManager() {
		super();
	}

	public TcEventManager(String id){
		super(id);
	}

	@Length(min=0, max=255, message="事件类型长度必须介于 0 和 255 之间")
	@ExcelField(title="事件类型", align=2, sort=40)
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	@Length(min=0, max=11, message="事件数量长度必须介于 0 和 11 之间")
	@ExcelField(title="事件数量", align=2, sort=40)
	public String getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(String eventNumber) {
		this.eventNumber = eventNumber;
	}
	
	@Length(min=0, max=255, message="事件占比长度必须介于 0 和 255 之间")
	@ExcelField(title="事件占比", align=2, sort=40)
	public String getEventRate() {
		return eventRate;
	}

	public void setEventRate(String eventRate) {
		this.eventRate = eventRate;
	}
	
	@Length(min=0, max=255, message="相关责任人长度必须介于 0 和 255 之间")
	@ExcelField(title="相关责任人", align=2, sort=40)
	public String getEventPersonal() {
		return eventPersonal;
	}

	public void setEventPersonal(String eventPersonal) {
		this.eventPersonal = eventPersonal;
	}
	
}