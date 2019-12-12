package cn.xy.tcmap.modules.catchup.screen.fzjc;

import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.fzjc.entity.*;
import cn.xy.tcmap.modules.catchup.fzjc.service.*;
import cn.xy.tcmap.modules.sys.entity.Dict;
import cn.xy.tcmap.modules.sys.utils.DictUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/30.
 */

@Controller
@Api(tags = "f/fzjc/screenfzjc", description = "城市辅助决策一张图相关接口")
@RequestMapping(value = "${frontPath}/fzjc/screenfzjc")
public class ScreenFzjcController extends BaseController {

    @Autowired
    private CatchSalaryStaffService catchSalaryStaffService;

    @Autowired
    private CatchHumanResourcesService catchHumanResourcesService;

    @Autowired
    private CatchEnergyConsumptionService catchEnergyConsumptionService;

    @Autowired
    private CatchIndexCompletionService catchIndexCompletionService;

    @Autowired
    private CatchApprovalMattersService catchApprovalMattersService;


    @Autowired
    private CatchVideoResourceService catchVideoResourceService;

    @Autowired
    private CatchIndexStatusService catchIndexStatusService;


    @Autowired
    private CatchGrowthSituationService catchGrowthSituationService;

    @Autowired
    private CacheEnterpriseBasicDataService cacheEnterpriseBasicDataService;

    @Autowired
    private CatchLaborEmploymentService catchLaborEmploymentService;
    @Autowired
    private CatchPractitionersService catchPractitionersService;
    @Autowired
    private CatchSalaryStructureService catchSalaryStructureService; 
    @Autowired
    private CatchElectronicEvidenceService catchElectronicEvidenceService;
    @Autowired
    private CatchElectronicCategoryService catchElectronicCategoryService;
    @Autowired
    private CatchDataStatusService catchDataStatusService;
    @Autowired
    private CatchServiceIndicatorsService catchServiceIndicatorsService;
    @Autowired
    private CatchResidentIncomeService catchResidentIncomeService;
    /**
     * 运行指标完成率
     * @param catchIndexCompletionC
     * @param response
     */
    @ApiOperation(value = "运行指标完成率", httpMethod = "GET", notes = "completionRate:指标完成率")
    @RequestMapping(value = "catchIndexCompletion")
    public void catchIndexCompletion (CatchIndexCompletion catchIndexCompletion, HttpServletResponse response){
        JSONArray jsonArray1 = new JSONArray();
        catchIndexCompletion.setNyear(nyear);
        List<CatchIndexCompletion> list =catchIndexCompletionService.groupByindexType(catchIndexCompletion);
        for(CatchIndexCompletion catchIndexCompletions:list){
            JSONArray jsonArray3 = new JSONArray();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("indexType",catchIndexCompletions.getIndexType());
            catchIndexCompletion.setIndexType(catchIndexCompletions.getIndexType());
            List<CatchIndexCompletion> lists =catchIndexCompletionService.findList(catchIndexCompletion);
            for (CatchIndexCompletion completion:lists){
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("indexName",completion.getIndexName());
                jsonObject2.put("completionRate",completion.getCompletionRate());
                jsonArray3.put(jsonObject2);
            }
            jsonObject1.put("data",jsonArray3);
            jsonArray1.put(jsonObject1);
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray1.toString());


    }


