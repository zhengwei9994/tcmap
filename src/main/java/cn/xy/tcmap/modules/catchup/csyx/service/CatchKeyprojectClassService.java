/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchKeyprojectClassDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyproject;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchKeyprojectClass;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * 月进度Service
 * @author xuzhou
 * @version 2018-05-02
 */
@Service
@Transactional(readOnly = true)
public class CatchKeyprojectClassService extends CrudService<CatchKeyprojectClassDao, CatchKeyprojectClass> {
	@Autowired
	private CatchKeyprojectClassDao catchKeyprojectClassDao;
	public CatchKeyprojectClass get(String id) {
		return super.get(id);
	}
	
	public List<CatchKeyprojectClass> findList(CatchKeyprojectClass catchKeyprojectClass) {
		return super.findList(catchKeyprojectClass);
	}
	public List<HashMap> totalData(CatchKeyproject catchKeyproject){
		return catchKeyprojectClassDao.totalData(catchKeyproject);
	}
	public List<HashMap> keyprojectNum(CatchKeyproject catchKeyproject){
		return catchKeyprojectClassDao.keyprojectNum(catchKeyproject);
	}
	public List<HashMap> keyprojectProgress(CatchKeyproject catchKeyproject){
		return catchKeyprojectClassDao.keyprojectProgress(catchKeyproject);
	}
	public Page<CatchKeyprojectClass> findPage(Page<CatchKeyprojectClass> page, CatchKeyprojectClass catchKeyprojectClass) {
		return super.findPage(page, catchKeyprojectClass);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchKeyprojectClass catchKeyprojectClass) {
		super.save(catchKeyprojectClass);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchKeyprojectClass catchKeyprojectClass) {
		super.delete(catchKeyprojectClass);
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
					List<CatchKeyprojectClass> list = new ArrayList<CatchKeyprojectClass>();
					for (Object jsonObject : jsonArray) {
						CatchKeyprojectClass catchKeyprojectClass  = (CatchKeyprojectClass)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchKeyprojectClass.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchKeyprojectClass.setId(null);
						catchKeyprojectClass.setKeyprojectId(new CatchKeyproject(json.getString("keyproject_id")));
						catchKeyprojectClass.setAmountCompleted(json.getString("amount_completed"));
						catchKeyprojectClass.setCompletionRatio(json.getString("completion_ratio"));
						catchKeyprojectClass.setProjectStatus(json.getString("project_status"));
						list.add(catchKeyprojectClass);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}