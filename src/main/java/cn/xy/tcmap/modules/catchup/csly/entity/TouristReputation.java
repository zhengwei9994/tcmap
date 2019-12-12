/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 美誉度趋势Entity
 * @author tuo
 * @version 2019-10-23
 */
public class TouristReputation extends DataEntity<TouristReputation> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年
	private String month;		// 月
	private String scenery;		// 景点
	private String reputation;		// 美誉度
	
	public TouristReputation() {
		super();
	}

	public TouristReputation(String id){
		super(id);
	}

	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	@ExcelField(title="年", align=2, sort=40)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	@ExcelField(title="月", align=2, sort=40)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=255, message="景点长度必须介于 0 和 255 之间")
	@ExcelField(title="景点", align=2, sort=40)
	public String getScenery() {
		return scenery;
	}

	public void setScenery(String scenery) {
		this.scenery = scenery;
	}
	
	@Length(min=0, max=255, message="美誉度长度必须介于 0 和 255 之间")
	@ExcelField(title="美誉度", align=2, sort=40)
	public String getReputation() {
		return reputation;
	}

	public void setReputation(String reputation) {
		this.reputation = reputation;
	}
	
}