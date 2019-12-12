/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.jkgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 危险源监控Entity
 * @author wufan
 * @version 2019-12-05
 */
public class TcDangerMonitor extends DataEntity<TcDangerMonitor> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 企业名称
	private String code;		// 组织机构代码
	private String representative;		// 代表人
	private String adress;		// 生产地址
	private String contactInformation;		// 联系方式
	private String industry;		// 所属行业
	private String pollutantsType;		// 污染物种类
	private String pollutantsName;		// 污染物名称
	private String emissions;		// 排放量
	private String isOver;		// 是否超标（1：超标，0：未超标）
	private String dangerGrade;		// 危险等级
	
	public TcDangerMonitor() {
		super();
	}

	public TcDangerMonitor(String id){
		super(id);
	}

	@Length(min=0, max=50, message="企业名称长度必须介于 0 和 50 之间")
	@ExcelField(title="企业名称", align=2, sort=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="组织机构代码长度必须介于 0 和 20 之间")
	@ExcelField(title="组织机构代码", align=2, sort=15)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=10, message="代表人长度必须介于 0 和 10 之间")
	@ExcelField(title="代表人", align=2, sort=20)
	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	
	@Length(min=0, max=50, message="生产地址长度必须介于 0 和 50 之间")
	@ExcelField(title="生产地址", align=2, sort=25)
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Length(min=0, max=20, message="联系方式长度必须介于 0 和 20 之间")
	@ExcelField(title="联系方式", align=2, sort=30)
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	@Length(min=0, max=20, message="所属行业长度必须介于 0 和 20 之间")
	@ExcelField(title="所属行业", align=2, sort=35)
	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	@Length(min=0, max=20, message="污染物种类长度必须介于 0 和 20 之间")
	@ExcelField(title="污染物种类", align=2, sort=40)
	public String getPollutantsType() {
		return pollutantsType;
	}

	public void setPollutantsType(String pollutantsType) {
		this.pollutantsType = pollutantsType;
	}
	
	@Length(min=0, max=20, message="污染物名称长度必须介于 0 和 20 之间")
	@ExcelField(title="污染物名称", align=2, sort=45)
	public String getPollutantsName() {
		return pollutantsName;
	}

	public void setPollutantsName(String pollutantsName) {
		this.pollutantsName = pollutantsName;
	}
	
	@Length(min=0, max=10, message="排放量长度必须介于 0 和 10 之间")
	@ExcelField(title="排放量", align=2, sort=50)
	public String getEmissions() {
		return emissions;
	}

	public void setEmissions(String emissions) {
		this.emissions = emissions;
	}
	
	@Length(min=0, max=1, message="是否超标（1：超标，0：未超标）长度必须介于 0 和 1 之间")
	@ExcelField(title="是否超标（1：超标，0：未超标）", align=2, sort=55)
	public String getIsOver() {
		return isOver;
	}

	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}
	
	@Length(min=0, max=10, message="危险等级长度必须介于 0 和 10 之间")
	@ExcelField(title="危险等级", align=2, sort=60)
	public String getDangerGrade() {
		return dangerGrade;
	}

	public void setDangerGrade(String dangerGrade) {
		this.dangerGrade = dangerGrade;
	}
	
}