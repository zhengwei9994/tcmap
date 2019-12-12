/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 算法结果表Entity
 * @author tuo
 * @version 2019-09-03
 */
public class AlgorithmicResult extends DataEntity<AlgorithmicResult> {
	
	private static final long serialVersionUID = 1L;
	private String exampleId;		// 实例ID
	private String resultName;		// 结果名称
	private String result;		// 结果集json
	
	public AlgorithmicResult() {
		super();
	}

	public AlgorithmicResult(String id){
		super(id);
	}

	@Length(min=0, max=255, message="实例ID长度必须介于 0 和 255 之间")
	public String getExampleId() {
		return exampleId;
	}

	public void setExampleId(String exampleId) {
		this.exampleId = exampleId;
	}
	
	@Length(min=0, max=255, message="结果名称长度必须介于 0 和 255 之间")
	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}