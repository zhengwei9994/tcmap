/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfqgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 示范区管理Entity
 * @author wufan
 * @version 2019-12-04
 */
public class TcDemonstrationArea extends DataEntity<TcDemonstrationArea> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String address;		// 地址
	private String details;		// 描述
	private String longitude;		// 经度
	private String dimension;		// 纬度
	private String source;		// 坐标来源
	
	public TcDemonstrationArea() {
		super();
	}

	public TcDemonstrationArea(String id){
		super(id);
	}

	@Length(min=0, max=20, message="名称长度必须介于 0 和 20 之间")
	@ExcelField(title="名称", align=2, sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="地址长度必须介于 0 和 50 之间")
	@ExcelField(title="地址", align=2, sort=25)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=255, message="描述长度必须介于 0 和 255 之间")
	@ExcelField(title="描述", align=2, sort=30)
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	@ExcelField(title="经度", align=2, sort=35)
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@ExcelField(title="纬度", align=2, sort=40)
	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	
	@Length(min=0, max=10, message="坐标来源长度必须介于 0 和 10 之间")
	@ExcelField(title="坐标来源", align=2, sort=45)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}