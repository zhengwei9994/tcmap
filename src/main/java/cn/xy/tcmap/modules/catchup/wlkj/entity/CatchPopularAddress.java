/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 热门公众号Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchPopularAddress extends DataEntity<CatchPopularAddress> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 热门公号
	private String hotValue;		// 关注数量
	
	public CatchPopularAddress() {
		super();
	}

	public CatchPopularAddress(String id){
		super(id);
	}

	@Length(min=0, max=50, message="热门公号长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="关注数量长度必须介于 0 和 10 之间")
	public String getHotValue() {
		return hotValue;
	}

	public void setHotValue(String hotValue) {
		this.hotValue = hotValue;
	}
	
}