/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import cn.xy.tcmap.common.utils.excel.fieldtype.AreaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.service.TreeService;
import cn.xy.tcmap.modules.sys.dao.AreaDao;
import cn.xy.tcmap.modules.sys.entity.Area;
import cn.xy.tcmap.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}

	//导出时区域类型转换
	public List<Area> findAreaExcel(){
		List<Area> areaList=findAll();
		for(Area area:areaList){
			if(area.getType()!=null){
				switch (area.getType()){
					case "1":
						area.setType("国家");
						break;
					case "2":
						area.setType("省份、直辖市");
						break;
					case "3":
						area.setType("地市");
						break;
					case "4":
						area.setType("区县");
						break;
					case "5":
						area.setType("其他");
						break;
				}
//				area.setName(AreaType.setValue(area));//导出时转换区域名称
			}
		}
		return areaList;
	}
	//导入时区域类型及名称转换
	public Area importArea(Area areas){
			if(areas.getType()!=null){
				switch (areas.getType()){
					case "国家":
						areas.setType("1");
						break;
					case "省份、直辖市":
						areas.setType("2");
						break;
					case "地市":
						areas.setType("3");
						break;
					case "区县":
						areas.setType("4");
						break;
					case "其他":
						areas.setType("5");
						break;
				}
			}
		return areas;
	}
 }
