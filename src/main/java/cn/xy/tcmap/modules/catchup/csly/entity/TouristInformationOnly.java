/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 单一景区资讯Entity
 * @author tuo
 * @version 2019-10-23
 */
public class TouristInformationOnly extends DataEntity<TouristInformationOnly> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// 景区资讯排行id
	private String area;		// 地区
	private String communication;		// 传播力
	private String total;		// 传播总量
	private String forward;		// 转发次数
	private String date;		// 时间
	
	public TouristInformationOnly() {
		super();
	}

	public TouristInformationOnly(String id){
		super(id);
	}

	@Length(min=0, max=100, message="景区资讯排行id长度必须介于 0 和 100 之间")
	@ExcelField(title="景区资讯排行id", align=2, sort=40)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=255, message="地区长度必须介于 0 和 255 之间")
	@ExcelField(title="地区", align=2, sort=40)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="传播力长度必须介于 0 和 255 之间")
	@ExcelField(title="传播力", align=2, sort=40)
	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}
	
	@Length(min=0, max=255, message="传播总量长度必须介于 0 和 255 之间")
	@ExcelField(title="传播总量", align=2, sort=40)
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=255, message="转发次数长度必须介于 0 和 255 之间")
	@ExcelField(title="转发次数", align=2, sort=40)
	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}
	
	@ExcelField(title="时间", align=2, sort=40)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}