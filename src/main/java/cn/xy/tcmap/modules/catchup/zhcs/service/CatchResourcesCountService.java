/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchResourcesCount;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchResourcesCountDao;

/**
 * 人才类型统计Service
 * @author liuyang
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class CatchResourcesCountService extends CrudService<CatchResourcesCountDao, CatchResourcesCount> {

	public CatchResourcesCount get(String id) {
		return super.get(id);
	}
	
	public List<CatchResourcesCount> findList(CatchResourcesCount catchResourcesCount) {
		return super.findList(catchResourcesCount);
	}
	
	public Page<CatchResourcesCount> findPage(Page<CatchResourcesCount> page, CatchResourcesCount catchResourcesCount) {
		return super.findPage(page, catchResourcesCount);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchResourcesCount catchResourcesCount) {
		super.save(catchResourcesCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchResourcesCount catchResourcesCount) {
		super.delete(catchResourcesCount);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params) {
		//通过接口获取数据
		String str = HttpUtils.post(url,params);
		if(StringUtils.isNotBlank(str)){
			Object object = JSONObject.fromObject(str).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					for (Object jsonObject : jsonArray) {
						JSONObject json = JSONObject.fromObject(jsonObject);
						CatchResourcesCount catchResourcesCount  = (CatchResourcesCount)JSONObject.toBean(json,CatchResourcesCount.class);
						catchResourcesCount.setPersonnelType(json.getString("personnel_type"));
						if(!(super.findList(catchResourcesCount).size()>0)){
							catchResourcesCount.setId(null);
						}
						super.save(catchResourcesCount);
					}
				}

			}

		};
	}
}