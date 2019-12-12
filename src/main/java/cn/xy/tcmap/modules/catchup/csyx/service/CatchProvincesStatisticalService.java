/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchProvincesStatisticalDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProvincesStatistical;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 游客分析Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchProvincesStatisticalService extends CrudService<CatchProvincesStatisticalDao, CatchProvincesStatistical> {
	@Autowired
	private CatchProvincesStatisticalDao catchProvincesStatisticalDao;
	public CatchProvincesStatistical get(String id) {
		return super.get(id);
	}
	
	public List<CatchProvincesStatistical> findList(CatchProvincesStatistical catchProvincesStatistical) {
		return super.findList(catchProvincesStatistical);
	}
	public CatchProvincesStatistical provincesData(CatchProvincesStatistical catchProvincesStatistical){
		return catchProvincesStatisticalDao.provincesData(catchProvincesStatistical);
	}
	public Page<CatchProvincesStatistical> findPage(Page<CatchProvincesStatistical> page, CatchProvincesStatistical catchProvincesStatistical) {
		return super.findPage(page, catchProvincesStatistical);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchProvincesStatistical catchProvincesStatistical) {
		super.save(catchProvincesStatistical);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchProvincesStatistical catchProvincesStatistical) {
		super.delete(catchProvincesStatistical);
	}

	@Transactional(readOnly = false)
	public void syndata(String url,String paramsAdd,String paramEdit) {
		/* String responseEditJsonStr= HttpUtils.post(url,paramEdit);
		//通过接口获取修改数据
		if(StringUtils.isNotBlank(responseEditJsonStr)) {
			//修改数据的处理
		}*/
		//通过接口获取新增数据
		String responseAddJsonStr = HttpUtils.post(url,paramsAdd);
		if(StringUtils.isNotBlank(responseAddJsonStr)){
			Object object = JSONObject.fromObject(responseAddJsonStr).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					List<CatchProvincesStatistical> list = new ArrayList<CatchProvincesStatistical>();
					for (Object jsonObject : jsonArray) {
						CatchProvincesStatistical catchProvincesStatistical  = (CatchProvincesStatistical)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchProvincesStatistical.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchProvincesStatistical.setId(null);
						catchProvincesStatistical.setOtherProvinces(json.getInt("other_provinces"));
						catchProvincesStatistical.setTouristsSum(json.getInt("tourists_sum"));
						catchProvincesStatistical.setThisProvinces(json.getInt("this_provinces"));
						list.add(catchProvincesStatistical);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
}