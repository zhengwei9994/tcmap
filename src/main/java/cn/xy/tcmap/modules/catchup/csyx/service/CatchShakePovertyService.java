/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchShakePovertyDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchShakePoverty;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 精准务实抓脱贫Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchShakePovertyService extends CrudService<CatchShakePovertyDao, CatchShakePoverty> {

	@Autowired
	private CatchShakePovertyDao catchShakePovertyDao;
	public CatchShakePoverty get(String id) {
		return super.get(id);
	}
	
	public List<CatchShakePoverty> findList(CatchShakePoverty catchShakePoverty) {
		return super.findList(catchShakePoverty);
	}
	
	public Page<CatchShakePoverty> findPage(Page<CatchShakePoverty> page, CatchShakePoverty catchShakePoverty) {
		return super.findPage(page, catchShakePoverty);
	}
	public List<HashMap> populationData(CatchShakePoverty catchShakePoverty){
		return catchShakePovertyDao.populationData(catchShakePoverty);
	}
	public List<CatchShakePoverty> histogramData(CatchShakePoverty catchShakePoverty){
		return catchShakePovertyDao.histogramData(catchShakePoverty);
	}
	@Transactional(readOnly = false)
	public void save(CatchShakePoverty catchShakePoverty) {
		super.save(catchShakePoverty);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchShakePoverty catchShakePoverty) {
		super.delete(catchShakePoverty);
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
					List<CatchShakePoverty> list = new ArrayList<CatchShakePoverty>();
					for (Object jsonObject : jsonArray) {
						CatchShakePoverty catchShakePoverty  = (CatchShakePoverty)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchShakePoverty.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchShakePoverty.setId(null);
						catchShakePoverty.setAreaName(json.getString("area_name"));
						catchShakePoverty.setOutPoverty(json.getInt("out_poverty"));
						catchShakePoverty.setPoorHouseholds(json.getInt("poor_households"));
						catchShakePoverty.setReasonsAlleviation(json.getString("reasons_alleviation"));
						catchShakePoverty.setTotalPeople(json.getInt("total_people"));
						list.add(catchShakePoverty);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
}