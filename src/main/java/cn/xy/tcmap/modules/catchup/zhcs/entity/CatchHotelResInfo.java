/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 酒店资源信息Entity
 * @author wl
 * @version 2018-09-27
 */
public class CatchHotelResInfo extends DataEntity<CatchHotelResInfo> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String hotelType;		// 酒店类型
	private String hotelLevel;		// 酒店级别
	private String hotelCount;		// 酒店数
	private String hotelCheckCount;		// 入住数
	private String hotelTopNames;		// 排名前3,逗号分隔
	
	public CatchHotelResInfo() {
		super();
	}

	public CatchHotelResInfo(String id){
		super(id);
	}

	@Length(min=0, max=4, message="年份长度必须介于 0 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=0, max=20, message="酒店类型长度必须介于 0 和 20 之间")
	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	
	@Length(min=0, max=255, message="酒店级别长度必须介于 0 和 255 之间")
	public String getHotelLevel() {
		return hotelLevel;
	}

	public void setHotelLevel(String hotelLevel) {
		this.hotelLevel = hotelLevel;
	}
	
	@Length(min=0, max=255, message="酒店数长度必须介于 0 和 255 之间")
	public String getHotelCount() {
		return hotelCount;
	}

	public void setHotelCount(String hotelCount) {
		this.hotelCount = hotelCount;
	}
	
	@Length(min=0, max=255, message="入住数长度必须介于 0 和 255 之间")
	public String getHotelCheckCount() {
		return hotelCheckCount;
	}

	public void setHotelCheckCount(String hotelCheckCount) {
		this.hotelCheckCount = hotelCheckCount;
	}
	
	@Length(min=0, max=255, message="排名前3,逗号分隔长度必须介于 0 和 255 之间")
	public String getHotelTopNames() {
		return hotelTopNames;
	}

	public void setHotelTopNames(String hotelTopNames) {
		this.hotelTopNames = hotelTopNames;
	}
	
}