/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 教育资产Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchEducationAssets extends DataEntity<CatchEducationAssets> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String educationType;		// 学校类别
	private Integer schoolNumber;		// 学校数量
	private Integer teachingStaff;		// 教职人员总数
	private String month;//月
	
	public CatchEducationAssets() {
		super();
	}

	public CatchEducationAssets(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="学校类别长度必须介于 1 和 255 之间")
	public String getEducationType() {
		return educationType;
	}

	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	
	@NotNull(message="学校数量不能为空")
	public Integer getSchoolNumber() {
		return schoolNumber;
	}

	public void setSchoolNumber(Integer schoolNumber) {
		this.schoolNumber = schoolNumber;
	}
	
	@NotNull(message="教职人员总数不能为空")
	public Integer getTeachingStaff() {
		return teachingStaff;
	}

	public void setTeachingStaff(Integer teachingStaff) {
		this.teachingStaff = teachingStaff;
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}