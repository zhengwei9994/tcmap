/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 一年各来源情绪占比Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristEmotionYear extends DataEntity<TouristEmotionYear> {
	
	private static final long serialVersionUID = 1L;
	private String source;		// 来源
	private String state;		// 正负面
	private String num;		// 数量
	
	public TouristEmotionYear() {
		super();
	}

	public TouristEmotionYear(String id){
		super(id);
	}

	@Length(min=0, max=255, message="来源长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=255, message="正负面长度必须介于 0 和 255 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=255, message="数量长度必须介于 0 和 255 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}