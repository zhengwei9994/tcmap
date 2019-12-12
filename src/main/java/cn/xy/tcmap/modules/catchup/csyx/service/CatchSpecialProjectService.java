/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchSpecialProjectDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSpecialProject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 特色项目Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchSpecialProjectService extends CrudService<CatchSpecialProjectDao, CatchSpecialProject> {

	public CatchSpecialProject get(String id) {
		return super.get(id);
	}
	
	public List<CatchSpecialProject> findList(CatchSpecialProject catchSpecialProject) {
		return super.findList(catchSpecialProject);
	}
	
	public Page<CatchSpecialProject> findPage(Page<CatchSpecialProject> page, CatchSpecialProject catchSpecialProject) {
		return super.findPage(page, catchSpecialProject);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchSpecialProject catchSpecialProject) {
		super.save(catchSpecialProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSpecialProject catchSpecialProject) {
		super.delete(catchSpecialProject);
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
					List<CatchSpecialProject> list = new ArrayList<CatchSpecialProject>();
					for (Object jsonObject : jsonArray) {
						CatchSpecialProject catchSpecialProject  = (CatchSpecialProject)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchSpecialProject.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchSpecialProject.setId(null);
						catchSpecialProject.setProjectname(json.getString("projectName"));
						list.add(catchSpecialProject);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}