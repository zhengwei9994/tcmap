/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchApprovalMatters;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexCompletion;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchResourcesCount;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchApprovalMattersDao;

/**
 * 行政审批事项统计Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchApprovalMattersService extends CrudService<CatchApprovalMattersDao, CatchApprovalMatters> {

	public CatchApprovalMatters get(String id) {
		return super.get(id);
	}
	
	public List<CatchApprovalMatters> findList(CatchApprovalMatters catchApprovalMatters) {
		return super.findList(catchApprovalMatters);
	}
	
	public Page<CatchApprovalMatters> findPage(Page<CatchApprovalMatters> page, CatchApprovalMatters catchApprovalMatters) {
		return super.findPage(page, catchApprovalMatters);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchApprovalMatters catchApprovalMatters) {
		super.save(catchApprovalMatters);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchApprovalMatters catchApprovalMatters) {
		super.delete(catchApprovalMatters);
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
						CatchApprovalMatters objectClass  = (CatchApprovalMatters)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchApprovalMatters.class);
						objectClass.setMattersCount(ob.getInt("matters_count"));	
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