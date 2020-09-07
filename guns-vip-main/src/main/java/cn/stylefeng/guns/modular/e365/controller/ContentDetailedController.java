package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Content;
import cn.stylefeng.guns.modular.e365.model.params.ContentParam;
import cn.stylefeng.guns.modular.e365.model.result.ContentResult;
import cn.stylefeng.guns.modular.e365.service.ContentService;
import cn.stylefeng.guns.sys.modular.rest.entity.RestFileInfo;
import cn.stylefeng.guns.sys.modular.rest.service.RestFileInfoService;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 控制器
 *
 * @author Alan_孔
 * @Date 2020-04-22 18:50:50
 */
@Controller
@RequestMapping("/contentDetailed")
public class ContentDetailedController extends BaseController {
    @Autowired
    private RestFileInfoService restFileInfoService;
    private String PREFIX = "/contentDetailed";
    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 跳转到警告教育页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/jingao")
    public String index(Model model, HttpServletRequest request) {
        String title=request.getParameter("title");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("type",1);
        queryWrapper.eq("status",2);
        queryWrapper.orderByDesc("create_time");
        if(StringUtils.isNotBlank(title)){
            queryWrapper.eq("title",title);
        }
        List<Content> list=contentService.list(queryWrapper);
        List<ContentResult> relist=new ArrayList<>();
        for (Content content:list){
            ContentResult contentResult =new ContentResult();
            contentResult.setId(content.getId());
            contentResult.setPic(content.getPic());
            contentResult.setReading(content.getReading());
            contentResult.setTitle(content.getTitle());
            contentResult.setTime(sdf.format(content.getCreateTime()));
            relist.add(contentResult);
        }
        model.addAttribute("list",relist);
        return PREFIX + "/safetykl.html";
    }

