/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchEconomicGrowth;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchIndexName;
import cn.xy.tcmap.modules.catchup.csyx.entity.CathEconomic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchEconomicGrowthDao;

/**
 * 区域经济增长Service
 * @author gxq
 * @version 2018-11-05
 */
@Service
@Transactional(readOnly = true)
public class CatchEconomicGrowthService extends CrudService<CatchEconomicGrowthDao, CatchEconomicGrowth> {

	public CatchEconomicGrowth get(String id) {
		return super.get(id);
	}
	
	public List<CatchEconomicGrowth> findList(CatchEconomicGrowth catchEconomicGrowth) {
		return super.findList(catchEconomicGrowth);
	}
	
	public Page<CatchEconomicGrowth> findPage(Page<CatchEconomicGrowth> page, CatchEconomicGrowth catchEconomicGrowth) {
		return super.findPage(page, catchEconomicGrowth);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchEconomicGrowth catchEconomicGrowth) {
		super.save(catchEconomicGrowth);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchEconomicGrowth catchEconomicGrowth) {
		super.delete(catchEconomicGrowth);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params,String paramDel) {
		 String responseEditJsonStr= HttpUtils.post(url,paramDel);
		//通过接口获取删除数据
		if(StringUtils.isNotBlank(responseEditJsonStr)) {
			//对数据删除处理
		}
		//通过接口获取数据
		String responseAddJsonStr = HttpUtils.post(url,params);
		if(StringUtils.isNotBlank(responseAddJsonStr)){
			Object object = JSONObject.fromObject(responseAddJsonStr).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					for (Object jsonObject : jsonArray) {
						CatchEconomicGrowth objectClass  = (CatchEconomicGrowth)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchEconomicGrowth.class);
						if(!(super.findList(objectClass).size() > 0)){
							//如果不存在 则库里进行添加
							objectClass.setId(null);
						}
						super.save(objectClass);
					}
				}
			}
		}
	}
}