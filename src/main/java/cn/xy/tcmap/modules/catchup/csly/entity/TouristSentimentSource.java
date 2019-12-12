/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 舆情数据来源Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristSentimentSource extends DataEntity<TouristSentimentSource> {
	
	private static final long serialVersionUID = 1L;
	private String source;		// 来源
	private String num;		// 数量
	
	public TouristSentimentSource() {
		super();
	}

	public TouristSentimentSource(String id){
		super(id);
	}

	@Length(min=0, max=255, message="来源长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=30, message="数量长度必须介于 0 和 30 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}