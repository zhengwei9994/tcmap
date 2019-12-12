/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 重拳治污染Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchPollutionTreatment extends DataEntity<CatchPollutionTreatment> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private Integer todaySum;		// 全年优良天数
	private String rank;		// 关中五市排名
	private Integer enterprise;		// 拆除企业
	private Integer markCar;		// 黄标车
	
	public CatchPollutionTreatment() {
		super();
	}

	public CatchPollutionTreatment(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@NotNull(message="全年优良天数不能为空")
	public Integer getTodaySum() {
		return todaySum;
	}

	public void setTodaySum(Integer todaySum) {
		this.todaySum = todaySum;
	}
	
	@Length(min=1, max=2, message="关中五市排名长度必须介于 1 和 2 之间")
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	@NotNull(message="拆除企业不能为空")
	public Integer getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Integer enterprise) {
		this.enterprise = enterprise;
	}
	
	@NotNull(message="黄标车不能为空")
	public Integer getMarkCar() {
		return markCar;
	}

	public void setMarkCar(Integer markCar) {
		this.markCar = markCar;
	}
	
}