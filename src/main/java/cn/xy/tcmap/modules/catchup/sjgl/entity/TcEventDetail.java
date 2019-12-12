/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 事件数据详情Entity
 * @author wh
 * @version 2019-12-05
 */
public class TcEventDetail extends DataEntity<TcEventDetail> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 事件类型
	private String source;		// 事件来源
	private String rate;		// 事件占比
	private String eventContent;		// 事件描述
	private String completion;		// 完成情况
	private String eventId;		// 事件表Id
	private String eventPerson;		// 相关责任人
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String createUser;		// 创建人
	
	public TcEventDetail() {
		super();
	}

	public TcEventDetail(String id){
		super(id);
	}

	@Length(min=0, max=255, message="事件类型长度必须介于 0 和 255 之间")
	@ExcelField(title="事件类型", align=2, sort=40)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="事件来源长度必须介于 0 和 255 之间")
	@ExcelField(title="事件来源", align=2, sort=40)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=255, message="事件占比长度必须介于 0 和 255 之间")
	@ExcelField(title="事件占比", align=2, sort=40)
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	@ExcelField(title="事件描述", align=2, sort=40)
	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	
	@Length(min=0, max=255, message="完成情况长度必须介于 0 和 255 之间")
	@ExcelField(title="完成情况", align=2, sort=40)
	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}
	
	@Length(min=0, max=255, message="事件表Id长度必须介于 0 和 255 之间")
	@ExcelField(title="事件表Id", align=2, sort=40)
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Length(min=0, max=255, message="相关责任人长度必须介于 0 和 255 之间")
	@ExcelField(title="相关责任人", align=2, sort=40)
	public String getEventPerson() {
		return eventPerson;
	}

	public void setEventPerson(String eventPerson) {
		this.eventPerson = eventPerson;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", align=2, sort=40)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="更新时间", align=2, sort=40)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=255, message="创建人长度必须介于 0 和 255 之间")
	@ExcelField(title="创建人", align=2, sort=40)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}