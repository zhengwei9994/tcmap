/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 模型管理主表Entity
 * @author wufan
 * @version 2019-08-22
 */
public class AlgorithmicRoot extends DataEntity<AlgorithmicRoot> {
	
	private static final long serialVersionUID = 1L;
	private String algorithmic;		// 算法名称
	private String type;		// 算法类型
	private String result;		// 结果值
	
	public AlgorithmicRoot() {
		super();
	}

	public AlgorithmicRoot(String id){
		super(id);
	}

	@Length(min=0, max=255, message="算法名称长度必须介于 0 和 255 之间")
	public String getAlgorithmic() {
		return algorithmic;
	}

	public void setAlgorithmic(String algorithmic) {
		this.algorithmic = algorithmic;
	}
	
	@Length(min=0, max=255, message="算法类型长度必须介于 0 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="结果值长度必须介于 0 和 255 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}