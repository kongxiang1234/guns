package cn.stylefeng.guns.modular.investigation.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationObject;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationObjectParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationObjectService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 控制器
 *
 * @author hujt
 * @Date 2020-09-12 10:09:34
 */
@Controller
@RequestMapping("/investigationObject")
public class InvestigationObjectController extends BaseController {

    private String PREFIX = "/investigationObject";

    @Autowired
    private InvestigationObjectService investigationObjectService;

    /**
     * 跳转到主页面
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("")
    public String index() {

        return PREFIX + "/investigationObject.html";
    }

    /**
     * 跳转到协查受理主页面
     * @author hujt
     * @Date 2020-09-12
     */

    @RequestMapping("/investigationObjectListByCompId")
    @ResponseBody
    public List<Map<String,Object>> investigationObjectListByCompId(InvestigationObjectParam param) {
        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        List<Map<String, Object>> mapList = investigationObjectService.investigationObjectListByCompId(currentUser.getDeptId().toString());

        Map<String, List<Map<String,Object>>> resultMapTemp = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            if(resultMapTemp.containsKey(mapList.get(i).get("info_id").toString())){ //map中异常批次已存在，将该数据存放到同一个key（key存放的是异常批次）的map中
                resultMapTemp.get(mapList.get(i).get("info_id").toString()).add(mapList.get(i));
            }else{//map中不存在，新建key，用来存放数据
                List<Map<String,Object>> tmpList = new ArrayList<>();
                tmpList.add(mapList.get(i));
                resultMapTemp.put(mapList.get(i).get("info_id").toString(), tmpList);
            }
        }
        Set<String> keySet = resultMapTemp.keySet();
        List<Map<String,Object>> rstlist = new ArrayList<>();
        for (String key : keySet) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("info_id",key);
            temp.put("infoList",resultMapTemp.get(key));
            rstlist.add(temp);
        }
        return  rstlist;
    }

    /**
     * 新增页面
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("/add")
    public String add(Model model, InvestigationObjectParam param) {
        Map<String,Object> map = new HashMap<>();
        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        map.put("unitId",currentUser.getDeptId());
        map.put("infoId",param.getInfoId());
        List<Map<String, Object>> mapList = investigationObjectService.getInvestigationObjectInfoByid(map);

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
        Map<String, Object> temp = new HashMap<>();
        for (String key : keySet) {
            temp.put("unitName",key);
            temp.put("infoList",resultMap.get(key));
        }
        model.addAttribute("invesMap",temp);

        return PREFIX + "/investigationObject_add.html";

    }

    /**
     * 编辑页面
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/investigationObject_edit.html";
    }

    /**
     * 新增接口
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InvestigationObjectParam investigationObjectParam) {
        this.investigationObjectService.add(investigationObjectParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(InvestigationObjectParam investigationObjectParam) {
        this.investigationObjectService.update(investigationObjectParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(InvestigationObjectParam investigationObjectParam) {
        this.investigationObjectService.delete(investigationObjectParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(InvestigationObjectParam investigationObjectParam) {
        InvestigationObject detail = this.investigationObjectService.getById(investigationObjectParam.getObjectId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author hujt
     * @Date 2020-09-12
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(InvestigationObjectParam investigationObjectParam) {
        return this.investigationObjectService.findPageBySpec(investigationObjectParam);
    }

}


