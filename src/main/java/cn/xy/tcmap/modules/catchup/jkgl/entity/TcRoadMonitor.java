/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.jkgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 路况监控Entity
 * @author wufan
 * @version 2019-12-05
 */
public class TcRoadMonitor extends DataEntity<TcRoadMonitor> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String adress;		// 地址
	private String longitude;		// 经度
	private String dimension;		// 纬度
	private String pixel;		// 像素
	private String resolvingPower;		// 分辨率
	private String netInterface;		// 接口
	private String agreement;		// 协议
	private String source;		// 来源
	private String isShow;		// 是否展示
	
	public TcRoadMonitor() {
		super();
	}

	public TcRoadMonitor(String id){
		super(id);
	}

	@Length(min=0, max=20, message="名称长度必须介于 0 和 20 之间")
	@ExcelField(title="名称", align=2, sort=40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=50, message="地址长度必须介于 0 和 50 之间")
	@ExcelField(title="地址", align=2, sort=40)
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@ExcelField(title="经度", align=2, sort=40)
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
	
	@Length(min=0, max=20, message="像素长度必须介于 0 和 20 之间")
	@ExcelField(title="像素", align=2, sort=40)
	public String getPixel() {
		return pixel;
	}

	public void setPixel(String pixel) {
		this.pixel = pixel;
	}
	
	@Length(min=0, max=50, message="分辨率长度必须介于 0 和 50 之间")
	@ExcelField(title="分辨率", align=2, sort=40)
	public String getResolvingPower() {
		return resolvingPower;
	}

	public void setResolvingPower(String resolvingPower) {
		this.resolvingPower = resolvingPower;
	}
	
	@Length(min=0, max=50, message="接口长度必须介于 0 和 50 之间")
	@ExcelField(title="接口", align=2, sort=40)
	public String getNetInterface() {
		return netInterface;
	}

	public void setNetInterface(String netInterface) {
		this.netInterface = netInterface;
	}
	
	@Length(min=0, max=50, message="协议长度必须介于 0 和 50 之间")
	@ExcelField(title="协议", align=2, sort=40)
	public String getAgreement() {
		return agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	
	@Length(min=0, max=50, message="来源长度必须介于 0 和 50 之间")
	@ExcelField(title="来源", align=2, sort=40)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=1, message="是否展示长度必须介于 0 和 1 之间")
	@ExcelField(title="是否展示", align=2, sort=40)
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
}