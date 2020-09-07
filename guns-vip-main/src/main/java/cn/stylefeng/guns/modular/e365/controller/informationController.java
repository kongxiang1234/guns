package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.modular.e365.entity.*;
import cn.stylefeng.guns.modular.e365.model.params.ContentParam;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.model.result.BreakResult;
import cn.stylefeng.guns.modular.e365.model.result.ContentResult;
import cn.stylefeng.guns.modular.e365.model.result.IntegralLogResuLt;
import cn.stylefeng.guns.modular.e365.service.BreakService;
import cn.stylefeng.guns.modular.e365.service.IntegralService;
import cn.stylefeng.guns.modular.e365.service.MonthlyPointsService;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.DeptService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 控制器 情况通报
 *
 * @author Alan_孔
 * @Date 2020-04-22 18:50:50
 */
@Slf4j
@Controller
@RequestMapping("/information")
public class informationController extends BaseController {

    private String PREFIX = "/tongbao";
    @Autowired
    private MonthlyPointsService monthlyPointsService;
    @Autowired
    private BreakService breakService;
    @Autowired
    private IntegralService integralService;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 跳转到违章通报
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/information")
    public String index(Model model, HttpServletRequest request,HttpSession session) {
        QueryWrapper<Break> queryWrapper=new QueryWrapper<Break>();
        queryWrapper.orderByDesc("create_time");
        List<Break> list=breakService.list(queryWrapper);
        model.addAttribute("list",list);
        return PREFIX + "/weizhang.html";
    }

