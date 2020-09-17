package cn.stylefeng.guns.sys.modular.system.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.AuditUser;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.params.AuditUserParam;
import cn.stylefeng.guns.sys.modular.system.service.AuditUserService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * 控制器
 *
 * @author Alan
 * @Date 2020-09-17 22:58:51
 */
@Controller
@RequestMapping("/auditUser")
public class AuditUserController extends BaseController {

    private String PREFIX = "/modular/system/auditUser";

    @Autowired
    private AuditUserService auditUserService;
    @Autowired
    private UserService userService;
    /**
     * 跳转到主页面
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/auditUser.html";
    }

    /**
     * 新增页面
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("/add")
    public String add() {
        return PREFIX + "/auditUser_add.html";
    }

    /**
     * 编辑页面
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("/edit")
    public String edit() {
        return PREFIX + "/auditUser_edit.html";
    }

    /**
     * 新增接口
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public ResponseData addItem(AuditUserParam auditUserParam) {
        this.auditUserService.add(auditUserParam);
        return ResponseData.success();
    }

    /**
     * 编辑接口
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("/editItem")
    @ResponseBody
    public ResponseData editItem(AuditUserParam auditUserParam) {
        String account = LoginContextHolder.getContext().getUser().getName();
        auditUserParam.setAuditBy(account);
        auditUserParam.setAuditTime(new Date());
        if("1".equals(auditUserParam.getTypes())){
            auditUserParam.setAuditStatus("审核通过");
            User user = userService.getById(auditUserParam.getUserId());
            user.setStatus("ENABLE");
            userService.updateById(user);
        }else {
            auditUserParam.setAuditStatus("审核不通过");
        }
        this.auditUserService.update(auditUserParam);
        return ResponseData.success();
    }

    /**
     * 删除接口
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData delete(AuditUserParam auditUserParam) {
        this.auditUserService.delete(auditUserParam);
        return ResponseData.success();
    }

    /**
     * 查看详情接口
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @RequestMapping("/detail")
    @ResponseBody
    public ResponseData detail(AuditUserParam auditUserParam) {
        AuditUser detail = this.auditUserService.getByIdAuditUser(auditUserParam.getId());
        return ResponseData.success(detail);
    }

    /**
     * 查询列表
     *
     * @author Alan
     * @Date 2020-09-17
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiPageInfo list(AuditUserParam auditUserParam) {
        return this.auditUserService.findPageBySpec(auditUserParam);
    }

}


