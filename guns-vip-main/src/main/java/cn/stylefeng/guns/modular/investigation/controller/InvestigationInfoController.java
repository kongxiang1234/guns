package cn.stylefeng.guns.modular.investigation.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationObjectParam;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationUnitParam;
import cn.stylefeng.guns.modular.investigation.service.InvestigationInfoService;
import cn.stylefeng.guns.modular.investigation.service.InvestigationObjectService;
import cn.stylefeng.guns.modular.investigation.service.InvestigationUnitService;
import cn.stylefeng.guns.sys.core.util.DefaultImages;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 控制器
 *
 * @author hujt
 * @Date 2020-09-09 23:06:15
 */
@Controller
@RequestMapping("/investigationInfo")
public class InvestigationInfoController extends BaseController {

    private String PREFIX = "/investigationInfo";

    @Autowired
    private InvestigationInfoService investigationInfoService;

    @Autowired
    private InvestigationObjectService investigationObjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private InvestigationUnitService investigationUnitService;
    /**
     * 跳转到主页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("")
    public String index(Model model, HttpServletRequest request, HttpSession session) {
        List<Map<String,Object>> allUsers = userService.getAllUsers();
        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        model.addAttribute("currentUser_name",currentUser.getName());
        model.addAttribute("currentUser_id",currentUser.getId());
        currentUser.getDeptId();
        String Documents_number= "宿监协字【2020】"+currentUser.getDeptId()+"0001";
        model.addAttribute("Documents_number",Documents_number);

        model.addAttribute("avatar", DefaultImages.defaultAvatarUrl());
        model.addAttribute("allUsersList",allUsers);
        return PREFIX + "/investigationInfo.html";
    }
    /**
     * 新增页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/investigationInfo_add.html";
    }

    /**
     * 编辑页面
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/investigationInfo_edit.html";
    }

    /**
     * 新增接口
     *
     * @author hujt
     * @Date 2020-09-09
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(InvestigationInfoParam investigationInfoParam,HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        investigationInfoParam.setStauts("2");

        long serialVersionUID = System.currentTimeMillis();

        String excelData2 = request.getParameter("excelData");
        System.out.println(excelData2);


        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        investigationInfoParam.setInfoId(serialVersionUID);
        investigationInfoParam.setUserId(currentUser.getUsername());
        investigationInfoParam.setApplyTime(new java.sql.Date(System.currentTimeMillis()));

        this.investigationInfoService.add(investigationInfoParam);

        JSONArray excelData = JSON.parseArray(excelData2);
        List<List<InvestigationObjectParam>> unitObjList = new ArrayList<>();
        for (int i = 0; i <excelData.size() ; i++) {
            List<InvestigationObjectParam> testlist =  JSON.parseArray(excelData.get(i).toString(), InvestigationObjectParam.class);
            unitObjList.add(testlist);
        }
        for (int i = 0; i <unitObjList.size() ; i++) {
            for (int j = 0; j <unitObjList.get(i).size() ; j++) {
                unitObjList.get(i).get(j).setInfoId(String.valueOf(serialVersionUID));
                unitObjList.get(i).get(j).setObjectNotice(String.valueOf(serialVersionUID));
                unitObjList.get(i).get(j).setEmployeeCard(String.valueOf(serialVersionUID));
                unitObjList.get(i).get(j).setEmployeeCardSelect(String.valueOf(serialVersionUID));
                unitObjList.get(i).get(j).setCreateTime(new java.sql.Date(System.currentTimeMillis()));
                unitObjList.get(i).get(j).setUpdateTime(new java.sql.Date(System.currentTimeMillis()));
                unitObjList.get(i).get(j).setUpdateBy(currentUser.getUsername());
                this.investigationObjectService.add(unitObjList.get(i).get(j));
            }
        }
        System.err.println("id:"+investigationInfoParam.getInfoId());
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
    public ResponseData editItem(InvestigationInfoParam investigationInfoParam) {
        this.investigationInfoService.update(investigationInfoParam);
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
    public ResponseData delete(InvestigationInfoParam investigationInfoParam) {
        this.investigationInfoService.delete(investigationInfoParam);
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
    public ResponseData detail(InvestigationInfoParam investigationInfoParam) {
        InvestigationInfo detail = this.investigationInfoService.getById(investigationInfoParam.getInfoId());
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
    public LayuiPageInfo list(InvestigationInfoParam investigationInfoParam) {
        return this.investigationInfoService.findPageBySpec(investigationInfoParam);
    }

    /**
     * 获取上传成功的数据
     */
    @RequestMapping("/getUploadData")
    @ResponseBody
    public ResponseData getUploadData(Model model, HttpServletRequest request, HttpSession session) {
        String name = (String) request.getSession().getAttribute("upFile");
        String fileSavePath = ConstantsContext.getFileUploadPath();
        if (name != null) {
            File file = new File(fileSavePath + name);
            try {
                ImportParams params = new ImportParams();
                params.setTitleRows(1);
                params.setHeadRows(1);
                Workbook  wb = readExcel(file,name.split("\\.")[1]); //文件
                int sheetNumbr = wb.getNumberOfSheets();
                List<Map<String,List<Map<String,Object>>>> resList = new ArrayList<>();

                for (int j = 0; j <sheetNumbr ; j++) {
                    Map<String,List<Map<String,Object>>> unitAllMap = new HashMap<>();
                    Sheet sheet = wb.getSheetAt(j);
                    String unitName = sheet.getRow(0).getCell(0).toString();
                    InvestigationUnitParam param = new InvestigationUnitParam();
                    param.setUnitName(unitName.trim());
                    Map<String, Object> investigationUnit = investigationUnitService.findInvestigationUnitById(param);

                    if (null==investigationUnit || investigationUnit.isEmpty()){
                        return ResponseData.error("excel的第个"+(j+1)+"sheet中未知的单位："+unitName);
                    }

                    List<Map<String,Object>> unitList = new ArrayList<>();
                    for (int i = 2; i <=sheet.getLastRowNum() ; i++) {
                        Row row = sheet.getRow(i);
                        Map<String,Object> unitMap = new HashMap<>();

                        unitMap.put("nameCompany",row.getCell(1).toString());
                        unitMap.put("cardNumber",row.getCell(2).toString());
                        unitMap.put("remarks",row.getCell(3).toString());
                        unitMap.put("unitId",investigationUnit.get("unit_id"));
                        unitList.add(unitMap);
                    }
                    unitAllMap.put(unitName,unitList);
                    resList.add(unitAllMap);
                }
//                List<InvestigationInfoDto> result = ExcelImportUtil.importExcel(file, InvestigationInfoDto.class, params);
                ResponseData responseData=new ResponseData();
                if(resList.size()==0){
                    return ResponseData.error("没有数据导入");
                }
//                model.addAttribute("excelResultData",result);
                return ResponseData.success(resList);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseData.error("导入失败");
            }
        }
        return SUCCESS_TIP;
    }

    public static Workbook readExcel(File file, String Suffix){
        Workbook wb = null;
        if(file==null){
            return null;
        }
        InputStream is =  null;
        try {
            is = new FileInputStream(file);
            if("xls".equals(Suffix)){
                return wb = new HSSFWorkbook(is);
            }else if("xlsx".equals(Suffix)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

}


