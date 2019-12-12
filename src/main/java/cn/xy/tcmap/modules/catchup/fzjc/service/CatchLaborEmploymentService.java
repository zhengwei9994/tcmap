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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchLaborEmployment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchLaborEmploymentDao;

/**
 * 劳动就业检索Service
 * @author gxq
 * @version 2018-10-18
 */
@Service
@Transactional(readOnly = true)
public class CatchLaborEmploymentService extends CrudService<CatchLaborEmploymentDao, CatchLaborEmployment> {

	public CatchLaborEmployment get(String id) {
		return super.get(id);
	}
	
	public List<CatchLaborEmployment> findList(CatchLaborEmployment catchLaborEmployment) {
		return super.findList(catchLaborEmployment);
	}
	
	public Page<CatchLaborEmployment> findPage(Page<CatchLaborEmployment> page, CatchLaborEmployment catchLaborEmployment) {
		return super.findPage(page, catchLaborEmployment);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchLaborEmployment catchLaborEmployment) {
		super.save(catchLaborEmployment);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchLaborEmployment catchLaborEmployment) {
		super.delete(catchLaborEmployment);
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
						CatchLaborEmployment objectClass  = (CatchLaborEmployment)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchLaborEmployment.class);
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