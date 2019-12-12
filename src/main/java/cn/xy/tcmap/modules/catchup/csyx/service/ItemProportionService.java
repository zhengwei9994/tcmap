/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ItemProportion;
import cn.xy.tcmap.modules.catchup.csyx.dao.ItemProportionDao;

/**
 * 项目完成投资占比Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ItemProportionService extends CrudService<ItemProportionDao, ItemProportion> {

	public ItemProportion get(String id) {
		return super.get(id);
	}
	
	public List<ItemProportion> findList(ItemProportion itemProportion) {
		return super.findList(itemProportion);
	}
	
	public Page<ItemProportion> findPage(Page<ItemProportion> page, ItemProportion itemProportion) {
		return super.findPage(page, itemProportion);
	}
	
	@Transactional(readOnly = false)
	public void save(ItemProportion itemProportion) {
		super.save(itemProportion);
	}
	
	@Transactional(readOnly = false)
	public void delete(ItemProportion itemProportion) {
		super.delete(itemProportion);
	}
	
}