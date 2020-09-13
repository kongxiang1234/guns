package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationContentService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        return PREFIX + "/investigationContent.html";

    }

    /**
     * 新增页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/add")
    public String add(Model model, HttpServletRequest request, HttpSession session,InvestigationContentParam investigationContentParam) {
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
     * 查询列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @ResponseBody
    @RequestMapping("/getinvestigationInfoList")
    public List<Map<String,Object>> getinvestigationInfoList() {
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

}


