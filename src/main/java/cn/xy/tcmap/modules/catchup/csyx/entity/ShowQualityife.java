/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 经济生活质量Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ShowQualityife extends DataEntity<ShowQualityife> {
	
	private static final long serialVersionUID = 1L;
	private String indexName;		// 指标名称
	private String indexNum;		// 指标数量
	private String indexIncrease;		// 指标增长
	private String year;		// 年
	private String month;       //月
	
	public ShowQualityife() {
		super();
	}

	public ShowQualityife(String id){
		super(id);
	}

	@Length(min=0, max=255, message="指标名称长度必须介于 0 和 255 之间")
	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	
	@Length(min=0, max=11, message="指标数量长度必须介于 0 和 11 之间")
	public String getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}
	
	@Length(min=0, max=255, message="指标增长长度必须介于 0 和 255 之间")
	public String getIndexIncrease() {
		return indexIncrease;
	}

	public void setIndexIncrease(String indexIncrease) {
		this.indexIncrease = indexIncrease;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}