    /**
     * 行政审批事项
     * @param catchApprovalMatters
     * @param response
     */
    @ApiOperation(value = "运行指标完成率", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchApprovalMatters")
    public void catchApprovalMatters (CatchApprovalMatters catchApprovalMatters, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        catchApprovalMatters.setNyear(nyear);
        List<CatchApprovalMatters> list = catchApprovalMattersService.findList(catchApprovalMatters);
        for (CatchApprovalMatters catchApprovalMatterss : list){
            jsonArray1.put(catchApprovalMatterss.getDepartment());
            jsonArray2.put(catchApprovalMatterss.getMattersCount());
        }
        jsonObject.put("department",jsonArray1);
        jsonObject.put("mattersCount",jsonArray2);

        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }


    /**
     * 公共安全视频资源覆盖情况
     * @param catchVideoResource
     * @param response
     */
    @ApiOperation(value = "公共安全视频资源覆盖情况", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchVideoResource")
    public void catchVideoResource (CatchVideoResource catchVideoResource, HttpServletResponse response){
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        catchVideoResource.setNyear(nyear);
        List<CatchVideoResource> list = catchVideoResourceService.findList(catchVideoResource);
        if(list!=null && list.size()>0){
            catchVideoResource = list.get(0);
            jsonArray1.put(catchVideoResource.getKeyRegionalCoverage());
            jsonArray1.put(catchVideoResource.getCoverageKeyAreas());
            jsonArray1.put(catchVideoResource.getHighDefinitionCamera());
            jsonArray1.put(catchVideoResource.getCameraIntegrityRatio());
            jsonArray1.put(catchVideoResource.getMachineCompletionKey());
        }
        jsonArray2.put("重点区域覆盖率");
        jsonArray2.put("重点领域覆盖率");
        jsonArray2.put("高清摄像机比率");
        jsonArray2.put("摄像机完好比率");
        jsonArray2.put("重点领域机器完好率");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",jsonArray2);
        jsonObject.put("data",jsonArray1);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }


    /**
     * 万元GDP能耗降低率
     * @param catchEnergyConsumption
     * @param response
     */
    @ApiOperation(value = "万元GDP能耗降低率", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchEnergyConsumption")
    public void catchEnergyConsumption (CatchEnergyConsumption  catchEnergyConsumption, HttpServletResponse response){
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        catchEnergyConsumption.setYear(nyear);
        List<CatchEnergyConsumption> list = catchEnergyConsumptionService.findList(catchEnergyConsumption);
        for(CatchEnergyConsumption  catchEnergyConsumptions : list){
            jsonArray1.put(catchEnergyConsumptions.getEnergyConsumption());
            jsonArray2.put(catchEnergyConsumptions.getEnergyConsumptionRate());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("energyConsumption",jsonArray1);
        jsonObject.put("energyConsumptionRate",jsonArray2);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }


    /**
     * 人力资源统计
     * @param catchHumanResources
     * @param response
     */
    @ApiOperation(value = "人力资源统计", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchHumanResources")
    public void catchHumanResources (CatchHumanResources catchHumanResources, HttpServletResponse response){
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        catchHumanResources.setNyear(nyear);
        List<CatchHumanResources> list = catchHumanResourcesService.findList(catchHumanResources);
        Double sum = 0.0;
        for(CatchHumanResources catchHumanResourcess : list){
            sum += catchHumanResourcess.getNumberPeople();
            jsonArray1.put(catchHumanResourcess.getCompanyType());
            jsonArray2.put(catchHumanResourcess.getNumberPeople());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",jsonArray1);
        jsonObject.put("data",jsonArray2);
        jsonObject.put("sum",sum);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObject.toString());
    }

    @ResponseBody
    @ApiOperation(value = "薪资及人员分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchSalaryStaff")
    public List<CatchSalaryStaff> catchSalaryStaff (CatchSalaryStaff catchSalaryStaff, HttpServletResponse response){
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        catchSalaryStaff.setNyear(nyear);
        List<CatchSalaryStaff> list = catchSalaryStaffService.findList(catchSalaryStaff);
        return list;
    }


    @ResponseBody
    @ApiOperation(value = "指标数据运行状态", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchIndexStatus")
    public List<CatchIndexStatus> catchIndexStatus (CatchIndexStatus catchIndexStatus, HttpServletResponse response){
        catchIndexStatus.setNyear(nyear);
        List<CatchIndexStatus> list = catchIndexStatusService.findList(catchIndexStatus);
        return list;
    }

    @ResponseBody
    @ApiOperation(value = "区县经济增长情况", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchGrowthSituation")
    public Map<String , Map > catchGrowthSituation (CatchGrowthSituation catchGrowthSituation, HttpServletResponse response){
        List<CatchGrowthSituation> list = catchGrowthSituationService.findList(catchGrowthSituation);

        List<Dict> dicts = DictUtils.getDictList("economic_growth_type");
        Map<String , Map > dataMap = new HashMap<String , Map >();
        for(Dict dict : dicts){
            dataMap.put(dict.getValue() ,new HashMap<String, Map>());
        }

        for(CatchGrowthSituation cn : list){
            Map<String , List> md = dataMap.get(cn.getIndexType());
            List<String> values = md.get(cn.getNyear());
            if(values==null){
                values = new ArrayList<String>();
            }
            values.add(cn.getIndexValue());
            md.put(cn.getNyear(), values);

            dataMap.put(cn.getIndexType() , md);
        }
        return dataMap;
    }


    @ResponseBody
    @ApiOperation(value = "企业结构基础数据分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "cacheEnterpriseBasicData")
    public List<CacheEnterpriseBasicData> cacheEnterpriseBasicData (CacheEnterpriseBasicData cacheEnterpriseBasicData, HttpServletResponse response){
        List<CacheEnterpriseBasicData> list = cacheEnterpriseBasicDataService.findList(cacheEnterpriseBasicData);
        return list;
    }
    @ResponseBody
    @ApiOperation(value = "劳动就业检索", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchLaborEmploymentData")
    public void cacheEnterpriseBasicData (CatchLaborEmployment catchLaborEmployment, HttpServletResponse response){
        JSONArray jsonArray =new  JSONArray();
        JSONObject jsonAll = new JSONObject();
       List<CatchLaborEmployment>  list = catchLaborEmploymentService.findList(catchLaborEmployment);
       int tatalPerson = 0 ;
        if (list != null && list.size() > 0) {
            for (CatchLaborEmployment ca: list) {
                JSONObject json = new JSONObject();
                json.put("kind",ca.getKind());
                json.put("number",ca.getNumber());
                json.put("proportion",ca.getProportion()+"%");
                json.put("imagePath",ca.getImagepath());
                tatalPerson += Integer.parseInt(ca.getNumber()); 
                jsonArray.put(json);
            }
            jsonAll.put("result",jsonArray);
            jsonAll.put("tatal",tatalPerson);
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonAll.toString());
    }
    
    @ResponseBody
    @ApiOperation(value = "劳动就业占比", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchPractitionersData")
    public void catchPractitionersData (CatchPractitioners catchPractitioners, HttpServletResponse response){
        JSONArray jsonArray =new  JSONArray();
        JSONObject jsonAll = new JSONObject();
       List<CatchPractitioners>  list = catchPractitionersService.findList(catchPractitioners);
        if (list != null && list.size() > 0) {
            for (CatchPractitioners ca: list) {
            /*	"address": "铜川市新区",
                "proportion":"从业人员占比89%",
                "width":"89%"*/
                JSONObject json = new JSONObject();
                json.put("address",ca.getAddress());
                json.put("proportion","从业人员占比"+ ca.getProportion()+"%");
                json.put("width",ca.getProportion());
                jsonArray.put(json); 
            }
            jsonAll.put("result",jsonArray);
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonAll.toString());
    }
    
    @ResponseBody
    @ApiOperation(value = "人才结构分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchSalaryStructureData")
    public void catchSalaryStructure  (CatchSalaryStructure catchSalaryStructure, HttpServletResponse response){
        JSONArray jsonArray =new  JSONArray();
        List<CatchSalaryStructure>  list = catchSalaryStructureService.findList(catchSalaryStructure);
        if(list !=null && list.size() > 0){
        for (CatchSalaryStructure ca : list) {
        	JSONArray ja =new  JSONArray();
			ja.put(ca.getDate());
			ja.put(ca.getJunior());//中专
			ja.put("中专");//中专
			JSONArray te =new  JSONArray();
			te.put(ca.getDate());
			te.put(ca.getTechnical());//大专
			te.put("大专");//大专
			JSONArray co =new  JSONArray();
			co.put(ca.getDate());
			co.put(ca.getCollege());//本科
			co.put("本科");//本科
			JSONArray de =new  JSONArray();
			de.put(ca.getDate());
			de.put(ca.getDucation());//硕士以上
			de.put("硕士以上");//硕士以上
	        jsonArray.put(ja);
	        jsonArray.put(te);
	        jsonArray.put(co);
	        jsonArray.put(de);
		}   	
    }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArray.toString());
    }
    
    @ResponseBody
    @ApiOperation(value = "电子证照使用", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchElectronicEvidenceData")
    public Map<String, List<Map<String, String>>> catchElectronicEvidenceData  (CatchElectronicEvidence catchElectronicEvidence, HttpServletResponse response){
    	
    	List<CatchElectronicEvidence> list = catchElectronicEvidenceService.findList(catchElectronicEvidence);
        Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String,String>>>();
        if(list != null && list.size()>0){
            for ( CatchElectronicEvidence st : list) {
                map.put(st.getNyear(), new ArrayList<Map<String,String>>());
            }
            for ( CatchElectronicEvidence st : list) {      	
                List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
                Map<String, String> map1= new HashMap<String , String>();       
                map1.put("month", st.getMonth());
                map1.put("banking", st.getBanking());
                map1.put("house", st.getHouse());
                map1.put("education", st.getEducation());
                map1.put("medical", st.getMedical());
                map1.put("traffic", st.getTraffic());
                map1.put("travel", st.getTravel());
                map1.put("shop", st.getShop());
                list1.add(map1);
                map.get(st.getNyear()).add(map1);
            }
        }   
        return map;
    }
    
    @ResponseBody
    @ApiOperation(value = "电子证照使用类别说明", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchElectronicCategoryData")
    public void catchElectronicCategoryData (CatchElectronicCategory catchElectronicCategory, HttpServletResponse response){
        JSONArray jsonArray1 =new  JSONArray();
        JSONArray jsonArray2 =new  JSONArray();
        JSONObject jsonArr  = new JSONObject();
       List<CatchElectronicCategory>  list = catchElectronicCategoryService.findList(catchElectronicCategory);
        if (list != null && list.size() > 0) {
        	//1:服务类型   2：行业类型
            for (CatchElectronicCategory ca: list) {
            	if("1".equals(ca.getType())){
            		 JSONObject json = new JSONObject();
                     json.put("name",ca.getName());
                     json.put("value",ca.getValue());
                     jsonArray1.put(json); 
            	}else{
            		 JSONObject json = new JSONObject();
                     json.put("name",ca.getName());
                     json.put("value",ca.getValue());
                     jsonArray2.put(json);
            	}
               
            }
            jsonArr.put("data1",jsonArray1);
            jsonArr.put("data2",jsonArray2);
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArr.toString());
    }
    
    @ResponseBody
    @ApiOperation(value = "指标数据运行状态", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchDataStatusData")
    public void catchDataStatusData (CatchDataStatus catchDataStatus, HttpServletResponse response){
        JSONArray jsonArray1 =new  JSONArray();
        JSONArray jsonArray2 =new  JSONArray();
        JSONArray jsonArray3 =new  JSONArray();
        JSONObject jsonArr  = new JSONObject();
        catchDataStatus.setNyear(nyear);
        catchDataStatus.getPage().setOrderBy("month");
        List<CatchDataStatus>  list = catchDataStatusService.findList(catchDataStatus);
        if(list != null && list.size()>0){
        	 for (CatchDataStatus ca : list) {
        		 jsonArray1.put(ca.getDataGov());//政府网站访问次数
        		 jsonArray2.put(ca.getDataSearch());//搜索引擎访问次数
        		 jsonArray3.put(ca.getDataDirect());//直接访问次数
			} 
        	 jsonArr.put("arr1", jsonArray3);
        	 jsonArr.put("arr2", jsonArray2);
        	 jsonArr.put("arr3", jsonArray1);
        } 
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonArr.toString());
    }
    @ResponseBody
    @ApiOperation(value = "区县综合政府服务指标", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchServiceIndicatorsData")
    public void catchServiceIndicatorsData (CatchServiceIndicators catchServiceIndicators, HttpServletResponse response){
        List<CatchServiceIndicators>  list = catchServiceIndicatorsService.findList(catchServiceIndicators);
        JSONObject jsonObejct  = new JSONObject();
        if(list != null && list.size() > 0){
        for (CatchServiceIndicators ca : list) {
            JSONArray jsonArray1 =new  JSONArray();
        	jsonArray1.put(ca.getFiber());
        	jsonArray1.put(ca.getBroadband());
        	jsonArray1.put(ca.getHospital());
        	jsonArray1.put(ca.getSecurity());
        	jsonArray1.put(ca.getUniform());
        	jsonArray1.put(ca.getProcessing());
           	jsonObejct.put(ca.getAreaName(), jsonArray1);
		  }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObejct);
    }
    
    @ResponseBody
    @ApiOperation(value = "居民收入", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchResidentIncomeData")
    public void catchResidentIncomeData (CatchResidentIncome catchResidentIncome, HttpServletResponse response){
        List<CatchResidentIncome>  list = catchResidentIncomeService.findList(catchResidentIncome);
        JSONObject jsonObejct  = new JSONObject();
        /*  wageIncome;		// 工资收入
            operatingIncome;		// 经营净收入
    	    ownershipIncome;		// 财产净收入
    	    transferIncome;		// 转移净收入
        */ 
        if(list != null && list.size() > 0){     
        for (CatchResidentIncome ca : list) {
            JSONArray jsonArray1 =new  JSONArray();
            jsonArray1.put(ca.getWageIncome());
            jsonArray1.put(ca.getOperatingIncome());
            jsonArray1.put(ca.getOwnershipIncome());
            jsonArray1.put(ca.getTransferIncome());
            jsonArray1.put(ca.getRate());
          	jsonObejct.put(ca.getResidentClusters(), jsonArray1);
		  }
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(jsonObejct);
    }
}
