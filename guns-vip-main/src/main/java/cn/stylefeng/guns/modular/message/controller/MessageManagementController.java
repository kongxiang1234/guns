package cn.stylefeng.guns.modular.message.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.message.entity.MessageManagement;
import cn.stylefeng.guns.modular.message.model.params.MessageManagementParam;
import cn.stylefeng.guns.modular.message.service.MessageManagementService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author Alan
 * @Date 2020-09-12 21:40:26
 */
@Controller
@RequestMapping("/messageManagement")
public class MessageManagementController extends BaseController {

    private String PREFIX = "/messageManagement";

    @Autowired
    private MessageManagementService messageManagementService;

    /**
     * 跳转到主页面
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/messageManagement.html";
    }

    /**
     * 新增页面
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/messageManagement_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/messageManagement_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(MessageManagementParam messageManagementParam) {
        this.messageManagementService.add(messageManagementParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(MessageManagementParam messageManagementParam) {
        this.messageManagementService.update(messageManagementParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(MessageManagementParam messageManagementParam) {
        this.messageManagementService.delete(messageManagementParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(MessageManagementParam messageManagementParam) {
        MessageManagement detail = this.messageManagementService.getById(messageManagementParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Alan
     * @Date 2020-09-12
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(MessageManagementParam messageManagementParam) {
        return this.messageManagementService.findPageBySpec(messageManagementParam);
    }

}


