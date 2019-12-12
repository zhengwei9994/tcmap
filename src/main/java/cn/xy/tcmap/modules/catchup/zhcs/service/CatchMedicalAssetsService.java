/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchPatentCount;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchMedicalAssets;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchMedicalAssetsDao;

/**
 * 医疗资源统计Service
 * @author liuyang
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class CatchMedicalAssetsService extends CrudService<CatchMedicalAssetsDao, CatchMedicalAssets> {

	public CatchMedicalAssets get(String id) {
		return super.get(id);
	}
	
	public List<CatchMedicalAssets> findList(CatchMedicalAssets catchMedicalAssets) {
		return super.findList(catchMedicalAssets);
	}
	
	public Page<CatchMedicalAssets> findPage(Page<CatchMedicalAssets> page, CatchMedicalAssets catchMedicalAssets) {
		return super.findPage(page, catchMedicalAssets);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchMedicalAssets catchMedicalAssets) {
		super.save(catchMedicalAssets);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchMedicalAssets catchMedicalAssets) {
		super.delete(catchMedicalAssets);
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
						CatchMedicalAssets catchMedicalAssets  = (CatchMedicalAssets)JSONObject.toBean(json,CatchMedicalAssets.class);
						catchMedicalAssets.setHospitalGrade(json.getString("hospital_grade"));// 医院等级
						catchMedicalAssets.setHospitalNumber(json.getInt("hospital_number"));// 医院数量
						catchMedicalAssets.setDoctorsNumber(json.getInt("doctors_number"));// 医生数量
						catchMedicalAssets.setNurseNumber(json.getInt("nurse_number"));//护士数量
						if(!(super.findList(catchMedicalAssets).size()>0)){
							catchMedicalAssets.setId(null);
						}
						super.save(catchMedicalAssets);
					}
				}
			}

		};
	}
}