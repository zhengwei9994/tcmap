/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 全国舆情热点Entity
 * @author xuzhou
 * @version 2018-05-28
 */
public class CatchSpotOpinion extends DataEntity<CatchSpotOpinion> {
	
	private static final long serialVersionUID = 1L;
	private String chinaType;		// 全国热点类型
	private Integer number;		// 热点数量
	private Date date;		// 录入日期
	
	public CatchSpotOpinion() {
		super();
	}

	public CatchSpotOpinion(String id){
		super(id);
	}

	@Length(min=1, max=10, message="全国热点类型长度必须介于 1 和 10 之间")
	public String getChinaType() {
		return chinaType;
	}

	public void setChinaType(String chinaType) {
		this.chinaType = chinaType;
	}
	
	@NotNull(message="热点数量不能为空")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
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