/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 热搜词汇Entity
 * @author xuzhou
 * @version 2018-05-30
 */
public class CatchHotSearch extends DataEntity<CatchHotSearch> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 热词名称
	private String link;		// 热词链接
	private String sort;		// 排序
	
	public CatchHotSearch() {
		super();
	}

	public CatchHotSearch(String id){
		super(id);
	}

	@Length(min=0, max=50, message="热词名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=500, message="热词链接长度必须介于 0 和 500 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=5, message="排序长度必须介于 0 和 5 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}