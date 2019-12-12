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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchEnergyConsumption;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchVideoResource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchEnergyConsumptionDao;

/**
 * 万元GDP能耗降低率Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchEnergyConsumptionService extends CrudService<CatchEnergyConsumptionDao, CatchEnergyConsumption> {

	public CatchEnergyConsumption get(String id) {
		return super.get(id);
	}
	
	public List<CatchEnergyConsumption> findList(CatchEnergyConsumption catchEnergyConsumption) {
		return super.findList(catchEnergyConsumption);
	}
	
	public Page<CatchEnergyConsumption> findPage(Page<CatchEnergyConsumption> page, CatchEnergyConsumption catchEnergyConsumption) {
		return super.findPage(page, catchEnergyConsumption);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchEnergyConsumption catchEnergyConsumption) {
		super.save(catchEnergyConsumption);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchEnergyConsumption catchEnergyConsumption) {
		super.delete(catchEnergyConsumption);
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
						JSONObject ob = JSONObject.fromObject(jsonObject);//因返回数据与实体字段不一致 则做如此处理
						CatchEnergyConsumption objectClass  = (CatchEnergyConsumption)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchEnergyConsumption.class);
						objectClass.setEnergyConsumption(ob.getDouble("energy_consumption"));	
						objectClass.setEnergyConsumptionRate(ob.getDouble("energy_consumption_rate"));
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