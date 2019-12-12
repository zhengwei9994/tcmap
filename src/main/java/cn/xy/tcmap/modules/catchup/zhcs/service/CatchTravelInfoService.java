/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchTravelInfoDao;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTravelInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 指标数据运行状态Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchTravelInfoService extends CrudService<CatchTravelInfoDao, CatchTravelInfo> {

	@Autowired
	private CatchTravelInfoDao catchTravelInfoDao;

	public List<CatchTravelInfo> groupByindexType(CatchTravelInfo catchTravelInfo){
		return catchTravelInfoDao.groupByindexType(catchTravelInfo);
	}

	public CatchTravelInfo get(String id) {
		return super.get(id);
	}
	
	public List<CatchTravelInfo> findList(CatchTravelInfo catchTravelInfo) {
		return super.findList(catchTravelInfo);
	}
	
	public Page<CatchTravelInfo> findPage(Page<CatchTravelInfo> page, CatchTravelInfo catchTravelInfo) {
		return super.findPage(page, catchTravelInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchTravelInfo catchTravelInfo) {
		super.save(catchTravelInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchTravelInfo catchTravelInfo) {
		super.delete(catchTravelInfo);
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
						CatchTravelInfo catchTravelInfo = (CatchTravelInfo) JSONObject.toBean(json, CatchTravelInfo.class);
						catchTravelInfo.setInfoType(json.getString("info_type"));
						catchTravelInfo.setInfoValue(json.getString("info_value"));
						catchTravelInfo.setTravelName(json.getString("travel_name"));
						if(!(super.findList(catchTravelInfo).size()>0)){
							catchTravelInfo.setId(null);
						}
						super.save(catchTravelInfo);
					}
				}
			}
		}
	}
	
}