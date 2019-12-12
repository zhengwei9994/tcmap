/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.wlkj.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 媒体类型Entity
 * @author guoxunquan
 * @version 2018-09-20
 */
public class CatchMediaMtlx extends DataEntity<CatchMediaMtlx> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 媒体
	private String contribution;		// 舆情贡献量
	private String proportion;		// 媒体占比
	
	public CatchMediaMtlx() {
		super();
	}

	public CatchMediaMtlx(String id){
		super(id);
	}

	@Length(min=0, max=50, message="媒体长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="舆情贡献量长度必须介于 0 和 10 之间")
	public String getContribution() {
		return contribution;
	}

	public void setContribution(String contribution) {
		this.contribution = contribution;
	}
	
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	
}