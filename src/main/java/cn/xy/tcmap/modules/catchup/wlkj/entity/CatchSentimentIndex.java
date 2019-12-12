/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 舆情指数Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchSentimentIndex extends DataEntity<CatchSentimentIndex> {
	
	private static final long serialVersionUID = 1L;
	private String sensitiveIndex;		// 敏感指数
	private String nonSensitiveIndex;		// 非敏感指数
	private Date date;		// 日期
	
	public CatchSentimentIndex() {
		super();
	}

	public CatchSentimentIndex(String id){
		super(id);
	}

	@Length(min=0, max=10, message="敏感指数长度必须介于 0 和 10 之间")
	public String getSensitiveIndex() {
		return sensitiveIndex;
	}

	public void setSensitiveIndex(String sensitiveIndex) {
		this.sensitiveIndex = sensitiveIndex;
	}
	
	@Length(min=0, max=10, message="非敏感指数长度必须介于 0 和 10 之间")
	public String getNonSensitiveIndex() {
		return nonSensitiveIndex;
	}

	public void setNonSensitiveIndex(String nonSensitiveIndex) {
		this.nonSensitiveIndex = nonSensitiveIndex;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}