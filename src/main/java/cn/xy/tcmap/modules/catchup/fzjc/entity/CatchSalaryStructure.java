/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 人才结构分析Entity
 * @author gxq
 * @version 2018-10-18
 */
public class CatchSalaryStructure extends DataEntity<CatchSalaryStructure> {
	
	private static final long serialVersionUID = 1L;
	private String date;		// 日期
	private String junior;		// 中专
	private String technical;		// 大专
	private String college;		// 本科
	private String ducation;		// 硕士以上
	
	public CatchSalaryStructure() {
		super();
	}

	public CatchSalaryStructure(String id){
		super(id);
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Length(min=0, max=11, message="中专长度必须介于 0 和 11 之间")
	public String getJunior() {
		return junior;
	}

	public void setJunior(String junior) {
		this.junior = junior;
	}
	
	@Length(min=0, max=11, message="大专长度必须介于 0 和 11 之间")
	public String getTechnical() {
		return technical;
	}

	public void setTechnical(String technical) {
		this.technical = technical;
	}
	
	@Length(min=0, max=11, message="本科长度必须介于 0 和 11 之间")
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	
	@Length(min=0, max=11, message="硕士以上长度必须介于 0 和 11 之间")
	public String getDucation() {
		return ducation;
	}

	public void setDucation(String ducation) {
		this.ducation = ducation;
	}
	
}