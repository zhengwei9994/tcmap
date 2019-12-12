/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 舆情来源Entity
 * @author xuzhou
 * @version 2018-05-28
 */
public class CatchSourceOpinion extends DataEntity<CatchSourceOpinion> {
	
	private static final long serialVersionUID = 1L;
	private String sourceType;		// 舆情来源类型
	private String number;		// 舆情来源流量
	private Date date;		// 录入日期
	
	public CatchSourceOpinion() {
		super();
	}

	public CatchSourceOpinion(String id){
		super(id);
	}

	@Length(min=0, max=10, message="舆情来源类型长度必须介于 0 和 10 之间")
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}