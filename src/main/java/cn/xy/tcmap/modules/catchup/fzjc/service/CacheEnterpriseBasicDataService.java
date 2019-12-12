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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CacheEnterpriseBasicData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CacheEnterpriseBasicDataDao;

/**
 * 企业结构基础数据分析Service
 * @author wl
 * @version 2018-09-29
 */
@Service
@Transactional(readOnly = true)
public class CacheEnterpriseBasicDataService extends CrudService<CacheEnterpriseBasicDataDao, CacheEnterpriseBasicData> {

	public CacheEnterpriseBasicData get(String id) {
		return super.get(id);
	}
	
	public List<CacheEnterpriseBasicData> findList(CacheEnterpriseBasicData cacheEnterpriseBasicData) {
		return super.findList(cacheEnterpriseBasicData);
	}
	
	public Page<CacheEnterpriseBasicData> findPage(Page<CacheEnterpriseBasicData> page, CacheEnterpriseBasicData cacheEnterpriseBasicData) {
		return super.findPage(page, cacheEnterpriseBasicData);
	}
	
	@Transactional(readOnly = false)
	public void save(CacheEnterpriseBasicData cacheEnterpriseBasicData) {
		super.save(cacheEnterpriseBasicData);
	}
	
	@Transactional(readOnly = false)
	public void delete(CacheEnterpriseBasicData cacheEnterpriseBasicData) {
		super.delete(cacheEnterpriseBasicData);
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
						CacheEnterpriseBasicData objectClass  = (CacheEnterpriseBasicData)JSONObject.toBean(JSONObject.fromObject(jsonObject),CacheEnterpriseBasicData.class);
						JSONObject ob = JSONObject.fromObject(jsonObject);//因返回数据与实体字段不一致 则做如此处理
						objectClass.setEnterpriseType(ob.getString("enterprise_type"));	
						objectClass.setEnterpriseMoney(ob.getString("enterprise_money"));
						objectClass.setEnterpriseCount(ob.getString("enterprise_count"));
						objectClass.setPersonCount(ob.getString("person_count"));
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