/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchApprovalMatters;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexCompletion;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchVideoResource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchVideoResourceDao;

/**
 * 公共安全视频资源覆盖情况Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchVideoResourceService extends CrudService<CatchVideoResourceDao, CatchVideoResource> {

	public CatchVideoResource get(String id) {
		return super.get(id);
	}
	
	public List<CatchVideoResource> findList(CatchVideoResource catchVideoResource) {
		return super.findList(catchVideoResource);
	}
	
	public Page<CatchVideoResource> findPage(Page<CatchVideoResource> page, CatchVideoResource catchVideoResource) {
		return super.findPage(page, catchVideoResource);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchVideoResource catchVideoResource) {
		super.save(catchVideoResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchVideoResource catchVideoResource) {
		super.delete(catchVideoResource);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params) {
		//通过接口获取数据
		String str = HttpUtils.post(url, params);
		if(StringUtils.isNotBlank(str)){  
			Object object = JSONObject.fromObject(str).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					for (Object jsonObject : jsonArray) {
						JSONObject ob = JSONObject.fromObject(jsonObject);//因返回数据与实体字段不一致 则做如此处理
						CatchVideoResource objectClass  = (CatchVideoResource)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchVideoResource.class);
						objectClass.setKeyRegionalCoverage(ob.getString("key_regional_coverage"));	
						objectClass.setCoverageKeyAreas(ob.getString("coverage_key_areas"));
						objectClass.setHighDefinitionCamera(ob.getString("high_definition_camera"));
						objectClass.setCameraIntegrityRatio(ob.getString("camera_integrity_ratio"));
						objectClass.setMachineCompletionKey(ob.getString("machine_completion_key"));
						if(!(super.findList(objectClass).size() > 0)){
							//如果不存在 则库里进行添加
							objectClass.setId(null);
						}
						super.save(objectClass);	
					}
				}

			}

		};
	}
}