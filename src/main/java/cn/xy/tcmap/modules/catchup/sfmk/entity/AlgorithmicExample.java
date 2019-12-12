/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 模型实例管理表Entity
 * @author wufan
 * @version 2019-08-26
 */
public class AlgorithmicExample extends DataEntity<AlgorithmicExample> {
	
	private static final long serialVersionUID = 1L;
	private String rootid;		// 模型主表id
	private String parameterid;		// 模型参数id
	private String numvalue;		// 数值
	private String result;		// 结果
	private String name;    //实例名称

	public AlgorithmicExample() {
		super();
	}

	public AlgorithmicExample(String id){
		super(id);
	}

	@Length(min=0, max=100, message="模型主表id长度必须介于 0 和 100 之间")
	public String getRootid() {
		return rootid;
	}

	public void setRootid(String rootid) {
		this.rootid = rootid;
	}
	
	@Length(min=0, max=100, message="模型参数id长度必须介于 0 和 100 之间")
	public String getParameterid() {
		return parameterid;
	}

	public void setParameterid(String parameterid) {
		this.parameterid = parameterid;
	}
	
	@Length(min=0, max=255, message="数值长度必须介于 0 和 255 之间")
	public String getNumvalue() {
		return numvalue;
	}

	public void setNumvalue(String numvalue) {
		this.numvalue = numvalue;
	}
	
	@Length(min=0, max=255, message="结果长度必须介于 0 和 255 之间")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Length(min=0, max=255, message="结果长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}