package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationContentService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


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

    @Autowired
    private UserService userService;



    /**
     * 跳转到协查申请审核列表页
     *
     * @author hujt
     * @Date 2020-09-15
     */
    @RequestMapping("/review")
    public String review(Model model, HttpServletRequest request, HttpSession session) {
        List<Map<String,Object>> allUsers = userService.getAllUsers();
        model.addAttribute("allUsersList",allUsers);
        LoginUser currentUser = LoginContextHolder.getContext().getUser();

        model.addAttribute("currentUser_name",currentUser.getName());
        model.addAttribute("currentUser_id",currentUser.getId());
        return PREFIX + "/investigationContent_review.html";

    }

    /**
     * 协查申请审核列表页获取数据  过滤掉审核不通过的（驳回的） //最高权限
     *
     * @author hujt
     * @Date 2020-09-15
     */
    @ResponseBody
    @RequestMapping("/getinvestigationInfoListByStatus")
    public List<Map<String,Object>> getinvestigationInfoListByStatus(Model model) {

//        List<Map<String, Object>> mapList = investigationContentService.investigationInfoList();
        List<Map<String, Object>> mapList = investigationContentService.getinvestigationInfoListByStatus();
        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMap.containsKey(mapList.get(i).get("info_id").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(mapList.get(i).get("info_id").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("info_Id",key);
            temp.put("infoList",resultMap.get(key));
            list.add(temp);
        }

        return  list;
    }

    /**
     * 跳转到协查申请审核页
     *
     * @author hujt
     * @Date 2020-09-15
     */
    @RequestMapping("/review_edit")
    public String review_edit(Model model, HttpServletRequest request, HttpSession session,InvestigationContentParam investigationContentParam) {
        List<Map<String, Object>> mapList = investigationContentService.getInvestigationInfoByid(investigationContentParam);

        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMap.containsKey(mapList.get(i).get("unit_name").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(mapList.get(i).get("unit_name").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(mapList.get(i).get("unit_name").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("unitName",key);
            temp.put("infoList",resultMap.get(key));
            list.add(temp);
        }
        model.addAttribute("infoList",list);
        return PREFIX + "/investigationContent_review_edit.html";
    }

    /**
     * 协查申请审核
     *
     * @author hujt
     * @Date 2020-09-15
     */
    @RequestMapping("/editInvestigationContent")
    @ResponseBody
    public ResponseData editInvestigationContent(InvestigationContentParam investigationContentParam) {
        investigationContentService.editInvestigationContent(investigationContentParam);
        return ResponseData.success();
    }

    /**
     * 协查申请删除
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteinvestigationInfoById")
    public ResponseData deleteinvestigationInfoById(InvestigationContentParam param) {
        this.investigationContentService.deleteinvestigationInfoById(param.getInfoId());
        return ResponseData.success();
    }

    /**
     * 跳转到主页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("")
    public String index(Model model, HttpServletRequest request, HttpSession session) {
//        List<Map<String, Object>> mapList = investigationContentService.investigationInfoList();

//        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
//        for (int i = 0; i < mapList.size(); i++) {
//            if(resultMap.containsKey(mapList.get(i).get("info_id").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
//                resultMap.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
//            }else{//map中不存在，新建key，用来存放数据
//                List<Map<String,Object>> tmpList = new ArrayList<>();
//                tmpList.add(mapList.get(i));
//                resultMap.put(mapList.get(i).get("info_id").toString(), tmpList);
//            }
//        }
//        Set<String> keySet = resultMap.keySet();
//        List<Map<String,Object>> list = new ArrayList<>();
//        for (String key : keySet) {
//            Map<String, Object> temp = new HashMap<>();
//            temp.put("info_Id",key);
//            temp.put("infoList",resultMap.get(key));
//            list.add(temp);
//        }
//        model.addAttribute("infoList",list);
        List<Map<String,Object>> allUsers = userService.getAllUsers();
        model.addAttribute("allUsersList",allUsers);
        LoginUser currentUser = LoginContextHolder.getContext().getUser();

        model.addAttribute("currentUser_name",currentUser.getName());
        model.addAttribute("currentUser_id",currentUser.getId());
        return PREFIX + "/investigationContent.html";

    }

    /**
     * 协查申请管理明细页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/add")
    public String add(Model model, HttpServletRequest request, HttpSession session,InvestigationContentParam investigationContentParam) throws Exception{
        List<Map<String, Object>> mapList = investigationContentService.getInvestigationInfoByid(investigationContentParam);

        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        Map<String, List<String>> pdfs = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < mapList.size(); i++) {
            Long time1 = sdf.parse(mapList.get(i).get("apply_time").toString()).getTime();
            long time2 = new Date().getTime();

            Long time = (time2-time1)/(24*60*60*1000);
            mapList.get(i).put("days",time);
            String unit_name = mapList.get(i).get("unit_name").toString();
            List<String> pdfNames = pdfs.get(unit_name);
            if(CollectionUtils.isEmpty(pdfNames)){
                pdfNames = new ArrayList<>();
            }
            if(Objects.nonNull(mapList.get(i).get("file_name"))) {
                String file_name = mapList.get(i).get("file_name").toString();
                if (StringUtils.isNotBlank(file_name) && !pdfNames.contains(file_name)) {
                    pdfNames.add(file_name);
                    pdfs.put(unit_name, pdfNames);
                }
            }
            if(resultMap.containsKey(unit_name)){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(unit_name).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(unit_name, tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("unitName",key);
            temp.put("infoList",resultMap.get(key));
            List<String> fileNames = pdfs.get(key);
            if(CollectionUtils.isEmpty(fileNames)){
                fileNames = Lists.newArrayList();
            }
            temp.put("pdfList", fileNames);

            list.add(temp);
        }
        model.addAttribute("infoRemark",mapList.get(0).get("info_remark"));
        model.addAttribute("status",mapList.get(0).get("stauts"));
        model.addAttribute("infoList",list);
        return PREFIX + "/investigationContent_add.html";
    }


    /**
     * 协查申请管理修改  跳到发起协查页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request, HttpSession session,InvestigationContentParam investigationContentParam) {


        List<Map<String, Object>> mapList = investigationContentService.getInvestigationInfoByid(investigationContentParam);

        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        Map<String, List<String>> pdfs = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {

            String unit_name = mapList.get(i).get("unit_name").toString();
            List<String> pdfNames = pdfs.get(unit_name);
            if(CollectionUtils.isEmpty(pdfNames)){
                pdfNames = new ArrayList<>();
            }
            if(Objects.nonNull(mapList.get(i).get("file_name"))) {
                String file_name = mapList.get(i).get("file_name").toString();
                if (StringUtils.isNotBlank(file_name) && !pdfNames.contains(file_name)) {
                    pdfNames.add(file_name);
                    pdfs.put(unit_name, pdfNames);
                }
            }
            if(resultMap.containsKey(unit_name)){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(unit_name).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(unit_name, tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("unitName",key);
            temp.put("infoList",resultMap.get(key));
            List<String> fileNames = pdfs.get(key);
            if(CollectionUtils.isEmpty(fileNames)){
                fileNames = Lists.newArrayList();
            }
            temp.put("pdfList", fileNames);

            list.add(temp);
        }
        model.addAttribute("infoList",list);

        List<Map<String,Object>> allUsers = userService.getAllUsers();
        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        model.addAttribute("currentUser_name",currentUser.getName());
        model.addAttribute("currentUser_id",currentUser.getId());
        model.addAttribute("allUsersList",allUsers);
        return  "/investigationInfo/investigationInfo.html";
    }

    /**
     * 首页我的申请跳过来，获取当前用户提交的协查申请
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("myApply")
    public String myApply(Model model, HttpServletRequest request, HttpSession session) {
        List<Map<String,Object>> allUsers = userService.getAllUsers();
        model.addAttribute("allUsersList",allUsers);
        LoginUser currentUser = LoginContextHolder.getContext().getUser();

        model.addAttribute("currentUser_name",currentUser.getName());
        model.addAttribute("currentUser_id",currentUser.getId());
        return PREFIX + "/investigationContent_myApply.html";

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

    /**
     * 查询列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @ResponseBody
    @RequestMapping("/getlist")
    public List<Map<String,Object>> investigationInfoList() {
        return this.investigationContentService.investigationInfoList();
    }
    /**
     * 协查申请管理列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @ResponseBody
    @RequestMapping("/getinvestigationInfoList")
    public List<Map<String,Object>> getinvestigationInfoList(Model model) {

        List<Map<String, Object>> mapList = investigationContentService.investigationInfoList();
        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMap.containsKey(mapList.get(i).get("info_id").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(mapList.get(i).get("info_id").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("info_Id",key);
            temp.put("infoList",resultMap.get(key));
            list.add(temp);
        }

        return  list;
    }


    /**
     * 获取我的协查申请管理列表（根据当前登陆人）
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @ResponseBody
    @RequestMapping("/getinvestigationInfoListByLoginUser")
    public List<Map<String,Object>> getinvestigationInfoListByLoginUser(Model model) {
        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        List<Map<String, Object>> mapList = investigationContentService.getinvestigationInfoListByLoginUser(currentUser.getName());
        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMap.containsKey(mapList.get(i).get("info_id").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(mapList.get(i).get("info_id").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("info_Id",key);
            temp.put("infoList",resultMap.get(key));
            list.add(temp);
        }

        return  list;
    }

    /**
     * 协查申请管理 页面普通搜索
     * @author hujt
     * @Date 2020-09-09
     */

    @ResponseBody
    @RequestMapping("/getInvestigationInfoListBySearch")
    public List<Map<String,Object>> getInvestigationInfoListBySearch(InvestigationContentParam param) {
//        List<Map<String, Object>> mapList = investigationContentService.investigationInfoList();
        List<Map<String, Object>> mapList = investigationContentService.getInvestigationInfoListBySearch(param.getNameCompany());

        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMap.containsKey(mapList.get(i).get("info_id").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(mapList.get(i).get("info_id").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("info_Id",key);
            temp.put("infoList",resultMap.get(key));
            list.add(temp);
        }
         return  list;
    }
/**
     * 协查申请管理 页面高级搜索
     * @author hujt
     * @Date 2020-09-09
     */

    @ResponseBody
    @RequestMapping("/getInvestigationInfoListByHeighSearch")
    public List<Map<String,Object>> getInvestigationInfoListByHeighSearch(InvestigationContentParam param) {
//        List<Map<String, Object>> mapList = investigationContentService.investigationInfoList();
        List<Map<String, Object>> mapList = investigationContentService.getInvestigationInfoListByHeighSearch(param);

        Map<String, List<Map<String,Object>>> resultMap = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMap.containsKey(mapList.get(i).get("info_id").toString())){//map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMap.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMap.put(mapList.get(i).get("info_id").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMap.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("info_Id",key);
            temp.put("infoList",resultMap.get(key));
            list.add(temp);
        }
         return  list;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "/uploadInvestigationResults")
    public ResponseData uploadInvs(HttpServletRequest request, @RequestPart("file") MultipartFile file) {

        String fileId = this.investigationContentService.uploadFile(request, file);
        HashMap<String, Object> map = new HashMap<>();
        map.put("fileId", fileId);
        return ResponseData.success(0, "上传成功", map);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/downFiles")
    public void downFiles(HttpServletResponse response) {
        this.investigationContentService.downFiles(response);
    }
}


