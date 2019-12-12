/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchWaterTradeDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchWaterTrade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 污水处置能力趋势分析Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchWaterTradeService extends CrudService<CatchWaterTradeDao, CatchWaterTrade> {

	public CatchWaterTrade get(String id) {
		return super.get(id);
	}
	
	public List<CatchWaterTrade> findList(CatchWaterTrade catchWaterTrade) {
		return super.findList(catchWaterTrade);
	}
	
	public Page<CatchWaterTrade> findPage(Page<CatchWaterTrade> page, CatchWaterTrade catchWaterTrade) {
		return super.findPage(page, catchWaterTrade);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchWaterTrade catchWaterTrade) {
		super.save(catchWaterTrade);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchWaterTrade catchWaterTrade) {
		super.delete(catchWaterTrade);
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
					List<CatchWaterTrade> list = new ArrayList<CatchWaterTrade>();
					for (Object jsonObject : jsonArray) {
						CatchWaterTrade catchWaterTrade  = (CatchWaterTrade)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchWaterTrade.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchWaterTrade.setId(null);
						catchWaterTrade.setProcessMax(json.getString("process_max"));
						catchWaterTrade.setSpeedMax(json.getString("speed_max"));
						list.add(catchWaterTrade);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}