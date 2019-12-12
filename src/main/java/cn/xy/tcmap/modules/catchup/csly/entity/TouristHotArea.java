/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 地区游客密度Entity
 * @author tuo
 * @version 2019-09-10
 */
public class TouristHotArea extends DataEntity<TouristHotArea> {
	
	private static final long serialVersionUID = 1L;
	private String area;		// 地区
	private String hot;		// 密度
	
	public TouristHotArea() {
		super();
	}

	public TouristHotArea(String id){
		super(id);
	}

	@Length(min=0, max=255, message="地区长度必须介于 0 和 255 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="密度长度必须介于 0 和 255 之间")
	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}
	
}