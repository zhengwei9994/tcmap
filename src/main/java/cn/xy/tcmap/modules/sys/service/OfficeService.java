/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.xy.tcmap.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xy.tcmap.common.service.TreeService;
import cn.xy.tcmap.modules.sys.dao.OfficeDao;
import cn.xy.tcmap.modules.sys.entity.Office;
import cn.xy.tcmap.modules.sys.utils.UserUtils;

/**
 * 机构Service
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {

    public List<Office> findAll() {
        return UserUtils.getOfficeList();
    }

    //导出到excel把公司类型和公司级别进行转化
    public List<Office> findAllExcel() {
        List<Office> officeList = UserUtils.getOfficeList();
        for (Office office : officeList) {
            if (office.getType() != null) {
                switch (office.getType()) {
                    case "1":
                        office.setType("公司");
                        break;
                    case "2":
                        office.setType("部门");
                        break;
                    case "3":
                        office.setType("小组");
                        break;
                    default:
                        office.setType("其他");
                        break;
                }
            }
            if (office.getGrade() != null) {
                switch (office.getGrade()) {
                    case "1":
                        office.setGrade("一级");
                        break;
                    case "2":
                        office.setGrade("二级");
                        break;
                    case "3":
                        office.setGrade("三级");
                        break;
                    case "4":
                        office.setGrade("四级");
                        break;
                    default:
                        office.setGrade("五级及以下");
                        break;
                }
            }
        }
        return officeList;
    }

    //把导入的excel把公司类型和公司级别进行转化
    public Office type2Int(Office office) {
            if (office.getType() != null) {
                switch (office.getType()) {
                    case "公司":
                        office.setType("1");
                        break;
                    case "部门":
                        office.setType("2");
                        break;
                    case "小组":
                        office.setType("3");
                        break;
                    default:
                        office.setType("4");
                        break;
                }
            }
            if (office.getGrade() != null) {
                switch (office.getGrade()) {
                    case "一级":
                        office.setGrade("1");
                        break;
                    case "二级":
                        office.setGrade("2");
                        break;
                    case "三级":
                        office.setGrade("3");
                        break;
                    case "四级":
                        office.setGrade("4");
                        break;
                    default:
                        office.setGrade("5");
                        break;
                }
            }
        return office;
    }

    //excel 数据导入
    public void getExcelimport(List<Office> list) {
        if (list != null && list.size() > 0) {
            for (Office office : list) {

            }
        }
    }

    public List<Office> findList(Boolean isAll) {
        if (isAll != null && isAll) {
            return UserUtils.getOfficeAllList();
        } else {
            return UserUtils.getOfficeList();
        }
    }

    public List<Office> findList(Office office,int a) {
        if (office != null) {
            return dao.findList(office);
        }
        return new ArrayList<Office>();
    }

    @Transactional(readOnly = true)
    public List<Office> findList(Office office) {
        if (office != null) {
            office.setParentIds(office.getParentIds() + "%");
            return dao.findByParentIdsLike(office);
        }
        return new ArrayList<Office>();
    }

    @Transactional(readOnly = false)
    public void save(Office office) {
        super.save(office);
        UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
    }

    @Transactional(readOnly = false)
    public void delete(Office office) {
        super.delete(office);
        UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
    }

}
