/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 指标数据运行状态
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchIndexStatus extends DataEntity<CatchIndexStatus> {

	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String indexType;		// 指标类型
	private String indexName;		// 指标名称
	private Double completionRate;// 同比增长率
	private String averageRates;//增长值

	public CatchIndexStatus() {
		super();
	}

	public CatchIndexStatus(String id){
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
	
	@NotNull(message="指标完成率不能为空")
	public Double getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(Double completionRate) {
		this.completionRate = completionRate;
	}

	@Length(min=1, max=20, message="增长值长度必须介于 1 和 20 之间")
	public String getAverageRates() {
		return averageRates;
	}

	public void setAverageRates(String averageRates) {
		this.averageRates = averageRates;
	}
}