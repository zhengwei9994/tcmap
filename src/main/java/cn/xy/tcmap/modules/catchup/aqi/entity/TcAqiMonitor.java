/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.aqi.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 气象数据Entity
 * @author 王浩
 * @version 2019-12-03
 */
public class TcAqiMonitor extends DataEntity<TcAqiMonitor> {
	
	private static final long serialVersionUID = 1L;
	private String city;		// 城市
	private String aqi;		// 空气质量指数
	private String fl;		// 风力
	private String fx;		// 风向
	private String highTemprature;		// 最高温
	private String lowTemprature;		// 最低温
	private String notice;		// 温馨提示
	private String sunRise;		// 日出
	private String sunSet;		// 日落
	private String week;		// 星期
	private String weather;		// 天气
	private String updateTime;		// 数据更新时间
	private Date createTime;		// 数据存入时间
	private String pm25;		// PM25
	private String pm10;		// PM10
	private String ymd;		// 气象数据日期
	
	public TcAqiMonitor() {
		super();
	}

	public TcAqiMonitor(String id){
		super(id);
	}

	@Length(min=0, max=255, message="城市长度必须介于 0 和 255 之间")
	@ExcelField(title="城市", align=2, sort=40)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@ExcelField(title="空气质量指数", align=2, sort=40)
	public String getAqi() {
		return aqi;
	}

	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	
	@Length(min=0, max=8, message="风力长度必须介于 0 和 8之间")
	@ExcelField(title="风力", align=2, sort=40)
	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}
	
	@ExcelField(title="风向", align=2, sort=40)
	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}
	
	@Length(min=0, max=8, message="最高温长度必须介于 0 和 8 之间")
	@ExcelField(title="最高温", align=2, sort=40)
	public String getHighTemprature() {
		return highTemprature;
	}

	public void setHighTemprature(String highTemprature) {
		this.highTemprature = highTemprature;
	}
	
	@Length(min=0, max=8, message="最低温长度必须介于 0 和 8 之间")
	@ExcelField(title="最低温", align=2, sort=40)
	public String getLowTemprature() {
		return lowTemprature;
	}

	public void setLowTemprature(String lowTemprature) {
		this.lowTemprature = lowTemprature;
	}
	
	@Length(min=0, max=255, message="温馨提示长度必须介于 0 和 255 之间")
	@ExcelField(title="温馨提示", align=2, sort=40)
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	@JsonFormat(pattern = "mm:ss")
	@ExcelField(title="日出", align=2, sort=40)
	public String getSunRise() {
		return sunRise;
	}

	public void setSunRise(String sunRise) {
		this.sunRise = sunRise;
	}
	
	@JsonFormat(pattern = "mm:ss")
	@ExcelField(title="日落", align=2, sort=40)
	public String getSunSet() {
		return sunSet;
	}

	public void setSunSet(String sunSet) {
		this.sunSet = sunSet;
	}
	
	@Length(min=0, max=4, message="星期长度必须介于 0 和 4 之间")
	@ExcelField(title="星期", align=2, sort=40)
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
	
	@Length(min=0, max=255, message="天气长度必须介于 0 和 255 之间")
	@ExcelField(title="天气", align=2, sort=40)
	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	@JsonFormat(pattern = "mm:ss")
	@ExcelField(title="数据更新时间", align=2, sort=40)
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd ")
	@ExcelField(title="数据存入时间", align=2, sort=40)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=8, message="PM25长度必须介于 0 和 8 之间")
	@ExcelField(title="PM25", align=2, sort=40)
	public String getPm25() {
		return pm25;
	}

	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	
	@Length(min=0, max=8, message="PM10长度必须介于 0 和 8 之间")
	@ExcelField(title="PM10", align=2, sort=40)
	public String getPm10() {
		return pm10;
	}

	public void setPm10(String pm10) {

		this.pm10 = pm10;
	}
	
	@Length(min=0, max=25, message="气象数据日期长度必须介于 0 和 25 之间")
    @JsonFormat(pattern = "yyyy-MM-dd ")
	@ExcelField(title="气象数据日期", align=2, sort=40)
	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	
}