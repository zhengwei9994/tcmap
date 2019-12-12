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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchHotelResInfo;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchHotelResInfoDao;

/**
 * 酒店资源信息Service
 * @author wl
 * @version 2018-09-27
 */
@Service
@Transactional(readOnly = true)
public class CatchHotelResInfoService extends CrudService<CatchHotelResInfoDao, CatchHotelResInfo> {

	public CatchHotelResInfo get(String id) {
		return super.get(id);
	}
	
	public List<CatchHotelResInfo> findList(CatchHotelResInfo catchHotelResInfo) {
		return super.findList(catchHotelResInfo);
	}
	
	public Page<CatchHotelResInfo> findPage(Page<CatchHotelResInfo> page, CatchHotelResInfo catchHotelResInfo) {
		return super.findPage(page, catchHotelResInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchHotelResInfo catchHotelResInfo) {
		super.save(catchHotelResInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchHotelResInfo catchHotelResInfo) {
		super.delete(catchHotelResInfo);
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
						CatchHotelResInfo catchHotelResInfo = (CatchHotelResInfo) JSONObject.toBean(json, CatchHotelResInfo.class);
						catchHotelResInfo.setHotelType(json.getString("hotel_type"));
						catchHotelResInfo.setHotelLevel(json.getString("hotel_level"));
						catchHotelResInfo.setHotelCount(json.getString("hotel_count"));
						catchHotelResInfo.setHotelCheckCount(json.getString("hotel_check_count"));
						catchHotelResInfo.setHotelTopNames(json.getString("hotel_top_names"));
						if(!(super.findList(catchHotelResInfo).size()>0)){
							catchHotelResInfo.setId(null);
						}
						super.save(catchHotelResInfo);
					}
				}
			}
		}
	}
	
}