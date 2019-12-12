package cn.xy.tcmap.modules.catchup.screen.zhcs;

import cn.xy.tcmap.common.web.BaseController;
import cn.xy.tcmap.modules.catchup.zhcs.entity.*;
import cn.xy.tcmap.modules.catchup.zhcs.service.*;
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
import java.util.*;


@Controller
@Api(tags = "f/csyx/screenzhcs", description = "智慧城市资产一张图相关接口")
@RequestMapping(value = "${frontPath}/zhcs/screenzhcs")
public class ScreenZhcsController extends BaseController {

    @Autowired
    private CatchMedicalAssetsService catchMedicalAssetsService;


    @Autowired
    private CatchAssetDetailsService catchAssetDetailsService;

    @Autowired
    private CatchPatentCountService catchPatentCountService;

    @Autowired
    private CatchHingeStatisticsService catchHingeStatisticsService;

    @Autowired
    private CatchResourcesCountService catchResourcesCountService;

    @Autowired
    private CatchScenicSpotService catchScenicSpotService;

    @Autowired
    private CatchRoadCountService catchRoadCountService;

    @Autowired
    private CatchEducationAssetsService catchEducationAssetsService;


    @Autowired
    private CatchTravelInfoService catchTravelInfoService;

    @Autowired
    private CatchHotelResInfoService  catchHotelResInfoService;

    @Autowired
    private CatchAreaRankingService  catchAreaRankingService;

    @Autowired
    private CatchTourCountAnalysisService  catchTourCountAnalysisService;

    @Autowired
    private CatchStayTimeService  catchStayTimeService;
    
