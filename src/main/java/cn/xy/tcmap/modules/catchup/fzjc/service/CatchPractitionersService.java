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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchPractitioners;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchPractitionersDao;

/**
 * 劳动就业占比Service
 * @author gxq
 * @version 2018-10-18
 */
@Service
@Transactional(readOnly = true)
public class CatchPractitionersService extends CrudService<CatchPractitionersDao, CatchPractitioners> {

	public CatchPractitioners get(String id) {
		return super.get(id);
	}
	
	public List<CatchPractitioners> findList(CatchPractitioners catchPractitioners) {
		return super.findList(catchPractitioners);
	}
	
	public Page<CatchPractitioners> findPage(Page<CatchPractitioners> page, CatchPractitioners catchPractitioners) {
		return super.findPage(page, catchPractitioners);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchPractitioners catchPractitioners) {
		super.save(catchPractitioners);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchPractitioners catchPractitioners) {
		super.delete(catchPractitioners);
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
						CatchPractitioners objectClass  = (CatchPractitioners)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchPractitioners.class);
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