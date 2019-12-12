/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchPlanInvestmentDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchPlanInvestment;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * 年计划总投资Service
 * @author xuzhou
 * @version 2018-05-02
 */
@Service
@Transactional(readOnly = true)
public class CatchPlanInvestmentService extends CrudService<CatchPlanInvestmentDao, CatchPlanInvestment> {

	@Autowired
	private CatchPlanInvestmentDao catchPlanInvestmentDao;
	public CatchPlanInvestment get(String id) {
		return super.get(id);
	}
	
	public List<CatchPlanInvestment> findList(CatchPlanInvestment catchPlanInvestment) {
		return super.findList(catchPlanInvestment);
	}
	
	public Page<CatchPlanInvestment> findPage(Page<CatchPlanInvestment> page, CatchPlanInvestment catchPlanInvestment) {
		return super.findPage(page, catchPlanInvestment);
	}
	public List<HashMap> yearPlanData(CatchPlanInvestment catchPlanInvestment){
		return catchPlanInvestmentDao.yearPlanData(catchPlanInvestment);
	}
	@Transactional(readOnly = false)
	public void save(CatchPlanInvestment catchPlanInvestment) {
		super.save(catchPlanInvestment);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchPlanInvestment catchPlanInvestment) {
		super.delete(catchPlanInvestment);
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
					List<CatchPlanInvestment> list = new ArrayList<CatchPlanInvestment>();
					for (Object jsonObject : jsonArray) {
						CatchPlanInvestment catchPlanInvestment  = (CatchPlanInvestment)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchPlanInvestment.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchPlanInvestment.setId(null);
						catchPlanInvestment.setPlannedInvestment(json.getString("planned_investment"));
						Object total = json.get("total_investment");
						if(total != null){
							catchPlanInvestment.setTotalInvestment(total.toString());
						}
						list.add(catchPlanInvestment);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}