    /**
     * 跳转到警告教育详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/jingaoDetailed/{id}")
    public String jingaoDetailed(@PathVariable("id") String id, Model model) {
         Content content=contentService.getById(id);
         int re=   Integer.parseInt(content.getReading()==null?"0":content.getReading())+1;
        ContentParam contentParam=new ContentParam();
        contentParam.setId(content.getId());
        contentParam.setReading(String.valueOf(re));
        contentService.update(contentParam);
        ContentResult contentResult =new ContentResult();
        contentResult.setContent(content.getContent());
        contentResult.setTitle(content.getTitle());
        contentResult.setTime(sdf.format(content.getCreateTime()));

        model.addAttribute("content",contentResult);
        return PREFIX + "/safetykldetail.html";
    }

    /**
     * 跳转到安全释义页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/anquan")
    public String anquan(Model model, HttpServletRequest request) {
        String title=request.getParameter("title");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("type",2);
        queryWrapper.eq("status",2);
        if(StringUtils.isNotBlank(title)){
            queryWrapper.like("title",title);
        }
        List<Content> list=contentService.list(queryWrapper);
        List<ContentResult> relist=new ArrayList<>();
        for (Content content:list){
            ContentResult contentResult =new ContentResult();
            contentResult.setId(content.getId());
            contentResult.setPic(content.getPic());
            contentResult.setReading(content.getReading());
            contentResult.setTitle(content.getTitle());
            contentResult.setTime(sdf.format(content.getCreateTime()));
            relist.add(contentResult);
        }
        model.addAttribute("list",relist);
        return PREFIX + "/anquan.html";
    }

    /**
     * 跳转到警告教育详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/anquanDetailed/{id}")
    public String anquanDetailed(@PathVariable("id") String id, Model model) {
        Content content=contentService.getById(id);
        int re=   Integer.parseInt(content.getReading()==null?"0":content.getReading())+1;
        ContentParam contentParam=new ContentParam();
        contentParam.setId(content.getId());
        contentParam.setReading(String.valueOf(re));
        contentService.update(contentParam);
        ContentResult contentResult =new ContentResult();
        contentResult.setContent(content.getContent());
        contentResult.setTitle(content.getTitle());
        contentResult.setTime(sdf.format(content.getCreateTime()));

        model.addAttribute("content",contentResult);
        return PREFIX + "/anquandetail.html";
    }
    /**
     * 跳转到安全发布页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/fabu")
    public String anquanfabu(Model model, HttpServletRequest request) {
        String title=request.getParameter("title");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("type",3);
        queryWrapper.eq("status",2);
        if(StringUtils.isNotBlank(title)){
            queryWrapper.like("title",title);
        }
        List<Content> list=contentService.list(queryWrapper);
        List<ContentResult> relist=new ArrayList<>();
        for (Content content:list){
            ContentResult contentResult =new ContentResult();
            contentResult.setId(content.getId());
            contentResult.setPic(content.getPic());
            contentResult.setReading(content.getReading());
            contentResult.setTitle(content.getTitle());
            contentResult.setTime(sdf.format(content.getCreateTime()));
            relist.add(contentResult);
        }
        model.addAttribute("list",relist);
        return PREFIX + "/fabu.html";
    }

    /**
     * 跳转到安全发布详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/fabuDetailed/{id}")
    public String fabuDetailed(@PathVariable("id") String id, Model model) {
        Content content=contentService.getById(id);
        int re=   Integer.parseInt(content.getReading()==null?"0":content.getReading())+1;
        ContentParam contentParam=new ContentParam();
        contentParam.setId(content.getId());
        contentParam.setReading(String.valueOf(re));
        contentService.update(contentParam);
        ContentResult contentResult =new ContentResult();
        contentResult.setContent(content.getContent());
        contentResult.setTitle(content.getTitle());
        contentResult.setTime(sdf.format(content.getCreateTime()));

        model.addAttribute("content",contentResult);
        return PREFIX + "/fabudetail.html";
    }


    /**
     * 跳转到电力设施保护页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/dianli")
    public String dianli(Model model, HttpServletRequest request) {
        String title=request.getParameter("title");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("type",4);
        queryWrapper.eq("status",2);
        if(StringUtils.isNotBlank(title)){
            queryWrapper.like("title",title);
        }
        List<Content> list=contentService.list(queryWrapper);
        List<ContentResult> relist=new ArrayList<>();
        for (Content content:list){
            ContentResult contentResult =new ContentResult();
            contentResult.setId(content.getId());
            contentResult.setPic(content.getPic());
            contentResult.setReading(content.getReading());
            contentResult.setTitle(content.getTitle());
            contentResult.setTime(sdf.format(content.getCreateTime()));
            relist.add(contentResult);
        }
        model.addAttribute("list",relist);
        return PREFIX + "/dianli.html";
    }

    /**
     * 跳转到电力设施保护详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/dianliDetailed/{id}")
    public String dianliDetailed(@PathVariable("id") String id, Model model) {
        Content content=contentService.getById(id);
        int re=   Integer.parseInt(content.getReading()==null?"0":content.getReading())+1;
        ContentParam contentParam=new ContentParam();
        contentParam.setId(content.getId());
        contentParam.setReading(String.valueOf(re));
        contentService.update(contentParam);
        ContentResult contentResult =new ContentResult();
        contentResult.setContent(content.getContent());
        contentResult.setTitle(content.getTitle());
        contentResult.setTime(sdf.format(content.getCreateTime()));

        model.addAttribute("content",contentResult);
        return PREFIX + "/dianlidetail.html";
    }


    /**
     * 跳转到专题测试页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/ceshi")
    public String ceshi(Model model, HttpServletRequest request) {
        String title=request.getParameter("title");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("type",6);
        queryWrapper.eq("status",2);
        queryWrapper.orderByDesc("create_time");
        if(StringUtils.isNotBlank(title)){
            queryWrapper.like("title",title);
        }
        List<Content> list=contentService.list(queryWrapper);
        List<ContentResult> relist=new ArrayList<>();
        for (Content content:list){
            ContentResult contentResult =new ContentResult();
            contentResult.setId(content.getId());
            contentResult.setPic(content.getPic());
            contentResult.setReading(content.getReading());
            contentResult.setTitle(content.getTitle());
            contentResult.setTime(sdf.format(content.getCreateTime()));
            relist.add(contentResult);
        }
        model.addAttribute("list",relist);
        return PREFIX + "/zhuanti.html";
    }

    /**
     * 跳转到警告教育详细页面
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    @RequestMapping("/ceshiDetailed/{id}")
    public String ceshiDetailed(@PathVariable("id") String id, Model model) {
        Content content=contentService.getById(id);
        int re=   Integer.parseInt(content.getReading()==null?"0":content.getReading())+1;
        ContentParam contentParam=new ContentParam();
        contentParam.setId(content.getId());
        contentParam.setReading(String.valueOf(re));
        contentService.update(contentParam);
        ContentResult contentResult =new ContentResult();
        contentResult.setContent(content.getContent());
        contentResult.setZtype(content.getZtype());
        contentResult.setTitle(content.getTitle());
        contentResult.setTime(sdf.format(content.getCreateTime()));
        model.addAttribute("content",contentResult);
        return PREFIX + "/zhuantidetail.html";
    }
}


