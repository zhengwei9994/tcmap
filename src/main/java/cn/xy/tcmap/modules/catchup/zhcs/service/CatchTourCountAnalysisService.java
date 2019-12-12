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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTourCountAnalysis;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchTourCountAnalysisDao;

/**
 * 旅游趋势分析Service
 * @author wl
 * @version 2018-09-28
 */
@Service
@Transactional(readOnly = true)
public class CatchTourCountAnalysisService extends CrudService<CatchTourCountAnalysisDao, CatchTourCountAnalysis> {

	public CatchTourCountAnalysis get(String id) {
		return super.get(id);
	}
	
	public List<CatchTourCountAnalysis> findList(CatchTourCountAnalysis catchTourCountAnalysis) {
		return super.findList(catchTourCountAnalysis);
	}
	
	public Page<CatchTourCountAnalysis> findPage(Page<CatchTourCountAnalysis> page, CatchTourCountAnalysis catchTourCountAnalysis) {
		return super.findPage(page, catchTourCountAnalysis);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchTourCountAnalysis catchTourCountAnalysis) {
		super.save(catchTourCountAnalysis);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchTourCountAnalysis catchTourCountAnalysis) {
		super.delete(catchTourCountAnalysis);
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
						CatchTourCountAnalysis catchTourCountAnalysis = (CatchTourCountAnalysis) JSONObject.toBean(json, CatchTourCountAnalysis.class);
						catchTourCountAnalysis.setTourCount(json.getString("tour_count"));
						catchTourCountAnalysis.setTourDay(json.getString("tour_day"));
						if(!(super.findList(catchTourCountAnalysis).size()>0)){
							catchTourCountAnalysis.setId(null);
						}
						super.save(catchTourCountAnalysis);
					}
				}
			}
		}
	}
	
}