/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 交通枢纽统计Entity
 * @author liuyang
 * @version 2018-06-12
 */
public class CatchHingeStatistics extends DataEntity<CatchHingeStatistics> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String hingeType;		// 枢纽类型
	private Integer hingeNumber;		// 枢纽数量
	
	public CatchHingeStatistics() {
		super();
	}

	public CatchHingeStatistics(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="枢纽类型长度必须介于 1 和 255 之间")
	public String getHingeType() {
		return hingeType;
	}

	public void setHingeType(String hingeType) {
		this.hingeType = hingeType;
	}
	
	@NotNull(message="枢纽数量不能为空")
	public Integer getHingeNumber() {
		return hingeNumber;
	}

	public void setHingeNumber(Integer hingeNumber) {
		this.hingeNumber = hingeNumber;
	}
	
}