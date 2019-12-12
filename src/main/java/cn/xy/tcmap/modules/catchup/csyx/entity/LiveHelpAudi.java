/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 帮扶受众Entity
 * @author tuo
 * @version 2019-08-14
 */
public class LiveHelpAudi extends DataEntity<LiveHelpAudi> {
	
	private static final long serialVersionUID = 1L;
	private String helpType;		// 帮扶类型
	private Integer households;		// 户数
	private Integer population;		// 人口
	
	public LiveHelpAudi() {
		super();
	}

	public LiveHelpAudi(String id){
		super(id);
	}

	@Length(min=0, max=100, message="帮扶类型长度必须介于 0 和 100 之间")
	@ExcelField(title = "帮扶类型",sort = 10)
	public String getHelpType() {
		return helpType;
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}
	@ExcelField(title = "户数",sort = 20)
	public Integer getHouseholds() {
		return households;
	}

	public void setHouseholds(Integer households) {
		this.households = households;
	}

	@ExcelField(title = "人口",sort = 30)
	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}
	
}