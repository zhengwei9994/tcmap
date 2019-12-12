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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchServiceIndicators;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchServiceIndicatorsDao;

/**
 * 区县综合政府服务指标Service
 * @author gxq
 * @version 2018-10-22
 */
@Service
@Transactional(readOnly = true)
public class CatchServiceIndicatorsService extends CrudService<CatchServiceIndicatorsDao, CatchServiceIndicators> {

	public CatchServiceIndicators get(String id) {
		return super.get(id);
	}
	
	public List<CatchServiceIndicators> findList(CatchServiceIndicators catchServiceIndicators) {
		return super.findList(catchServiceIndicators);
	}
	
	public Page<CatchServiceIndicators> findPage(Page<CatchServiceIndicators> page, CatchServiceIndicators catchServiceIndicators) {
		return super.findPage(page, catchServiceIndicators);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchServiceIndicators catchServiceIndicators) {
		super.save(catchServiceIndicators);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchServiceIndicators catchServiceIndicators) {
		super.delete(catchServiceIndicators);
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
						CatchServiceIndicators objectClass  = (CatchServiceIndicators)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchServiceIndicators.class);
						JSONObject ob = JSONObject.fromObject(jsonObject);//因返回数据与实体字段不一致 则做如此处理
						objectClass.setAreaName(ob.getString("area_name"));	
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