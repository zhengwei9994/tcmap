/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicParameter;
import cn.xy.tcmap.modules.catchup.sfmk.dao.AlgorithmicParameterDao;

/**
 * 模型因子管理表Service
 * @author wufan
 * @version 2019-08-22
 */
@Service
@Transactional(readOnly = true)
public class AlgorithmicParameterService extends CrudService<AlgorithmicParameterDao, AlgorithmicParameter> {

	public AlgorithmicParameter get(String id) {
		return super.get(id);
	}
	
	public List<AlgorithmicParameter> findList(AlgorithmicParameter algorithmicParameter) {
		return super.findList(algorithmicParameter);
	}

	public Page<AlgorithmicParameter> findPage(Page<AlgorithmicParameter> page, AlgorithmicParameter algorithmicParameter) {
		return super.findPage(page, algorithmicParameter);
	}
	
	@Transactional(readOnly = false)
	public void save(AlgorithmicParameter algorithmicParameter) {
		super.save(algorithmicParameter);
	}
	
	@Transactional(readOnly = false)
	public void delete(AlgorithmicParameter algorithmicParameter) {
		super.delete(algorithmicParameter);
	}
	
}