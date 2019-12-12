/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.sys.entity;

import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.TreeEntity;

import javax.validation.constraints.NotNull;

/**
 * 区域Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;
//	private Area parent;	// 父级编号
//	private String parentIds; // 所有父级编号
	private String code; 	// 区域编码
//	private String name; 	// 区域名称
//	private Integer sort;		// 排序
	private String type; 	// 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
//	private String parentName;//父级区域
	public Area(){
		super();
		this.sort = 30;
	}

	public Area(String id){
		super(id);
	}
	
//	@JsonBackReference
//	@NotNull
	public Area getParent() {
		return parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}
//
//	@Length(min=1, max=2000)
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}
//	
	@Length(min=1, max=100)
	@ExcelField(title = "区域名称",align = 1,sort = 2)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}

//	@NotNull
//	@ExcelField(title = "上级区域",align = 1,sort = 3)
//	public String getParentName() {
//		if(this.parent!=null){
//			return this.parentName=parent.name;
//		}else {
//			return parentName;
//		}
//
//	}

//	public void setParentName(String parentName) {
//		this.parentName = parentName;
//	}

	@Length(min=1, max=1)
	@ExcelField(title = "区域类型",sort = 4,align = 1)//区域类型(国家：1；省份直辖市：2；地市：3；区县：4；其他：5)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min=0, max=100)
	@ExcelField(title = "区域编码",sort = 1,align = 1)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
//
//	public String getParentId() {
//		return parent != null && parent.getId() != null ? parent.getId() : "0";
//	}

	@Override
	public String toString() {
		return name;
	}
}