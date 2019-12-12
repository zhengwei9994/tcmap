/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchEducationAssets;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAreaRanking;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchAreaRankingDao;

/**
 * 地区景点排名Service
 * @author wl
 * @version 2018-09-27
 */
@Service
@Transactional(readOnly = true)
public class CatchAreaRankingService extends CrudService<CatchAreaRankingDao, CatchAreaRanking> {

	public CatchAreaRanking get(String id) {
		return super.get(id);
	}
	
	public List<CatchAreaRanking> findList(CatchAreaRanking catchAreaRanking) {
		return super.findList(catchAreaRanking);
	}
	
	public Page<CatchAreaRanking> findPage(Page<CatchAreaRanking> page, CatchAreaRanking catchAreaRanking) {
		return super.findPage(page, catchAreaRanking);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchAreaRanking catchAreaRanking) {
		super.save(catchAreaRanking);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchAreaRanking catchAreaRanking) {
		super.delete(catchAreaRanking);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params) {
		//通过接口获取数据
		String str = HttpUtils.post(url,params);
		if(StringUtils.isNotBlank(str)) {
			Object object = JSONObject.fromObject(str).get("data");
			if (object != null) {
				JSONArray jsonArray = JSONArray.fromObject(object);
				if (jsonArray.size() > 0) {
					//更新或添加获取的数据
					for (Object jsonObject : jsonArray) {
						JSONObject json = JSONObject.fromObject(jsonObject);
						CatchAreaRanking catchAreaRanking = (CatchAreaRanking) JSONObject.toBean(json, CatchAreaRanking.class);
						catchAreaRanking.setAreaType(json.getString("area_type"));
						catchAreaRanking.setAreaName(json.getString("area_name"));
						catchAreaRanking.setAreaCount(json.getString("area_count"));
						catchAreaRanking.setAreaSort(json.getString("area_sort"));
						if(!(super.findList(catchAreaRanking).size()>0)){
							catchAreaRanking.setId(null);
						}
						super.save(catchAreaRanking);
					}
				}
			}
		}
	}
	
}