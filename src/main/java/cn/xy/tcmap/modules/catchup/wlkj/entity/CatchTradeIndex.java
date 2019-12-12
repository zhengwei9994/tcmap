/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 行业指数Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchTradeIndex extends DataEntity<CatchTradeIndex> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 行业名称
	private String value;		// 指数占比
	
	public CatchTradeIndex() {
		super();
	}

	public CatchTradeIndex(String id){
		super(id);
	}

	@Length(min=0, max=50, message="行业名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="value长度必须介于 0 和 50 之间")
	@Pattern(regexp = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", message = "行业指数占比仅支持数字")  
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}