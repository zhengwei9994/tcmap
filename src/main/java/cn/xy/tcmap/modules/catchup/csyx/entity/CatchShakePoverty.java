/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 精准务实抓脱贫Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchShakePoverty extends DataEntity<CatchShakePoverty> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String areaName;		// 区县
	private Integer outPoverty;//已脱贫人口
	private Integer totalPeople;		// 未脱贫人口
	private String reasonsAlleviation;		// 脱贫原因
	private Integer poorHouseholds;		// 贫困户
	
	public CatchShakePoverty() {
		super();
	}

	public CatchShakePoverty(String id){
		super(id);
	}

	public Integer getOutPoverty() {
		return outPoverty;
	}

	public void setOutPoverty(Integer outPoverty) {
		this.outPoverty = outPoverty;
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=10, message="区县长度必须介于 1 和 10 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@NotNull(message="脱贫人口不能为空")
	public Integer getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}
	
	@Length(min=1, max=30, message="脱贫原因长度必须介于 1 和 30 之间")
	public String getReasonsAlleviation() {
		return reasonsAlleviation;
	}

	public void setReasonsAlleviation(String reasonsAlleviation) {
		this.reasonsAlleviation = reasonsAlleviation;
	}
	
	@NotNull(message="贫困户不能为空")
	public Integer getPoorHouseholds() {
		return poorHouseholds;
	}

	public void setPoorHouseholds(Integer poorHouseholds) {
		this.poorHouseholds = poorHouseholds;
	}
	
}