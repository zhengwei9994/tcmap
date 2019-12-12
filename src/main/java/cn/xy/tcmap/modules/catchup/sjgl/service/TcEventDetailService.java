/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.sjgl.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import io.swagger.models.auth.In;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.sjgl.entity.TcEventDetail;
import cn.xy.tcmap.modules.catchup.sjgl.dao.TcEventDetailDao;

/**
 * 事件数据详情Service
 * @author wh
 * @version 2019-12-05
 */
@Service
@Transactional(readOnly = true)
public class TcEventDetailService extends CrudService<TcEventDetailDao, TcEventDetail> {

	@Autowired
	TcEventDetailDao tcEventDetailDao;
	public TcEventDetail get(String id) {
		return super.get(id);
	}
	
	public List<TcEventDetail> findList(TcEventDetail tcEventDetail) {
		return super.findList(tcEventDetail);
	}
	
	public Page<TcEventDetail> findPage(Page<TcEventDetail> page, TcEventDetail tcEventDetail) {
		return super.findPage(page, tcEventDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(TcEventDetail tcEventDetail) {
		super.save(tcEventDetail);
	}
	
	@Transactional(readOnly = false)
	public void delete(TcEventDetail tcEventDetail) {
		super.delete(tcEventDetail);
	}

	/***
	 * 根据事件类型，统计各类型事件数据
	 * @return 使用json 传递
	 */
	@Transactional(readOnly = false)
	public JSONArray sumEvent(){
		JSONArray jsonArray=new JSONArray();
		List<TcEventDetail> list=tcEventDetailDao.findEventType();//找到所有事件类型
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setMaximumFractionDigits(2);
		for(TcEventDetail detail:list){
	      JSONObject jsonObject=new JSONObject();//声明存放数据的jsonObjcet
			try {
				TcEventDetail tcEventDetail=new TcEventDetail();
				tcEventDetail.setType(detail.getType());//组装对象，计算该类型的统计数据
                double detailTypeSum=findList(tcEventDetail).size();//某类型事件Sum
				double sum=findList(new TcEventDetail()).size();//所有事件Sum
				jsonObject.put("type",detail.getType());//将事件类型名称放入
				jsonObject.put("eventSum",detailTypeSum);
				if(sum ==0){
					jsonObject.put("sum",1);
				}else {
					jsonObject.put("sum",sum);
				}
				jsonObject.put("personal",detail.getEventPerson());
				jsonArray.add(jsonObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonArray;
	}
}