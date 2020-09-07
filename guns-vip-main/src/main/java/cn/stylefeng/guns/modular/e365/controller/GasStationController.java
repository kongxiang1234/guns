package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.modular.e365.entity.*;
import cn.stylefeng.guns.modular.e365.model.params.IntegralParam;
import cn.stylefeng.guns.modular.e365.service.IntegralService;
import cn.stylefeng.guns.modular.e365.service.IntegrationService;
import cn.stylefeng.guns.modular.e365.service.PaperService;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.guns.sys.modular.system.entity.Role;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.DeptService;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.guns.sys.modular.system.service.RoleService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 控制器 加油站
 *
 * @author Alan_孔
 * @Date 2020-04-22 18:50:50
 */
@Slf4j
@Controller
@RequestMapping("/gasStation")
public class GasStationController extends BaseController {

    private String PREFIX = "/jiayouzhan";
    @Autowired
    private UserService userService;
    @Autowired
    private PaperService paperService;

    @Autowired
    private DeptService deptService;
    @Autowired
    private DictService dictService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private IntegralService integralService;
    @Autowired
    private IntegrationService integrationService;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 每日练习
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/meiri")
    public String index(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user=(User)session.getAttribute("user");
        String str = "";
        List<String> strings=Arrays.asList(user.getSpecialty().split("、"));
        if(strings.size()>1){
            str=strings.get(0);
        }else {
            str=user.getSpecialty();
        }
        List<Map> mapList= paperService.selectQuary(str);
        String string = "";
        for (Map map:mapList){
            if(map.get("questions_type").toString().equals("1")){
                string=string+map.get("single").toString()+".";
            }else if (map.get("questions_type").toString().equals("2")){
                string=string+map.get("Multiple").toString()+".";
            }else if (map.get("questions_type").toString().equals("3")){
                string=string+map.get("flag").toString()+".";
            }
        }
        model.addAttribute("string",string);
        model.addAttribute("mapList",mapList);
        return PREFIX + "/dailyTest.html";
    }
    /**
     * 每日记录积分
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/meiriAudit")
    @ResponseBody
    public ResponseData addItem(HttpServletRequest request,HttpSession session) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date et=new Date();
        String string=sdf.format(et);//当前时间
        User user=(User)session.getAttribute("user");
        Integration one = integrationService.getOne(new QueryWrapper<Integration>());
        DailyPoints dailyPoints= integralService.selectMeiriAudit(user.getAccount());//获取当前用户现在的每日练习多少次了
        Integral integ = integralService.getOne(new QueryWrapper<Integral>().eq("userId", user.getUserId()));
        IntegralParam integralParam = new IntegralParam();
        DailyPoints daily=new DailyPoints();
        if(dailyPoints==null){
            daily.setDate(new Date());
            daily.setName(user.getAccount());
            daily.setUnit(String.valueOf(user.getDeptId()));
            daily.setTotel(1);
            if (daily.getTotel() % one.getMrcs() == 0) {//每日统计的次数是积分规则的倍数就计入积分表
                if(integ==null){
                    integralParam.setCreateTime(new Date());
                    integralParam.setCreateUser(user.getName());
                    integralParam.setName(user.getName());
                    integralParam.setUserId(user.getUserId());
                    integralParam.setSsdw(String.valueOf(user.getDeptId()));
                    integralParam.setMrIntegral(one.getMrjf());
                    integralService.add(integralParam);
                }else {
                    integralParam.setId(integ.getId());
                    integralParam.setMrIntegral(integ.getMrIntegral() + one.getMrjf());
                    if(integralParam.getMrIntegral() % one.getMrdk() == 0){ //判断积分与积分规则设置的积分倍数时就进行违章抵扣积分的累加
                        if(integ.getDkwzIntegral()==0){//如果第一次直接加一分
                            integralParam.setDkwzIntegral(1);
                        }else {
                            if(integ.getDkwzIntegral()!=null){
                                if(ConstantsContext.getScore()!=integ.getDkwzIntegral()){//如果常量积分不等于累加的违章积分就不累加了
                                    integralParam.setDkwzIntegral(integ.getDkwzIntegral()+1);
                                }
                            }
                        }
                        IntegralLog log=new IntegralLog();
                        log.setDate(new Date());
                        log.setName("每日测试违章积分");
                        log.setScore(String.valueOf(integralParam.getDkwzIntegral()));
                        log.setUserId(user.getUserId());
                        integralService.addIntegralLog(log);
                    }
                    integralService.update(integralParam);
                }
            }
        }else {
            if (string.equals(sdf.format(dailyPoints.getDate()))) {//判断最近添加的时间和当前时间对比，如果对等直接返回不添加
                return ResponseData.error(100,"12");
            }
            if (dailyPoints.getTotel() % one.getMrcs() == 0) {//每日统计的次数是积分规则的倍数就计入积分表
                if(integ==null){
                    integralParam.setCreateTime(new Date());
                    integralParam.setCreateUser(user.getName());
                    integralParam.setName(user.getName());
                    integralParam.setUserId(user.getUserId());
                    integralParam.setSsdw(String.valueOf(user.getDeptId()));
                    integralParam.setMrIntegral(one.getMrjf());
                    integralService.add(integralParam);
                }else {
                    integralParam.setId(integ.getId());
                    integralParam.setMrIntegral(integ.getMrIntegral() + one.getMrjf());
                    if(integralParam.getMrIntegral() % one.getMrdk() == 0){ //判断积分与积分规则设置的积分倍数时就进行违章抵扣积分的累加
                        if(integ.getDkwzIntegral()==0){//如果第一次直接加一分
                            integralParam.setDkwzIntegral(1);
                        }else {
                            if(integ.getDkwzIntegral()!=null){
                                if(ConstantsContext.getScore()!=integ.getDkwzIntegral()){//如果常量积分不等于累加的违章积分就不累加了
                                    integralParam.setDkwzIntegral(integ.getDkwzIntegral()+1);
                                }
                            }
                        }
                        IntegralLog log=new IntegralLog();
                        log.setDate(new Date());
                        log.setName("每日测试违章积分");
                        log.setScore(String.valueOf(integralParam.getDkwzIntegral()));
                        log.setUserId(user.getUserId());
                        integralService.addIntegralLog(log);
                    }
                    integralService.update(integralParam);
                }
            }
            daily.setDate(new Date());
            daily.setName(user.getAccount());
            daily.setUnit(String.valueOf(user.getDeptId()));
            daily.setTotel(dailyPoints.getTotel() + 1);
        }
        integralService.addMeiriAudit(daily);
        return ResponseData.success("已提交今天的测试");
    }


    /**
     * 每周练习
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/meizhou")
    public String meizhou(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        List<Dict> dicts = this.dictService.listDictsByName("专题");
        for (Dict dict:dicts){
            if(dict.getCode().equals(type)){
                model.addAttribute("type",dict.getName());
            }
        }
        List<Map> mapList= paperService.selectZhouQuary(type);
        String string = "";
        for (Map map:mapList){
            if(map.get("questions_type").toString().equals("1")){
                string=string+map.get("single").toString()+".";
            }else if (map.get("questions_type").toString().equals("2")){
                string=string+map.get("Multiple").toString()+".";
            }else if (map.get("questions_type").toString().equals("3")){
                string=string+map.get("flag").toString()+".";
            }
        }
        model.addAttribute("string",string);
        model.addAttribute("mapList",mapList);
        return PREFIX + "/weeklyTest.html";
    }
    /**
     * 每周记录积分
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/meizhouAudit")
    @ResponseBody
    public ResponseData meizhouAudit(HttpServletRequest request,HttpSession session) {
        String ztype=getPara("ztype");
        User user=(User)session.getAttribute("user");
        Integration one = integrationService.getOne(new QueryWrapper<Integration>());
        WeeklyPoints dailyPoints= integralService.selectMeizhouAudit(user.getAccount());//获取当前用户现在的每日练习多少次了
        WeeklyPoints daily=new WeeklyPoints();
        if(dailyPoints==null){
            daily.setDate(new Date());
            daily.setName(user.getAccount());
            daily.setUnit(String.valueOf(user.getDeptId()));
            daily.setTotel(1);
            daily.setZtype(ztype);
        }else {
            if (dailyPoints.getTotel() % one.getMzcs() == 0) {//每日统计的次数是积分规则的倍数就计入积分表
                Integral integ = integralService.getOne(new QueryWrapper<Integral>().eq("userId", user.getUserId()));

                IntegralParam integralParam = new IntegralParam();
                integralParam.setId(integ.getId());
                if(integ.getMzIntegral()==0){
                    integralParam.setMzIntegral(one.getMzjf());
                }else {
                    integralParam.setMzIntegral(integ.getMzIntegral() + one.getMzjf());
                    if(integralParam.getMzIntegral() % one.getMzdk() == 0){ //判断积分与积分规则设置的积分倍数时就进行违章抵扣积分的累加
                        if(integ.getDkwzIntegral()==0){//如果第一次直接加一分
                            integralParam.setDkwzIntegral(1);
                        }else {
                            if(ConstantsContext.getScore()!=integ.getDkwzIntegral()){//如果常量积分不等于累加的违章积分就不累加了
                                integralParam.setDkwzIntegral(integ.getDkwzIntegral()+1);
                            }
                        }
                        IntegralLog log=new IntegralLog();
                        log.setDate(new Date());
                        log.setName("每周专题测试违章积分");
                        log.setScore(String.valueOf(integralParam.getDkwzIntegral()));
                        log.setUserId(user.getUserId());
                        integralService.addIntegralLog(log);
                    }
                }
                integralService.update(integralParam);
            }
            daily.setDate(new Date());
            daily.setName(user.getAccount());
            daily.setUnit(String.valueOf(user.getDeptId()));
            daily.setTotel(dailyPoints.getTotel() + 1);
            daily.setZtype(ztype);
        }
        integralService.addMeizhouAudit(daily);
        return ResponseData.success("已提交今天的测试");
    }
    /**
     * 每月练习
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/meiyue")
    public String meiyue(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException, ParseException {
        String role=request.getParameter("role");//角色那里人
        String major=request.getParameter("major");//角色那里人
        String area=request.getParameter("area");//部门那里人
        List<String> strs=Arrays.asList(major.split("、"));
        model.addAttribute("role",role);
        model.addAttribute("major",major);
        model.addAttribute("area",area);
        Role role1=roleService.getById(role);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date d2 = new Date();
        QueryWrapper<Paper> queryWrapper1=new QueryWrapper();
        queryWrapper1.eq("date_format(btime, '%Y-%m-%d' )",sdf2.format(d2));
        queryWrapper1.last("limit 1");
        queryWrapper1.in("sszy",strs);
        queryWrapper1.eq("type","2");
        Paper paper= paperService.getOne(queryWrapper1);
        if(paper==null){
            return PREFIX +"/monthlyeva.html";
        }
        List<Map> mapList= paperService.selectYueQuary(paper.getSjbh(),strs,role1.getName());
        Date btime = paper.getBtime();//考试开始时间
        Date etime = paper.getEtime();//考试开始时间
        model.addAttribute("etime",sdf1.format(etime));
        model.addAttribute("time",(int)(etime.getTime()-btime.getTime())/1000);
        String string = "";
        for (Map map:mapList){
            if(map.get("questions_type")!=null){
                if(map.get("questions_type").toString().equals("1")){
                    string=string+map.get("single").toString()+"/";
                }else if (map.get("questions_type").toString().equals("2")){
                    string=string+map.get("Multiple").toString()+"/";
                }else if (map.get("questions_type").toString().equals("3")){
                    string=string+map.get("flag").toString()+"/";
                }
            }
        }
        List<String> strings=new ArrayList<>();
        for (String string1:string.split("/")){
            strings.add(string1);
        }
        model.addAttribute("id",paper.getId());
        model.addAttribute("danxtValue",paper.getDanxtValue());
        model.addAttribute("duoxtValue",paper.getDuoxtValue());
        model.addAttribute("pdtValue",paper.getPdtValue());
        model.addAttribute("string", strings);
        model.addAttribute("mapList",mapList);
        model.addAttribute("mapListSize",mapList.size());
        return PREFIX + "/monthlyTest1.html";
    }
    /**
     * 每月练习
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/monthly")
    public String monthly(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user=(User)session.getAttribute("user");
        Dept dept= deptService.getById(user.getDeptId());
        Role role= roleService.getById(user.getRoleId());
        List<String> strings=Arrays.asList(user.getSpecialty().split("、"));
        model.addAttribute("specialty",user.getSpecialty());
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date d2 = new Date();
        QueryWrapper<Paper> queryWrapper1=new QueryWrapper();
        queryWrapper1.eq("date_format(btime, '%Y-%m-%d' )",sdf2.format(d2));
        queryWrapper1.last("limit 1");
        queryWrapper1.in("sszy",strings);
        queryWrapper1.eq("type","2");
        Paper paper= paperService.getOne(queryWrapper1);
        List<Map> mapList= paperService.selectYueQuary(paper.getSjbh(),strings,role.getName());
        model.addAttribute("specialtyValue",user.getSpecialty());
        model.addAttribute("departId",user.getDeptId());
        model.addAttribute("roleId",user.getRoleId());
        model.addAttribute("departName",dept.getSimpleName());
        model.addAttribute("name",role.getName());
        model.addAttribute("message","");
        if(paper==null || mapList.size()==0){
            model.addAttribute("message","暂无试卷考试");
        }else {
            /**
                 * 1表示大于，返回-1表示小于，返回0表示相等。
                 */
            if(paper.getBtime().compareTo(d2)==1){
                model.addAttribute("message","考试时间还没到，请稍候点击开始考试，开始时间"+sdf.format(paper.getBtime())+"结束时间"+sdf.format(paper.getEtime())+"");
            }
        }

        return PREFIX + "/monthlyeva.html";
    }

    /**
     * 每月记录积分
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/meiyueAudit")
    @ResponseBody
    public ResponseData meiyueAudit(HttpServletRequest request,HttpSession session) throws ParseException {
        String score=request.getParameter("score");
        String paperId=request.getParameter("paperId");
        String role=request.getParameter("role");//角色那里人
        String major=request.getParameter("major");//角色那里人
        String area=request.getParameter("area");//部门那里人
        String etime=request.getParameter("etime");
        User user=(User)session.getAttribute("user");
        Paper paper=new Paper();
        paper.setId(Integer.parseInt(paperId));
        paper.setType("4");
        paperService.updateById(paper);
        List<String> strings=Arrays.asList(major.split("、"));
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("role_id",role);
        queryWrapper.in("specialty",strings);
        queryWrapper.eq("dept_id",area);

        List<User> userList=userService.list(queryWrapper);//查询要考试的人员
        MonthlyPoints daily=new MonthlyPoints();
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(etime)); //获取结束时间参数
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        cal.add(Calendar.MINUTE, 5);//加五分钟执行定时任务
        daily.setDateYear(String.valueOf(year));
        daily.setMonth(String.valueOf(month));
        daily.setName(user.getName());
        daily.setPaperId(Integer.parseInt(paperId));
        daily.setScore(Integer.parseInt(score) );
        daily.setUnit(String.valueOf(user.getDeptId()));
        daily.setUserId(user.getUserId());
        integralService.addMeiyueAudit(daily);
        /*List<MonthlyPoints> monthlyPoints=integralService.listMonthlyPoints(year,month);*/
       /* if(monthlyPoints.size()==1){*/
            String string = sdf.format(cal.getTime());
            // 要执行的时间参数
            Date dateTime = sdf.parse(string);
            Timer timer = new Timer();
            timer.schedule(new RemindTask(userList,year,month,paperId), dateTime);
      /*  }*/
        return ResponseData.success("已提交今天的测试");
    }
}


