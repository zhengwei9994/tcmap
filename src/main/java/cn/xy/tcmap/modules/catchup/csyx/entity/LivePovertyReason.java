/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 致贫原因Entity
 * @author tuo
 * @version 2019-08-14
 */
public class LivePovertyReason extends DataEntity<LivePovertyReason> {
	
	private static final long serialVersionUID = 1L;
	private String area;		// 地区
	private String reason;		// 致贫原因
	private Integer num;		// 数量
	
	public LivePovertyReason() {
		super();
	}

	public LivePovertyReason(String id){
		super(id);
	}

	@Length(min=0, max=10, message="地区长度必须介于 0 和 10 之间")
	@ExcelField(title="地区", align=2, sort=10)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=10, message="致贫原因长度必须介于 0 和 10 之间")
	@ExcelField(title="致贫原因", align=2, sort=20)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@ExcelField(title="数量", align=2, sort=30)
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}