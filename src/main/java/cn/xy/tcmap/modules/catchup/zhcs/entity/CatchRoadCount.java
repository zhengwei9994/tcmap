/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 公路统计Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchRoadCount extends DataEntity<CatchRoadCount> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String roadType;		// 公路类型
	private Integer roadMileage;		// 公路里程
	
	public CatchRoadCount() {
		super();
	}

	public CatchRoadCount(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="公路类型长度必须介于 1 和 255 之间")
	public String getRoadType() {
		return roadType;
	}

	public void setRoadType(String roadType) {
		this.roadType = roadType;
	}
	
	@NotNull(message="公路里程不能为空")
	public Integer getRoadMileage() {
		return roadMileage;
	}

	public void setRoadMileage(Integer roadMileage) {
		this.roadMileage = roadMileage;
	}
	
}