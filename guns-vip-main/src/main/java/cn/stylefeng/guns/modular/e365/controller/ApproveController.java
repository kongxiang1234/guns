package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.modular.e365.entity.*;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.service.*;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.DeptService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 控制器 个人认证
 *
 * @author Alan_孔
 * @Date 2020-04-22 18:50:50
 */
@Slf4j
@Controller
@RequestMapping("/approve")
public class ApproveController extends BaseController {

    private String PREFIX = "/approve";
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private IntegralService integralService;
    @Autowired
    private MonthlyPointsService monthlyPointsService;

    @Autowired
    private DailyPointsService dailyPointsService;

    @Autowired
    private WeeklyPointsService weeklyPointsService;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdfValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 跳转到每月考试统计页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/montotal")
    public String montotal(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user=(User)session.getAttribute("user");
        List<Map> monthlyPoints= monthlyPointsService.listPaper(user.getUserId());
        model.addAttribute("monthlyPoints",monthlyPoints);
        return PREFIX + "/monTotal.html";
    }
    /**
     * 跳转到每周考试统计页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/weeklytotal")
    public String weeklytotal(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user=(User)session.getAttribute("user");
        List<WeeklyPoints> monthlyPoints= weeklyPointsService.list(new QueryWrapper<WeeklyPoints>().eq("name",user.getAccount()));
        for (WeeklyPoints weeklyPoints:monthlyPoints){
            weeklyPoints.setAccount(sdfValue.format(weeklyPoints.getDate()));
        }
        model.addAttribute("monthlyPoints",monthlyPoints);
        return PREFIX + "/weeklyTotal.html";
    }
    /**
     * 跳转到每日考试统计页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/meiritotal")
    public String meiritotal(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user=(User)session.getAttribute("user");
        List<DailyPoints> monthlyPoints= dailyPointsService.list(new QueryWrapper<DailyPoints>().eq("name",user.getAccount()));
        for (DailyPoints weeklyPoints:monthlyPoints){
            weeklyPoints.setAccount(sdfValue.format(weeklyPoints.getDate()));
        }
        model.addAttribute("monthlyPoints",monthlyPoints);
        return PREFIX + "/meiriTotal.html";
    }

    /**
     * 跳转到积分统计页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/jifentotal")
    public String jifentotal(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user=(User)session.getAttribute("user");
        Integral integral=integralService.getOne(new QueryWrapper<Integral>().eq("userId",user.getUserId()));
        if(integral!=null){
            integral.setYjIntegral(integral.getMrIntegral()+integral.getMzIntegral()+integral.getMyIntegral());
        }
        model.addAttribute("integral",integral);
        return PREFIX + "/jifenTotal.html";
    }
    /**
     * 跳转到有奖奖励页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/appAward")
    public String appAward(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {

        return PREFIX + "/awardreport.html";
    }
    /**
     * 跳转到意见建议页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/appSwitch")
    public String appSwitch(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {

        return PREFIX + "/switch.html";
    }
    /**
     * 跳转到我的意见反馈页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/meSwitch")
    public String meSwitch(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Feedback> feedbacks=feedbackService.list(new QueryWrapper<Feedback>().eq("userId",user.getUserId()));
        model.addAttribute("feedbacks",feedbacks);
        return PREFIX + "/meSwitch.html";
    }
    /**
     * 跳转到警告教育详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/meSwitchDetailed/{id}")
    public String meSwitchDetailed(@PathVariable("id") String id, Model model) {
        Feedback feedback=feedbackService.getById(id);
        model.addAttribute("feedback",feedback);
        return PREFIX + "/meSwitchdetail.html";
    }
    /**
     * 意见建议添加
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/addappSwitch")
    @ResponseBody
    public ResponseData addappSwitch(Model model,HttpSession session) {
        User user=(User)session.getAttribute("user");
        String type = getPara("type");
        String feedbackSuggestions = getPara("feedbackSuggestions");
        FeedbackParam feedbackParam=new FeedbackParam();
        feedbackParam.setFeedbackType(type);
        feedbackParam.setCreateTime(new Date());
        feedbackParam.setCreateUser(user.getName());
        feedbackParam.setFeedbackSuggestions(feedbackSuggestions);
        feedbackParam.setUserId(user.getUserId());
        this.feedbackService.add(feedbackParam);
        return ResponseData.success();
    }
    /**
     * 跳转到个人认证页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/app")
    public String index(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        model.addAttribute("title",title);
        if(session.getAttribute("user")!=null){
            return PREFIX + "/formor.html";
        }
        List<Dept> depts=deptService.list();
        model.addAttribute("depts",depts);
        return PREFIX + "/approve.html";
    }
    /**
     * 退出
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/loginOut")
    public String loginOut(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        String title=request.getParameter("title");
        model.addAttribute("title",title);
        session.removeAttribute("user");
        return PREFIX + "/approve.html";
    }
    /**
     * 跳转到个人信息页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/appUser")
    public String appUser(Model model, HttpServletRequest request,HttpSession session,HttpServletResponse response) throws ServletException, IOException {
        User user1=(User)session.getAttribute("user");
        Dept dept= deptService.getOne(new QueryWrapper<Dept>().eq("dept_id",user1.getDeptId()));
        User user =userService.getOne(new QueryWrapper<User>().eq("user_id",user1.getUserId()));
        UserDto userDto=new UserDto();
        userDto.setAvatar(user.getAvatar());
        userDto.setName(user.getName());
        userDto.setDeptName(dept.getSimpleName());
        userDto.setUserId(user.getUserId());
        /**
         * 获取每月考试次数
         */
        int monCount=integralService.getMonthly(String.valueOf(user.getUserId()));
        /**
         * 获取每周考试次数
         */
        int weekCount=integralService.getWeekly(user.getAccount());
        /**
         * 获取每日考试次数
         */
        int dayCount=integralService.getDay(user.getAccount());
        Integral integralCount=integralService.getOne(new QueryWrapper<Integral>().eq("userId",user.getUserId()));
        if(integralCount!=null){
            userDto.setIntegralCount(integralCount.getMrIntegral()+integralCount.getMzIntegral()+integralCount.getMyIntegral());
        }else {
            userDto.setIntegralCount(0);
        }

        userDto.setMonCount(monCount);
        userDto.setWeekCount(weekCount);
        userDto.setDayCount(dayCount);
        model.addAttribute("user",userDto);
        return PREFIX + "/personalcenter.html";
    }

    /**
     * 个人中心认证
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/appAudit")
    @ResponseBody
    public ResponseData addItem(HttpServletRequest request,HttpSession session) {
        String account=request.getParameter("account");
        String dept=request.getParameter("dept");
        User user= userService.getByAccount(account, new Long(dept));
        if(user==null){
            return ResponseData.error(1,"认证失败");
        }
        if(user.getStatus().equals("DISABLE")){
            return ResponseData.error(0,"用户已禁用");
        }
        session.setAttribute("user", user);
        //session设置为永久
        session.setMaxInactiveInterval(-1);
        return ResponseData.success();
    }
    /**
     * 个人中心认证
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/addappAudit")
    @ResponseBody
    public ResponseData jingaoDetailed(Model model) {
        String account = getPara("account");
        String depart = getPara("depart");
        String idcart = getPara("idcart");
        String mobile = getPara("mobile");

        UserDto user=new UserDto();
        user.setDeptId(new Long(depart));
        user.setAccount(account);
        user.setEmail(idcart);
        user.setPhone(mobile);
        user.setPassword(ConstantsContext.getDefaultPassword());
        user.setStatus("DISABLE");
        this.userService.addUser(user);
        return ResponseData.success();
    }


}


