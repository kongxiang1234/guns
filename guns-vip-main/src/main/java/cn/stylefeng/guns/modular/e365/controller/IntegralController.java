package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Integral;
import cn.stylefeng.guns.modular.e365.entity.Integration;
import cn.stylefeng.guns.modular.e365.model.params.IntegralParam;
import cn.stylefeng.guns.modular.e365.model.params.IntegrationParam;
import cn.stylefeng.guns.modular.e365.service.IntegralService;
import cn.stylefeng.guns.modular.e365.service.IntegrationService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 控制器
 *
 * @author Alan-孔
 * @Date 2020-04-24 18:29:55
 */
@Controller
@RequestMapping("/integral")
public class IntegralController extends BaseController {

    private String PREFIX = "/integral";

    @Autowired
    private IntegralService integralService;

    @Autowired
    private IntegrationService integrationService;

    /**
     * 跳转到主页面
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/integral.html";
    }



    /**
     * 跳转到主页面
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/integ")
    @BussinessLog(value = "查询积分规则", key = "content", dict = ContentMap.class)
    public String integ(Model model) {
        Integration integration=integrationService.getOne(new QueryWrapper<>());
        if(integration!=null){
            model.addAttribute("integration",integration);
        }
        return PREFIX + "/integ.html";
    }

    /**
     * 新增接口
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/addItem")
    @BussinessLog(value = "新增积分规则", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addItem(IntegrationParam integralParam) {
        integralParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        integralParam.setCreateTime(new Date());
        this.integrationService.add(integralParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/editItem")
    @BussinessLog(value = "编辑积分规则", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData editItem(IntegrationParam integralParam) {
        this.integrationService.update(integralParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(IntegralParam integralParam) {
        this.integralService.delete(integralParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(IntegralParam integralParam) {
        Integral detail = this.integralService.getById(integralParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    @ResponseBody
    @RequestMapping("/list")
    @BussinessLog(value = "查询积分列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo list(IntegralParam integralParam) {
        return this.integralService.findPageBySpec(integralParam);
    }

}


