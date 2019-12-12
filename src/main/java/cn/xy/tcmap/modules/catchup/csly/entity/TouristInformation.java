/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 景区资讯排行Entity
 * @author wufan
 * @version 2019-10-23
 */
public class TouristInformation extends DataEntity<TouristInformation> {
	
	private static final long serialVersionUID = 1L;
	private String information;		// 资讯
	private String media;		// 媒体
	private String volume;		// 新闻量
	private Date date;		// 时间
	
	public TouristInformation() {
		super();
	}

	public TouristInformation(String id){
		super(id);
	}

	@Length(min=0, max=255, message="资讯长度必须介于 0 和 255 之间")
	@ExcelField(title="资讯", align=2, sort=40)
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
	
	@Length(min=0, max=255, message="媒体长度必须介于 0 和 255 之间")
	@ExcelField(title="媒体", align=2, sort=40)
	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
	
	@Length(min=0, max=255, message="新闻量长度必须介于 0 和 255 之间")
	@ExcelField(title="新闻量", align=2, sort=40)
	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="时间", align=2, sort=40)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}