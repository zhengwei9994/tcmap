package cn.xy.tcmap.modules.catchup.screen.csyx;

import cn.xy.tcmap.common.utils.DateUtils;
import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.csyx.entity.*;
import cn.xy.tcmap.modules.catchup.csyx.service.*;
import cn.xy.tcmap.modules.catchup.wlkj.entity.CatchEarlyWarning;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "f/csyx/screenCsyx", description = "城市全景运行一张图相关接口")
@RequestMapping(value = "${frontPath}/csyx/screenCsyx")
public class ScreenCsyxController extends BaseController{

    @Autowired
    private CathEconomicService cathEconomicService;
    @Autowired
    private CatchServiceIndustryService catchServiceIndustryService;
    @Autowired
    private CatchSpecialProjectService catchSpecialProjectService;
    @Autowired
    private CatchPlanInvestmentService catchPlanInvestmentService;
    @Autowired
    private CatchKeyprojectClassService catchKeyprojectClassService;
    @Autowired
    private CatchKeyprojectService catchKeyprojectService;
    @Autowired
    private CatchGovernmentService catchGovernmentService;
    @Autowired
    private CatchPollutionTreatmentService catchPollutionTreatmentService;
    @Autowired
    private CatchAqiparamService catchAqiparamService;
    @Autowired
    private CatchProvincesStatisticalService catchProvincesStatisticalService;
    @Autowired
    private CatchShakePovertyService catchShakePovertyService;
    @Autowired
    private CatchSceniAreaService catchSceniAreaService;
    @Autowired
    private CatchEconomicRateService catchEconomicRateService;
    @Autowired
    private CatchAirQualityService catchAirQualityService;
    @Autowired
    private CatchSwageTreatService catchSwageTreatService;
    @Autowired
    private CatchWasteWaterService catchWasteWaterService;
    @Autowired
    private CatchWaterTradeService catchWaterTradeService;
    @Autowired
    private CatchGasResourceService catchGasResourceService;
    @Autowired
    private CatchProjectConstructionService catchProjectConstructionService;
    @Autowired
    private CatchProblemDisposalService catchProblemDisposalService;
    @Autowired
    private CatchInvestmentProgressService catchInvestmentProgressService;
    @Autowired
    private CatchSuperviseDynamicService superviseDynamicService;
    @Autowired
    private CatchProblemSolvingService catchProblemSolvingService;
	@Autowired
	private CatchEconomicGrowthService catchEconomicGrowthService;

