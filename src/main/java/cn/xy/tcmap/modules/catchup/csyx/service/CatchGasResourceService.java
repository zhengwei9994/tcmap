/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchGasResourceDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchGasResource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 废气排放指标来源构成分析Service
 * @author guoxunquan
 * @version 2018-09-20
 */
@Service
@Transactional(readOnly = true)
public class CatchGasResourceService extends CrudService<CatchGasResourceDao, CatchGasResource> {

	public CatchGasResource get(String id) {
		return super.get(id);
	}
	
	public List<CatchGasResource> findList(CatchGasResource catchGasResource) {
		return super.findList(catchGasResource);
	}
	
	public Page<CatchGasResource> findPage(Page<CatchGasResource> page, CatchGasResource catchGasResource) {
		return super.findPage(page, catchGasResource);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchGasResource catchGasResource) {
		super.save(catchGasResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchGasResource catchGasResource) {
		super.delete(catchGasResource);
	}

	@Transactional(readOnly = false)
	public void syndata(String url,String paramsAdd,String paramEdit) {
		/*String responseEditJsonStr= HttpUtils.post(url,paramEdit);
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
					List<CatchGasResource> list = new ArrayList<CatchGasResource>();
					for (Object jsonObject : jsonArray) {
						CatchGasResource catchGasResource  = (CatchGasResource)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchGasResource.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchGasResource.setId(null);
						catchGasResource.setPowderValue(json.getString("powder_value"));
						catchGasResource.setOxynitrideValue(json.getString("oxynitride_value"));
						catchGasResource.setCoValue(json.getString("co_value"));
						list.add(catchGasResource);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}