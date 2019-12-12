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
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchEducationAssets;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchEducationAssetsDao;

/**
 * 教育资产Service
 * @author liuyang
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class CatchEducationAssetsService extends CrudService<CatchEducationAssetsDao, CatchEducationAssets> {

	public CatchEducationAssets get(String id) {
		return super.get(id);
	}
	
	public List<CatchEducationAssets> findList(CatchEducationAssets catchEducationAssets) {
		return super.findList(catchEducationAssets);
	}
	
	public Page<CatchEducationAssets> findPage(Page<CatchEducationAssets> page, CatchEducationAssets catchEducationAssets) {
		return super.findPage(page, catchEducationAssets);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchEducationAssets catchEducationAssets) {
		super.save(catchEducationAssets);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchEducationAssets catchEducationAssets) {
		super.delete(catchEducationAssets);
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
						CatchEducationAssets catchEducationAssets = (CatchEducationAssets) JSONObject.toBean(json, CatchEducationAssets.class);
						catchEducationAssets.setEducationType(json.getString("education_type"));
						catchEducationAssets.setSchoolNumber(Integer.parseInt(json.getString("school_number")));
						catchEducationAssets.setTeachingStaff(Integer.parseInt(json.getString("teaching_staff")));
						if(!(super.findList(catchEducationAssets).size()>0)){
							catchEducationAssets.setId(null);
						}
						super.save(catchEducationAssets);
					}
				}
			}
		}
	}
}