/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 旅游相关Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchTravelInfo extends DataEntity<CatchTravelInfo> {

	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String travelName;    //标题类型
	private String infoType;		// 信息类别
	private String infoValue;		// 信息值

	public CatchTravelInfo() {
		super();
	}

	public CatchTravelInfo(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}

	@Length(min=1, max=255, message="类型长度必须介于 1 和 255 之间")
	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	@Length(min=1, max=255, message="类型长度必须介于 1 和 255 之间")
	public String getInfoType() {
		return infoType;
	}

	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	@Length(min=1, max=255, message="值长度必须介于 1 和 255 之间")
	public String getInfoValue() {
		return infoValue;
	}

	public void setInfoValue(String infoValue) {
		this.infoValue = infoValue;
	}
}