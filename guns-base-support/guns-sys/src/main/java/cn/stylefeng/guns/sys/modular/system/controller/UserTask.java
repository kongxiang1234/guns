package cn.stylefeng.guns.sys.modular.system.controller;


import cn.stylefeng.guns.sys.core.util.ApplicationContextUtil;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import cn.stylefeng.guns.sys.modular.system.service.UserService;

import java.util.TimerTask;

public class UserTask extends TimerTask {
    private User user;

    public UserTask(User user) {
        this.user=user;
    }
    public void run() {
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        user.setStatus("DISABLE");
        userService.updateById(user);
    }
}
