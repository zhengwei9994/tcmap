/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchPollutionTreatmentDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPollutionTreatment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 重拳治污染Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchPollutionTreatmentService extends CrudService<CatchPollutionTreatmentDao, CatchPollutionTreatment> {
	@Autowired
	private CatchPollutionTreatmentDao catchPollutionTreatmentDao;
	public CatchPollutionTreatment get(String id) {
		return super.get(id);
	}
	
	public List<CatchPollutionTreatment> findList(CatchPollutionTreatment catchPollutionTreatment) {
		return super.findList(catchPollutionTreatment);
	}
	
	public Page<CatchPollutionTreatment> findPage(Page<CatchPollutionTreatment> page, CatchPollutionTreatment catchPollutionTreatment) {
		return super.findPage(page, catchPollutionTreatment);
	}
	public List<CatchPollutionTreatment> pollutionTodayData(CatchPollutionTreatment catchPollutionTreatment){
		return catchPollutionTreatmentDao.pollutionTodayData(catchPollutionTreatment);
	}
	@Transactional(readOnly = false)
	public void save(CatchPollutionTreatment catchPollutionTreatment) {
		super.save(catchPollutionTreatment);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchPollutionTreatment catchPollutionTreatment) {
		super.delete(catchPollutionTreatment);
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
					List<CatchPollutionTreatment> list = new ArrayList<CatchPollutionTreatment>();
					for (Object jsonObject : jsonArray) {
						CatchPollutionTreatment catchPollutionTreatment  = (CatchPollutionTreatment)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchPollutionTreatment.class);
						catchPollutionTreatment.setId(null);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchPollutionTreatment.setTodaySum(json.getInt("today_sum"));
						catchPollutionTreatment.setMarkCar(json.getInt("mark_car"));
						list.add(catchPollutionTreatment);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
}