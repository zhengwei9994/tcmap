/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

import java.util.Date;

/**
 * 舆情统计Entity
 * @author xuzhou
 * @version 2018-05-28
 */
public class CatchOpinionStatistics extends DataEntity<CatchOpinionStatistics> {
	
	private static final long serialVersionUID = 1L;
	private String statisticalType;		// 统计类型
	private Double indexPositive;		// 情感指数正
	private Double indexThe;		// 情感指数中
	private Double indexNegative;		// 情感指数负
	private Double totalNetizen;		// 总量网民
	private Double totalMedia;		// 总量媒体
	private Date dateEntry;
	
	public CatchOpinionStatistics() {
		super();
	}

	public CatchOpinionStatistics(String id){
		super(id);
	}

	@Length(min=1, max=10, message="统计类型长度必须介于 1 和 10 之间")
	public String getStatisticalType() {
		return statisticalType;
	}

	public void setStatisticalType(String statisticalType) {
		this.statisticalType = statisticalType;
	}
	
	@NotNull(message="情感指数正不能为空")
	public Double getIndexPositive() {
		return indexPositive;
	}

	public void setIndexPositive(Double indexPositive) {
		this.indexPositive = indexPositive;
	}
	
	@NotNull(message="情感指数中不能为空")
	public Double getIndexThe() {
		return indexThe;
	}

	public void setIndexThe(Double indexThe) {
		this.indexThe = indexThe;
	}
	
	@NotNull(message="情感指数负不能为空")
	public Double getIndexNegative() {
		return indexNegative;
	}

	public void setIndexNegative(Double indexNegative) {
		this.indexNegative = indexNegative;
	}
	
	@NotNull(message="总量网民不能为空")
	public Double getTotalNetizen() {
		return totalNetizen;
	}

	public void setTotalNetizen(Double totalNetizen) {
		this.totalNetizen = totalNetizen;
	}
	
	@NotNull(message="总量媒体不能为空")
	public Double getTotalMedia() {
		return totalMedia;
	}

	public void setTotalMedia(Double totalMedia) {
		this.totalMedia = totalMedia;
	}

	public Date getDateEntry() {
		return dateEntry;
	}

	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;
	}
}