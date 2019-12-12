/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.entity;

import org.hibernate.validator.constraints.Length;

import cn.xy.tcmap.common.persistence.DataEntity;

/**
 * 公共安全视频资源覆盖情况Entity
 * @author liuyang
 * @version 2018-05-29
 */
public class CatchVideoResource extends DataEntity<CatchVideoResource> {
	
	private static final long serialVersionUID = 1L;
	private String nyear;		// 年份
	private String keyRegionalCoverage;		// 重点区域覆盖率
	private String coverageKeyAreas;		// 重点领域覆盖率
	private String highDefinitionCamera;		// 高清摄像机比率
	private String cameraIntegrityRatio;		// 摄像机完好比率
	private String machineCompletionKey;		// 重点领域机器完好率
	
	public CatchVideoResource() {
		super();
	}

	public CatchVideoResource(String id){
		super(id);
	}

	@Length(min=1, max=4, message="年份长度必须介于 1 和 4 之间")
	public String getNyear() {
		return nyear;
	}

	public void setNyear(String nyear) {
		this.nyear = nyear;
	}
	
	public String getKeyRegionalCoverage() {
		return keyRegionalCoverage;
	}

	public void setKeyRegionalCoverage(String keyRegionalCoverage) {
		this.keyRegionalCoverage = keyRegionalCoverage;
	}
	
	public String getCoverageKeyAreas() {
		return coverageKeyAreas;
	}

	public void setCoverageKeyAreas(String coverageKeyAreas) {
		this.coverageKeyAreas = coverageKeyAreas;
	}
	
	public String getHighDefinitionCamera() {
		return highDefinitionCamera;
	}

	public void setHighDefinitionCamera(String highDefinitionCamera) {
		this.highDefinitionCamera = highDefinitionCamera;
	}
	
	public String getCameraIntegrityRatio() {
		return cameraIntegrityRatio;
	}

	public void setCameraIntegrityRatio(String cameraIntegrityRatio) {
		this.cameraIntegrityRatio = cameraIntegrityRatio;
	}
	
	public String getMachineCompletionKey() {
		return machineCompletionKey;
	}

	public void setMachineCompletionKey(String machineCompletionKey) {
		this.machineCompletionKey = machineCompletionKey;
	}
	
}