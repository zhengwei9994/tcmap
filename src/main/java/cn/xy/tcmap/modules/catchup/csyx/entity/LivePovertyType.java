/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 贫困类型Entity
 * @author tuo
 * @version 2019-08-14
 */
public class LivePovertyType extends DataEntity<LivePovertyType> {
	
	private static final long serialVersionUID = 1L;
	private String area;		// 地区
	private String type;		// 类型
	private Integer num;		// num
	
	public LivePovertyType() {
		super();
	}

	public LivePovertyType(String id){
		super(id);
	}

	@Length(min=0, max=10, message="地区长度必须介于 0 和 10 之间")
	@ExcelField(title = "地区",sort = 10)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=50, message="类型长度必须介于 0 和 50 之间")
	@ExcelField(title = "贫困类型",sort = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ExcelField(title = "贫困人口",sort = 30)
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}