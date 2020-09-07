package cn.stylefeng.guns.modular.e365.controller;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.modular.e365.entity.Integral;
import cn.stylefeng.guns.modular.e365.entity.IntegralLog;
import cn.stylefeng.guns.modular.e365.entity.Integration;
import cn.stylefeng.guns.modular.e365.entity.MonthlyPoints;
import cn.stylefeng.guns.modular.e365.model.params.IntegralParam;
import cn.stylefeng.guns.modular.e365.service.IntegralService;
import cn.stylefeng.guns.modular.e365.service.IntegrationService;
import cn.stylefeng.guns.sys.core.util.ApplicationContextUtil;
import cn.stylefeng.guns.sys.modular.system.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

public class RemindTask extends TimerTask {
    private List<User> userList;
    private int year;
    private int month;
    private String paperId;
    protected RemindTask(List<User> userList,int year,int month,String paperId) {
        this.userList=userList;
        this.paperId=paperId;
        this.year=year;
        this.month=month;
    }
    public void run() {
        IntegralService integralService = (IntegralService) ApplicationContextUtil.getBean("integralService");
        IntegrationService integrationService = (IntegrationService) ApplicationContextUtil.getBean("integrationService");
        List<MonthlyPoints> monthlyPoints=integralService.listMonthlyPoints(year,month);

        for (int i = 0; i < monthlyPoints.size(); i++) {//正常考试的人员考试成绩排序从高到低
            for (int j = 0; j <userList.size(); j++) {//需要考试的人员
                if(String.valueOf(monthlyPoints.get(i).getUserId()).equals(String.valueOf(userList.get(j).getUserId()))){//判断删除相同的人员，留下需要补分的人员
                    userList.remove(j);
                }
            }
        }
        for (User user:userList){
            MonthlyPoints daily=new MonthlyPoints();
            daily.setDateYear(String.valueOf(year));
            daily.setMonth(String.valueOf(month));
            daily.setName(user.getName());
            daily.setPaperId(Integer.parseInt(paperId));
            daily.setScore(0);
            daily.setUnit(String.valueOf(user.getDeptId()));
            daily.setUserId(user.getUserId());
            daily.setRanking(0);
            integralService.addMeiyueAudit(daily);
        }
        for (int i = 0; i < monthlyPoints.size(); i++) {//正常考试的人员考试成绩排序从高到低
            MonthlyPoints daily=new MonthlyPoints();
            daily.setRanking(i+1);
            daily.setUserId(monthlyPoints.get(i).getUserId());
            daily.setMonth(String.valueOf(month));
            daily.setDateYear(String.valueOf(year));
            integralService.updateMeiyueAudit(daily);
        }
        List<MonthlyPoints> listMonthlyPoints=integralService.QueryMonthlyPoints(year,month);//根据积分规则月度前几获取排名前几名进行积分给予
        for (MonthlyPoints monthlyPoints1:listMonthlyPoints){
            if(monthlyPoints1.getRanking()!=0){
                Integration one = integrationService.getOne(new QueryWrapper<Integration>());
                Integral integ = integralService.getOne(new QueryWrapper<Integral>().eq("userId", monthlyPoints1.getUserId()));
                IntegralParam integralParam = new IntegralParam();
                integralParam.setId(integ.getId());
                if(integ.getMyIntegral()==0){
                    integralParam.setMyIntegral(one.getMyjf());
                }else {
                    integralParam.setMyIntegral(integ.getMyIntegral() + one.getMyjf());
                    if(integralParam.getMyIntegral() % one.getMydk() == 0){ //判断积分与积分规则设置的积分倍数时就进行违章抵扣积分的累加
                        if(integ.getDkwzIntegral()==0){//如果第一次直接加一分
                            integralParam.setDkwzIntegral(1);
                        }else {
                            if(ConstantsContext.getScore()!=integ.getDkwzIntegral()){//如果常量积分不等于累加的违章积分就不累加了
                                integralParam.setDkwzIntegral(integ.getDkwzIntegral()+1);
                            }
                        }
                        IntegralLog log=new IntegralLog();
                        log.setDate(new Date());
                        log.setName("每月测试违章积分");
                        log.setScore(String.valueOf(integralParam.getDkwzIntegral()));
                        log.setUserId(integralParam.getUserId());
                        integralService.addIntegralLog(log);
                    }
                }
                integralService.update(integralParam);
            }

        }
    }
}
