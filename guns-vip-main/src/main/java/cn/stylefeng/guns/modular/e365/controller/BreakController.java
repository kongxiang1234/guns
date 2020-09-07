package cn.stylefeng.guns.modular.e365.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Break;
import cn.stylefeng.guns.modular.e365.model.params.BreakParam;
import cn.stylefeng.guns.modular.e365.service.BreakService;
import cn.stylefeng.guns.modular.e365.util.CheckObjectIsNullUtils;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.rest.entity.RestFileInfo;
import cn.stylefeng.guns.sys.modular.rest.service.RestFileInfoService;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.Position;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;


/**
 * 控制器
 *
 * @author Alan-孔
 * @Date 2020-04-23 22:25:02
 */
@Slf4j
@Controller
@RequestMapping("/break")
public class BreakController extends BaseController {

    private String PREFIX = "/break";

    @Autowired
    private BreakService breakService;
    @Autowired
    private RestFileInfoService restFileInfoService;
    /**
     * 跳转到主页面
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/break.html";
    }

    @RequestMapping("breakTotal")
    public String breakTotal() {
        return PREFIX + "/breakTotal.html";
    }

    @RequestMapping("breakDan")
    public String breakDan(Model model) {
        model.addAttribute("wzUnit",getPara("wzUnit"));
        return PREFIX + "/breakDan.html";
    }
    /**
     * 新增页面
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/break_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/break_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/addItem")
    @BussinessLog(value = "新增违章列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addItem(BreakParam breakParam) {
        breakParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        breakParam.setCreateTime(new Date());
        this.breakService.add(breakParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/editItem")
    @BussinessLog(value = "编辑违章列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData editItem(BreakParam breakParam) {
        breakParam.setUpdateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        breakParam.setUpdateTime(new Date());
        this.breakService.update(breakParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除违章列表", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData delete(BreakParam breakParam) {
        this.breakService.delete(breakParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(BreakParam breakParam) {
        Break detail = this.breakService.getById(breakParam.getId());
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
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @ResponseBody
    @RequestMapping("/list")
    @BussinessLog(value = "查询违章列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo list(@RequestParam(required = false) String dcTime,
                              @RequestParam(required = false) String dcType,
                              @RequestParam(required = false) String wzTitle,@RequestParam(required = false) String wzType,@RequestParam(required = false) String wzStatus) {
        //拼接查询条件
        String beginTime = "";
        String endTime = "";
        if (ToolUtil.isNotEmpty(dcTime)) {
            String[] split = dcTime.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }

        return this.breakService.findPageBySpec(beginTime,endTime,dcType,wzTitle,wzType,wzStatus);
    }

    /**
     * 查询列表
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @ResponseBody
    @RequestMapping("/listTotal")
    @BussinessLog(value = "查询违章列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo listTotal(@RequestParam(required = false) String time,
                              @RequestParam(required = false) String wzUnit
                            ) {
        //拼接查询条件
        String beginTime = "";
        String endTime = "";
        if (ToolUtil.isNotEmpty(time)) {
            String[] split = time.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }

        return this.breakService.findPageListTotal(beginTime,endTime,wzUnit);
    }
    /**
     * 查询列表
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    @ResponseBody
    @RequestMapping("/listDan")
    @BussinessLog(value = "查询违章列表", key = "content", dict = ContentMap.class)
    public LayuiPageInfo listDan(
                                   @RequestParam(required = false) String wzUnit,
                                   @RequestParam(required = false) String wzPeople
    ) {

        QueryWrapper<Break> queryWrapper=new QueryWrapper();
        queryWrapper.eq("xm_unit",wzUnit);
        if(StringUtils.isNotBlank(wzPeople)){
            queryWrapper.eq("wz_people",wzPeople);
        }

        Page<Break> page=this.breakService.page(LayuiPageFactory.defaultPage(),queryWrapper);
        return LayuiPageFactory.createPageInfo(page);
    }
    /**
     * 上传excel填报
     */
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public ResponseData uploadExcel(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String name = file.getOriginalFilename();
        if(!name.endsWith(".xlsx") && !name.endsWith(".xls") ){
            return ResponseData.error("上传失败,文件格式是.xlsx/.xls");
        }
        request.getSession().setAttribute("upFile", name);
        String fileSavePath = ConstantsContext.getFileUploadPath();
        try {
            file.transferTo(new File(fileSavePath + name));
        } catch (Exception e) {
            log.error("上传那文件出错！", e);
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", IdWorker.getIdStr());
        return ResponseData.success(0, "上传成功", map);
    }
    /**
     * 获取上传成功的数据
     */
    @RequestMapping("/getUploadData")
    @ResponseBody
    public ResponseData getUploadData(HttpServletRequest request) {
        String name = (String) request.getSession().getAttribute("upFile");
        String fileSavePath = ConstantsContext.getFileUploadPath();
        if (name != null) {
            File file = new File(fileSavePath + name);
            try {
                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                List<BreakParam> result = ExcelImportUtil.importExcel(file, BreakParam.class, params);
                List<BreakParam> results =new ArrayList<>();
                for (BreakParam breakParam:result){
                    if(!CheckObjectIsNullUtils.isAllFieldNull(breakParam)){
                        breakParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
                        breakParam.setCreateTime(new Date());
                        results.add(breakParam);
                    }
                }
                ResponseData responseData=new ResponseData();
                if(result.size()!=0){
                    responseData=  insertUser(results);
                }else {
                    return ResponseData.error("没有数据导入");
                }
                if(responseData.getSuccess()){
                    return ResponseData.success();
                }else {
                    return responseData;
                }
            } catch (Exception e) {

                e.printStackTrace();
                return ResponseData.error("导入失败,请联系管理员");
            }
        }
        return SUCCESS_TIP;
    }
    private ResponseData insertUser(List<BreakParam> result) {
        for (BreakParam breakParam:result){
           this.breakService.add(breakParam);
        }
        return ResponseData.success();
    }
}


