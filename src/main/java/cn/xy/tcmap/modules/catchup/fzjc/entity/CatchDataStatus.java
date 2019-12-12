/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 指标数据运行状态Entity
 * @author gxq
 * @version 2018-10-22
 */
public class CatchDataStatus extends DataEntity<CatchDataStatus> {
	
	private static final long serialVersionUID = 1L;
	private String dataDirect;		// 直接访问次数
	private String nyear;		// 年份
	private String month;		// 月份
	private String dataSearch;		// 搜索引擎访问次数
	private String dataGov;		// 政府网站访问次数
	
	public CatchDataStatus() {
		super();
	}

	public CatchDataStatus(String id){
		super(id);
	}

	@Length(min=0, max=11, message="直接访问次数长度必须介于 0 和 11 之间")
	public String getDataDirect() {
		return dataDirect;
	}

	public void setDataDirect(String dataDirect) {
		this.dataDirect = dataDirect;
	}
	
	@Length(min=0, max=5, message="年份长度必须介于 0 和 5 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=5, message="月份长度必须介于 0 和 5 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=11, message="搜索引擎访问次数长度必须介于 0 和 11 之间")
	public String getDataSearch() {
		return dataSearch;
	}

	public void setDataSearch(String dataSearch) {
		this.dataSearch = dataSearch;
	}
	
	@Length(min=0, max=11, message="政府网站访问次数长度必须介于 0 和 11 之间")
	public String getDataGov() {
		return dataGov;
	}

	public void setDataGov(String dataGov) {
		this.dataGov = dataGov;
	}
	
}