/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.fzjc.service;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.Reflections;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.fzjc.dao.CatchIndexCompletionDao;
import cn.xy.tcmap.modules.catchup.fzjc.entity.CatchIndexCompletion;
import cn.xy.tcmap.modules.catchup.zhcs.entity.CatchResourcesCount;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运行指标完成率Service
 * @author liuyang
 * @version 2018-05-29
 */
@Service
@Transactional(readOnly = true)
public class CatchIndexCompletionService extends CrudService<CatchIndexCompletionDao, CatchIndexCompletion> {

	@Autowired
	private CatchIndexCompletionDao catchIndexCompletionDao;

	public List<CatchIndexCompletion> groupByindexType(CatchIndexCompletion catchIndexCompletion){
		return catchIndexCompletionDao.groupByindexType(catchIndexCompletion);
	}

	public CatchIndexCompletion get(String id) {
		return super.get(id);
	}
	
	public List<CatchIndexCompletion> findList(CatchIndexCompletion catchIndexCompletion) {
		return super.findList(catchIndexCompletion);
	}
	
	public Page<CatchIndexCompletion> findPage(Page<CatchIndexCompletion> page, CatchIndexCompletion catchIndexCompletion) {
		return super.findPage(page, catchIndexCompletion);
	}
	
	@Transactional(readOnly = false)
	public void save(CatchIndexCompletion catchIndexCompletion) {
		super.save(catchIndexCompletion);
	}
	
	@Transactional(readOnly = false)
	public void delete(CatchIndexCompletion catchIndexCompletion) {
		super.delete(catchIndexCompletion);
	}
	@Transactional(readOnly = false)
	public void syndata(String url,String params,String entity,String service) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, BeansException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
/*		String jsonStr = "{\"index_name\":\"zhangsan\",\"completion_rate\":\"25\",\"index_type\":\"2\"}";
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		Class<?> entityClass = Class.forName(entity);
		Object ab = mapper.readValue(jsonStr.toString().getBytes(), entityClass);
		System.out.println(ab);*/
	
		/*String str = HttpUtils.post(url, params);
		if(StringUtils.isNotBlank(str)){ 
			ObjectMapper mapper = new ObjectMapper();
			mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
			Object object = JSONObject.fromObject(str).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					List<CatchIndexCompletion> list = new ArrayList<CatchIndexCompletion>();
					for (Object jsonObject : jsonArray) {
						CatchIndexCompletion catchIndexCompletion =  mapper.readValue(jsonObject.toString().getBytes(), entity.getClass());
						if(super.get(catchIndexCompletion) == null){
							//如果不存在 则库里进行添加
							list.add(catchIndexCompletion);
						}else{
							//修改
							super.save(catchIndexCompletion);	
						}
						
					}
					super.insertBatch(list);
				}

			}
		};*/
		
	/*	//通过接口获取数据
		String str = HttpUtils.post(url, params);
		if(StringUtils.isNotBlank(str)){  
			Object object = JSONObject.fromObject(str).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					List<CatchIndexCompletion> list = new ArrayList<CatchIndexCompletion>();
					for (Object jsonObject : jsonArray) {
						CatchIndexCompletion a = new ObjectMapper().readValue(jsonObject.toString().getBytes(), CatchIndexCompletion.class);
						
						String  indexType = JSONObject.fromObject(jsonObject).getString("index_type");//因返回数据与实体字段不一致 则做如此处理
						CatchIndexCompletion	catchIndexCompletion  = (CatchIndexCompletion)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchIndexCompletion.class);
						catchIndexCompletion.setIndexType(indexType);
						catchIndexCompletion.setIndexName(JSONObject.fromObject(jsonObject).getString("index_name"));
						catchIndexCompletion.setCompletionRate(Double.valueOf(JSONObject.fromObject(jsonObject).getString("completion_rate")));
						if(super.get(catchIndexCompletion) == null){
							//如果不存在 则库里进行添加
							list.add(catchIndexCompletion);
						}else{
							//修改
							super.save(catchIndexCompletion);	
						}
						
					}
					super.insertBatch(list);
				}

			}

		};*/
	}
}