/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchWasteWaterDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchWasteWater;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 污水排放量趋势分析Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchWasteWaterService extends CrudService<CatchWasteWaterDao, CatchWasteWater> {

	public CatchWasteWater get(String id) {
		return super.get(id);
	}
	
	public List<CatchWasteWater> findList(CatchWasteWater catchWasteWater) {
		return super.findList(catchWasteWater);
	}
	
	public Page<CatchWasteWater> findPage(Page<CatchWasteWater> page, CatchWasteWater catchWasteWater) {
		return super.findPage(page, catchWasteWater);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchWasteWater catchWasteWater) {
		super.save(catchWasteWater);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchWasteWater catchWasteWater) {
		super.delete(catchWasteWater);
	}

	@Transactional(readOnly = false)
	public void syndata(String url,String paramsAdd,String paramEdit) {
		/* String responseEditJsonStr= HttpUtils.post(url,paramEdit);
		//通过接口获取修改数据
		if(StringUtils.isNotBlank(responseEditJsonStr)) {
			//修改数据的处理
		}*/
		//通过接口获取新增数据
		String responseAddJsonStr = HttpUtils.post(url,paramsAdd);
		if(StringUtils.isNotBlank(responseAddJsonStr)){
			Object object = JSONObject.fromObject(responseAddJsonStr).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					List<CatchWasteWater> list = new ArrayList<CatchWasteWater>();
					for (Object jsonObject : jsonArray) {
						CatchWasteWater catchWasteWater  = (CatchWasteWater)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchWasteWater.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchWasteWater.setId(null);
						catchWasteWater.setIndustryValue(json.getString("industry_value"));
						catchWasteWater.setCityValue(json.getString("city_value"));
						catchWasteWater.setFarmValue(json.getString("farm_value"));
						catchWasteWater.setAmmoniaValue(json.getString("ammonia_value"));
						list.add(catchWasteWater);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}