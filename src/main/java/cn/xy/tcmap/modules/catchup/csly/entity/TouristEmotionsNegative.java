/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 负面情绪Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristEmotionsNegative extends DataEntity<TouristEmotionsNegative> {
	
	private static final long serialVersionUID = 1L;
	private String category;		// 负面类别
	private String num;		// 数量
	
	public TouristEmotionsNegative() {
		super();
	}

	public TouristEmotionsNegative(String id){
		super(id);
	}

	@Length(min=0, max=255, message="负面类别长度必须介于 0 和 255 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}