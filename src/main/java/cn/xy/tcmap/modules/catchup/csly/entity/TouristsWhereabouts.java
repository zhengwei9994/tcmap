/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 游客去向地Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristsWhereabouts extends DataEntity<TouristsWhereabouts> {
	
	private static final long serialVersionUID = 1L;
	private String province;		// 省份
	private String num;		// 数量
	
	public TouristsWhereabouts() {
		super();
	}

	public TouristsWhereabouts(String id){
		super(id);
	}

	@Length(min=0, max=255, message="省份长度必须介于 0 和 255 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=255, message="数量长度必须介于 0 和 255 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}