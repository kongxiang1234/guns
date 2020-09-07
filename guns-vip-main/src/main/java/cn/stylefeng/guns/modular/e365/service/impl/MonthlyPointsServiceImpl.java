package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.modular.e365.entity.DailyPoints;
import cn.stylefeng.guns.modular.e365.entity.MonthlyPoints;
import cn.stylefeng.guns.modular.e365.entity.WeeklyPoints;
import cn.stylefeng.guns.modular.e365.mapper.*;
import cn.stylefeng.guns.modular.e365.service.MonthlyPointsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
@Service
public class MonthlyPointsServiceImpl extends ServiceImpl<MonthlyPointsMapper, MonthlyPoints> implements MonthlyPointsService {
    @Autowired
    private DailyPointsMapper dailyPointsMapper;
    @Autowired
    private MonthlyPointsMapper monthlyPointsMapper;
    @Autowired
    private WeeklyPointsMapper weeklyPointsMapper;



    @Override
    public List<DailyPoints>  listMeiri(String name) {
        return dailyPointsMapper.listMeiri(name);
    }

    @Override
    public List<WeeklyPoints> listMeizhou(String name) {
        return weeklyPointsMapper.listMeizhou(name);
    }

    @Override
    public List<Map> listMeiyue(Map name) {
        return monthlyPointsMapper.listMeiyue(name);
    }

    @Override
    public List<Map> listPaper(Long userId) {
        return baseMapper.listPaper(userId);
    }
}
