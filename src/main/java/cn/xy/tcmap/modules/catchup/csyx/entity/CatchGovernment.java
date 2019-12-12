/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 社会治理Entity
 * @author xuzhou
 * @version 2018-05-24
 */
public class CatchGovernment extends DataEntity<CatchGovernment> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String governanceType;		// 社会治理类型
	private Integer numericalValue;		// 治理数
	private String unit;		// 单位
	
	public CatchGovernment() {
		super();
	}

	public CatchGovernment(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=100, message="社会治理类型长度必须介于 1 和 100 之间")
	public String getGovernanceType() {
		return governanceType;
	}

	public void setGovernanceType(String governanceType) {
		this.governanceType = governanceType;
	}
	
	@NotNull(message="治理数不能为空")
	public Integer getNumericalValue() {
		return numericalValue;
	}

	public void setNumericalValue(Integer numericalValue) {
		this.numericalValue = numericalValue;
	}
	
	@Length(min=1, max=10, message="单位长度必须介于 1 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}