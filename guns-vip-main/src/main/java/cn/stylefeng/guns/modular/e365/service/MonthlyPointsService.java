package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.modular.e365.entity.DailyPoints;
import cn.stylefeng.guns.modular.e365.entity.MonthlyPoints;
import cn.stylefeng.guns.modular.e365.entity.WeeklyPoints;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
public interface MonthlyPointsService extends IService<MonthlyPoints> {


    List<Map> listPaper(Long userId);

    List<DailyPoints>  listMeiri(String name);

    List<WeeklyPoints> listMeizhou(String name);

    List<Map> listMeiyue(Map name);


}
