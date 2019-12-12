/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchIndexNameDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchIndexName;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 经济指标名称Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchIndexNameService extends CrudService<CatchIndexNameDao, CatchIndexName> {

	public CatchIndexName get(String id) {
		return super.get(id);
	}
	
	public List<CatchIndexName> findList(CatchIndexName catchIndexName) {
		return super.findList(catchIndexName);
	}
	
	public Page<CatchIndexName> findPage(Page<CatchIndexName> page, CatchIndexName catchIndexName) {
		return super.findPage(page, catchIndexName);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchIndexName catchIndexName) {
		super.save(catchIndexName);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchIndexName catchIndexName) {
		super.delete(catchIndexName);
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
					List<CatchIndexName> list = new ArrayList<CatchIndexName>();
					for (Object jsonObject : jsonArray) {
						CatchIndexName catchIndexName  = (CatchIndexName)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchIndexName.class);
						list.add(catchIndexName);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
}