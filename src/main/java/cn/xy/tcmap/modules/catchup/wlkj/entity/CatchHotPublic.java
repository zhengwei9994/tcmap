/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 热点舆情Entity
 * @author xuzhou
 * @version 2018-05-28
 */
public class CatchHotPublic extends DataEntity<CatchHotPublic> {
	
	private static final long serialVersionUID = 1L;
	private String publicOpinion;		// public_opinion
	private String sort;		// 排序
	
	public CatchHotPublic() {
		super();
	}

	public CatchHotPublic(String id){
		super(id);
	}

	@Length(min=0, max=100, message="public_opinion长度必须介于 0 和 100 之间")
	public String getPublicOpinion() {
		return publicOpinion;
	}

	public void setPublicOpinion(String publicOpinion) {
		this.publicOpinion = publicOpinion;
	}
	
	@Length(min=0, max=1, message="排序长度必须介于 0 和 1 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}