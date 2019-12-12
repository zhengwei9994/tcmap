/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.ddgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 服务系统管理Entity
 * @author wh
 * @version 2019-12-06
 */
public class TcSyserviceMananger extends DataEntity<TcSyserviceMananger> {
	
	private static final long serialVersionUID = 1L;
	private String sysName;		// 系统名
	private String sysDomain;		// 系统域名
	private String sysCode;		// 系统标识
	private String keyWords;		// 关键词
	private String sysIntro;		// 系统简介
	
	public TcSyserviceMananger() {
		super();
	}

	public TcSyserviceMananger(String id){
		super(id);
	}

	@Length(min=0, max=255, message="系统名长度必须介于 0 和 255 之间")
	@ExcelField(title="系统名", align=2, sort=40)
	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	@Length(min=0, max=255, message="系统域名长度必须介于 0 和 255 之间")
	@ExcelField(title="系统域名", align=2, sort=40)
	public String getSysDomain() {
		return sysDomain;
	}

	public void setSysDomain(String sysDomain) {
		this.sysDomain = sysDomain;
	}
	
	@Length(min=0, max=255, message="系统标识长度必须介于 0 和 255 之间")
	@ExcelField(title="系统标识", align=2, sort=40)
	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	@Length(min=0, max=255, message="关键词长度必须介于 0 和 255 之间")
	@ExcelField(title="关键词", align=2, sort=40)
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	@Length(min=0, max=255, message="系统简介长度必须介于 0 和 255 之间")
	@ExcelField(title="系统简介", align=2, sort=40)
	public String getSysIntro() {
		return sysIntro;
	}

	public void setSysIntro(String sysIntro) {
		this.sysIntro = sysIntro;
	}
	
}