/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchDataStatus;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchSalaryStructure;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchSalaryStructureDao;

/**
 * 人才结构分析Service
 * @author gxq
 * @version 2018-10-18
 */
@Service
@Transactional(readOnly = true)
public class CatchSalaryStructureService extends CrudService<CatchSalaryStructureDao, CatchSalaryStructure> {

	public CatchSalaryStructure get(String id) {
		return super.get(id);
	}
	
	public List<CatchSalaryStructure> findList(CatchSalaryStructure catchSalaryStructure) {
		return super.findList(catchSalaryStructure);
	}
	
	public Page<CatchSalaryStructure> findPage(Page<CatchSalaryStructure> page, CatchSalaryStructure catchSalaryStructure) {
		return super.findPage(page, catchSalaryStructure);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchSalaryStructure catchSalaryStructure) {
		super.save(catchSalaryStructure);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSalaryStructure catchSalaryStructure) {
		super.delete(catchSalaryStructure);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params) {
		//通过接口获取数据
		String str = HttpUtils.post(url, params);
		if(StringUtils.isNotBlank(str)){  
			Object object = JSONObject.fromObject(str).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					for (Object jsonObject : jsonArray) {
						CatchSalaryStructure objectClass  = (CatchSalaryStructure)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchSalaryStructure.class);
						if(!(super.findList(objectClass).size() > 0)){
							//如果不存在 则库里进行添加
							objectClass.setId(null);
						}
						super.save(objectClass);	
					}
				}
			}
		};
	}
}