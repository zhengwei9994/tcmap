/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.khgl.entity;

import cn.xy.tcmap.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 考核管理Entity
 * @author wufan
 * @version 2019-12-03
 */
public class CatchAssessmentManagement extends DataEntity<CatchAssessmentManagement> {

	private static final long serialVersionUID = 1L;
	private String department;		// 考核部门
	private String sector;		// 下发部门
	private String name;		// 任务名称
	private String mission;		// 任务内容
	private String score;		// 分值
	private Date startDate;         // 开始时间
	private Date endDate;			// 结束时间


	public CatchAssessmentManagement() {
		super();
	}

	public CatchAssessmentManagement(String id){
		super(id);
	}

	@Length(min=1, max=10, message="考核部门长度必须介于 1 和 10 之间")
	@ExcelField(title="考核部门", align=2, sort=20)
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Length(min=1, max=10, message="下发部门长度必须介于 1 和 10 之间")
	@ExcelField(title="下发部门", align=2, sort=25)
	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	@Length(min=1, max=20, message="任务名称长度必须介于 1 和 20 之间")
	@ExcelField(title="任务名称", align=2, sort=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@ExcelField(title="任务内容", align=2, sort=35)
	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	@Length(min=1, max=3, message="分值长度必须介于 1 和 3 之间")
	@ExcelField(title="分值", align=2, sort=40)
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@JsonFormat(pattern = "yyyy-MM")
	@ExcelField(title="开始时间", align=2, sort=36)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonFormat(pattern = "yyyy-MM")
	@ExcelField(title="结束时间", align=2, sort=37)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return false;
		}
		if (obj==null){
			return false;
		}
		if(obj.getClass()!= this.getClass()){
			return false;
		}
		CatchAssessmentManagement catchAssessmentManagement = (CatchAssessmentManagement) obj;
		if (!this.department.equals(catchAssessmentManagement.department)){
			return false;
		}
		if(!this.sector.equals(catchAssessmentManagement.sector)){
			return false;
		}
		if(!this.name.equals(catchAssessmentManagement.name)){
			return false;
		}
		if(!this.mission.equals(catchAssessmentManagement.mission)){
			return false;
		}
		if(!this.score.equals(catchAssessmentManagement.score)){
			return false;
		}
		if(!DateUtils.formatDateTime(startDate).equals(DateUtils.formatDateTime(catchAssessmentManagement.startDate))){
			return false;
		}
		if(!DateUtils.formatDateTime(endDate).equals(DateUtils.formatDateTime(catchAssessmentManagement.endDate))){
			return false;
		}
		return true;
	}
}