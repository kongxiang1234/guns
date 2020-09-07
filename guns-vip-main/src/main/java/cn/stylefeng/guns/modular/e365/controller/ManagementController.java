package cn.stylefeng.guns.modular.e365.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.stylefeng.guns.base.auth.annotion.Permission;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Answer;
import cn.stylefeng.guns.modular.e365.entity.Management;
import cn.stylefeng.guns.modular.e365.model.params.AnswerParam;
import cn.stylefeng.guns.modular.e365.model.params.BreakParam;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.service.AnswerService;
import cn.stylefeng.guns.modular.e365.service.ManagementService;
import cn.stylefeng.guns.modular.e365.util.CheckObjectIsNullUtils;
import cn.stylefeng.guns.sys.core.constant.Const;
import cn.stylefeng.guns.sys.core.constant.dictmap.NoticeMap;
import cn.stylefeng.guns.sys.core.constant.dictmap.UserDict;
import cn.stylefeng.guns.sys.core.constant.state.ManagerStatus;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.rest.entity.RestDictType;
import cn.stylefeng.guns.sys.modular.system.entity.Dict;
import cn.stylefeng.guns.sys.modular.system.service.DictService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;


/**
 * 控制器
 *
 * @author Alan _孔
 * @Date 2020-04-17 10:18:13
 */
@Controller
@RequestMapping("/management")
public class ManagementController extends BaseController {

    private String PREFIX = "/management";
    @Autowired
    private DictService dictService;
    @Autowired
    private ManagementService managementService;

    @Autowired
    private AnswerService answerService;

