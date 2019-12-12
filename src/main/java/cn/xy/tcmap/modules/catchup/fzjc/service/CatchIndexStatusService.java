/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchIndexStatusDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexStatus;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 指标数据运行状态Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchIndexStatusService extends CrudService<CatchIndexStatusDao, CatchIndexStatus> {

	@Autowired
	private CatchIndexStatusDao catchIndexStatusDao;

	public List<CatchIndexStatus> groupByindexType(CatchIndexStatus catchIndexStatus){
		return catchIndexStatusDao.groupByindexType(catchIndexStatus);
	}

	public CatchIndexStatus get(String id) {
		return super.get(id);
	}
	
	public List<CatchIndexStatus> findList(CatchIndexStatus catchIndexStatus) {
		return super.findList(catchIndexStatus);
	}
	
	public Page<CatchIndexStatus> findPage(Page<CatchIndexStatus> page, CatchIndexStatus catchIndexStatus) {
		return super.findPage(page, catchIndexStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchIndexStatus catchIndexStatus) {
		super.save(catchIndexStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchIndexStatus catchIndexStatus) {
		super.delete(catchIndexStatus);
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
						CatchIndexStatus objectClass  = (CatchIndexStatus)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchIndexStatus.class);
						objectClass.setIndexType(ob.getString("index_type"));	
						objectClass.setCompletionRate(ob.getDouble("completion_rate"));
						objectClass.setAverageRates(ob.getString("average_rates"));
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