/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchScenicSpotDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchResourcesCount;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchScenicSpot;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 旅游资产Service
 * @author liuyang
 * @version 2018-06-11
 */
@Service
@Transactional(readOnly = true)
public class CatchScenicSpotService extends CrudService<CatchScenicSpotDao, CatchScenicSpot> {

	@Autowired
	private CatchScenicSpotDao CatchScenicSpotDao;

	public List<HashMap> totalData(CatchScenicSpot catchScenicSpot){
		return CatchScenicSpotDao.totalData(catchScenicSpot);
	}

	public CatchScenicSpot get(String id) {
		return super.get(id);
	}
	
	public List<CatchScenicSpot> findList(CatchScenicSpot catchScenicSpot) {
		return super.findList(catchScenicSpot);
	}
	
	public Page<CatchScenicSpot> findPage(Page<CatchScenicSpot> page, CatchScenicSpot catchScenicSpot) {
		return super.findPage(page, catchScenicSpot);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchScenicSpot catchScenicSpot) {
		super.save(catchScenicSpot);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchScenicSpot catchScenicSpot) {
		super.delete(catchScenicSpot);
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
					super.deleteAll();//清空表内数据
					//更新或添加获取的数据
					for (Object jsonObject : jsonArray) {
		                JSONObject json = JSONObject.fromObject(jsonObject);
		                CatchScenicSpot catchScenicSpot  = (CatchScenicSpot)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchScenicSpot.class);
						catchScenicSpot.setSpotType(json.getString("spot_type"));//景点等级
						catchScenicSpot.setSpotName(json.getString("spot_name"));//景点名称
						catchScenicSpot.setDisplayPosition(json.getString("display_position"));// 展示位置
						if(!(super.findList(catchScenicSpot).size()>0)){
							catchScenicSpot.setId(null);
						}
						super.save(catchScenicSpot);
					}
				}

			}

		};
	}
}