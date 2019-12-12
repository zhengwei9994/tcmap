/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 满意度雷达图Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristSatisfaction extends DataEntity<TouristSatisfaction> {
	
	private static final long serialVersionUID = 1L;
	private String senic;		// 景区
	private String category;		// 类别
	private String num;		// 数值
	
	public TouristSatisfaction() {
		super();
	}

	public TouristSatisfaction(String id){
		super(id);
	}

	@Length(min=0, max=255, message="景区长度必须介于 0 和 255 之间")
	public String getSenic() {
		return senic;
	}

	public void setSenic(String senic) {
		this.senic = senic;
	}
	
	@Length(min=0, max=255, message="类别长度必须介于 0 和 255 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=20, message="数值长度必须介于 0 和 20 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}