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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchHingeStatistics;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchHingeStatisticsDao;

/**
 * 交通枢纽统计Service
 * @author liuyang
 * @version 2018-06-12
 */
@Service
@Transactional(readOnly = true)
public class CatchHingeStatisticsService extends CrudService<CatchHingeStatisticsDao, CatchHingeStatistics> {

	public CatchHingeStatistics get(String id) {
		return super.get(id);
	}
	
	public List<CatchHingeStatistics> findList(CatchHingeStatistics catchHingeStatistics) {
		return super.findList(catchHingeStatistics);
	}
	
	public Page<CatchHingeStatistics> findPage(Page<CatchHingeStatistics> page, CatchHingeStatistics catchHingeStatistics) {
		return super.findPage(page, catchHingeStatistics);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchHingeStatistics catchHingeStatistics) {
		super.save(catchHingeStatistics);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchHingeStatistics catchHingeStatistics) {
		super.delete(catchHingeStatistics);
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
						CatchHingeStatistics catchHingeStatistics = (CatchHingeStatistics) JSONObject.toBean(JSONObject.fromObject(jsonObject), CatchHingeStatistics.class);
						//对数据进行同步
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchHingeStatistics.setHingeType(json.getString("hinge_type"));
						catchHingeStatistics.setHingeNumber(Integer.parseInt(json.getString("hinge_number")));
						if(!(super.findList(catchHingeStatistics).size()>0)){
							catchHingeStatistics.setId(null);
						}
						super.save(catchHingeStatistics);
					}
				}
			}
		}
	}
}