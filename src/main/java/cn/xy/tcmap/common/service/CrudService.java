/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.common.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import cn.xy.tcmap.common.persistence.CrudDao;
import cn.xy.tcmap.common.persistence.DataEntity;
import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);
		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

	/**
	 * 删除所有数据
	 */
	@Transactional(readOnly = false)
	public void deleteAll() {
		dao.deleteAll();
	}
	/**
	 * 批量插入所有数据
	 */
	@Transactional(readOnly = false)
	public void insertBatch(List<T> list){
		        if(list != null && list.size() > 0){
					for (T entity : list) {
						entity.preInsert();
					}
					dao.insertBatch(list);
				}
	}
	/**
	 * 数据同步
	 * @param url 请求链接
	 * @param addOrEditParams  新增或修改标记参数
	 * @param delParams        删除标记参数
	 * @param  entity          实体类
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public void syncDataTask(String url,String addOrEditParams,String delParams, T entity) throws JsonParseException, JsonMappingException, IOException{
		//json获取的结果数据
		String json = HttpUtils.post(url, addOrEditParams);
		String delJson = HttpUtils.post(url, delParams); 
		ObjectMapper mapper = new ObjectMapper();
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		if(StringUtils.isNotBlank(json)){ 
			Object object = JSONObject.fromObject(json).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//添加获取的数据
					List<T> listAll = new ArrayList<T>();
					for (Object jsonObject : jsonArray) {
						JSONObject obj = JSONObject.fromObject(jsonObject);
						obj.remove("dateTime");
						obj.remove("flag");
						entity = (T) mapper.readValue(obj.toString(), entity.getClass());
						if(get(entity) == null){
							//如果不存在 则库里进行添加
							listAll.add(entity);
						}else{
							//修改
							save(entity);	
						}
						
					}
					insertBatch(listAll);
				}

			}
		};
		if(StringUtils.isNotBlank(delJson)){
			Object object = JSONObject.fromObject(delJson).get("data");
			if(object != null){
				JSONArray jsonArray = JSONArray.fromObject(object);
				if(jsonArray.size() > 0 ){
					//删除数据
					for (Object jsonObject : jsonArray) {
						 JSONObject obj = JSONObject.fromObject(jsonObject);
						 obj.remove("dateTime");
						 obj.remove("flag");
						 entity = (T) mapper.readValue(obj.toString(), entity.getClass());
						 delete(entity);	
					}
				}

			}
		}
	}
}
