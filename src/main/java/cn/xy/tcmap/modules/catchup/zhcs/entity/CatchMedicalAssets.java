/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 医疗资源统计Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchMedicalAssets extends DataEntity<CatchMedicalAssets> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String month;
	private String hospitalGrade;		// 医院等级
	private Integer hospitalNumber;		// 医院数量
	private Integer doctorsNumber;		// 医生人数
	private Integer nurseNumber;		// 护士人数
	
	public CatchMedicalAssets() {
		super();
	}

	public CatchMedicalAssets(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="医院等级长度必须介于 1 和 255 之间")
	public String getHospitalGrade() {
		return hospitalGrade;
	}

	public void setHospitalGrade(String hospitalGrade) {
		this.hospitalGrade = hospitalGrade;
	}
	
	@NotNull(message="医院数量不能为空")
	public Integer getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(Integer hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
	
	@NotNull(message="医生人数不能为空")
	public Integer getDoctorsNumber() {
		return doctorsNumber;
	}

	public void setDoctorsNumber(Integer doctorsNumber) {
		this.doctorsNumber = doctorsNumber;
	}
	
	@NotNull(message="护士人数不能为空")
	public Integer getNurseNumber() {
		return nurseNumber;
	}

	public void setNurseNumber(Integer nurseNumber) {
		this.nurseNumber = nurseNumber;
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}