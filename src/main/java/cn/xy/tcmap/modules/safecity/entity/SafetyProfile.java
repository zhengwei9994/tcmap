/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

import java.util.Date;

/**
 * 安全概况Entity
 * @author tuo
 * @version 2019-12-04
 */
public class SafetyProfile extends DataEntity<SafetyProfile> {
	
	private static final long serialVersionUID = 1L;
	private String safeCityId;		// 平安城市主键
	private String safetyIndex;		// 公共安全指数
	private String drugRate;		// 药品合格率
	private String foodRate;		// 食品合格率
	private String crimelSolveRate;		// 刑事案件破案率
	private String crimeRate;		// 刑事案件案发率
	private String civilRate;		// 民事案件发案率
	private String trafficNum;		// 交通违法案件数量
	private String yearRate;		// 交通违法同比
	private String chainRate;		// 交通违法环比
	private String fireNum;		// 火灾案件数量
	private String fireTruckNum;		// 消防车数量
	private String fireBrigade;		// 消防队伍
	private Date statisticDate;		// 统计日期

	private String safeCity;//平安城市
	
	public SafetyProfile() {
		super();
	}

	public SafetyProfile(String id){
		super(id);
	}

	@Length(min=0, max=255, message="平安城市主键长度必须介于 0 和 255 之间")
	public String getSafeCityId() {
		return safeCityId;
	}

	public void setSafeCityId(String safeCityId) {
		this.safeCityId = safeCityId;
	}
	
	@Length(min=0, max=100, message="公共安全指数长度必须介于 0 和 100 之间")
	@ExcelField(title="公共安全指数", align=2, sort=40)
	public String getSafetyIndex() {
		return safetyIndex;
	}

	public void setSafetyIndex(String safetyIndex) {
		this.safetyIndex = safetyIndex;
	}
	
	@Length(min=0, max=100, message="药品合格率长度必须介于 0 和 100 之间")
	@ExcelField(title="药品合格率", align=2, sort=40)
	public String getDrugRate() {
		return drugRate;
	}

	public void setDrugRate(String drugRate) {
		this.drugRate = drugRate;
	}
	
	@Length(min=0, max=100, message="食品合格率长度必须介于 0 和 100 之间")
	@ExcelField(title="食品合格率", align=2, sort=40)
	public String getFoodRate() {
		return foodRate;
	}

	public void setFoodRate(String foodRate) {
		this.foodRate = foodRate;
	}
	
	@Length(min=0, max=100, message="刑事案件破案率长度必须介于 0 和 100 之间")
	@ExcelField(title="刑事案件破案率", align=2, sort=40)
	public String getCrimelSolveRate() {
		return crimelSolveRate;
	}

	public void setCrimelSolveRate(String crimelSolveRate) {
		this.crimelSolveRate = crimelSolveRate;
	}
	
	@Length(min=0, max=100, message="刑事案件案发率长度必须介于 0 和 100 之间")
	@ExcelField(title="刑事案件案发率", align=2, sort=40)
	public String getCrimeRate() {
		return crimeRate;
	}

	public void setCrimeRate(String crimeRate) {
		this.crimeRate = crimeRate;
	}
	
	@Length(min=0, max=100, message="民事案件发案率长度必须介于 0 和 100 之间")
	@ExcelField(title="民事案件发案率", align=2, sort=40)
	public String getCivilRate() {
		return civilRate;
	}

	public void setCivilRate(String civilRate) {
		this.civilRate = civilRate;
	}
	
	@Length(min=0, max=100, message="交通违法案件数量长度必须介于 0 和 100 之间")
	@ExcelField(title="交通违法案件数量", align=2, sort=40)
	public String getTrafficNum() {
		return trafficNum;
	}

	public void setTrafficNum(String trafficNum) {
		this.trafficNum = trafficNum;
	}
	
	@Length(min=0, max=100, message="交通违法同比长度必须介于 0 和 100 之间")
	@ExcelField(title="交通违法同比", align=2, sort=40)
	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}
	
	@Length(min=0, max=100, message="交通违法环比长度必须介于 0 和 100 之间")
	@ExcelField(title="交通违法环比", align=2, sort=40)
	public String getChainRate() {
		return chainRate;
	}

	public void setChainRate(String chainRate) {
		this.chainRate = chainRate;
	}
	
	@Length(min=0, max=100, message="火灾案件数量长度必须介于 0 和 100 之间")
	@ExcelField(title="火灾案件数量", align=2, sort=40)
	public String getFireNum() {
		return fireNum;
	}

	public void setFireNum(String fireNum) {
		this.fireNum = fireNum;
	}
	
	@Length(min=0, max=100, message="消防车数量长度必须介于 0 和 100 之间")
	@ExcelField(title="消防车数量", align=2, sort=40)
	public String getFireTruckNum() {
		return fireTruckNum;
	}

	public void setFireTruckNum(String fireTruckNum) {
		this.fireTruckNum = fireTruckNum;
	}
	
	@Length(min=0, max=100, message="消防队伍长度必须介于 0 和 100 之间")
	@ExcelField(title="消防队伍", align=2, sort=40)
	public String getFireBrigade() {
		return fireBrigade;
	}

	public void setFireBrigade(String fireBrigade) {
		this.fireBrigade = fireBrigade;
	}

	@ExcelField(title="统计日期", align=2, sort=40)
	public Date getStatisticDate() {
		return statisticDate;
	}

	public void setStatisticDate(Date statisticDate) {
		this.statisticDate = statisticDate;
	}

	@ExcelField(title="平安城市", align=2, sort=40)
	public String getSafeCity() {
		return safeCity;
	}

	public void setSafeCity(String safeCity) {
		this.safeCity = safeCity;
	}
}