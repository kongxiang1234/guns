package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.core.dist.ContentMap;
import cn.stylefeng.guns.modular.e365.entity.Management;
import cn.stylefeng.guns.modular.e365.entity.Paper;
import cn.stylefeng.guns.modular.e365.entity.Papermanage;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.model.params.PaperParam;
import cn.stylefeng.guns.modular.e365.service.ManagementService;
import cn.stylefeng.guns.modular.e365.service.PaperService;
import cn.stylefeng.guns.modular.e365.service.PapermanageService;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 控制器
 *
 * @author Alan
 * @Date 2020-04-18 22:10:39
 */
@Controller
@RequestMapping("/paper")
public class PaperController extends BaseController {

    private String PREFIX = "/paper";

    @Autowired
    private PaperService paperService;
    @Autowired
    private ManagementService managementService;


    @Autowired
    private PapermanageService papermanageService;
    /**
     * 跳转到主页面
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/paper.html";
    }

    /**
     * 新增页面
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("/add")
    public String add(Model model) {
        return PREFIX + "/paper_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("/edit")
    public String edit(Model model, @RequestParam("paperId") String paperId,@RequestParam("sszy") String sszy) {
        model.addAttribute("paperId",paperId);
        model.addAttribute("sszy",sszy);
        return PREFIX + "/paper_edit.html";
    }



    /**
     * 新增接口
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("/addItem")
    @BussinessLog(value = "新增试卷", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData addItem(PaperParam paperParam) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String string = simpleDateFormat.format(date);
        paperParam.setSjbh(string);
        paperParam=paperParam(paperParam);
        paperParam.setType("1");
        paperParam.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        paperParam.setCreateTime(new Date());
        this.paperService.add(paperParam);
        return ResponseData.success();
    }
    private PaperParam paperParam(PaperParam paperParam){
        redanxt(paperParam);//批量新增单选题
        reduoxt(paperParam);//批量新增多选题
        repandt(paperParam);//批量新增判断题
        int total=paperParam.getDanxtd()+paperParam.getDanxtg()+paperParam.getDanxtz()+paperParam.getDuoxtd()+
                paperParam.getDuoxtg()+paperParam.getDuoxtz()+paperParam.getPdtd()+paperParam.getPdtg()+
                paperParam.getPdtz();
        int danfenshu=(paperParam.getDanxtd()+paperParam.getDanxtg()+paperParam.getDanxtz())*paperParam.getDanxtValue();//单选分数
        int duofenshu=(paperParam.getDuoxtd()+paperParam.getDuoxtg()+paperParam.getDuoxtz())*paperParam.getDuoxtValue();//多选分数
        int pdtfenshu=(paperParam.getPdtd()+paperParam.getPdtg()+paperParam.getPdtz())*paperParam.getPdtValue();//判断分数
        paperParam.setTmtotle(total);
        paperParam.setTmfs(danfenshu+duofenshu+pdtfenshu);
        return paperParam;
    }

    private void repandt(PaperParam paperParam) {
        QueryWrapper queryWrapper=new QueryWrapper();//难度高
        queryWrapper.eq("specialty",paperParam.getSszy());
        queryWrapper.eq("type","1");
        queryWrapper.eq("questions_type","3");
        queryWrapper.last("ORDER BY RAND()  limit "+paperParam.getPdtg()+"");
        List<Management> nanGlist=managementService.list(queryWrapper);
        List<Papermanage> papermanageList=new ArrayList<>();
        if (nanGlist.size()!=0){
            for (Management management:nanGlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        QueryWrapper queryWrapperz=new QueryWrapper();//难度中
        queryWrapperz.eq("specialty",paperParam.getSszy());
        queryWrapperz.eq("type","2");
        queryWrapperz.eq("questions_type","3");
        queryWrapperz.last("ORDER BY RAND()  limit "+paperParam.getPdtz()+"");
        List<Management> nanZlist=managementService.list(queryWrapperz);
        if (nanZlist.size()!=0){
            for (Management management:nanZlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        QueryWrapper queryWrapperd=new QueryWrapper();//难度低
        queryWrapperd.eq("specialty",paperParam.getSszy());
        queryWrapperd.eq("type","3");
        queryWrapperd.eq("questions_type","3");
        queryWrapperd.last("ORDER BY RAND()  limit "+paperParam.getPdtd()+"");
        List<Management> nanDlist=managementService.list(queryWrapperd);

        if (nanDlist.size()!=0){
            for (Management management:nanDlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        papermanageService.saveBatch(papermanageList);
    }

    private void reduoxt(PaperParam paperParam) {
        QueryWrapper queryWrapper=new QueryWrapper();//难度高
        queryWrapper.eq("specialty",paperParam.getSszy());
        queryWrapper.eq("type","1");
        queryWrapper.eq("questions_type","2");
        queryWrapper.last("ORDER BY RAND()  limit "+paperParam.getDuoxtg()+"");
        List<Management> nanGlist=managementService.list(queryWrapper);
        List<Papermanage> papermanageList=new ArrayList<>();
        if (nanGlist.size()!=0){
            for (Management management:nanGlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        QueryWrapper queryWrapperz=new QueryWrapper();//难度中
        queryWrapperz.eq("specialty",paperParam.getSszy());
        queryWrapperz.eq("type","2");
        queryWrapperz.eq("questions_type","2");
        queryWrapperz.last("ORDER BY RAND()  limit "+paperParam.getDuoxtz()+"");
        List<Management> nanZlist=managementService.list(queryWrapperz);
        if (nanZlist.size()!=0){
            for (Management management:nanZlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        QueryWrapper queryWrapperd=new QueryWrapper();//难度低
        queryWrapperd.eq("specialty",paperParam.getSszy());
        queryWrapperd.eq("type","3");
        queryWrapperd.eq("questions_type","2");
        queryWrapperd.last("ORDER BY RAND()  limit "+paperParam.getDuoxtd()+"");
        List<Management> nanDlist=managementService.list(queryWrapperd);

        if (nanDlist.size()!=0){
            for (Management management:nanDlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        papermanageService.saveBatch(papermanageList);
    }

    private void redanxt(PaperParam paperParam) {
        QueryWrapper queryWrapper=new QueryWrapper();//难度高
        queryWrapper.eq("specialty",paperParam.getSszy());
        queryWrapper.eq("type","1");
        queryWrapper.eq("questions_type","1");
        queryWrapper.last("ORDER BY RAND()  limit "+paperParam.getDanxtg()+"");
        List<Management> nanGlist=managementService.list(queryWrapper);
        List<Papermanage> papermanageList=new ArrayList<>();
        if (nanGlist.size()!=0){
            for (Management management:nanGlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        QueryWrapper queryWrapperz=new QueryWrapper();//难度中
        queryWrapperz.eq("specialty",paperParam.getSszy());
        queryWrapperz.eq("type","2");
        queryWrapperz.eq("questions_type","1");
        queryWrapperz.last("ORDER BY RAND()  limit "+paperParam.getDanxtz()+"");
        List<Management> nanZlist=managementService.list(queryWrapperz);
        if (nanZlist.size()!=0){
            for (Management management:nanZlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        QueryWrapper queryWrapperd=new QueryWrapper();//难度低
        queryWrapperd.eq("specialty",paperParam.getSszy());
        queryWrapperd.eq("type","3");
        queryWrapperd.eq("questions_type","1");
        queryWrapperd.last("ORDER BY RAND()  limit "+paperParam.getDanxtd()+"");
        List<Management> nanDlist=managementService.list(queryWrapperd);

        if (nanDlist.size()!=0){
            for (Management management:nanDlist){
                Papermanage papermanage=new Papermanage();
                papermanage.setManagementId(management.getId());
                papermanage.setPaperNuber(paperParam.getSjbh());
                papermanageList.add(papermanage);
            }
        }
        papermanageService.saveBatch(papermanageList);
    }

    /**
     * 编辑接口
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("/editItem")
    @BussinessLog(value = "编辑试卷", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData editItem(PaperParam paperParam) {
        paperParam=paperParam(paperParam);
        this.paperService.update(paperParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除试卷", key = "content", dict = ContentMap.class)
    @ResponseBody
    public ResponseData delete(PaperParam paperParam) {
        this.paperService.delete(paperParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(PaperParam paperParam) {
        Paper detail = this.paperService.getById(paperParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Alan
     * @Date 2020-04-18
     */
    @ResponseBody
    @BussinessLog(value = "查询试卷", key = "content", dict = ContentMap.class)
    @RequestMapping("/list")
    public LayuiPageInfo list(@RequestParam(required = false) String time,
                              @RequestParam(required = false) String sjbh,
                              @RequestParam(required = false) String specialty,@RequestParam(required = false) String type) {
        //拼接查询条件
        String beginTime = "";
        String endTime = "";
        if (ToolUtil.isNotEmpty(time)) {
            String[] split = time.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }
        return this.paperService.findPageBySpec(beginTime,endTime,sjbh,specialty,type);
    }



    /**
     * 查询所有
     *
     * @author stylefeng
     * @Date 2019-03-13
     */
    @ResponseBody
    @RequestMapping("/listdanxtg")
    public LayuiPageInfo listdanxtg(ManagementParam managementParam) {
        return this.paperService.listdanxtg(managementParam);
    }
}


