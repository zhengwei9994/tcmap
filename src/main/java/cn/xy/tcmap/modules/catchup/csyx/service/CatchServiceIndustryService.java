/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchServiceIndustryDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchServiceIndustry;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务业稳重向好Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchServiceIndustryService extends CrudService<CatchServiceIndustryDao, CatchServiceIndustry> {

	public CatchServiceIndustry get(String id) {
		return super.get(id);
	}
	
	public List<CatchServiceIndustry> findList(CatchServiceIndustry catchServiceIndustry) {
		return super.findList(catchServiceIndustry);
	}
	
	public Page<CatchServiceIndustry> findPage(Page<CatchServiceIndustry> page, CatchServiceIndustry catchServiceIndustry) {
		return super.findPage(page, catchServiceIndustry);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchServiceIndustry catchServiceIndustry) {
		super.save(catchServiceIndustry);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchServiceIndustry catchServiceIndustry) {
		super.delete(catchServiceIndustry);
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
					List<CatchServiceIndustry> list = new ArrayList<CatchServiceIndustry>();
					for (Object jsonObject : jsonArray) {
						CatchServiceIndustry catchServiceIndustry  = (CatchServiceIndustry)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchServiceIndustry.class);
						catchServiceIndustry.setId(null);
						list.add(catchServiceIndustry);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
}