/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ItemReason;
import cn.xy.tcmap.modules.catchup.csyx.dao.ItemReasonDao;

/**
 * 项目问题原因Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ItemReasonService extends CrudService<ItemReasonDao, ItemReason> {

	public ItemReason get(String id) {
		return super.get(id);
	}
	
	public List<ItemReason> findList(ItemReason itemReason) {
		return super.findList(itemReason);
	}
	
	public Page<ItemReason> findPage(Page<ItemReason> page, ItemReason itemReason) {
		return super.findPage(page, itemReason);
	}
	
	@Transactional(readOnly = false)
	public void save(ItemReason itemReason) {
		super.save(itemReason);
	}
	
	@Transactional(readOnly = false)
	public void delete(ItemReason itemReason) {
		super.delete(itemReason);
	}
	
}