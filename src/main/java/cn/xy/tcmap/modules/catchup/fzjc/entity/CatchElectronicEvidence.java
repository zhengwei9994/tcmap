/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 电子证照使用率Entity
 * @author gxq
 * @version 2018-10-19
 */
public class CatchElectronicEvidence extends DataEntity<CatchElectronicEvidence> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String month;       //月份
	private String banking;		// 金融使用次数
	private String house;		// 置业
	private String education;		// 教育
	private String medical;		// 医疗
	private String traffic;		// 交通
	private String travel;		// 旅游
	private String shop;		// 购物
	
	public CatchElectronicEvidence() {
		super();
	}

	public CatchElectronicEvidence(String id){
		super(id);
	}
    
	@Length(min=0, max=10, message="年份长度必须介于 0 和 10 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	 
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Length(min=0, max=11, message="金融使用次数长度必须介于 0 和 11 之间")
	public String getBanking() {
		return banking;
	}

	public void setBanking(String banking) {
		this.banking = banking;
	}
	
	@Length(min=0, max=11, message="置业长度必须介于 0 和 11 之间")
	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}
	
	@Length(min=0, max=11, message="教育长度必须介于 0 和 11 之间")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	@Length(min=0, max=11, message="医疗长度必须介于 0 和 11 之间")
	public String getMedical() {
		return medical;
	}

	public void setMedical(String medical) {
		this.medical = medical;
	}
	
	@Length(min=0, max=11, message="交通长度必须介于 0 和 11 之间")
	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	
	@Length(min=0, max=11, message="旅游长度必须介于 0 和 11 之间")
	public String getTravel() {
		return travel;
	}

	public void setTravel(String travel) {
		this.travel = travel;
	}
	
	@Length(min=0, max=11, message="购物长度必须介于 0 和 11 之间")
	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}
	
}