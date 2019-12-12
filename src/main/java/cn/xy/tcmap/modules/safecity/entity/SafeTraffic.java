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
 * 交通违法案件Entity
 * @author tuo
 * @version 2019-12-05
 */
public class SafeTraffic extends DataEntity<SafeTraffic> {
	
	private static final long serialVersionUID = 1L;
	private String safeCityId;		// 平安城市主键
	private String area;		// 区域
	private String num;		// 交通违法案件数量
	private Date countDate;		// 统计日期

	private String safeCity;
	
	public SafeTraffic() {
		super();
	}

	public SafeTraffic(String id){
		super(id);
	}

	@Length(min=0, max=255, message="平安城市主键长度必须介于 0 和 255 之间")
	public String getSafeCityId() {
		return safeCityId;
	}

	public void setSafeCityId(String safeCityId) {
		this.safeCityId = safeCityId;
	}
	
	@Length(min=0, max=255, message="区域长度必须介于 0 和 255 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=11, message="交通违法案件数量长度必须介于 0 和 11 之间")
	@ExcelField(title="交通违法案件数量", align=2, sort=40)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="统计日期", align=2, sort=40)
	public Date getCountDate() {
		return countDate;
	}

	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}

	@ExcelField(title="平安城市", align=2, sort=10)
	public String getSafeCity() {
		return safeCity;
	}

	public void setSafeCity(String safeCity) {
		this.safeCity = safeCity;
	}
}