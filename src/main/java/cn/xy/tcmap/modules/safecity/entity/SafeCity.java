/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 平安城市Entity
 * @author tuo
 * @version 2019-12-04
 */
public class SafeCity extends DataEntity<SafeCity> {
	
	private static final long serialVersionUID = 1L;
	private String area;		// 区域
	private String name;		// 安全名称
	private String description;		// 安全描述
	
	public SafeCity() {
		super();
	}

	public SafeCity(String id){
		super(id);
	}

	@Length(min=0, max=255, message="区域长度必须介于 0 和 255 之间")
	@ExcelField(title="区域", align=2, sort=40)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=255, message="安全名称长度必须介于 0 和 255 之间")
	@ExcelField(title="安全名称", align=2, sort=40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="安全描述", align=2, sort=40)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}