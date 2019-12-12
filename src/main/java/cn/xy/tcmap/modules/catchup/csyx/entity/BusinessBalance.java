/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 金融机构本外币存款余额Entity
 * @author wufan
 * @version 2019-07-31
 */
public class BusinessBalance extends DataEntity<BusinessBalance> {
	
	private static final long serialVersionUID = 1L;
	private String balance;		// 余额
	private String month;		// 月
	private String year;		// 年
	
	public BusinessBalance() {
		super();
	}

	public BusinessBalance(String id){
		super(id);
	}

	@Length(min=0, max=255, message="余额长度必须介于 0 和 255 之间")
	@ExcelField(title="余额", align=2, sort=20)
	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	@JsonFormat(pattern = "MM")
	@Length(min=0, max=255, message="月长度必须介于 0 和 255 之间")
	@ExcelField(title="月", align=2, sort=25)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@JsonFormat(pattern = "yyyy")
	@Length(min=0, max=255, message="年长度必须介于 0 和 255 之间")
	@ExcelField(title="年", align=2, sort=30)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}