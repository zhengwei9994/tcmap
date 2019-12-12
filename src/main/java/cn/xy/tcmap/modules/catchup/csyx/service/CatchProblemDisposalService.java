package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchProblemDisposalDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProblemDisposal;
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
public class CatchProblemDisposalService extends CrudService<CatchProblemDisposalDao, CatchProblemDisposal> {

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
                    List<CatchProblemDisposal> list = new ArrayList<CatchProblemDisposal>();
                    for (Object jsonObject : jsonArray) {
                        CatchProblemDisposal catchProblemDisposal  = (CatchProblemDisposal)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchProblemDisposal.class);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchProblemDisposal.setId(null);
                        catchProblemDisposal.setHostUnit(json.getString("host_unit"));
                        catchProblemDisposal.setProjectName(json.getString("project_name"));
                        Object disposal = json.get("problem_disposal");
                        if(disposal != null){
                            catchProblemDisposal.setProblemDisposal(Double.parseDouble(disposal.toString()));
                        }
                        list.add(catchProblemDisposal);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }

}
