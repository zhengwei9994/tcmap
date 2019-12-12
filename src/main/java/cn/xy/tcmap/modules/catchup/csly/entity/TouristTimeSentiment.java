/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 实时预警舆情Entity
 * @author wufan
 * @version 2019-08-19
 */
public class TouristTimeSentiment extends DataEntity<TouristTimeSentiment> {
	
	private static final long serialVersionUID = 1L;
	private String abstracts;		// 摘要
	private Date date;		// 时间
	private String source;		// 来源
	private String scenic;		// 景区
	
	public TouristTimeSentiment() {
		super();
	}

	public TouristTimeSentiment(String id){
		super(id);
	}

	@Length(min=0, max=255, message="摘要长度必须介于 0 和 255 之间")
	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Length(min=0, max=255, message="来源长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=255, message="景区长度必须介于 0 和 255 之间")
	public String getScenic() {
		return scenic;
	}

	public void setScenic(String scenic) {
		this.scenic = scenic;
	}
	
}