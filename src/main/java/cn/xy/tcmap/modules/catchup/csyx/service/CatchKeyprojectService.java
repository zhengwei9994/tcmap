/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchKeyprojectDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyproject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 重点项目Service
 * @author xuzhou
 * @version 2018-05-02
 */
@Service
@Transactional(readOnly = true)
public class CatchKeyprojectService extends CrudService<CatchKeyprojectDao, CatchKeyproject> {
	@Autowired
	private CatchKeyprojectDao catchKeyprojectDao;
	public CatchKeyproject get(String id) {
		return super.get(id);
	}
	
	public List<CatchKeyproject> findList(CatchKeyproject catchKeyproject) {
		return super.findList(catchKeyproject);
	}
	
	public Page<CatchKeyproject> findPage(Page<CatchKeyproject> page, CatchKeyproject catchKeyproject) {
		return super.findPage(page, catchKeyproject);
	}
	public List<CatchKeyproject> keyprojectTypeData(CatchKeyproject catchKeyproject){
		return catchKeyprojectDao.keyprojectTypeData(catchKeyproject);
	}
	@Transactional(readOnly = false)
	public void save(CatchKeyproject catchKeyproject) {
		super.save(catchKeyproject);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchKeyproject catchKeyproject) {
		super.delete(catchKeyproject);
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
					List<CatchKeyproject> list = new ArrayList<CatchKeyproject>();
					for (Object jsonObject : jsonArray) {
						CatchKeyproject catchKeyproject  = (CatchKeyproject)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchKeyproject.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchKeyproject.setEntryName(json.getString("entry_name"));
						catchKeyproject.setProjectType(json.getString("project_type"));
						catchKeyproject.setTotalInvestment(json.getString("total_investment"));
						list.add(catchKeyproject);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
}