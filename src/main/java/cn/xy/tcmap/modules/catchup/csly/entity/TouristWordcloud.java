/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 词云Entity
 * @author wufan
 * @version 2019-08-16
 */
public class TouristWordcloud extends DataEntity<TouristWordcloud> {
	
	private static final long serialVersionUID = 1L;
	private String word;		// word
	private String num;		// num
	
	public TouristWordcloud() {
		super();
	}

	public TouristWordcloud(String id){
		super(id);
	}

	@Length(min=0, max=255, message="word长度必须介于 0 和 255 之间")
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	@Length(min=0, max=30, message="num长度必须介于 0 和 30 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
}