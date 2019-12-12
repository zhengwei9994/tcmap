/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济指标名称经济指标统计Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CathEconomic extends DataEntity<CathEconomic> {
	
	private static final long serialVersionUID = 1L;
	private CatchIndexName indexId;		// 经济指标
	private String nyear;		// 年份
	private String quarter;		// 季度
	private Double indicators;		// 经济指标统计
	private String indicatorsUnit;		// 单位
	private Double growth;		// 增速
	
	public CathEconomic() {
		super();
	}

	public CathEconomic(String id){
		super(id);
	}

	public CatchIndexName getIndexId() {
		return indexId;
	}

	public void setIndexId(CatchIndexName indexId) {
		this.indexId = indexId;
	}
	
	@Length(min=1, max=10, message="年份长度必须介于 1 和 10 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=1, message="季度长度必须介于 1 和 1 之间")
	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	
	@NotNull(message="经济指标统计不能为空")
	public Double getIndicators() {
		return indicators;
	}

	public void setIndicators(Double indicators) {
		this.indicators = indicators;
	}
	
	@Length(min=1, max=1, message="单位长度必须介于 1 和 1 之间")
	public String getIndicatorsUnit() {
		return indicatorsUnit;
	}

	public void setIndicatorsUnit(String indicatorsUnit) {
		this.indicatorsUnit = indicatorsUnit;
	}
	
	@NotNull(message="增速不能为空")
	public Double getGrowth() {
		return growth;
	}

	public void setGrowth(Double growth) {
		this.growth = growth;
	}
	
}