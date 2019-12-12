/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 产业发展Entity
 * @author tuo
 * @version 2019-09-27
 */
public class IndustrialDevelopment extends DataEntity<IndustrialDevelopment> {
	
	private static final long serialVersionUID = 1L;
	private String year;		// 年
	private String profession;		// 行业
	private String professionson1;		// 子行业
	private String professionson2;		// 子行业2
	private String capital;		// 资本
	private String growthrate;		// 增长率
	private String month;		// 月
	private String reserve1;		// 预留1
	private String reserve2;		// 预留2
	private String reserve3;		// 预留3
	private String reserve4;		// 预留4
	private String reserve5;		// 预留5
	
	public IndustrialDevelopment() {
		super();
	}

	public IndustrialDevelopment(String id){
		super(id);
	}

	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="行业长度必须介于 0 和 255 之间")
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	@Length(min=0, max=255, message="子行业长度必须介于 0 和 255 之间")
	public String getProfessionson1() {
		return professionson1;
	}

	public void setProfessionson1(String professionson1) {
		this.professionson1 = professionson1;
	}
	
	@Length(min=0, max=255, message="子行业2长度必须介于 0 和 255 之间")
	public String getProfessionson2() {
		return professionson2;
	}

	public void setProfessionson2(String professionson2) {
		this.professionson2 = professionson2;
	}
	
	@Length(min=0, max=255, message="资本长度必须介于 0 和 255 之间")
	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	@Length(min=0, max=255, message="增长率长度必须介于 0 和 255 之间")
	public String getGrowthrate() {
		return growthrate;
	}

	public void setGrowthrate(String growthrate) {
		this.growthrate = growthrate;
	}
	
	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=255, message="预留1长度必须介于 0 和 255 之间")
	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	
	@Length(min=0, max=255, message="预留2长度必须介于 0 和 255 之间")
	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	
	@Length(min=0, max=255, message="预留3长度必须介于 0 和 255 之间")
	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	
	@Length(min=0, max=255, message="预留4长度必须介于 0 和 255 之间")
	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
	
	@Length(min=0, max=255, message="预留5长度必须介于 0 和 255 之间")
	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}
	
}