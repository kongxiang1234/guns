package cn.stylefeng.guns.modular.demos.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.demos.entity.TGameAgentUser;
import cn.stylefeng.guns.modular.demos.model.params.TGameAgentUserParam;
import cn.stylefeng.guns.modular.demos.service.TGameAgentUserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 代理员信息控制器
 *
 * @author Alan
 * @Date 2020-04-15 14:04:56
 */
@Controller
@RequestMapping("/tGameAgentUser")
public class TGameAgentUserController extends BaseController {

    private String PREFIX = "/Agent/tGameAgentUser";

    @Autowired
    private TGameAgentUserService tGameAgentUserService;

    /**
     * 跳转到主页面
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/tGameAgentUser.html";
    }

    /**
     * 新增页面
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/tGameAgentUser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/tGameAgentUser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(TGameAgentUserParam tGameAgentUserParam) {
        this.tGameAgentUserService.add(tGameAgentUserParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(TGameAgentUserParam tGameAgentUserParam) {
        this.tGameAgentUserService.update(tGameAgentUserParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(TGameAgentUserParam tGameAgentUserParam) {
        this.tGameAgentUserService.delete(tGameAgentUserParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(TGameAgentUserParam tGameAgentUserParam) {
        TGameAgentUser detail = this.tGameAgentUserService.getById(tGameAgentUserParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Alan
     * @Date 2020-04-15
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(TGameAgentUserParam tGameAgentUserParam) {
        return this.tGameAgentUserService.findPageBySpec(tGameAgentUserParam);
    }

}


