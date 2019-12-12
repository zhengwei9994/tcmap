package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchProjectConstructionDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProjectConstruction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
public class CatchProjectConstructionService extends CrudService<CatchProjectConstructionDao, CatchProjectConstruction> {

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
                    List<CatchProjectConstruction> list = new ArrayList<CatchProjectConstruction>();
                    for (Object jsonObject : jsonArray) {
                        CatchProjectConstruction catchProjectConstruction  = (CatchProjectConstruction)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchProjectConstruction.class);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchProjectConstruction.setId(null);
                        catchProjectConstruction.setProjectName(json.getString("project_name"));
                        catchProjectConstruction.setHostUnit(json.getString("host_unit"));
                        String startTimeString = json.getString("start_time");
                        if(StringUtils.isNoneBlank(startTimeString)){
                            catchProjectConstruction.setStartTime(DateUtils.parseDate(startTimeString));
                        }
                        catchProjectConstruction.setAnnualTask(json.getDouble("annual_task"));
                        catchProjectConstruction.setPlannedInvestment(json.getDouble("planned_investment"));
                        catchProjectConstruction.setProjectIntro(json.getString("project_intro"));
                        catchProjectConstruction.setImagePath(json.getString("image_path"));
                        list.add(catchProjectConstruction);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }
}
