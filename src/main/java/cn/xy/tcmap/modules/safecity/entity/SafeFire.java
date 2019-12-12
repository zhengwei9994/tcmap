/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.safecity.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 消防安全Entity
 * @author tuo
 * @version 2019-12-05
 */
public class SafeFire extends DataEntity<SafeFire> {
	
	private static final long serialVersionUID = 1L;
	private String safeCityId;		// 平安城市主键
	private String fireNum;		// 火灾案件数量
	private Date countDate;		// 统计年月

	private String safeCity;
	
	public SafeFire() {
		super();
	}

	public SafeFire(String id){
		super(id);
	}

	@Length(min=0, max=100, message="平安城市主键长度必须介于 0 和 100 之间")
	public String getSafeCityId() {
		return safeCityId;
	}

	public void setSafeCityId(String safeCityId) {
		this.safeCityId = safeCityId;
	}
	
	@Length(min=0, max=11, message="火灾案件数量长度必须介于 0 和 11 之间")
	@ExcelField(title="火灾案件数量", align=2, sort=40)
	public String getFireNum() {
		return fireNum;
	}

	public void setFireNum(String fireNum) {
		this.fireNum = fireNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM")
	@ExcelField(title="统计年月", align=2, sort=40)
	public Date getCountDate() {
		return countDate;
	}

	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}

	@ExcelField(title="平安城市", align=2, sort=30)
	public String getSafeCity() {
		return safeCity;
	}

	public void setSafeCity(String safeCity) {
		this.safeCity = safeCity;
	}
}