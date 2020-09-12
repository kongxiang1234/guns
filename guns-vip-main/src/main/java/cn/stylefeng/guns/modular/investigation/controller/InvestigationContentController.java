package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationContentService;
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
 * @Date 2020-09-09 23:09:38
 */
@Controller
@RequestMapping("/investigationContent")
public class InvestigationContentController extends BaseController {

    private String PREFIX = "/investigationContent";

    @Autowired
    private InvestigationContentService investigationContentService;

    /**
     * 跳转到主页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/investigationContent.html";
    }

    /**
     * 新增页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/investigationContent_add.html";
    }

    /**
     * 编辑页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/investigationContent_edit.html";
    }

    /**
     * 新增接口
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InvestigationContentParam investigationContentParam) {
        this.investigationContentService.add(investigationContentParam);
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
    public ResponseData editItem(InvestigationContentParam investigationContentParam) {
        this.investigationContentService.update(investigationContentParam);
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
    public ResponseData delete(InvestigationContentParam investigationContentParam) {
        this.investigationContentService.delete(investigationContentParam);
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
    public ResponseData detail(InvestigationContentParam investigationContentParam) {
        InvestigationContent detail = this.investigationContentService.getById(investigationContentParam.getContentId());
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
    public LayuiPageInfo list(InvestigationContentParam investigationContentParam) {
        return this.investigationContentService.findPageBySpec(investigationContentParam);
    }

}


