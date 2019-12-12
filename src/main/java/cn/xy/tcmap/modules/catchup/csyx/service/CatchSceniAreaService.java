/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchSceniAreaDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSceniArea;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 景区旅游数据分析Service
 * @author xuzhou
 * @version 2018-05-24
 */
@Service
@Transactional(readOnly = true)
public class CatchSceniAreaService extends CrudService<CatchSceniAreaDao, CatchSceniArea> {

	@Autowired
	private CatchSceniAreaDao catchSceniAreaDao;
	public CatchSceniArea get(String id) {
		return super.get(id);
	}
	
	public List<CatchSceniArea> findList(CatchSceniArea catchSceniArea) {
		return super.findList(catchSceniArea);
	}
	
	public Page<CatchSceniArea> findPage(Page<CatchSceniArea> page, CatchSceniArea catchSceniArea) {
		return super.findPage(page, catchSceniArea);
	}
	public List<CatchSceniArea> scenicSpotData(CatchSceniArea catchSceniArea){
		return catchSceniAreaDao.scenicSpotData(catchSceniArea);
	}
	@Transactional(readOnly = false)
	public void save(CatchSceniArea catchSceniArea) {
		super.save(catchSceniArea);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchSceniArea catchSceniArea) {
		super.delete(catchSceniArea);
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
					List<CatchSceniArea> list = new ArrayList<CatchSceniArea>();
					for (Object jsonObject : jsonArray) {
						CatchSceniArea catchSceniArea  = (CatchSceniArea)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchSceniArea.class);
						JSONObject json = JSONObject.fromObject(jsonObject);
						catchSceniArea.setId(null);
						catchSceniArea.setScenicArea(json.getString("scenic_area"));
						catchSceniArea.setTotalRevenue(json.getInt("total_revenue"));
						list.add(catchSceniArea);
					}
					//对数据进行同步
					this.insertBatch(list);
				}
			}
		}
	}
	
}