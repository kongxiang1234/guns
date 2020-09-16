package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationUnit;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationPdfPageParam;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationUnitParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationPdfService;
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
@RequestMapping("/investigationPdf")
public class InvestigationPdfController extends BaseController {

    private String PREFIX = "/investigationPdf";

    @Autowired
    private InvestigationPdfService investigationPdfService;

    /**
     * 跳转到主页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/investigationPdf.html";
    }

    /**
     * 查询列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(InvestigationPdfPageParam investigationPdfPageParam) {
        return this.investigationPdfService.findPageBySpec(investigationPdfPageParam);
    }

}