    @Autowired 
    private CatchTouristViewService catchTouristViewService;
    @Autowired
    private CatchAreaPositionService catchAreaPositionService;
    /**
     *人力资源
     * @param catchResourcesCount
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "人力资源", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchResourcesCount")
    public void catchResourcesCount (CatchResourcesCount catchResourcesCount, HttpServletResponse response){
        catchResourcesCount.setNyear(nyear);
        JSONObject jsonObject = new JSONObject();
        JSONArray typeAry = new JSONArray();
        JSONArray maleAry = new JSONArray();
        JSONArray femaleAry = new JSONArray();
        List<CatchResourcesCount> list = catchResourcesCountService.findList(catchResourcesCount);
        if(list != null && list.size()>0){
            for(CatchResourcesCount catchResourcesCount1 : list){
                typeAry.put(catchResourcesCount1.getPersonnelType());
                maleAry.put(catchResourcesCount1.getMale());
                femaleAry.put(catchResourcesCount1.getFemale());
            }
        }
        jsonObject.put("typeAry",typeAry);
        jsonObject.put("maleAry",maleAry);
        jsonObject.put("femaleAry",femaleAry);

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
     *旅游资产
     * @param catchScenicSpot
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "旅游资产", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchScenicSpot")
    public void catchScenicSpot (CatchScenicSpot catchScenicSpot, HttpServletResponse response){
        catchScenicSpot.setNyear(nyear);
        JSONObject jsonObject = new JSONObject();
        JSONArray nameAry = new JSONArray();
        JSONArray totalAry = new JSONArray();

        List<CatchScenicSpot> list = catchScenicSpotService.findList(catchScenicSpot);
        if(list != null && list.size()>0){
            for(CatchScenicSpot catchScenicSpot1 : list){
                nameAry.put(catchScenicSpot1.getSpotName());
            }
        }
        List<HashMap> totalList = catchScenicSpotService.totalData(catchScenicSpot);
        if(totalList != null && totalList.size()>0){
            for(HashMap map : totalList){
                JSONObject json = new JSONObject();
                json.put("typeName",map.get("typeName"));
                json.put("total",map.get("total"));
                totalAry.put(json);
            }
        }
        jsonObject.put("nameAry",nameAry);
        jsonObject.put("totalAry",totalAry);

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
     *旅游资产
     * @param catchTouristView
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "旅游资产", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchTouristView")
    public void catchTouristView (CatchTouristView catchTouristView, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        JSONArray cityAry = new JSONArray();
        JSONArray spotAry = new JSONArray();
        JSONArray totalAry = new JSONArray();
        List<CatchAreaPosition> areaList = catchAreaPositionService.findList(new CatchAreaPosition());
        List<CatchTouristView> list = catchTouristViewService.findList(catchTouristView);
        if(list != null && list.size()>0){
            for(CatchTouristView catchTouristView1 : list){
                JSONObject json1 = new JSONObject();
                json1.put("name",catchTouristView1.getName());
                JSONArray geoAry = new JSONArray();
                geoAry.put(catchTouristView1.getLon());
                geoAry.put(catchTouristView1.getLat());
                json1.put("value",geoAry);
                spotAry.put(json1);
            }
        }
        if(areaList !=null && areaList.size() > 0){
        	for (CatchAreaPosition cp : areaList) {
        		  JSONObject json1 = new JSONObject();
                  json1.put("name",cp.getAreaName());
                  JSONArray geoAry = new JSONArray();
                  geoAry.put(cp.getLon());
                  geoAry.put(cp.getLat());
                  json1.put("value",geoAry);
                  cityAry.put(json1);
			}
        }
        String rankTotal;
        rankTotal = catchTouristViewService.totalData("2");
        totalAry.put(rankTotal);
        rankTotal = catchTouristViewService.totalData("1");
        totalAry.put(rankTotal);
        rankTotal = catchTouristViewService.totalData("0");
        totalAry.put(rankTotal);

        jsonObject.put("CITY",cityAry);
        jsonObject.put("JD",spotAry);
        jsonObject.put("STAR",totalAry);

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
     *交通资产(公路)
     * @param catchRoadCount
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "交通资产(公路)", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchRoadCount")
    public void catchRoadCount (CatchRoadCount catchRoadCount, HttpServletResponse response){
        catchRoadCount.setNyear(nyear);
        JSONObject jsonObject = new JSONObject();
        JSONArray typeNameAry = new JSONArray();
        JSONArray roadMileageAry = new JSONArray();
        List<CatchRoadCount> list = catchRoadCountService.findList(catchRoadCount);
        if(list != null && list.size()>0){
            for(CatchRoadCount catchRoadCount1 : list){
                typeNameAry.put(catchRoadCount1.getRoadType());
                roadMileageAry.put(catchRoadCount1.getRoadMileage());
            }
        }
        jsonObject.put("typeNameAry",typeNameAry);
        jsonObject.put("roadMileageAry",roadMileageAry);

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
     *交通资产(交通枢纽)
     * @param catchHingeStatistics
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "交通资产(交通枢纽)", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchHingeStatistics")
    public void catchHingeStatistics (CatchHingeStatistics catchHingeStatistics, HttpServletResponse response){
        catchHingeStatistics.setNyear(nyear);
        JSONArray array = new JSONArray();
        List<CatchHingeStatistics> list = catchHingeStatisticsService.findList(catchHingeStatistics);
        if(list != null && list.size()>0){
            for(CatchHingeStatistics catchHingeStatistics1 : list){
                JSONObject json = new JSONObject();
                json.put("hingeType",catchHingeStatistics1.getHingeType());
                json.put("hingeNumber",catchHingeStatistics1.getHingeNumber());
                array.put(json);
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
        writer.print(array.toString());

    }


    /**
     *专利数量统计
     * @param catchPatentCount
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "利数量统计", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchPatentCount")
    public void catchPatentCount (CatchPatentCount catchPatentCount, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        JSONArray yearAry = new JSONArray();
        JSONArray totalAry = new JSONArray();
        List<CatchPatentCount> list = catchPatentCountService.findList(catchPatentCount);
        if(list != null && list.size()>0){
            if(list.size()>5){
                list = list.subList(0,5);
            }
            for(CatchPatentCount catchPatentCount1 : list){
                yearAry.put(catchPatentCount1.getNyear());
                totalAry.put(catchPatentCount1.getPatentNumber());
            }
        }
        jsonObject.put("yearAry",yearAry);
        jsonObject.put("totalAry",totalAry);
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
     *资源详情(非物质文化遗产)1
     * @param catchAssetDetails
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "资源详情(非物质文化遗产)", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchAssetDetailsCulture")
    public void catchAssetDetailsCulture (CatchAssetDetails catchAssetDetails, HttpServletResponse response){
        catchAssetDetails.setNyear(nyear);
        catchAssetDetails.setType("1");
        JSONArray array = new JSONArray();
        List<CatchAssetDetails> list = catchAssetDetailsService.findList(catchAssetDetails);
        if(list != null && list.size()>0){
//            if(list.size()>2){
//                list = list.subList(0,2);
//            }
            for(CatchAssetDetails catchAssetDetails1 : list){
                JSONObject json = new JSONObject();
                json.put("imagePath",catchAssetDetails1.getImagePath());
                json.put("cont",catchAssetDetails1.getCont());
                array.put(json);
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
        writer.print(array.toString());

    }

    /**
     *资源详情(活动场所)
     * @param catchAssetDetails
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "资源详情(活动场所)", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchAssetDetailsActivity")
    public void catchAssetDetailsActivity (CatchAssetDetails catchAssetDetails, HttpServletResponse response){
        catchAssetDetails.setNyear(nyear);
        catchAssetDetails.setType("2");
        JSONArray array = new JSONArray();
        List<CatchAssetDetails> list = catchAssetDetailsService.findList(catchAssetDetails);
        if(list != null && list.size()>0){
            if(list.size()>3){
                list = list.subList(0,3);
            }
            for(CatchAssetDetails catchAssetDetails1 : list){
                JSONObject json = new JSONObject();
                json.put("imagePath",catchAssetDetails1.getImagePath());
                json.put("cont",catchAssetDetails1.getCont());
                array.put(json);
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
        writer.print(array.toString());

    }

    /**
     *医疗资源
     * @param catchMedicalAssets
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "医疗资源", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchMedicalAssets")
    public void catchMedicalAssets (CatchMedicalAssets catchMedicalAssets, HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        JSONArray hospitalGradeAry = new JSONArray();
        JSONArray doctorsNumberAry = new JSONArray();
        List<CatchMedicalAssets> list = catchMedicalAssetsService.findList(catchMedicalAssets);
        Integer doctorsNumber = 0;
        Integer nurseNumber = 0;
        if(list != null && list.size()>0){
            for(CatchMedicalAssets catchMedicalAssets1 : list){
                hospitalGradeAry.put(catchMedicalAssets1.getHospitalGrade());
                doctorsNumberAry.put(catchMedicalAssets1.getHospitalNumber());
                doctorsNumber += catchMedicalAssets1.getDoctorsNumber();
                nurseNumber += catchMedicalAssets1.getNurseNumber();
            }
        }
        jsonObject.put("hospitalGradeAry",hospitalGradeAry);
        jsonObject.put("doctorsNumberAry",doctorsNumberAry);
        jsonObject.put("doctorsNumber",doctorsNumber);
        jsonObject.put("nurseNumber",nurseNumber);
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
     *教育资产
     * @param catchEducationAssets
     * @param response
     */
    @ResponseBody
    @ApiOperation(value = "教育资产", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchEducationAssets")
    public void catchEducationAssets (CatchEducationAssets catchEducationAssets, HttpServletResponse response){
        catchEducationAssets.setNyear(nyear);
        JSONObject jsonObject = new JSONObject();
        JSONArray educationTypeAry = new JSONArray();
        JSONArray schoolNumberAry = new JSONArray();
        JSONArray teachingStaffAry = new JSONArray();
        List<CatchEducationAssets> list = catchEducationAssetsService.findList(catchEducationAssets);
        if(list != null && list.size()>0){
            for(CatchEducationAssets catchEducationAssets1 : list){
                educationTypeAry.put(catchEducationAssets1.getEducationType());
                schoolNumberAry.put(catchEducationAssets1.getSchoolNumber());
                teachingStaffAry.put(catchEducationAssets1.getTeachingStaff());
            }
        }
        jsonObject.put("educationTypeAry",educationTypeAry);
        jsonObject.put("schoolNumberAry",schoolNumberAry);
        jsonObject.put("teachingStaffAry",teachingStaffAry);

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
    @ApiOperation(value = "旅游相关信息", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchTravelInfo")
    public Map<String , Map > catchTravelInfo (CatchTravelInfo catchTravelInfo, HttpServletResponse response){
        catchTravelInfo.setNyear(nyear);
        List<CatchTravelInfo> list = catchTravelInfoService.findList(catchTravelInfo);

        List<Dict> dicts = DictUtils.getDictList("travel_info_type");
        Map<String , Map > dataMap = new HashMap<String , Map >();
        for(Dict dict : dicts){
            dataMap.put(dict.getDescription() ,new HashMap<String, Map>());
        }

        for(CatchTravelInfo cn : list){
            Map<String , String> md = dataMap.get(cn.getTravelName());
            if(md==null){
                md = new HashMap<String, String>();
            }
            md.put(cn.getInfoType() , cn.getInfoValue());
            dataMap.put(cn.getTravelName() , md);

        }
        return dataMap;
    }


    @ResponseBody
    @ApiOperation(value = "酒店资源相关信息", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchHotelResInfo")
    public Map<String , List > catchHotelResInfo (CatchHotelResInfo catchHotelResInfo, HttpServletResponse response){

        catchHotelResInfo.setNyear(nyear);
        List<CatchHotelResInfo> list = catchHotelResInfoService.findList(catchHotelResInfo);

        List<Dict> dicts = DictUtils.getDictList("hotel_res_type");
        Map<String , List > dataMap = new HashMap<String , List >();
        for(Dict dict : dicts){
            dataMap.put(dict.getValue() ,new ArrayList<CatchHotelResInfo>());
        }
        for(CatchHotelResInfo cn : list){
            dataMap.get(cn.getHotelType()).add(cn);
        }
        return dataMap;
    }


    @ResponseBody
    @ApiOperation(value = "地区排名信息", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchAreaRanking")
    public Map<String , List > catchAreaRanking (CatchAreaRanking catchAreaRanking, HttpServletResponse response){
        catchAreaRanking.setNyear(nyear);
        List<CatchAreaRanking> list = catchAreaRankingService.findList(catchAreaRanking);

        List<Dict> dicts = DictUtils.getDictList("area_ranking_type");
        Map<String , List > dataMap = new HashMap<String , List >();
        for(Dict dict : dicts){
            dataMap.put(dict.getValue() ,new ArrayList<CatchHotelResInfo>());
        }
        for(CatchAreaRanking cn : list){
            dataMap.get(cn.getAreaType()).add(cn);
        }
        Collection<List> vals = dataMap.values();
        for(List l : vals){
            Collections.sort(l, new Comparator<CatchAreaRanking>() {
                @Override
                public int compare(CatchAreaRanking o1, CatchAreaRanking o2) {
                    return Integer.parseInt(o1.getAreaSort()) - Integer.parseInt(o2.getAreaSort());
                }
            });
        }

        return dataMap;
    }

    @ResponseBody
    @ApiOperation(value = "旅游趋势分析", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchTourCountAnalysis")
    public List<CatchTourCountAnalysis> catchTourCountAnalysis (CatchTourCountAnalysis catchTourCountAnalysis, HttpServletResponse response){
        catchTourCountAnalysis.setNyear(nyear);
        List<CatchTourCountAnalysis> list = catchTourCountAnalysisService.findList(catchTourCountAnalysis);
        return list;
    }

    @ResponseBody
    @ApiOperation(value = "停留时长分布", httpMethod = "GET", notes = "")
    @RequestMapping(value = "catchStayTime")
    public List<CatchStayTime> catchStayTime (CatchStayTime catchStayTime, HttpServletResponse response){
        catchStayTime.setNyear(nyear);
        List<CatchStayTime> list = catchStayTimeService.findList(catchStayTime);
        return list;
    }



}