package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.*;
import cn.stylefeng.guns.modular.e365.mapper.DailyPointsMapper;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.service.*;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.DeptService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;


/**
 * 控制器 统计
 *
 * @author Alan_孔
 * @Date 2020-04-22 18:50:50
 */
@Slf4j
@Controller
@RequestMapping("/practice")
public class PracticeTotalController extends BaseController {

    private String PREFIX = "/integral";
    @Autowired
    private MonthlyPointsService monthlyPointsService;
    @Autowired
    private WeeklyPointsService weeklyPointsService;
    @Autowired
    private DailyPointsService dailyPointsService;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat YearsdfValue = new SimpleDateFormat("yyyy-M");
    private static SimpleDateFormat yearsdfValue = new SimpleDateFormat("yyyy");
    private static SimpleDateFormat monthsdfValue = new SimpleDateFormat("MM");
    /**
     * 跳转到每日练习
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/totalMeiri")
    public String index() {
        return PREFIX + "/totalMeiri.html";
    }
    /**
     * 跳转到缺席的每日练习
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/queTotalMeiri")
    public String queTotalMeiri(Model model) {
        String account=getPara("account");
        String btime=getPara("btime");
        String etime=getPara("etime");
        model.addAttribute("account",account);
        model.addAttribute("btime",btime);
        model.addAttribute("etime",etime);
        return PREFIX + "/queTotalMeiri.html";
    }

    /**
     * 跳转到每周练习
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/totalMeiZhou")
    public String totalMeiZhou() {
        return PREFIX + "/totalMeiZhou.html";
    }
    /**
     * 跳转到缺席的每周练习
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/queTotalMeizhou")
    public String queTotalMeizhou(Model model) {
        String account=getPara("account");
        model.addAttribute("account",account);
        return PREFIX + "/queTotalMeiZhou.html";
    }
    /**
     * 跳转到每月练习
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/totalMeiYue")
    public String totalMeiYue() {
        return PREFIX + "/totalMeiYue.html";
    }


    public  List<String> findDates(String start, String end){
        List<String> timeList = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return timeList;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            timeList.add(f.toString());
        });
        return timeList;
    }

    /**
     * 查询每日统计列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @ResponseBody
    @RequestMapping("/listMeiri")
    @BussinessLog(value = "查询每日统计列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo listMeiri(@RequestParam(required = false) String name,@RequestParam(required = false) String btime,
                                   @RequestParam(required = false) String etime
                              ) {
        List<DailyPoints> dailyPoints= this.monthlyPointsService.listMeiri(name);
        List<Map> dailyPointsList=new ArrayList<>();
        List<String> list=new ArrayList<>();
        if(StringUtils.isBlank(btime) && StringUtils.isBlank(etime)){
            Calendar cale = null;
            String firstday, lastday;
            // 获取前月的第一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
            firstday = sdf.format(cale.getTime());
            // 获取前月的最后一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
            lastday = sdf.format(cale.getTime());
            list=findDates(firstday,lastday);

        }else {
            list=findDates(btime,etime);
        }
        for (DailyPoints dailyPoints1:dailyPoints){
            QueryWrapper<DailyPoints> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("name",dailyPoints1.getAccount());
            queryWrapper.in("date_format( date, '%Y-%m-%d' )",list);
            List<DailyPoints> daily=dailyPointsService.list(queryWrapper);
            Map map=new HashMap();
            map.put("depart",dailyPoints1.getUnit());
            map.put("account",dailyPoints1.getAccount());
            map.put("user",dailyPoints1.getName());
            map.put("ytotal",list.size());
            map.put("stotal",daily.size());
            map.put("qtotal",list.size()-daily.size());
            dailyPointsList.add(map);
        }
        LayuiPageInfo layuiPageInfo=new LayuiPageInfo();
        layuiPageInfo.setData(dailyPointsList);
        layuiPageInfo.setCount(dailyPointsList.size());
        return layuiPageInfo;
    }
    /**
     * 查询每日统计列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @ResponseBody
    @RequestMapping("/queListMeiri")
    @BussinessLog(value = "查询每日统计列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo queListMeiri(@RequestParam(required = false) String name,@RequestParam(required = false) String btime,
                                   @RequestParam(required = false) String etime
    ) {
        List<Map> dailyPointsList=new ArrayList<>();
        List<String> list=new ArrayList<>();
        List<String> lists=new ArrayList<>();
        list=findDates(btime,etime);
        QueryWrapper<DailyPoints> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.in("date_format( date, '%Y-%m-%d' )",list);
        List<DailyPoints> daily=dailyPointsService.list(queryWrapper);
        for (DailyPoints dailyPoints:daily){
            lists.add(sdf.format(dailyPoints.getDate()));
        }
        List<String> c = null;
        List<String> d = null;
        c  = new ArrayList(list);
        c.retainAll(lists); // 得到  a, b 的交集。
        d = new ArrayList(list);
        d.addAll(lists); // 合并 a, b 值到 d 中。
        d.removeAll(c);// 去掉交集 c 中的所有条目。留下只出现在a 或 b 中的条目。
        for (String s:d){
            Map map=new HashMap();
            map.put("date",s);
            dailyPointsList.add(map);
        }
        LayuiPageInfo layuiPageInfo=new LayuiPageInfo();
        layuiPageInfo.setData(dailyPointsList);
        layuiPageInfo.setCount(dailyPointsList.size());
        return layuiPageInfo;
    }
    /**
     * 查询每周统计列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @ResponseBody
    @RequestMapping("/listMeizhou")
    @BussinessLog(value = "查询每周统计列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo listMeizhou(@RequestParam(required = false) String name,@RequestParam(required = false) String btime,
                                     @RequestParam(required = false) String etime
    ) {
        List<WeeklyPoints> dailyPoints= this.monthlyPointsService.listMeizhou(name);
        List<Map> dailyPointsList=new ArrayList<>();
        List<String> list=new ArrayList<>();
        if(StringUtils.isBlank(btime) && StringUtils.isBlank(etime)){
            Calendar cale = null;
            String firstday, lastday;
            // 获取前月的第一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
            firstday = sdf.format(cale.getTime());
            // 获取前月的最后一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
            lastday = sdf.format(cale.getTime());
            list=findDates(firstday,lastday);
        }else {
            list=findDates(btime,etime);
        }
        for (WeeklyPoints dailyPoints1:dailyPoints){
            QueryWrapper<WeeklyPoints> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("name",dailyPoints1.getAccount());
            queryWrapper.in("date_format( date, '%Y-%m-%d' )",list);
            List<WeeklyPoints> daily=weeklyPointsService.list(queryWrapper);
            Map map=new HashMap();
            map.put("depart",dailyPoints1.getUnit());
            map.put("account",dailyPoints1.getAccount());
            map.put("user",dailyPoints1.getName());
            BigDecimal bigDecimal=new BigDecimal(list.size());
            BigDecimal bigDecimal1=new BigDecimal(7);
            int se=bigDecimal.divide(bigDecimal1,0,BigDecimal.ROUND_DOWN).intValue();
            map.put("ytotal",se);
            map.put("stotal",daily.size());
            map.put("qtotal",se-daily.size());
            dailyPointsList.add(map);
        }
        LayuiPageInfo layuiPageInfo=new LayuiPageInfo();
        layuiPageInfo.setData(dailyPointsList);
        layuiPageInfo.setCount(dailyPointsList.size());
        return layuiPageInfo;
    }
    /**
     * 查询每月统计列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @ResponseBody
    @RequestMapping("/listMeiyue")
    @BussinessLog(value = "查询每日统计列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo listMeiyue(@RequestParam(required = false) String name,@RequestParam(required = false) String btime,
                                    @RequestParam(required = false) String etime
    ) throws ParseException {
        Map map=new HashMap();
        if(StringUtils.isBlank(btime) && StringUtils.isBlank(etime)){
            Calendar cale = null;
            // 获取前月的第一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 0);
            cale.set(Calendar.DAY_OF_MONTH, 1);
            btime = YearsdfValue.format(cale.getTime());
            // 获取前月的最后一天
            cale = Calendar.getInstance();
            cale.add(Calendar.MONTH, 1);
            cale.set(Calendar.DAY_OF_MONTH, 0);
            etime = YearsdfValue.format(cale.getTime());
        }else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(YearsdfValue.parse(btime));
            btime = YearsdfValue.format(cal.getTime());

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(YearsdfValue.parse(etime));
            etime = YearsdfValue.format(cal2.getTime());
        }
        String str1=btime.substring(0, btime.indexOf("-"));
        String str2=btime.substring(str1.length()+1, btime.length());
        String str3=etime.substring(0, etime.indexOf("-"));
        String str4=etime.substring(str1.length()+1, etime.length());
        map.put("byear",str1);
        map.put("bmonth",str2);
        map.put("eyear",str3);
        map.put("emonth",str4);
        if(StringUtils.isNotBlank(name)){
            map.put("name",name);
        }
        List<Map> dailyPoints= this.monthlyPointsService.listMeiyue(map);
        LayuiPageInfo layuiPageInfo=new LayuiPageInfo();
        layuiPageInfo.setData(dailyPoints);
        layuiPageInfo.setCount(dailyPoints.size());
        return layuiPageInfo;
    }

}


