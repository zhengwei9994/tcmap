/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.zhcs.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchEducationAssets;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchAssetDetails;
import cn.xy.tcmap.modules.catchup.zhcs.dao.CatchAssetDetailsDao;

/**
 * 资源详情Service
 * @author liuyang
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class CatchAssetDetailsService extends CrudService<CatchAssetDetailsDao, CatchAssetDetails> {

	public CatchAssetDetails get(String id) {
		return super.get(id);
	}
	
	public List<CatchAssetDetails> findList(CatchAssetDetails catchAssetDetails) {
		return super.findList(catchAssetDetails);
	}
	
	public Page<CatchAssetDetails> findPage(Page<CatchAssetDetails> page, CatchAssetDetails catchAssetDetails) {
		return super.findPage(page, catchAssetDetails);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchAssetDetails catchAssetDetails) {
		super.save(catchAssetDetails);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchAssetDetails catchAssetDetails) {
		super.delete(catchAssetDetails);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params) {
		//通过接口获取数据
		String str = HttpUtils.post(url,params);
		if(StringUtils.isNotBlank(str)) {
			Object object = JSONObject.fromObject(str).get("data");
			if (object != null) {
				JSONArray jsonArray = JSONArray.fromObject(object);
				if (jsonArray.size() > 0) {
					//更新或添加获取的数据
					for (Object jsonObject : jsonArray) {
						JSONObject json = JSONObject.fromObject(jsonObject);
						CatchAssetDetails catchAssetDetails = (CatchAssetDetails) JSONObject.toBean(json, CatchAssetDetails.class);
						catchAssetDetails.setImagePath(json.getString("image_path"));
						if(!(super.findList(catchAssetDetails).size()>0)){
							catchAssetDetails.setId(null);
						}
						super.save(catchAssetDetails);
					}
				}
			}
		}
	}
}