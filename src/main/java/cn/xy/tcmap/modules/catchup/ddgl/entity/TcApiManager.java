/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.ddgl.entity;

import org.hibernate.validator.constraints.Length;
import cn.xy.tcmap.common.utils.excel.annotation.ExcelField;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 接口调度管理Entity
 * @author wh
 * @version 2019-12-06
 */
public class TcApiManager extends DataEntity<TcApiManager> {
	
	private static final long serialVersionUID = 1L;
	private String apiName;		// 接口名称
	private String apiUrl;		// 接口地址
	private String apiType;		// 接口类型
	private String requestType;		// 请求类型
	private String apiDes;		// 接口描述
	private String requestParam;		// 请求参数
	private String returnParam;		// 返回参数
	private String apiStatus;		// 接口状态
	private Date releaseTime;		// 发布时间
	private String sysDomain;		// 系统域名
	private TcSyserviceMananger tcSyserviceMananger;
	public TcApiManager() {
		super();
	}

	public TcApiManager(String id){
		super(id);
	}

	@Length(min=0, max=255, message="接口名称长度必须介于 0 和 255 之间")
	@ExcelField(title="接口名称", align=2, sort=40)
	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
	@Length(min=0, max=255, message="接口地址长度必须介于 0 和 255 之间")
	@ExcelField(title="接口地址", align=2, sort=40)
	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	@Length(min=0, max=255, message="接口类型长度必须介于 0 和 255 之间")
	@ExcelField(title="接口类型", align=2, sort=40)
	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	
	@Length(min=0, max=255, message="请求类型长度必须介于 0 和 255 之间")
	@ExcelField(title="请求类型", align=2, sort=40)
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	@Length(min=0, max=255, message="接口描述长度必须介于 0 和 255 之间")
	@ExcelField(title="接口描述", align=2, sort=40)
	public String getApiDes() {
		return apiDes;
	}

	public void setApiDes(String apiDes) {
		this.apiDes = apiDes;
	}
	
	@Length(min=0, max=255, message="请求参数长度必须介于 0 和 255 之间")
	@ExcelField(title="请求参数", align=2, sort=40)
	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
	
	@Length(min=0, max=255, message="返回参数长度必须介于 0 和 255 之间")
	@ExcelField(title="返回参数", align=2, sort=40)
	public String getReturnParam() {
		return returnParam;
	}

	public void setReturnParam(String returnParam) {
		this.returnParam = returnParam;
	}
	
	@Length(min=0, max=255, message="接口状态长度必须介于 0 和 255 之间")
	@ExcelField(title="接口状态", align=2, sort=40)
	public String getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(String apiStatus) {
		this.apiStatus = apiStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发布时间", align=2, sort=40)
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Length(min=0, max=255, message="系统域名长度必须介于 0 和 255 之间")
	@ExcelField(title="系统域名", align=2, sort=40)
	public String getSysDomain() {
	    if(this.tcSyserviceMananger!=null){
            return sysDomain=setValue(tcSyserviceMananger);
        }
		 return sysDomain;
	}
    public static String setValue(Object val) {
        if (val != null && ((TcSyserviceMananger)val).getSysName() != null){
            return ((TcSyserviceMananger)val).getSysName();
        }
        return "";
    }

    public TcSyserviceMananger getTcSyserviceMananger() {
        return tcSyserviceMananger;
    }

    public void setTcSyserviceMananger(TcSyserviceMananger tcSyserviceMananger) {
        this.tcSyserviceMananger = tcSyserviceMananger;
    }

    public void setSysDomain(String sysDomain) {

		this.sysDomain = sysDomain;
	}
	
}