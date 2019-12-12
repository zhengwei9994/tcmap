/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 网上办理情况Entity
 * @author wufan
 * @version 2019-10-14
 */
public class LiveGovementSituation extends DataEntity<LiveGovementSituation> {
	
	private static final long serialVersionUID = 1L;
	private String net;		// 网上办理情况
	private Integer netSituation;		// 百分率
	
	public LiveGovementSituation() {
		super();
	}

	public LiveGovementSituation(String id){
		super(id);
	}

	@Length(min=0, max=255, message="网上办理情况长度必须介于 0 和 255 之间")
	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}
	
	public Integer getNetSituation() {
		return netSituation;
	}

	public void setNetSituation(Integer netSituation) {
		this.netSituation = netSituation;
	}
	
}