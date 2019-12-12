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
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchDataStatus;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchResidentIncome;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchResidentIncomeDao;

/**
 * 居民收入Service
 * @author FSH
 * @version 2018-10-18
 */
@Service
@Transactional(readOnly = true)
public class CatchResidentIncomeService extends CrudService<CatchResidentIncomeDao, CatchResidentIncome> {

	public CatchResidentIncome get(String id) {
		return super.get(id);
	}
	
	public List<CatchResidentIncome> findList(CatchResidentIncome catchResidentIncome) {
		return super.findList(catchResidentIncome);
	}
	
	public Page<CatchResidentIncome> findPage(Page<CatchResidentIncome> page, CatchResidentIncome catchResidentIncome) {
		return super.findPage(page, catchResidentIncome);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchResidentIncome catchResidentIncome) {
		super.save(catchResidentIncome);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchResidentIncome catchResidentIncome) {
		super.delete(catchResidentIncome);
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
						CatchResidentIncome objectClass  = (CatchResidentIncome)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchResidentIncome.class);
						JSONObject ob = JSONObject.fromObject(jsonObject);//因返回数据与实体字段不一致 则做如此处理
						objectClass.setResidentClusters(ob.getString("resident_clusters"));	
						objectClass.setWageIncome(ob.getString("wage_income"));
						objectClass.setOperatingIncome(ob.getString("operating_income"));
						objectClass.setOwnershipIncome(ob.getString("ownership_income"));
						objectClass.setTransferIncome(ob.getString("transfer_income"));
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