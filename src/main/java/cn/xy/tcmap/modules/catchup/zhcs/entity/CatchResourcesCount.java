/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 人才类型统计Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchResourcesCount extends DataEntity<CatchResourcesCount> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String personnelType;		// 人才类型
	private Integer male;		// 男性人数
	private Integer female;		// 女性人数
	private String month;//月份
	
	public CatchResourcesCount() {
		super();
	}

	public CatchResourcesCount(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="人才类型长度必须介于 1 和 255 之间")
	public String getPersonnelType() {
		return personnelType;
	}

	public void setPersonnelType(String personnelType) {
		this.personnelType = personnelType;
	}
	
	@NotNull(message="男性人数不能为空")
	public Integer getMale() {
		return male;
	}

	public void setMale(Integer male) {
		this.male = male;
	}
	
	@NotNull(message="女性人数不能为空")
	public Integer getFemale() {
		return female;
	}

	public void setFemale(Integer female) {
		this.female = female;
	}
	@Length(min=1, max=4, message="年份长度必须介于 1 和 10 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}