/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchPatentCount;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchPatentCountDao;

/**
 * 专利数量统计Service
 * @author liuyang
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class CatchPatentCountService extends CrudService<CatchPatentCountDao, CatchPatentCount> {

	public CatchPatentCount get(String id) {
		return super.get(id);
	}
	
	public List<CatchPatentCount> findList(CatchPatentCount catchPatentCount) {
		return super.findList(catchPatentCount);
	}
	
	public Page<CatchPatentCount> findPage(Page<CatchPatentCount> page, CatchPatentCount catchPatentCount) {
		return super.findPage(page, catchPatentCount);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchPatentCount catchPatentCount) {
		super.save(catchPatentCount);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchPatentCount catchPatentCount) {
		super.delete(catchPatentCount);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params) {
		//通过接口获取数据
		String str = HttpUtils.post(url,params);
		if(StringUtils.isNotBlank(str)){
			Object object = JSONObject.fromObject(str).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//更新或添加获取的数据
					for (Object jsonObject : jsonArray) {
						JSONObject json = JSONObject.fromObject(jsonObject);
						CatchPatentCount catchPatentCount  = (CatchPatentCount)JSONObject.toBean(json,CatchPatentCount.class);
						catchPatentCount.setPatentNumber(json.getInt("patent_number"));
						if(!(super.findList(catchPatentCount).size()>0)){
							catchPatentCount.setId(null);
						}
						super.save(catchPatentCount);
					}
				}
			}

		};
	}
}