/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 责任人管理Entity
 * @author wh
 * @version 2019-12-05
 */
public class TcManagerInfo extends DataEntity<TcManagerInfo> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 姓名
	private String phone;		// 电话
	private String location;		// 地址
	private String role;		// 职位
	private String email;		// email
	
	public TcManagerInfo() {
		super();
	}

	public TcManagerInfo(String id){
		super(id);
	}

	@Length(min=0, max=255, message="姓名长度必须介于 0 和 255 之间")
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="电话长度必须介于 0 和 255 之间")
	@ExcelField(title="电话", align=2, sort=40)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	@ExcelField(title="地址", align=2, sort=40)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=255, message="职位长度必须介于 0 和 255 之间")
	@ExcelField(title="职位", align=2, sort=40)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Length(min=0, max=255, message="email长度必须介于 0 和 255 之间")
	@ExcelField(title="email", align=2, sort=40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}