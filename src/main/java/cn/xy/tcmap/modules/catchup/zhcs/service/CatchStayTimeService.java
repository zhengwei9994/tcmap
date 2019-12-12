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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchStayTime;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchStayTimeDao;

/**
 * 停留时长分布Service
 * @author wl
 * @version 2018-09-29
 */
@Service
@Transactional(readOnly = true)
public class CatchStayTimeService extends CrudService<CatchStayTimeDao, CatchStayTime> {

	public CatchStayTime get(String id) {
		return super.get(id);
	}
	
	public List<CatchStayTime> findList(CatchStayTime catchStayTime) {
		return super.findList(catchStayTime);
	}
	
	public Page<CatchStayTime> findPage(Page<CatchStayTime> page, CatchStayTime catchStayTime) {
		return super.findPage(page, catchStayTime);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchStayTime catchStayTime) {
		super.save(catchStayTime);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchStayTime catchStayTime) {
		super.delete(catchStayTime);
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
						CatchStayTime catchStayTime = (CatchStayTime) JSONObject.toBean(json, CatchStayTime.class);
						catchStayTime.setStayPlace(json.getString("stay_place"));
						catchStayTime.setStayTime(json.getString("stay_time"));
						catchStayTime.setStayCount(json.getString("stay_count"));
						if(!(super.findList(catchStayTime).size()>0)){
							catchStayTime.setId(null);
						}
						super.save(catchStayTime);
					}
				}
			}
		}
	}
	
}