    /**
     * 跳转到违章通报详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/informationDetailed/{id}")
    public String jingaoDetailed(@PathVariable("id") String id, Model model) {
        Break content=breakService.getById(id);
        BreakResult breakResult=new BreakResult();
        breakResult.setCreateTime(sdf.format(content.getCreateTime()));
        breakResult.setContent(content.getContent());
        breakResult.setWzPeople(content.getWzPeople());
        breakResult.setCreateUser(content.getCreateUser());
        breakResult.setXmUnit(content.getXmUnit());
        breakResult.setDcTime(sdf.format(content.getDcTime()));
        model.addAttribute("content",breakResult);
        return PREFIX + "/weizhangDeailt.html";
    }




    /**
     * 跳转到个人违章计分查询
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/scoresQuery")
    public String scoresQuery(Model model, HttpServletRequest request,HttpSession session) {
        String year=request.getParameter("year");
        if(year==null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date()); //获取结束时间参数
            year = String.valueOf(cal.get(Calendar.YEAR));
        }
        User user=(User)session.getAttribute("user");
        List<Integral> integ = integralService.list(new QueryWrapper<Integral>().eq("userId", user.getUserId()).eq("date_format(create_time,'%Y')",year));
        QueryWrapper<Break> queryWrapper=new QueryWrapper();
        queryWrapper.eq("wz_people",user.getName());
        queryWrapper.eq("date_format(dc_time,'%Y')",year);
        int total=0;
        List<Break> list = breakService.list(queryWrapper);
        for (Break breaks:list){
            breaks.setCreateUser(sdf.format(breaks.getDcTime()));
            total=total+breaks.getScore();
        }
        List<IntegralLogResuLt> logResuLts = new ArrayList<>();
        List<IntegralLog> listIntegralLog = integralService.listIntegralLog(user.getUserId(),year);
        for (IntegralLog breaks:listIntegralLog){
            IntegralLogResuLt integralLogResuLt=new IntegralLogResuLt();
            integralLogResuLt.setDate(sdf.format(breaks.getDate()));
            integralLogResuLt.setName(breaks.getName());
            integralLogResuLt.setScore(breaks.getScore());
            logResuLts.add(integralLogResuLt);
        }
        int interto=0;
        int intertotal=0;
        if(integ.size()!=0){
            for (Integral integral:integ){
                interto=interto+integral.getDkwzIntegral();
                intertotal=intertotal+integral.getMrIntegral()+integral.getMzIntegral()+integral.getMyIntegral();
            }
            model.addAttribute("dkwzIntegral",interto);
            model.addAttribute("intertotal",intertotal);
        }else {
            model.addAttribute("dkwzIntegral",0);
            model.addAttribute("intertotal",0);
        }


        model.addAttribute("list",list);
        model.addAttribute("year",year);
        model.addAttribute("total",total);
        model.addAttribute("user",user);
        model.addAttribute("logResuLts",logResuLts);
        return PREFIX + "/weizhangjifen.html";
    }


    /**
     * 跳转到安全测试排名情况
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/safetyTest")
    public String safetyTest(Model model, HttpServletRequest request,HttpSession session) throws ParseException {
        String time=request.getParameter("time");
        List<MonthlyPoints> monthlyPoints=new ArrayList<>();
        if(StringUtils.isBlank(time)){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date()); //获取结束时间参数
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            QueryWrapper<MonthlyPoints> queryWrapper=new QueryWrapper();
            queryWrapper.eq("dateYear",year);
            queryWrapper.eq("month",month);
            queryWrapper.orderByDesc("ranking");
            monthlyPoints=monthlyPointsService.list(queryWrapper);
        }else {
            Date d1 = new SimpleDateFormat("yyyy-M").parse(time);//定义起始日期
            SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("M");
            String str1 = sdf0.format(d1);
            String str2 = sdf1.format(d1);
            QueryWrapper<MonthlyPoints> queryWrapper=new QueryWrapper();
            queryWrapper.eq("dateYear",str1);
            queryWrapper.eq("month",str2);
            queryWrapper.orderByDesc("ranking");
            monthlyPoints=monthlyPointsService.list(queryWrapper);
        }
        model.addAttribute("monthlyPoints",monthlyPoints);
        return PREFIX + "/safetyTest.html";

    }


    /**
     * 跳转到个人违章积分抵扣
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/creditDeduction")
    public String creditDeduction(Model model, HttpServletRequest request,HttpSession session) {
        String year=request.getParameter("year");
        if(year==null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date()); //获取结束时间参数
            year = String.valueOf(cal.get(Calendar.YEAR));
        }
        User user=(User)session.getAttribute("user");
        Integral integ = integralService.getOne(new QueryWrapper<Integral>().eq("userId", user.getUserId()));
        QueryWrapper<Break> queryWrapper=new QueryWrapper();
        queryWrapper.eq("wz_people",user.getName());
        queryWrapper.eq("date_format(dc_time,'%Y')",year);
        queryWrapper.isNull("deduction");
        int total=0;
         List<Break> list = breakService.list(queryWrapper);
         for (Break breaks:list){
             breaks.setCreateUser(sdf.format(breaks.getDcTime()));
             total=total+breaks.getScore();
         }
        if(integ!=null){
            model.addAttribute("dkwzIntegral",integ.getDkwzIntegral());
        }else {
            model.addAttribute("dkwzIntegral",0);
        }

        model.addAttribute("list",list);
        model.addAttribute("year",year);
        model.addAttribute("total",total);
        model.addAttribute("user",user);
        return PREFIX + "/creditDeduction.html";
    }

    /**
     * 抵扣积分
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/updateDeduction")
    @ResponseBody
    public ResponseData updateDeduction(Model model,HttpSession session) {
        User user=(User)session.getAttribute("user");
        String array = getPara("array");
        String dkwzIntegral = getPara("dkwzIntegral");
        String[] str=array.split(",");
        int num=0;
        for (String string:str){
           Break br= breakService.getById(string);
           num=num+br.getScore();
        }
        if(Integer.parseInt(dkwzIntegral)<num){
            return ResponseData.error("选择的抵扣违章大于有奖积分了，请重新选择");
        }
        for (String string:str){
            Break br= breakService.getById(string);
            br.setDeduction(br.getScore());
            breakService.updateById(br);
        }
        Integral integ = integralService.getOne(new QueryWrapper<Integral>().eq("userId", user.getUserId()));
        if(integ.getMrIntegral()!=0){
            integ.setMrIntegral(integ.getMrIntegral()-num);
        }else if(integ.getMzIntegral()!=0){
            integ.setMzIntegral(integ.getMzIntegral()-num);
        }else if(integ.getMyIntegral()!=0){
            integ.setMyIntegral(integ.getMyIntegral()-num);
        }
        integ.setDkwzIntegral(integ.getDkwzIntegral()-num);
        integ.setSdkwzIntegral(num);
        integralService.updateById(integ);
        return ResponseData.success("已抵扣");
    }
}


