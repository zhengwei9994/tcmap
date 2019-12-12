/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.entity;

import cn.xy.tcmap.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 模型实例管理表Entity
 * @author wufan
 * @version 2019-08-26
 */
public class AlgorithmicExampleList extends DataEntity<AlgorithmicExampleList> {

	private List<AlgorithmicExample> algorithmicExampleList;

	public List<AlgorithmicExample> getAlgorithmicExampleList() {
		return algorithmicExampleList;
	}

	public void setAlgorithmicExampleList(List<AlgorithmicExample> algorithmicExampleList) {
		this.algorithmicExampleList = algorithmicExampleList;
	}

	public AlgorithmicExampleList(){
		super();
	}

	public AlgorithmicExampleList(List<AlgorithmicExample> algorithmicExampleList){
		super();
		this.algorithmicExampleList=algorithmicExampleList;
	}
}