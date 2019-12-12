/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 资源详情Entity
 * @author liuyang
 * @version 2018-06-05
 */
public class CatchAssetDetails extends DataEntity<CatchAssetDetails> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String type;		// 资产类型
	private String cont;		// 详情
	private String imagePath;		// 展示图片
	
	public CatchAssetDetails() {
		super();
	}

	public CatchAssetDetails(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	@Length(min=1, max=255, message="资产类型长度必须介于 1 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}
	
	@Length(min=1, max=255, message="展示图片必须小于200k")
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}