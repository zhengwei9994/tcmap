/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchTouristView;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchTouristViewDao;

/**
 * 旅游资产Service
 * @author zzc
 * @version 2018-11-07
 */
@Service
@Transactional(readOnly = true)
public class CatchTouristViewService extends CrudService<CatchTouristViewDao, CatchTouristView> {

	public CatchTouristView get(String id) {
		return super.get(id);
	}
	
	public List<CatchTouristView> findList(CatchTouristView catchTouristView) {
		return super.findList(catchTouristView);
	}
	
	public Page<CatchTouristView> findPage(Page<CatchTouristView> page, CatchTouristView catchTouristView) {
		return super.findPage(page, catchTouristView);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchTouristView catchTouristView) {
		super.save(catchTouristView);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchTouristView catchTouristView) {
		super.delete(catchTouristView);
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
						CatchTouristView catchTouristView = (CatchTouristView) JSONObject.toBean(json, CatchTouristView.class);
						if(!(super.findList(catchTouristView).size()>0)){
							catchTouristView.setId(null);
						}
						super.save(catchTouristView);
					}
				}
			}
		}
	}

	@Autowired
	private CatchTouristViewDao CatchTouristViewDao;

	public String totalData(String rank){
		return CatchTouristViewDao.totalData(rank);
	}
}