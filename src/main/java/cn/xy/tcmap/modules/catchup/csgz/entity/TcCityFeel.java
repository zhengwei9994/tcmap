/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csgz.entity;

import cn.xy.tcmap.common.utils.excel.fieldtype.AreaType;
import cn.xy.tcmap.modules.sys.entity.Area;
import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 城市感知数据管理Entity
 * @author wh
 * @version 2019-12-04
 */
public class TcCityFeel extends DataEntity<TcCityFeel> {
	
	private static final long serialVersionUID = 1L;
	private String focusArea;		// 重点区域
	private String keyEntranceTraffic;		// 重点出入口交通
	private String securityCheckpoint;		// 治安卡口
	private String focusAreaCover;		// 区域覆盖
	private String proportionHdCameras;		// 高清摄像机占比
	private String cameraIntactRate;		// 摄像机完好率
	public TcCityFeel() {
		super();
	}

	public TcCityFeel(String id){
		super(id);
	}

	@Length(min=0, max=255, message="重点区域长度必须介于 0 和 255 之间")
	@ExcelField(title="重点区域", align=2, sort=40)
	public String getFocusArea() {
		return focusArea;
	}

	public void setFocusArea(String focusArea) {
		this.focusArea = focusArea;
	}


	@ExcelField(title="重点出入口交通", align=2, sort=40)
	public String getKeyEntranceTraffic() {
		return keyEntranceTraffic;
	}

	public void setKeyEntranceTraffic(String keyEntranceTraffic) {
		this.keyEntranceTraffic = keyEntranceTraffic;
	}
	
	@ExcelField(title="治安卡口", align=2, sort=40)
	public String getSecurityCheckpoint() {
		return securityCheckpoint;
	}

	public void setSecurityCheckpoint(String securityCheckpoint) {
		this.securityCheckpoint = securityCheckpoint;
	}
	
	@ExcelField(title="区域覆盖", align=2, sort=40)
	public String getFocusAreaCover() {
		return focusAreaCover;
	}

	public void setFocusAreaCover(String focusAreaCover) {
		this.focusAreaCover = focusAreaCover;
	}
	
	@ExcelField(title="高清摄像机占比", align=2, sort=40)
	public String getProportionHdCameras() {
		return proportionHdCameras;
	}

	public void setProportionHdCameras(String proportionHdCameras) {
		this.proportionHdCameras = proportionHdCameras;
	}
	
	@ExcelField(title="摄像机完好率", align=2, sort=40)
	public String getCameraIntactRate() {
		return cameraIntactRate;
	}

	public void setCameraIntactRate(String cameraIntactRate) {
		this.cameraIntactRate = cameraIntactRate;
	}
	
}