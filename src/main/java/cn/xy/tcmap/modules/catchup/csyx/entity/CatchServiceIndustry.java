/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 服务业稳重向好Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchServiceIndustry extends DataEntity<CatchServiceIndustry> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private Double growth;		// 增速
	private Double point;		// 百分点
	
	public CatchServiceIndustry() {
		super();
	}

	public CatchServiceIndustry(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@NotNull(message="增速不能为空")
	public Double getGrowth() {
		return growth;
	}

	public void setGrowth(Double growth) {
		this.growth = growth;
	}
	
	@NotNull(message="百分点不能为空")
	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
	
}