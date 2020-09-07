/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.sys.modular.system.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.stylefeng.guns.base.auth.annotion.Permission;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.exception.AuthException;
import cn.stylefeng.guns.base.auth.exception.enums.AuthExceptionEnum;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.log.BussinessLog;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.core.constant.Const;
import cn.stylefeng.guns.sys.core.constant.dictmap.UserDict;
import cn.stylefeng.guns.sys.core.constant.state.ManagerStatus;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.core.log.LogObjectHolder;
import cn.stylefeng.guns.sys.core.util.SaltUtil;
import cn.stylefeng.guns.sys.modular.system.entity.Dept;
import cn.stylefeng.guns.sys.modular.system.entity.Position;
import cn.stylefeng.guns.sys.modular.system.entity.Role;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.DeptService;
import cn.stylefeng.guns.sys.modular.system.service.PositionService;
import cn.stylefeng.guns.sys.modular.system.service.RoleService;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.guns.sys.modular.system.warpper.UserWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.response.ErrorResponseData;
import cn.stylefeng.roses.kernel.model.response.ResponseData;
import cn.stylefeng.roses.kernel.model.response.SuccessResponseData;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.*;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Slf4j
@Controller
@RequestMapping("/mgr")
@Validated
public class UserMgrController extends BaseController {

    private static String PREFIX = "/modular/system/user/";

    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private PositionService  positionService;
    @Autowired
    private RoleService roleService;
    /**
     * 跳转到查看管理员列表的页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到查看管理员列表的页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * 跳转到角色分配页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @Permission
    @RequestMapping("/role_assign")
    public String roleAssign(@RequestParam Long userId, Model model) {
        model.addAttribute("userId", userId);
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 跳转到编辑管理员页面
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @Permission
    @RequestMapping("/user_edit")
    public String userEdit(@RequestParam Long userId) {
        User user = this.userService.getById(userId);
        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * 获取用户详情
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public SuccessResponseData getUserInfo(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new RequestEmptyException();
        }

        this.userService.assertAuth(userId);
        return new SuccessResponseData(userService.getUserInfo(userId));
    }


    /**
     * 修改当前用户的密码
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam("oldPassword") @NotBlank String oldPassword,
                            @RequestParam("newPassword") @Length(min = 6, max = 12) String newPassword) {
        this.userService.changePwd(oldPassword, newPassword);
        return SUCCESS_TIP;
    }

    /**
     * 查询管理员列表
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:43
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long deptId) {

        //拼接查询条件
        String beginTime = "";
        String endTime = "";

        if (ToolUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }

        if (LoginContextHolder.getContext().isAdmin()) {
            Page<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        } else {
            DataScope dataScope = new DataScope(LoginContextHolder.getContext().getDeptDataScope());
            Page<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        }
    }

    /**
     * 添加管理员
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData add(@Valid UserDto user) {
        user.setStatus("ENABLE");
        this.userService.addUser(user);
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public ResponseData edit(UserDto user) {
        this.userService.editUser(user);
        return SUCCESS_TIP;
    }

    /**
     * 删除管理员（逻辑删除）
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.deleteUser(userId);
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        return this.userService.getById(userId);
    }


    /**
     * 查询用户
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/userList")
    @ResponseBody
    public List<User> view() {
        return this.userService.list(new QueryWrapper<User>().eq("status","ENABLE"));
    }

    /**
     * 查询用户
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/departList")
    @ResponseBody
    public List<Dept> departList() {
        return this.deptService.list();
    }
    /**
     * 重置管理员的密码
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData reset(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        User user = this.userService.getById(userId);
        user.setSalt(SaltUtil.getRandomSalt());
        user.setPassword(SaltUtil.md5Encrypt(ConstantsContext.getDefaultPassword(), user.getSalt()));
        this.userService.updateById(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData freeze(@RequestParam Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userService.assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData unfreeze(@RequestParam Long userId,@RequestParam int year) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date()); //获取结束时间参数
        cal.add(Calendar.YEAR, year);
        Timer timer = new Timer();
        User user= userService.getById(userId);
        timer.schedule(new UserTask(user), cal.getTime());
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData setRole(@RequestParam("userId") Long userId,
                                @RequestParam("roleIds") @NotBlank String roleIds) {
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        this.userService.assertAuth(userId);
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

    /**
     * 上传图片
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:44
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = ConstantsContext.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }

    /**
     * 选择办理人
     *
     * @author fengshuonan
     * @Date 2019-8-22 15:48
     */
    @RequestMapping("/listUserAndRoleExpectAdmin")
    @ResponseBody
    public LayuiPageInfo listUserAndRoleExpectAdmin() {
        Page pageContext = LayuiPageFactory.defaultPage();
        IPage page = userService.listUserAndRoleExpectAdmin(pageContext);
        return LayuiPageFactory.createPageInfo(page);
    }


    /**
     * 上传excel填报
     */
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public ResponseData uploadExcel(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String name = file.getOriginalFilename();
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
                List<UserDto> result = ExcelImportUtil.importExcel(file, UserDto.class, params);
                ResponseData responseData=new ResponseData();
                if(result.size()!=0){
                    responseData=  insertUser(result);
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
                return ResponseData.error("导入失败");
            }
        }
        return SUCCESS_TIP;
    }
    private ResponseData insertUser(List<UserDto> result) {
        for (UserDto userDto:result){
            if(StringUtils.isBlank(userDto.getAccount())){
                return ResponseData.error("账号为空请检查");
            }
            if(StringUtils.isBlank(userDto.getName())){
                return ResponseData.error("姓名为空请检查");
            }
           /* if(StringUtils.isBlank(userDto.getEmail())){
                return ResponseData.error("身份证号码为空请检查");
            }
            if(StringUtils.isBlank(userDto.getPhone())){
                return ResponseData.error("电话为空请检查");
            }*/
            if(StringUtils.isBlank(userDto.getDeptName())){
                return ResponseData.error("部门名称为空请检查");
            }
            if(StringUtils.isBlank(userDto.getSpecialty())){
                return ResponseData.error("所属专业为空请检查");
            }
            if(StringUtils.isBlank(userDto.getPosition())){
                return ResponseData.error("职位名称为空请检查");
            }

                Dept dept=  deptService.getOne(new QueryWrapper<Dept>().eq("simple_name",userDto.getDeptName()));
                if(dept==null){
                    return ResponseData.error("导入数据部门名称错误");
                }
            userDto.setDeptId(dept.getDeptId());
                QueryWrapper<User> queryWrapper=new QueryWrapper();
                queryWrapper.eq("account",userDto.getAccount());
              /*  queryWrapper.eq("email",userDto.getEmail());*/
               /* User theUser = userService.getOne(queryWrapper);*/
               /* if (theUser != null) {
                    return ResponseData.error("导入的某一个用户已经注册/或者重复"+theUser.getName());
                }*/

                Position position=  positionService.getOne(new QueryWrapper<Position>().like("name",userDto.getPosition()));
                if(position==null){
                    return ResponseData.error("导入数据职称名称错误");
                }
            userDto.setRoleId(String.valueOf(roleService.getOne(new QueryWrapper<Role>().like("name",userDto.getPosition())).getRoleId()));
                userDto.setPosition(String.valueOf(position.getPositionId()));
                userDto.setPassword(ConstantsContext.getDefaultPassword());
                userDto.setStatus("ENABLE");

                userService.addUser(userDto);

        }
        return ResponseData.success();
    }
}
