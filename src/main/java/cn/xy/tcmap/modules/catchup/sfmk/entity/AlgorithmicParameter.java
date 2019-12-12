/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 模型因子管理表Entity
 * @author wufan
 * @version 2019-08-22
 */
public class AlgorithmicParameter extends DataEntity<AlgorithmicParameter> {
	
	private static final long serialVersionUID = 1L;
	private String algorithmicid;		// 算法主表id
	private String parametername;		// 参数名
	private String parametertype;		// 参数类型
	
	public AlgorithmicParameter() {
		super();
	}

	public AlgorithmicParameter(String id){
		super(id);
	}

	@Length(min=1, max=100, message="算法主表id长度必须介于 1 和 100 之间")
	public String getAlgorithmicid() {
		return algorithmicid;
	}

	public void setAlgorithmicid(String algorithmicid) {
		this.algorithmicid = algorithmicid;
	}
	
	@Length(min=0, max=255, message="参数名长度必须介于 0 和 255 之间")
	public String getParametername() {
		return parametername;
	}

	public void setParametername(String parametername) {
		this.parametername = parametername;
	}
	
	@Length(min=0, max=255, message="参数类型长度必须介于 0 和 255 之间")
	public String getParametertype() {
		return parametertype;
	}

	public void setParametertype(String parametertype) {
		this.parametertype = parametertype;
	}
	
}