/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CathEconomicDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchIndexName;
import cn.xy.tcmap.modules.catchup.csyx.entity.CathEconomic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 经济指标名称经济指标统计Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CathEconomicService extends CrudService<CathEconomicDao, CathEconomic> {
	@Autowired
	private CathEconomicDao cathEconomicDao;
	public CathEconomic get(String id) {
		return super.get(id);
	}
	
	public List<CathEconomic> findList(CathEconomic cathEconomic) {
		return super.findList(cathEconomic);
	}
	
	public Page<CathEconomic> findPage(Page<CathEconomic> page, CathEconomic cathEconomic) {
		return super.findPage(page, cathEconomic);
	}
	public List<CathEconomic> jjzbData(CathEconomic cathEconomic){
		return cathEconomicDao.jjzbData(cathEconomic);
	}
	@Transactional(readOnly = false)
	public void save(CathEconomic cathEconomic) {
		super.save(cathEconomic);
	}
	
	@Transactional(readOnly = false)
	public void delete(CathEconomic cathEconomic) {
		super.delete(cathEconomic);
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
					List<CathEconomic> list = new ArrayList<CathEconomic>();
					for (Object jsonObject : jsonArray) {
						CathEconomic cathEconomic  = (CathEconomic)JSONObject.toBean(JSONObject.fromObject(jsonObject),CathEconomic.class);
						cathEconomic.setId(null);
						JSONObject json = JSONObject.fromObject(jsonObject);
						CatchIndexName indexName = new CatchIndexName(json.getString("index_id"));
						cathEconomic.setIndexId(indexName);
						cathEconomic.setIndicatorsUnit(json.getString("indicators_unit"));
						list.add(cathEconomic);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}