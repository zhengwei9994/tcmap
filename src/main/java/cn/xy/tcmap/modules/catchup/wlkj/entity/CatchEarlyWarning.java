/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;


import org.apache.poi.hpsf.Variant;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

import java.util.Date;

/**
 * 红色预警Entity
 * @author xuzhou
 * @version 2018-05-28
 */
public class CatchEarlyWarning extends DataEntity<CatchEarlyWarning> {
	
	private static final long serialVersionUID = 1L;
	private String date;		// 日期
	private Integer number;		// 红色预警次数
	
	public CatchEarlyWarning() {
		super();
	}

	public CatchEarlyWarning(String id){
		super(id);
	}

	@Length(min=1, max=10, message="日期长度必须介于 1 和 10 之间")
	public String getDate() {
		return date;
	}

	public void setDate(String  date) {
		this.date = date;
	}
	
	@NotNull(message="红色预警次数不能为空")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}