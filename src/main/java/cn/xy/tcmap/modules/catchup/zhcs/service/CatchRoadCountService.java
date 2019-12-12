/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchScenicSpot;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchRoadCount;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchRoadCountDao;

/**
 * 公路统计Service
 * @author liuyang
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class CatchRoadCountService extends CrudService<CatchRoadCountDao, CatchRoadCount> {

	public CatchRoadCount get(String id) {
		return super.get(id);
	}
	
	public List<CatchRoadCount> findList(CatchRoadCount catchRoadCount) {
		return super.findList(catchRoadCount);
	}
	
	public Page<CatchRoadCount> findPage(Page<CatchRoadCount> page, CatchRoadCount catchRoadCount) {
		return super.findPage(page, catchRoadCount);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchRoadCount catchRoadCount) {
		super.save(catchRoadCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchRoadCount catchRoadCount) {
		super.delete(catchRoadCount);
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
					//更新或添加获取的数据
					for (Object jsonObject : jsonArray) {
						JSONObject json = JSONObject.fromObject(jsonObject);
						CatchRoadCount catchRoadCount  = (CatchRoadCount)JSONObject.toBean(json,CatchRoadCount.class);
						catchRoadCount.setRoadType(json.getString("road_type"));//公路类型
						catchRoadCount.setRoadMileage(json.getInt("road_mileage"));//公路里程
						if(!(super.findList(catchRoadCount).size()>0)){
							catchRoadCount.setId(null);
						}
						super.save(catchRoadCount);
					}
				}
			}

		};
	}
}