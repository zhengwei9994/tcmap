/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 多景区品牌聆听气泡图Entity
 * @author tuo
 * @version 2019-10-23
 */
public class TouristMultiscenicVolue extends DataEntity<TouristMultiscenicVolue> {
	
	private static final long serialVersionUID = 1L;
	private String month;		// 月
	private String year;		// 年
	private String brand;		// 品牌值
	private String scenic;		// 景区名
	private String volue;		// 声量
	
	public TouristMultiscenicVolue() {
		super();
	}

	public TouristMultiscenicVolue(String id){
		super(id);
	}

	@Length(min=1, max=255, message="月长度必须介于 1 和 255 之间")
	@ExcelField(title="月", align=2, sort=40)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	@ExcelField(title="年", align=2, sort=40)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Length(min=0, max=255, message="品牌值长度必须介于 0 和 255 之间")
	@ExcelField(title="品牌值", align=2, sort=40)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=255, message="景区名长度必须介于 0 和 255 之间")
	@ExcelField(title="景区名", align=2, sort=40)
	public String getScenic() {
		return scenic;
	}

	public void setScenic(String scenic) {
		this.scenic = scenic;
	}
	
	@Length(min=0, max=255, message="声量长度必须介于 0 和 255 之间")
	@ExcelField(title="声量", align=2, sort=40)
	public String getVolue() {
		return volue;
	}

	public void setVolue(String volue) {
		this.volue = volue;
	}
	
}