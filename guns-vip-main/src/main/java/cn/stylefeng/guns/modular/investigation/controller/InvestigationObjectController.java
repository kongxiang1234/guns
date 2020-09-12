package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationObject;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationObjectParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationObjectService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author hujuntao
 * @Date 2020-09-12 09:33:48
 */
@Controller
@RequestMapping("/investigationObject")
public class InvestigationObjectController extends BaseController {

    private String PREFIX = "/investigationObject";

    @Autowired
    private InvestigationObjectService investigationObjectService;

    /**
     * 跳转到主页面
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/investigationObject.html";
    }

    /**
     * 新增页面
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/investigationObject_add.html";
    }

    /**
     * 编辑页面
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/investigationObject_edit.html";
    }

    /**
     * 新增接口
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InvestigationObjectParam investigationObjectParam) {
        this.investigationObjectService.add(investigationObjectParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(InvestigationObjectParam investigationObjectParam) {
        this.investigationObjectService.update(investigationObjectParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(InvestigationObjectParam investigationObjectParam) {
        this.investigationObjectService.delete(investigationObjectParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(InvestigationObjectParam investigationObjectParam) {
        InvestigationObject detail = this.investigationObjectService.getById(investigationObjectParam.getObjectId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author hujuntao
     * @Date 2020-09-12
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(InvestigationObjectParam investigationObjectParam) {
        return this.investigationObjectService.findPageBySpec(investigationObjectParam);
    }

}


