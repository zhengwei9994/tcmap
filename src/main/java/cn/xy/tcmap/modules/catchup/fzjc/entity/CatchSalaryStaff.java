/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 薪资及人员分析Entity
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchSalaryStaff extends DataEntity<CatchSalaryStaff> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String statisticalContent;		// 统计内容
	private Double numericalValue;		// 数量
	private String unti;		// 单位
	private Double growthRate;		// 增速
	
	public CatchSalaryStaff() {
		super();
	}

	public CatchSalaryStaff(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="统计内容长度必须介于 1 和 255 之间")
	public String getStatisticalContent() {
		return statisticalContent;
	}

	public void setStatisticalContent(String statisticalContent) {
		this.statisticalContent = statisticalContent;
	}
	
	@NotNull(message="数量不能为空")
	public Double getNumericalValue() {
		return numericalValue;
	}

	public void setNumericalValue(Double numericalValue) {
		this.numericalValue = numericalValue;
	}
	
	@Length(min=1, max=255, message="单位长度必须介于 1 和 255 之间")
	public String getUnti() {
		return unti;
	}

	public void setUnti(String unti) {
		this.unti = unti;
	}
	
	@NotNull(message="增速不能为空")
	public Double getGrowthRate() {
		return growthRate;
	}

	public void setGrowthRate(Double growthRate) {
		this.growthRate = growthRate;
	}
	
}