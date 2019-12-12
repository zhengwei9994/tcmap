/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 区县经济增长情况（季度）
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchGrowthSituation extends DataEntity<CatchGrowthSituation> {

	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String indexType;		// 指标类型
	private String indexName;		// 指标名称
	private String indexValue;	// 数值
	private String cmonth;//月份

	public CatchGrowthSituation() {
		super();
	}

	public CatchGrowthSituation(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=20, message="指标类型长度必须介于 1 和 20 之间")
	public String getIndexType() {
		return indexType;
	}

	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	
	@Length(min=1, max=20, message="指标名称长度必须介于 1 和 20 之间")
	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	@Length(min=1, max=20, message="值长度必须介于 1 和 20 之间")
	public String getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

	@Length(min=1, max=20, message="月份长度必须介于 1 和 20 之间")
	public String getCmonth() {
		return cmonth;
	}

	public void setCmonth(String cmonth) {
		this.cmonth = cmonth;
	}
}