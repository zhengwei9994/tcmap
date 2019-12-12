/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csyx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csyx.entity.ItemDepartment;
import cn.xy.tcmap.modules.catchup.csyx.dao.ItemDepartmentDao;

/**
 * 项目部委问题Service
 * @author wufan
 * @version 2019-07-31
 */
@Service
@Transactional(readOnly = true)
public class ItemDepartmentService extends CrudService<ItemDepartmentDao, ItemDepartment> {

	public ItemDepartment get(String id) {
		return super.get(id);
	}
	
	public List<ItemDepartment> findList(ItemDepartment itemDepartment) {
		return super.findList(itemDepartment);
	}
	
	public Page<ItemDepartment> findPage(Page<ItemDepartment> page, ItemDepartment itemDepartment) {
		return super.findPage(page, itemDepartment);
	}
	
	@Transactional(readOnly = false)
	public void save(ItemDepartment itemDepartment) {
		super.save(itemDepartment);
	}
	
	@Transactional(readOnly = false)
	public void delete(ItemDepartment itemDepartment) {
		super.delete(itemDepartment);
	}
	
}