/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 项目完成投资占比Entity
 * @author wufan
 * @version 2019-07-31
 */
public class ItemProportion extends DataEntity<ItemProportion> {
	
	private static final long serialVersionUID = 1L;
	private String item;		// 项目
	private String proportion;		// 比例
	
	public ItemProportion() {
		super();
	}

	public ItemProportion(String id){
		super(id);
	}

	@Length(min=0, max=255, message="项目长度必须介于 0 和 255 之间")
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	@Length(min=0, max=255, message="比例长度必须介于 0 和 255 之间")
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
}