/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sfmk.service;

import java.util.List;

import cn.xy.tcmap.common.utils.IdGen;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.sfmk.dao.AlgorithmicResultDao;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sfmk.entity.AlgorithmicExample;
import cn.xy.tcmap.modules.catchup.sfmk.dao.AlgorithmicExampleDao;

/**
 * 模型实例管理表Service
 * @author wufan
 * @version 2019-08-26
 */
@Service
@Transactional(readOnly = true)
public class AlgorithmicExampleService extends CrudService<AlgorithmicExampleDao, AlgorithmicExample> {
	@Autowired
	AlgorithmicResultDao algorithmicResultDao;

	public AlgorithmicExample get(String id) {
		return super.get(id);
	}
	
	public List<AlgorithmicExample> findList(AlgorithmicExample algorithmicExample) {
		return super.findList(algorithmicExample);
	}
	
	public Page<AlgorithmicExample> findPage(Page<AlgorithmicExample> page, AlgorithmicExample algorithmicExample) {
		return super.findPage(page, algorithmicExample);
	}
	
	@Transactional(readOnly = false)
	public void save(AlgorithmicExample algorithmicExample) {

		super.save(algorithmicExample);
		String algorithmicExampleId = algorithmicExample.getId();
        AlgorithmicResult  condition = new AlgorithmicResult();
        condition.setExampleId(algorithmicExampleId);
		List<AlgorithmicResult> results = algorithmicResultDao.findList(condition);
		if(results != null && results.size()>0){
            for (AlgorithmicResult result:results){
                result.setExampleId(algorithmicExampleId);
                algorithmicResultDao.update(result);
            }
        }else{
            for(int i=0;i<15;i++){
                AlgorithmicResult result = new AlgorithmicResult();
                result.setExampleId(algorithmicExampleId);
                result.setId(IdGen.uuid());
                algorithmicResultDao.insert(result);
            }
        }


	}

	//批量插入
	@Transactional(readOnly = false)
	public void saveList(List<AlgorithmicExample> algorithmicExampleList,String name) {
        String result = testAlgorithm(algorithmicExampleList);
        for (AlgorithmicExample algorithmicExample:algorithmicExampleList){
			if (algorithmicExample.getRootid()!=null){
                algorithmicExample.setResult(result);
                algorithmicExample.setName(name);
				super.save(algorithmicExample);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(AlgorithmicExample algorithmicExample) {
		super.delete(algorithmicExample);
	}

	//测试算法
	public String testAlgorithm(List<AlgorithmicExample> list){
        Double sum = new Double("0");
        for (AlgorithmicExample ae:list ){
            sum+=StringUtils.toDouble(ae.getNumvalue());
        }
        return StringUtils.toString(sum,"0");
    }
	
}