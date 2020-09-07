package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.*;
import cn.stylefeng.guns.modular.e365.mapper.*;
import cn.stylefeng.guns.modular.e365.model.params.IntegralParam;
import cn.stylefeng.guns.modular.e365.model.result.IntegralResult;
import  cn.stylefeng.guns.modular.e365.service.IntegralService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-24
 */
@Service("integralService")
public class IntegralServiceImpl extends ServiceImpl<IntegralMapper, Integral> implements IntegralService {

    @Autowired
    private DailyPointsMapper dailyPointsMapper;
    @Autowired
    private IntegralLogMapper integralLogMapper;
    @Autowired
    private IntegrationMapper integrationMapper;
    @Autowired
    private MonthlyPointsMapper monthlyPointsMapper;
    @Autowired
    private WeeklyPointsMapper weeklyPointsMapper;

    @Override
    public List<IntegralLog> listIntegralLog(Long userId, String year) {
        return integralLogMapper.selectList(new QueryWrapper<IntegralLog>().eq("userId",userId).eq("date_format(date,'%Y')",year));
    }

    @Override
    public void addIntegralLog(IntegralLog log) {
        integralLogMapper.insert(log);
    }

    @Override
    public List<MonthlyPoints> QueryMonthlyPoints(int year, int month) {
        QueryWrapper<MonthlyPoints> queryWrapper=new QueryWrapper();
        queryWrapper.eq("dateYear",year);
        queryWrapper.eq("month",month);
        queryWrapper.orderByDesc("score");
        Integration one = integrationMapper.selectOne(new QueryWrapper<Integration>());
        queryWrapper.last("limit "+one.getMycs()+"");
        return monthlyPointsMapper.selectList(queryWrapper);
    }
    @Override
    public void updateMeiyueAudit(MonthlyPoints daily) {
        QueryWrapper<MonthlyPoints> queryWrapper=new QueryWrapper();
        queryWrapper.eq("userId",daily.getUserId());
        queryWrapper.eq("dateYear",daily.getDateYear());
        queryWrapper.eq("month",daily.getMonth());
        monthlyPointsMapper.update(daily,queryWrapper);
    }

    @Override
    public List<MonthlyPoints> listMonthlyPoints(int year, int month) {
        QueryWrapper<MonthlyPoints> queryWrapper=new QueryWrapper();
        queryWrapper.eq("dateYear",year);
        queryWrapper.eq("month",month);
        queryWrapper.orderByDesc("score");
        return monthlyPointsMapper.selectList(queryWrapper);
    }

    @Override
    public int getMonthly(String account) {
        return monthlyPointsMapper.selectCount(new QueryWrapper<MonthlyPoints>().eq("userId",account));
    }

    @Override
    public int getDay(String account) {
        return dailyPointsMapper.selectCount(new QueryWrapper<DailyPoints>().eq("name",account));
    }

    @Override
    public int getWeekly(String account) {
        return weeklyPointsMapper.selectCount(new QueryWrapper<WeeklyPoints>().eq("name",account));
    }

    @Override
    public void addMeiriAudit(DailyPoints daily) {
        dailyPointsMapper.insert(daily);
    }

    @Override
    public void addMeizhouAudit(WeeklyPoints daily) {
        weeklyPointsMapper.insert(daily);
    }

    @Override
    public MonthlyPoints selectMeiyueAudit(String account) {
        QueryWrapper<MonthlyPoints> queryWrapper=new QueryWrapper<MonthlyPoints>();
        queryWrapper.eq("name",account);
        queryWrapper.orderByDesc("date");
        queryWrapper.last("limit 1");
        return monthlyPointsMapper.selectOne(queryWrapper);
    }

    @Override
    public void addMeiyueAudit(MonthlyPoints daily) {
        monthlyPointsMapper.insert(daily);
    }

    @Override
    public DailyPoints selectMeiriAudit(String account) {
        QueryWrapper<DailyPoints> queryWrapper=new QueryWrapper<DailyPoints>();
        queryWrapper.eq("name",account);
        queryWrapper.orderByDesc("date");
        queryWrapper.last("limit 1");
        return dailyPointsMapper.selectOne(queryWrapper);
    }

    @Override
    public WeeklyPoints selectMeizhouAudit(String account) {
        QueryWrapper<WeeklyPoints> queryWrapper=new QueryWrapper<WeeklyPoints>();
        queryWrapper.eq("name",account);
        queryWrapper.orderByDesc("date");
        queryWrapper.last("limit 1");
        return weeklyPointsMapper.selectOne(queryWrapper);
    }

    @Override
    public void add(IntegralParam param){
        Integral entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(IntegralParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(IntegralParam param){
        Integral oldEntity = getOldEntity(param);
        Integral newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public IntegralResult findBySpec(IntegralParam param){
        return null;
    }

    @Override
    public List<IntegralResult> findListBySpec(IntegralParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(IntegralParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param.getName());
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(IntegralParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Integral getOldEntity(IntegralParam param) {
        return this.getById(getKey(param));
    }

    private Integral getEntity(IntegralParam param) {
        Integral entity = new Integral();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
