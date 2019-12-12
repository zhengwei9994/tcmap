/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicResult;
import cn.xy.tcmap.modules.catchup.sfmk.dao.AlgorithmicResultDao;

/**
 * 算法结果表Service
 * @author tuo
 * @version 2019-09-03
 */
@Service
@Transactional(readOnly = true)
public class AlgorithmicResultService extends CrudService<AlgorithmicResultDao, AlgorithmicResult> {

	public AlgorithmicResult get(String id) {
		return super.get(id);
	}
	
	public List<AlgorithmicResult> findList(AlgorithmicResult algorithmicResult) {
		return super.findList(algorithmicResult);
	}
	
	public Page<AlgorithmicResult> findPage(Page<AlgorithmicResult> page, AlgorithmicResult algorithmicResult) {
		return super.findPage(page, algorithmicResult);
	}
	
	@Transactional(readOnly = false)
	public void save(AlgorithmicResult algorithmicResult) {
		super.save(algorithmicResult);
	}
	
	@Transactional(readOnly = false)
	public void delete(AlgorithmicResult algorithmicResult) {
		super.delete(algorithmicResult);
	}
	
}