package cn.xy.tcmap.modules.catchup.csyx.service;

import cn.xy.tcmap.common.service.CrudService;
import cn.xy.tcmap.common.utils.HttpUtils;
import cn.xy.tcmap.common.utils.StringUtils;
import cn.xy.tcmap.modules.catchup.csyx.dao.CatchProblemSolvingDao;
import cn.xy.tcmap.modules.catchup.csyx.entity.CatchProblemSolving;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CatchProblemSolvingService extends CrudService<CatchProblemSolvingDao, CatchProblemSolving> {

    public Map<String,Object> findEchartData() {
        Map<String , Object> data = new HashMap<String, Object>();
        List<CatchProblemSolving> catchProblemSolvingList = super.findList(new CatchProblemSolving());
        if(catchProblemSolvingList !=null && catchProblemSolvingList.size() > 0){
            int resolved = 0;
            int problemCount = catchProblemSolvingList.size();
            for (CatchProblemSolving catchProblem: catchProblemSolvingList) {
                if("0".equals(catchProblem.getHasSolve())){
                    resolved++;
                }
            }
            data.put("problemTypeList",catchProblemSolvingList);
            data.put("problemSum",problemCount);

            data.put("resolved",resolved);

            DecimalFormat df=new DecimalFormat("0.00");
            data.put("solvingRate",df.format((float)resolved/problemCount*100));
        }
        return data;
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
                    List<CatchProblemSolving> list = new ArrayList<CatchProblemSolving>();
                    for (Object jsonObject : jsonArray) {
                        CatchProblemSolving catchProblemSolving  = (CatchProblemSolving)JSONObject.toBean(JSONObject.fromObject(jsonObject),CatchProblemSolving.class);
                        catchProblemSolving.setId(null);
                        JSONObject json = JSONObject.fromObject(jsonObject);
                        catchProblemSolving.setHasSolve(json.getString("has_solve"));
                        list.add(catchProblemSolving);
                    }
                    //对数据进行同步
                    this.insertBatch(list);
                }
            }
        }
    }

}
