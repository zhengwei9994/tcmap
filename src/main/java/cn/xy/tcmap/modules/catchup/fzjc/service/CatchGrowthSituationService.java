/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchGrowthSituationDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchGrowthSituation;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 指标数据运行状态Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchGrowthSituationService extends CrudService<CatchGrowthSituationDao, CatchGrowthSituation> {

	@Autowired
	private CatchGrowthSituationDao catchGrowthSituationDao;

	public List<CatchGrowthSituation> groupByindexType(CatchGrowthSituation catchGrowthSituation){
		return catchGrowthSituationDao.groupByindexType(catchGrowthSituation);
	}

	public CatchGrowthSituation get(String id) {
		return super.get(id);
	}
	
	public List<CatchGrowthSituation> findList(CatchGrowthSituation catchGrowthSituation) {
		return super.findList(catchGrowthSituation);
	}
	
	public Page<CatchGrowthSituation> findPage(Page<CatchGrowthSituation> page, CatchGrowthSituation catchGrowthSituation) {
		return super.findPage(page, catchGrowthSituation);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchGrowthSituation catchGrowthSituation) {
		super.save(catchGrowthSituation);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchGrowthSituation catchGrowthSituation) {
		super.delete(catchGrowthSituation);
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
						CatchGrowthSituation objectClass  = (CatchGrowthSituation)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchGrowthSituation.class);
						objectClass.setIndexType(ob.getString("index_type"));	
						objectClass.setIndexValue(ob.getString("index_value"));
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