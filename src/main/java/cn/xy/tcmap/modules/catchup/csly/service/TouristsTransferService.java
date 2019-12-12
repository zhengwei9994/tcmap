/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.catchup.csly.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.persistence.Page;
import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.modules.catchup.csly.entity.TouristsTransfer;
import cn.xy.tcmap.modules.catchup.csly.dao.TouristsTransferDao;

/**
 * 客流迁移Service
 * @author wufan
 * @version 2019-08-16
 */
@Service
@Transactional(readOnly = true)
public class TouristsTransferService extends CrudService<TouristsTransferDao, TouristsTransfer> {

	public TouristsTransfer get(String id) {
		return super.get(id);
	}
	
	public List<TouristsTransfer> findList(TouristsTransfer touristsTransfer) {
		return super.findList(touristsTransfer);
	}
	
	public Page<TouristsTransfer> findPage(Page<TouristsTransfer> page, TouristsTransfer touristsTransfer) {
		return super.findPage(page, touristsTransfer);
	}
	
	@Transactional(readOnly = false)
	public void save(TouristsTransfer touristsTransfer) {
		super.save(touristsTransfer);
	}
	
	@Transactional(readOnly = false)
	public void delete(TouristsTransfer touristsTransfer) {
		super.delete(touristsTransfer);
	}
	
}