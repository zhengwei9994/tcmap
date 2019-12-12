/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 当日游客量Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristVolumeTime extends DataEntity<TouristVolumeTime> {
	
	private static final long serialVersionUID = 1L;
	private String time;		// time
	private String date;		// date
	private String num;		// num
	private String scenic;//景区名称
	
	public TouristVolumeTime() {
		super();
	}

	public TouristVolumeTime(String id){
		super(id);
	}

	@Length(min=0, max=255, message="time长度必须介于 0 和 255 之间")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Length(min=0, max=255, message="date长度必须介于 0 和 255 之间")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Length(min=0, max=255, message="num长度必须介于 0 和 255 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Length(min=0, max=255, message="景区名称长度必须介于 0 和 255 之间")
	public String getScenic() {
		return scenic;
	}

	public void setScenic(String scenic) {
		this.scenic = scenic;
	}
}