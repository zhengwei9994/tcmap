/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 专利数量统计Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchPatentCount extends DataEntity<CatchPatentCount> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private Integer patentNumber;		// 专利数量
	
	public CatchPatentCount() {
		super();
	}

	public CatchPatentCount(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@NotNull(message="专利数量不能为空")
	public Integer getPatentNumber() {
		return patentNumber;
	}

	public void setPatentNumber(Integer patentNumber) {
		this.patentNumber = patentNumber;
	}
	
}