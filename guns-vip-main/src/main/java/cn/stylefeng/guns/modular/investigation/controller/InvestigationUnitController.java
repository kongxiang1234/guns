package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationUnit;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationUnitParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationUnitService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 控制器
 *
 * @author hujt
 * @Date 2020-09-09 23:06:15
 */
@Controller
@RequestMapping("/investigationUnit")
public class InvestigationUnitController extends BaseController {

    private String PREFIX = "/investigationUnit";

    @Autowired
    private InvestigationUnitService investigationUnitService;

    /**
     * 跳转到主页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/investigationUnit.html";
    }

    /**
     * 新增页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/investigationUnit_add.html";
    }

    /**
     * 编辑页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/investigationUnit_edit.html";
    }

    /**
     * 新增接口
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InvestigationUnitParam investigationUnitParam) {
        this.investigationUnitService.add(investigationUnitParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(InvestigationUnitParam investigationUnitParam) {
        this.investigationUnitService.update(investigationUnitParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(InvestigationUnitParam investigationUnitParam) {
        this.investigationUnitService.delete(investigationUnitParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(InvestigationUnitParam investigationUnitParam) {
        InvestigationUnit detail = this.investigationUnitService.getById(investigationUnitParam.getUnitId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(InvestigationUnitParam investigationUnitParam) {
        return this.investigationUnitService.findPageBySpec(investigationUnitParam);
    }

}


