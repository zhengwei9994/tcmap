/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 人才结构现状分析Entity
 * @author wl
 * @version 2018-09-29
 */
public class CatchTalentAnalysis extends DataEntity<CatchTalentAnalysis> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String talentType;		// 类型
	private String talentMonth;		// 月份
	private String talentCount;		// 人数
	
	public CatchTalentAnalysis() {
		super();
	}

	public CatchTalentAnalysis(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=255, message="类型长度必须介于 0 和 255 之间")
	public String getTalentType() {
		return talentType;
	}

	public void setTalentType(String talentType) {
		this.talentType = talentType;
	}
	
	@Length(min=0, max=255, message="月份长度必须介于 0 和 255 之间")
	public String getTalentMonth() {
		return talentMonth;
	}

	public void setTalentMonth(String talentMonth) {
		this.talentMonth = talentMonth;
	}
	
	@Length(min=0, max=255, message="人数长度必须介于 0 和 255 之间")
	public String getTalentCount() {
		return talentCount;
	}

	public void setTalentCount(String talentCount) {
		this.talentCount = talentCount;
	}
	
}