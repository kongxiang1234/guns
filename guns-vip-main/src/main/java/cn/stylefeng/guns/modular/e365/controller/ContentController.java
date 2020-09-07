package cn.stylefeng.guns.modular.e365.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Content;
import cn.stylefeng.guns.modular.e365.model.params.ContentParam;
import cn.stylefeng.guns.modular.e365.service.ContentService;
import cn.stylefeng.guns.sys.core.constant.dictmap.NoticeMap;
import cn.stylefeng.guns.sys.core.constant.factory.ConstantFactory;
import cn.stylefeng.guns.sys.modular.rest.entity.RestFileInfo;
import cn.stylefeng.guns.sys.modular.rest.service.RestFileInfoService;
import cn.stylefeng.guns.sys.modular.system.model.DeptDto;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 控制器
 *
 * @author Alan_孔
 * @Date 2020-04-22 18:50:50
 */
@Controller
@RequestMapping("/content")
public class ContentController extends BaseController {
    @Autowired
    private RestFileInfoService restFileInfoService;
    private String PREFIX = "/content";

    @Autowired
    private ContentService contentService;

    /**
     * 跳转到主页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/content.html";
    }

    /**
     * 新增页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/content_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam(required = false) String Id, Model model) {
        Content detail = this.contentService.getById(Id);
        model.addAttribute("detail",detail);
        return PREFIX + "/content_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/addItem")
    @BussinessLog(value = "新增信息列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addItem(ContentParam contentParam) {
        contentParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        contentParam.setCreateTime(new Date());
        this.contentService.add(contentParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/editItem")
    @BussinessLog(value = "编辑信息列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData editItem(ContentParam contentParam) {
        contentParam.setUpdateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        contentParam.setUpdateTime(new Date());
        this.contentService.update(contentParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除信息列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData delete(ContentParam contentParam) {
        this.contentService.delete(contentParam);
        return ResponseData.success();
    }

    /**
     * 批量发布接口
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/piFabu")
    @BussinessLog(value = "批量发布列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData piFabu(ContentParam contentParam) {
        String[] strings = contentParam.getTitle().split(",");
        for (String string:strings){
            Content contentParam1=new Content();
            contentParam1.setId(Integer.parseInt(string));
            contentParam1.setStatus(2);
            contentService.updateById(contentParam1);
        }
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ContentParam contentParam) {
        Content detail = this.contentService.getById(contentParam.getId());
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("final_name",detail.getPic());
        RestFileInfo restFileInfo= restFileInfoService.getOne(queryWrapper);
        if(restFileInfo!=null){
            detail.setPic(restFileInfo.getFileId());
        }

        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @ResponseBody
    @RequestMapping("/list")
    @BussinessLog(value = "查询信息列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo list(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String time,
                              @RequestParam(required = false) String status,@RequestParam(required = false) String type) {
        //拼接查询条件
        String beginTime = "";
        String endTime = "";
        if (ToolUtil.isNotEmpty(time)) {
            String[] split = time.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }

        return this.contentService.findPageBySpec(beginTime,endTime,title,status,type);
    }

}


