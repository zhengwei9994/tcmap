package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchSuperviseDynamicsDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchSuperviseDynamic;
import cn.xy.tcmap.modules.sys.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : ${Desc}
 * ---------------------------------
 * @Author : dell
 * @Date : Create in 2018/9/19
 */
@Service
public class CatchSuperviseDynamicService extends CrudService<CatchSuperviseDynamicsDao, CatchSuperviseDynamic> {
    @Autowired
    private  CatchSuperviseDynamicsDao catchSuperviseDynamicsDao;
    public List<CatchSuperviseDynamic> findAll(CatchSuperviseDynamic catchSuperviseDynamic) {
        return catchSuperviseDynamicsDao.findAll(catchSuperviseDynamic);
    }

    @Transactional(readOnly = false)
    public void syndata(String url,String paramsAdd,String paramEdit) {
        /*String responseEditJsonStr= HttpUtils.post(url,paramEdit);
        //通过接口获取修改数据
        if(StringUtils.isNotBlank(responseEditJsonStr)) {
            //修改数据的处理
        }*/
        //通过接口获取新增数据
        String responseAddJsonStr = HttpUtils.post(url,paramsAdd);
        if(StringUtils.isNotBlank(responseAddJsonStr)){
            Object object = JSONObject.fromObject(responseAddJsonStr).get("data");
            if(object != null){
                JSONArray jsonArray = JSONArray.fromObject(object);
                if(jsonArray.size() > 0 ){
                    //添加获取的数据
                    List<CatchSuperviseDynamic> list = new ArrayList<CatchSuperviseDynamic>();
                    for (Object jsonObject : jsonArray) {
                        CatchSuperviseDynamic catchSuperviseDynamic  = (CatchSuperviseDynamic)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchSuperviseDynamic.class);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchSuperviseDynamic.setId(null);
                        catchSuperviseDynamic.setHasRead(json.getString("has_read"));
                        String createTimeString = json.getString("create_time");
                        createTimeString = createTimeString.substring(0,createTimeString.lastIndexOf("."));
                        if(StringUtils.isNoneBlank(createTimeString)){
                            catchSuperviseDynamic.setCreateTime(DateUtils.parseDate(createTimeString));
                        }
                        catchSuperviseDynamic.setProjectName(json.getString("project_name"));
                        catchSuperviseDynamic.setUser(new User(json.getString("user_id")));
                        list.add(catchSuperviseDynamic);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }
}
