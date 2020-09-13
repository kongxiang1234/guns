package cn.stylefeng.guns.modular.e365.controller;

import cn.afterturn.easypoi.entity.vo.MapExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.view.PoiBaseView;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Feedback;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.service.FeedbackService;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.guns.sys.modular.system.service.DictTypeService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author Alan-孔
 * @Date 2020-04-16 10:21:21
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    private String PREFIX = "/feedback";

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private DictService dictService;
    /**
     * 跳转到主页面
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/feedback.html";
    }

    /**
     * 新增页面
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/feedback_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/feedback_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @RequestMapping("/addItem")
    @BussinessLog(value = "新增意见建议", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addItem(FeedbackParam feedbackParam) {
        this.feedbackService.add(feedbackParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @RequestMapping("/editItem")
    @BussinessLog(value = "编辑意见建议", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData editItem(FeedbackParam feedbackParam) {
        this.feedbackService.update(feedbackParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(FeedbackParam feedbackParam) {

        return ResponseData.success();
    }

    /**
     * 查询列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    @ResponseBody
    @RequestMapping("/list")
    @BussinessLog(value = "查询意见建议", key = "content", dict = ContentMap.class)
    public LayuiPageInfo list(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String timeLimit,
                              @RequestParam(required = false) String type) {
        //拼接查询条件
        String beginTime = "";
        String endTime = "";

        if (ToolUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }
        return this.feedbackService.findPageBySpec(name,beginTime,endTime,type);
    }

    /**
     * excel导出
     *
     * @author fengshuonan
     * @Date 2019/3/9 11:03
     */
    @RequestMapping("/export")
    public void export(ModelMap modelMap, HttpServletRequest request,
                       HttpServletResponse response) {
        //初始化表头
        List<ExcelExportEntity> entity = new ArrayList<>();
        entity.add(new ExcelExportEntity("意见类型", "feedback_type"));
        entity.add(new ExcelExportEntity("意见建议", "feedback_suggestions"));
        entity.add(new ExcelExportEntity("意见反馈", "feedback_content"));
        entity.add(new ExcelExportEntity("创建人", "create_user"));
        entity.add(new ExcelExportEntity("创建时间", "create_Time"));

        //初始化化数据
        List<Map<String, Object>> maps = feedbackService.listMaps();
        ArrayList<Map<String, Object>> total = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            total.addAll(maps);
        }
        ExportParams params = new ExportParams("意见建议", "意见表", ExcelType.XSSF);
        modelMap.put(MapExcelConstants.MAP_LIST, total);
        modelMap.put(MapExcelConstants.ENTITY_LIST, entity);
        modelMap.put(MapExcelConstants.PARAMS, params);
        modelMap.put(MapExcelConstants.FILE_NAME, "E365管理系统所有意见建议");
        PoiBaseView.render(modelMap, request, response, MapExcelConstants.EASYPOI_MAP_EXCEL_VIEW);
    }
}


