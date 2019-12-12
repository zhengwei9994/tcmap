/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicRoot;
import cn.xy.tcmap.modules.catchup.sfmk.dao.AlgorithmicRootDao;

/**
 * 模型管理主表Service
 * @author wufan
 * @version 2019-08-22
 */
@Service
@Transactional(readOnly = true)
public class AlgorithmicRootService extends CrudService<AlgorithmicRootDao, AlgorithmicRoot> {

	public AlgorithmicRoot get(String id) {
		return super.get(id);
	}
	
	public List<AlgorithmicRoot> findList(AlgorithmicRoot algorithmicRoot) {
		return super.findList(algorithmicRoot);
	}
	
	public Page<AlgorithmicRoot> findPage(Page<AlgorithmicRoot> page, AlgorithmicRoot algorithmicRoot) {
		return super.findPage(page, algorithmicRoot);
	}
	
	@Transactional(readOnly = false)
	public void save(AlgorithmicRoot algorithmicRoot) {
		super.save(algorithmicRoot);
	}
	
	@Transactional(readOnly = false)
	public void delete(AlgorithmicRoot algorithmicRoot) {
		super.delete(algorithmicRoot);
	}
	
}