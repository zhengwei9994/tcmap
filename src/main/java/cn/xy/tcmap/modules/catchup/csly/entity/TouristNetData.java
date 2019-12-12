/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 互联网数据流Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristNetData extends DataEntity<TouristNetData> {
	
	private static final long serialVersionUID = 1L;
	private String source;		// 来源
	private String scenic;		// 景区
	private String content;		// 评价内容
	
	public TouristNetData() {
		super();
	}

	public TouristNetData(String id){
		super(id);
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
	
	@Length(min=0, max=255, message="评价内容长度必须介于 0 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}