    //经济指标统计
    @ResponseBody
    @ApiOperation(value = "经济指标统计", httpMethod = "GET", notes = "indicators:指标值，growth：增速")
    @RequestMapping(value = "indexData",method = RequestMethod.GET)
    public void indexData(CathEconomic cathEconomic, HttpServletResponse response){
        cathEconomic.setNyear(nyear);
        //季度
        int season = DateUtils.getSeason(new Date());
        JSONArray jsonArray =new  JSONArray();
        List<CathEconomic> cathEconomics = cathEconomicService.jjzbData(cathEconomic);
        if(cathEconomics != null && cathEconomics.size()>0){
            for(CathEconomic cathEconomic1 : cathEconomics){
                JSONObject json = new JSONObject();
                json.put("year",cathEconomic1.getNyear());
                json.put("indexName",cathEconomic1.getIndexId().getName());
                if(cathEconomic1.getIndexId().getName().contains("人均")){
                	   json.put("indicators",cathEconomic1.getIndicators()/season);
                }else{
                	 json.put("indicators",cathEconomic1.getIndicators());
                }
             
                if("1".equals(cathEconomic1.getIndicatorsUnit())){
                    json.put("unit","元");
                }else if("2".equals(cathEconomic1.getIndicatorsUnit())){
                	json.put("unit","万元");
                }else{
                	json.put("unit","亿元");
                }
                json.put("indexNameEN", cathEconomic1.getIndexId().getNameEn());
                json.put("growth",cathEconomic1.getGrowth()/season);
                jsonArray.put(json);
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
    //服务业稳中向好
    @ResponseBody
    @ApiOperation(value = "服务业稳中向好", httpMethod = "GET", notes = "growth：增速，point：百分点")
    @RequestMapping(value = "fuWuData",method = RequestMethod.GET)
    public void fuWuData(CatchServiceIndustry catchServiceIndustry,HttpServletResponse response){
            catchServiceIndustry.setNyear(nyear);
            JSONArray jsonArray = new JSONArray();
            List<CatchServiceIndustry> list = catchServiceIndustryService.findList(catchServiceIndustry);
            if(list != null && list.size()>0){
                for(CatchServiceIndustry catchServiceIndustry1 : list){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("year",catchServiceIndustry1.getNyear());
                    jsonObject.put("growth",catchServiceIndustry1.getGrowth());
                    jsonObject.put("point",catchServiceIndustry1.getPoint());
                    jsonArray.put(jsonObject);
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
    //特色项目
    @ResponseBody
    @ApiOperation(value = "特色项目", httpMethod = "GET", notes = "details：明细，sort：序列（从左到右依次）")
    @RequestMapping(value = "speProjectList",method = RequestMethod.GET)
    public void speProjectList(CatchSpecialProject catchSpecialProject,HttpServletResponse response){
        JSONArray jsonArray = new JSONArray();
        catchSpecialProject.setNyear(nyear);
        List<CatchSpecialProject> list = catchSpecialProjectService.findList(catchSpecialProject);
        if(list != null && list.size()>0){
            for(CatchSpecialProject catchSpecialProject1 : list){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("year",catchSpecialProject1.getNyear());
                jsonObject.put("name",catchSpecialProject1.getProjectname());
                jsonObject.put("details",catchSpecialProject1.getDetails());//明细
                jsonObject.put("sort",catchSpecialProject1.getSort());
                jsonArray.put(jsonObject);
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
    //当前年计划投资
    @ResponseBody
    @ApiOperation(value = "当前年计划投资", httpMethod = "GET", notes = "SDFSDFSD")
    @RequestMapping(value = "yearPlan")
    public void yearPlan(CatchPlanInvestment catchPlanInvestment,CatchKeyproject catchKeyproject,HttpServletResponse response){
        JSONArray jsonArray = new JSONArray();
        catchKeyproject.setNyear(nyear);
        catchPlanInvestment.setNyear(nyear);
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
         List<HashMap> list = catchKeyprojectClassService.totalData(catchKeyproject);//累计完成投资，和已完成占比
        if(list != null && list.size()>0){
            net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(list.get(0));
            jsonObject2.put("name","累计投资");
            jsonObject2.put("shuJu",json.get("amountCompleted").toString());
            jsonObject2.put("unit","亿元");
            jsonObject3.put("name","已完成投资占比");
            jsonObject3.put("shuJu",json.get("zhanbi").toString());
            jsonObject3.put("unit","%");
        }
        List<HashMap> catchPlanInvestments = catchPlanInvestmentService.yearPlanData(catchPlanInvestment);//年计划总投资
        if(catchPlanInvestments != null && catchPlanInvestments.size()>0){
            net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(catchPlanInvestments.get(0));
            jsonObject1.put("name","年计划投资");
            jsonObject1.put("shuJu",json.get("plannedInvestment").toString());
            if(json.get("unit").toString().equals("3")){
            	 jsonObject1.put("unit","亿元");
            }else if(json.get("unit").toString().equals("2")){
            	 jsonObject1.put("unit","万元");
            }else{
            	 jsonObject1.put("unit","元");
            }
           
        }
        List<HashMap> num = catchKeyprojectClassService.keyprojectNum(catchKeyproject);//已完成项目总个数
        if(num != null && num.size()>0){
            net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(num.get(0));
            Object jsonObject = json.get("projectStatus");
            jsonObject4.put("name","已完成项目");
            jsonObject4.put("shuJu",json.get("projectStatus").toString());
            jsonObject4.put("unit","个");
        }
        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject2);
        jsonArray.put(jsonObject3);
        jsonArray.put(jsonObject4);
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

    //工程创新饼图
    @ResponseBody
    @ApiOperation(value = "工程创新饼图", httpMethod = "GET", notes = "")
    @RequestMapping(value = "projectBt")
    public  List<CatchKeyproject> projectBt(CatchKeyproject catchKeyproject){
        catchKeyproject.setNyear(nyear);
        List<CatchKeyproject> list = catchKeyprojectService.keyprojectTypeData(catchKeyproject);
        for(CatchKeyproject catchKeyproject1 : list){
            catchKeyproject1.setUnit("亿元");
        }
        return list;
    }

    //工程创新项目进度条
    @ResponseBody
    @ApiOperation(value = "工程创新项目进度条", httpMethod = "GET", notes = "")
    @RequestMapping(value = "keyprojectProgress")
    public Map keyprojectProgress(CatchKeyproject catchKeyproject){
        Map map = new HashMap();
        catchKeyproject.setNyear(nyear);
        List<HashMap> list = catchKeyprojectClassService.keyprojectProgress(catchKeyproject);
        map.put("resultList",list);
        return map;
    }
    //社会治理
    @ResponseBody
    @RequestMapping(value = "catchShzl")
    public List<CatchGovernment>  catchShzl(CatchGovernment catchGovernment){
        catchGovernment.setNyear(nyear);
        List<CatchGovernment> list = catchGovernmentService.governmentData(catchGovernment);
        for(CatchGovernment catchGovernment1 : list){
            if("1".equals(catchGovernment1.getUnit())){
                catchGovernment1.setUnit("%");
            }
        }
        return  list;
    }
    //重拳治污染-全年空气优良天数
    @ResponseBody
    @ApiOperation(value = "重拳治污染-全年空气优良天数", httpMethod = "GET", notes = "")
    @RequestMapping(value = "pollutionToday")
    public List<CatchPollutionTreatment> pollutionToday(CatchPollutionTreatment catchPollutionTreatment){
        catchPollutionTreatment.setNyear(nyear);
        List<CatchPollutionTreatment> list = catchPollutionTreatmentService.pollutionTodayData(catchPollutionTreatment);
        return list;
    }
    //重拳治污染-空气质量
    @ResponseBody
    @ApiOperation(value = "重拳治污染-空气质量", httpMethod = "GET", notes = "")
    @RequestMapping(value = "aqiparam")
    public List<CatchAqiparam> aqiparam(){
        List<CatchAqiparam> list = catchAqiparamService.aqiparamData();
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getAreaName().equals("铜川市")){
                CatchAqiparam catchAqiparam = list.remove(i);
                list.add(0,catchAqiparam);
            }
        }
        return list;
    }
    //脱贫人口,移民脱贫
    @ResponseBody
    @ApiOperation(value = "脱贫人口,移民脱贫", httpMethod = "GET", notes = "")
    @RequestMapping(value = "population")
    public void population(CatchShakePoverty catchShakePoverty,HttpServletResponse response){
        catchShakePoverty.setNyear(nyear);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        List<HashMap> list = catchShakePovertyService.populationData(catchShakePoverty);
        if(list != null&& list.size()>0){
            net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(list.get(0));
            jsonObject1.put("name","脱贫人口占比");
            String zhan = json.get("zhanbi").toString();
            DecimalFormat df = new DecimalFormat("#.00");
            String zhanbi = df.format(Double.parseDouble(zhan));
            jsonObject1.put("zhanbi",zhanbi);
            jsonObject2.put("name","脱贫人口");
            jsonObject2.put("shuju",json.get("outPoverty").toString());
            jsonObject3.put("name","移民脱贫");
            jsonObject3.put("shuju",json.get("poorHouseholds").toString());
        }
        jsonArray.put(jsonObject2);
        jsonArray.put(jsonObject3);
        jsonObject.put("ratio",jsonObject1);
        jsonObject.put("Poverty ",jsonArray);
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
    //脱贫人口,柱状图
    @ResponseBody
    @ApiOperation(value = "脱贫人口,柱状图", httpMethod = "GET", notes = "")
    @RequestMapping(value = "histogram")
    public void povertyHistogram(CatchShakePoverty catchShakePoverty,HttpServletResponse response){
        catchShakePoverty.setNyear(nyear);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        List<CatchShakePoverty> list = catchShakePovertyService.histogramData(catchShakePoverty);
        if(list != null && list.size()>0){
            for(CatchShakePoverty catchShakePoverty1 : list){
                jsonArray1.put(catchShakePoverty1.getAreaName());
                jsonArray2.put(catchShakePoverty1.getOutPoverty());
                jsonArray3.put(catchShakePoverty1.getPoorHouseholds());
                jsonObject.put("name",jsonArray1);
                jsonObject.put("population",jsonArray2);
                jsonObject.put("households",jsonArray3);
                //jsonArray.put(jsonObject);
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
        writer.print(jsonObject.toString());

    }
    //游客分析
    @ResponseBody
    @ApiOperation(value = "游客分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "provinces")
    public void provinces(CatchProvincesStatistical catchProvincesStatistical,HttpServletResponse response){
        catchProvincesStatistical.setNyear(nyear);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();
       CatchProvincesStatistical list = catchProvincesStatisticalService.provincesData(catchProvincesStatistical);
        if(list != null){
                jsonObject1.put("name",nyear+"年累计接待旅游数量");
                jsonObject1.put("num",list.getTouristsSum());
                jsonObject2.put("name","外省游客");
                jsonObject2.put("num",list.getOtherProvinces());
                jsonObject3.put("name","本省游客");
                jsonObject3.put("num",list.getThisProvinces());
                jsonObject4.put("name","外国游客");
                jsonObject4.put("num",list.getForeigns());
        }
        jsonArray.put(jsonObject2);
        jsonArray.put(jsonObject3);
        jsonArray.put(jsonObject4);
        jsonObject.put("total",jsonObject1);
        jsonObject.put("fenxi",jsonArray);
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
    //特色旅游表格
    @ResponseBody
    @ApiOperation(value = "特色旅游表格", httpMethod = "GET", notes = "")
    @RequestMapping(value = "scenicSpot")
    public void scenicSpot(CatchSceniArea catchSceniArea,HttpServletResponse response){
        catchSceniArea.setNyear(nyear);
        JSONArray jsonArray = new JSONArray();
        List<CatchSceniArea> list = catchSceniAreaService.scenicSpotData(catchSceniArea);
        if(list != null&& list.size()>0){
            for(CatchSceniArea catchSceniArea1 : list){
                JSONObject  jsonObject= new JSONObject();
                jsonObject.put("name",catchSceniArea1.getScenicArea());
                jsonObject.put("total",catchSceniArea1.getTotalRevenue());
                jsonObject.put("growth",catchSceniArea1.getGrowth());
                jsonObject.put("reception",catchSceniArea1.getReception());
                jsonArray.put(jsonObject);
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
    //经济指标管理
    @ResponseBody
    @ApiOperation(value = "经济指标管理", httpMethod = "GET", notes = "indicators:指标值")
    @RequestMapping(value = "catchEconomicRateData",method = RequestMethod.GET)
    public  Map<String, List<Map<String, Object>>>  catchEconomicRateData(CatchEconomicRate catchEconomicRate, HttpServletResponse response){
        catchEconomicRate.setNyear(nyear);
        List<CatchEconomicRate> catchEconomicRateList = catchEconomicRateService.economicRateList(catchEconomicRate);
        List<Map<String,Object>> data = new ArrayList();
        Map<String ,Object> mp = null;
        Map<String, List<Map<String, Object>>> m = new HashMap<String, List<Map<String,Object>>>();
        if(catchEconomicRateList != null && catchEconomicRateList.size()>0){
            for(CatchEconomicRate rate : catchEconomicRateList){
                mp = new HashMap<String, Object>();
                mp.put("area_name",rate.getAreaName());
                mp.put("index_name",rate.getIndexName());
                mp.put("indicators",rate.getIndicators());
                mp.put("unit",rate.getIndicatorsUnit());
                data.add(mp);
            }

            for(Map<String,Object> item : data){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                m.put(item.get("index_name").toString(),list);
            }
            for(Map<String,Object> item : data){
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                Map<String, Object> map1= new HashMap<String , Object>();
                map1.put("area_name", item.get("area_name"));
                map1.put("indicators", item.get("indicators"));
                map1.put("unit",item.get("unit"));
                list1.add(map1);
                m.get(item.get("index_name")).add(map1);
            }
        }
        return m;
    }
    //空气质量分析
    @ResponseBody
    @ApiOperation(value = "空气质量分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchAirQualityList")
    public void catchAirQualityList(CatchAirQuality catchAirQuality, HttpServletResponse response) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
    	catchAirQuality.getPage().setOrderBy("date");
        List<CatchAirQuality> list = catchAirQualityService.findList(catchAirQuality);
        if(list != null && list.size()>0){
            for(CatchAirQuality airQuality :list){

                jsonArray1.put(airQuality.getDate());
                jsonArray2.put(airQuality.getNumber());
                jsonObject.put("date",jsonArray1);
                jsonObject.put("number",jsonArray2);
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
        writer.print(jsonObject.toString());
    }
    //水质监测数据分析  
    @ResponseBody
    @ApiOperation(value = "水质监测数据分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchSwageTreatList")
    public Map<String, List<Map<String, Object>>> catchSwageTreatList(CatchSwageTreat catchSwageTreat, HttpServletResponse response) throws ParseException {
        List<CatchSwageTreat> list = catchSwageTreatService.findList(catchSwageTreat);
        Map<String, List<Map<String, Object>>> map = new HashMap<String, List<Map<String,Object>>>();
        if(list != null && list.size()>0){
            for ( CatchSwageTreat st : list) {
                map.put(st.getNyear(), new ArrayList<Map<String,Object>>());
            }
            for ( CatchSwageTreat st : list) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                Map<String, Object> map1= new HashMap<String , Object>();
                map1.put("month", st.getMonth());
                map1.put("rank", st.getRank());
                map1.put("value", st.getSwageValue());
                list1.add(map1);
                map.get(st.getNyear()).add(map1);
            }
        }
        System.out.println(map.toString());
        return map;
    }
    
    //污水排放量趋势分析
    @ResponseBody
    @ApiOperation(value = "污水排放量趋势分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchWasteWaterList")
    public void catchWasteWaterList(CatchWasteWater catchWasteWater, HttpServletResponse response) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        JSONArray jsonArray4 = new JSONArray();
        JSONArray jsonArray5 = new JSONArray();
        catchWasteWater.getPage().setOrderBy("nyear"); 
        List<CatchWasteWater> list = catchWasteWaterService.findList(catchWasteWater);
        if(list != null && list.size()>0){
            for(CatchWasteWater cwt :list){

                jsonArray1.put(cwt.getNyear());
                jsonArray2.put(cwt.getIndustryValue());
                jsonArray3.put(cwt.getCityValue());
                jsonArray4.put(cwt.getFarmValue());
                jsonArray5.put(cwt.getAmmoniaValue());
                jsonObject.put("date",jsonArray1);
                jsonObject.put("industry",jsonArray2);
                jsonObject.put("city",jsonArray3);
                jsonObject.put("farm",jsonArray4);
                jsonObject.put("ammon",jsonArray5);
            }
        }
        System.out.println(jsonObject.toString());
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
    //污水处置能力趋势分析
    @ResponseBody
    @ApiOperation(value = "污水处置能力趋势分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchWaterTradeList")
    public void catchWaterTradeList(CatchWaterTrade catchWaterTrade, HttpServletResponse response) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        catchWaterTrade.getPage().setOrderBy("nyear"); 
        List<CatchWaterTrade> list = catchWaterTradeService.findList(catchWaterTrade);
        if(list != null && list.size()>0){
            for(CatchWaterTrade cwt : list){
                jsonArray1.put(cwt.getNyear());
                jsonArray2.put(cwt.getSpeedMax());
                jsonArray3.put(cwt.getProcessMax());
  
                jsonObject.put("date",jsonArray1);
                jsonObject.put("speedmax",jsonArray2);
                jsonObject.put("processmax",jsonArray3);
            }
        }
        System.out.println(jsonObject.toString());
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
  //废气排放指标来源构成分析
    @ResponseBody
    @ApiOperation(value = "废气排放指标来源构成分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchGasResourceList")
    public void catchGasResourceList(CatchGasResource catchGasResource, HttpServletResponse response) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        JSONArray jsonArray3 = new JSONArray();
        JSONArray jsonArray4 = new JSONArray();
        List<CatchGasResource> list = catchGasResourceService.findList(catchGasResource);
        if(list != null && list.size()>0){
            for(CatchGasResource cwt : list){
                jsonArray1.put(cwt.getType());
                jsonArray2.put(cwt.getOxynitrideValue());
                jsonArray3.put(cwt.getCoValue());
                jsonArray4.put(cwt.getPowderValue());
  
                jsonObject.put("type",jsonArray1);
                jsonObject.put("oxy",jsonArray2);//氮氧化物
                jsonObject.put("co",jsonArray3);//二氧化碳
                jsonObject.put("powder",jsonArray4);//烟粉尘
            }
        }
        System.out.println(jsonObject.toString());
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
    
    //重点项目建设
    @RequestMapping(value = "catchProjectConstructionList",method = RequestMethod.GET)
    @ResponseBody
    public List<CatchProjectConstruction> catchProjectConstructionList(){
        return catchProjectConstructionService.findList(new CatchProjectConstruction());
    }
    
	//存在问题处置率
    @RequestMapping(value = "catchProblemDisposalList",method = RequestMethod.GET)
    @ResponseBody
    public List<CatchProblemDisposal> catchProblemDisposalList(@RequestBody(required = false) CatchProblemDisposal catchProblemDisposal){
        return catchProblemDisposalService.findList(catchProblemDisposal);
    }
    //项目投资进度排行榜
    @RequestMapping(value = "catchInvestmentProgressList",method = RequestMethod.GET)
    @ResponseBody
    public List<CatchInvestmentProgress> catchInvestmentProgressList(@RequestBody(required = false) CatchInvestmentProgress catchSuperviseDynamic){
        return catchInvestmentProgressService.findList(catchSuperviseDynamic);
    }
    //24小时包抓监督实时动态
    @RequestMapping(value = "catchSuperviseDynamicList",method = RequestMethod.GET)
    @ResponseBody
    public List<CatchSuperviseDynamic> catchSuperviseDynamicList(@RequestBody(required = false) CatchSuperviseDynamic catchSuperviseDynamic){
        return superviseDynamicService.findAll(catchSuperviseDynamic);
    }


    @RequestMapping(value = "findEchartData",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findEchartData(){
        return catchProblemSolvingService.findEchartData();
    }
    
    //区域经济增长率
    @RequestMapping(value = "catchEconomicGrowthData",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> catchEconomicGrowthData(CatchEconomicGrowth catchEconomicGrowth, HttpServletResponse response){
    	catchEconomicGrowth.setNyear(nyear);
    	List<CatchEconomicGrowth> dataList = catchEconomicGrowthService.findList(catchEconomicGrowth);
    	 List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
         Map<String ,Object> mp = null;//区域经济指标详情
         Map<String, List<Map<String, Object>>> m = new HashMap<String, List<Map<String,Object>>>();        
         List< Map<String,Object>> listAll = new ArrayList<Map<String,Object>>();
         if(dataList != null && dataList.size()>0){
             for(CatchEconomicGrowth ca : dataList){
                 mp = new HashMap<String, Object>();
                 mp.put("area_name",ca.getAreaName());
                 mp.put("index_name",ca.getIndexName());
                 mp.put("indicators",ca.getIndicators());
                 if("3".equals(ca.getIndicatorsUnit())){
                	 mp.put("unit", "亿元");
                 }else if("2".equals(ca.getIndicatorsUnit())){
                	 mp.put("unit", "万元");
                 }else{
                	 mp.put("unit", "元");
                 }
                
                 mp.put("tb", ca.getRate());
                 data.add(mp);
             }

             for(Map<String,Object> item : data){
                 List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                 m.put(item.get("area_name").toString(),list);
             }
             
            // List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
             for(Map<String,Object> item : data){
                 List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                 Map<String, Object> map1= new HashMap<String , Object>();
                 map1.put("name", item.get("index_name"));
                 map1.put("numbar", item.get("indicators"));
                 map1.put("unit",item.get("unit"));
                 map1.put("tb", item.get("tb"));
                 map1.put("date", nyear + "年" + DateUtils.getMonth()+"月");
                 list1.add(map1);
                 m.get(item.get("area_name")).add(map1);
             }
                 
             for(String key: m.keySet()){
                 System.out.println("key : "+key+" value : "+m.get(key));
                 Map<String,Object> maps   = new HashMap<String,Object>();
                 maps.put("name",key);
                 maps.put("data",m.get(key));
                 listAll.add(maps);
             }
         }
         return listAll;
    }
    
  //地图区域统计显示
    @RequestMapping(value = "catchEconomicAreaData",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, List<List<Double>>>  catchEconomicAreaData(CatchEconomicGrowth catchEconomicGrowth, HttpServletResponse response){
    	catchEconomicGrowth.setNyear(nyear);
    	List<CatchEconomicGrowth> dataList = catchEconomicGrowthService.findList(catchEconomicGrowth);
    	 List<Map<String,Object>> data = new ArrayList<Map<String, Object>>();
         Map<String ,Object> mp = null;//区域经济指标详情
         Map<String, List<List<Double>>> m = new HashMap<String, List<List<Double>>>();        
         if(dataList != null && dataList.size()>0){
             for(CatchEconomicGrowth ca : dataList){
                 mp = new HashMap<String, Object>();
                 mp.put("name_en",ca.getNameEn());
                 mp.put("lat",ca.getLat());
                 mp.put("indicators",ca.getIndicators());             
                 mp.put("lon", ca.getLon());
                 data.add(mp);
             }

             for(Map<String,Object> item : data){
                 List<List<Double>> list = new ArrayList<List<Double>>();
                 m.put(item.get("name_en").toString(),list);
             }
             
             for(Map<String,Object> item : data){  
            	 List<Double> list = new ArrayList<Double>();
            	 list.add((Double)item.get("lon"));
            	 list.add((Double)item.get("lat"));
            	 list.add(Double.parseDouble(item.get("indicators").toString()));
                 m.get(item.get("name_en")).add(list);
             }
         }
         return m;
    }
} 