    /**
     * 跳转到主页面
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/management.html";
    }

    /**
     * 新增页面
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/management_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam("Id") Long Id, Model model) {
        model.addAttribute("Id",Id);
        return PREFIX + "/management_edit.html";
    }
    /**
     * 编辑答案单选题页面
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/setDaan")
    public String setDaan(@RequestParam("Id") Long Id, Model model) {
        Management detail = this.managementService.getById(Id);
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.eq("managementId", detail.getId());
        Answer answer=answerService.getOne(wrapper);
        List<Dict> dicts = this.dictService.listDictsByName("难易");
        for (Dict dict:dicts){
            if(detail.getType().equals(dict.getCode())){
                detail.setType(dict.getName());
            }
        }
        List<Dict> dicts1 = this.dictService.listDictsByName("所属");
        for (Dict dict:dicts1){
            if(detail.getSpecialty().equals(dict.getCode())){
                detail.setSpecialty(dict.getName());
            }
        }
        List<Dict> dicts2 = this.dictService.listDictsByName("试题");
        for (Dict dict:dicts2){
            if(detail.getQuestionsType().equals(dict.getCode())){
                detail.setQuestionsType(dict.getName());
            }
        }
        model.addAttribute("managementId",Id);
        model.addAttribute("detail",detail);
        model.addAttribute("answer",answer);
        return PREFIX + "/management_setDaan.html";
    }

    /**
     * 编辑答案多选题页面
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/setDaanDuo")
    public String setDaanDuo(@RequestParam("Id") Long Id, Model model) {
        Management detail = this.managementService.getById(Id);
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.eq("managementId", detail.getId());
        Answer answer=answerService.getOne(wrapper);
        List<Dict> dicts = this.dictService.listDictsByName("难易");
        for (Dict dict:dicts){
            if(detail.getType().equals(dict.getCode())){
                detail.setType(dict.getName());
            }
        }
        List<Dict> dicts1 = this.dictService.listDictsByName("所属");
        for (Dict dict:dicts1){
            if(detail.getSpecialty().equals(dict.getCode())){
                detail.setSpecialty(dict.getName());
            }
        }
        List<Dict> dicts2 = this.dictService.listDictsByName("试题");
        for (Dict dict:dicts2){
            if(detail.getQuestionsType().equals(dict.getCode())){
                detail.setQuestionsType(dict.getName());
            }
        }
        model.addAttribute("managementId",Id);
        model.addAttribute("detail",detail);
        model.addAttribute("answer",answer);
        return PREFIX + "/management_setDaanDuo.html";
    }
    /**
     * 编辑答案判断题页面
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/setDaanPan")
    public String setDaanPan(@RequestParam("Id") Long Id, Model model) {
        Management detail = this.managementService.getById(Id);
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.eq("managementId", detail.getId());
        Answer answer=answerService.getOne(wrapper);
        List<Dict> dicts = this.dictService.listDictsByName("难易");
        for (Dict dict:dicts){
            if(detail.getType().equals(dict.getCode())){
                detail.setType(dict.getName());
            }
        }
        List<Dict> dicts1 = this.dictService.listDictsByName("所属");
        for (Dict dict:dicts1){
            if(detail.getSpecialty().equals(dict.getCode())){
                detail.setSpecialty(dict.getName());
            }
        }
        List<Dict> dicts2 = this.dictService.listDictsByName("试题");
        for (Dict dict:dicts2){
            if(detail.getQuestionsType().equals(dict.getCode())){
                detail.setQuestionsType(dict.getName());
            }
        }
        model.addAttribute("managementId",Id);
        model.addAttribute("detail",detail);
        model.addAttribute("answer",answer);
        return PREFIX + "/management_setDaanPan.html";
    }

    /**
     * 新增接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/addItem")
    @BussinessLog(value = "新增试题", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addItem(ManagementParam managementParam) {
        this.managementService.add(managementParam);
        return ResponseData.success();
    }

    /**
     * 新增接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/addAnswer")
    @BussinessLog(value = "新增答案", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addAnswer(AnswerParam answerParam) {

        answerParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        answerParam.setCreateTime(new Date());
        this.answerService.add(answerParam);
        return ResponseData.success();
    }
    /**
     * 新增接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/updateAnswer")
    @BussinessLog(value = "编辑答案", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData updateAnswer(AnswerParam answerParam) {
        this.answerService.update(answerParam);
        return ResponseData.success();
    }
    /**
     * 编辑接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/editItem")
    @BussinessLog(value = "编辑试题", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData editItem(ManagementParam managementParam) {
        this.managementService.update(managementParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除试题", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData delete(ManagementParam managementParam) {
        this.managementService.delete(managementParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(ManagementParam managementParam) {
        Management detail = this.managementService.getById(managementParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查看专业有没有接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/detailManagement")
    @ResponseBody
    public ResponseData detailManagement(ManagementParam managementParam) {
       /* QueryWrapper<Management> wrapper = new QueryWrapper<>();
        wrapper.eq("specialty", managementParam.getSpecialty());
        Map detail =managementService.getMap(wrapper);*/
       Map detail = this.managementService.detailManagement(managementParam.getSpecialty());
        return ResponseData.success(detail);
    }

    /**
     * 查看题数接口
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @RequestMapping("/detailManagementQuery")
    @ResponseBody
    public LayuiPageInfo detailManagementQuery(ManagementParam managementParam) {
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        QueryWrapper<Management> wrapper = new QueryWrapper<>();
        wrapper.eq("questions_type", managementParam.getQuestionsType());
        wrapper.eq("specialty", managementParam.getSpecialty());
        wrapper.eq("type", managementParam.getType());
        List<Management> management=managementService.list(wrapper);
        layuiPageInfo.setData(management);
        return layuiPageInfo;
    }

    /**
     * 查询列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    @ResponseBody
    @RequestMapping("/list")
    @BussinessLog(value = "查询试题", key = "content", dict = ContentMap.class)
    public LayuiPageInfo list(ManagementParam managementParam) {
        return this.managementService.findPageBySpec(managementParam);
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
                List<ManagementParam> result = ExcelImportUtil.importExcel(file, ManagementParam.class, params);
                List<ManagementParam> results =new ArrayList<>();
                for (ManagementParam breakParam:result){
                    if(!CheckObjectIsNullUtils.isAllFieldNull(breakParam)){
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
            }
        }
        return SUCCESS_TIP;
    }
    private ResponseData insertUser(List<ManagementParam> result) {
        for (ManagementParam breakParam:result){
                List<Dict> dicts = this.dictService.listDictsByName("专题");
                for (Dict dict:dicts){
                    if(dict.getName().equals(breakParam.getZtype())){
                        breakParam.setZtype(dict.getCode());
                    }

                }
                String[] string = breakParam.getOption().split("/");
                List<String> stringList= Arrays.asList(string);

                QueryWrapper<Management> wrapper = new QueryWrapper<>();
                wrapper.eq("number", breakParam.getNumber());
                Management management=this.managementService.getOne(wrapper);
                if(management!=null){
                    return ResponseData.error("导入数据试题编号不能相同");
                }
                if("单选题".equals(breakParam.getQuestionsType())){
                    breakParam.setQuestionsType("1");
                    breakParam.setAsubjectSingle(stringList.get(0));
                    breakParam.setBsubjectSingle(stringList.get(1));
                    breakParam.setCsubjectSingle(stringList.get(2));
                    breakParam.setDsubjectSingle(stringList.get(3));
                    breakParam.setSingle(breakParam.getSingle());
                }else if("多选题".equals(breakParam.getQuestionsType())){
                    breakParam.setQuestionsType("2");
                    breakParam.setAsubjectMultiple(stringList.get(0));
                    breakParam.setBsubjectMultiple(stringList.get(1));
                    breakParam.setCsubjectMultiple(stringList.get(2));
                    breakParam.setDsubjectMultiple(stringList.get(3));
                    breakParam.setMultiple(breakParam.getSingle());
                }else if("判断题".equals(breakParam.getQuestionsType())){
                    breakParam.setFlag(breakParam.getSingle());
                    breakParam.setQuestionsType("3");
                }else {
                    return ResponseData.error("导入数据试题类型不识别请检查");
                }
                if("高".equals(breakParam.getType())){
                    breakParam.setType("1");
                }else if("中".equals(breakParam.getType())){
                    breakParam.setType("2");
                }else if("低".equals(breakParam.getType())){
                    breakParam.setType("3");
                }else {
                    return ResponseData.error("导入数据督查类型不识别请检查");
                }
                breakParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
                breakParam.setCreateTime(new Date());
                breakParam.setStatus("ENABLE");
                breakParam.setTitle("<p>"+breakParam.getTitle()+"</p>");
                long manId= this.managementService.add(breakParam);
                Answer answer=new Answer();
                answer.setManagementId(manId);
                if("1".equals(breakParam.getQuestionsType())){
                    if(StringUtils.isNotBlank(breakParam.getAsubjectSingle())){
                        answer.setAsubjectSingle(breakParam.getAsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getBsubjectSingle())){
                        answer.setBsubjectSingle(breakParam.getBsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getCsubjectSingle())){
                        answer.setCsubjectSingle(breakParam.getCsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getDsubjectSingle())){
                        answer.setDsubjectSingle(breakParam.getDsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getSingle())){
                        answer.setSingle(breakParam.getSingle());
                    }

                }else if("2".equals(breakParam.getQuestionsType())){
                    if(StringUtils.isNotBlank(breakParam.getAsubjectSingle())){
                        answer.setAsubjectMultiple(breakParam.getAsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getBsubjectSingle())){
                        answer.setBsubjectMultiple(breakParam.getBsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getCsubjectSingle())){
                        answer.setCsubjectMultiple(breakParam.getCsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getDsubjectSingle())){
                        answer.setDsubjectMultiple(breakParam.getDsubjectSingle());
                    }
                    if(StringUtils.isNotBlank(breakParam.getMultiple())){
                        answer.setMultiple(breakParam.getMultiple());
                    }
                }else if("3".equals(breakParam.getQuestionsType())){
                    if(StringUtils.isNotBlank(breakParam.getFlag())){
                        answer.setFlag(breakParam.getFlag());
                    }
                }
            answer.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
            answer.setCreateTime(new Date());
               answerService.save(answer);
        }
        return ResponseData.success();
    }

}


