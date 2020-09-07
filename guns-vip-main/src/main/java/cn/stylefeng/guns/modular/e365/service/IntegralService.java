package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.*;
import cn.stylefeng.guns.modular.e365.model.params.IntegralParam;
import cn.stylefeng.guns.modular.e365.model.result.IntegralResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-24
 */
public interface IntegralService extends IService<Integral> {

    /**
     * 新增
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    void add(IntegralParam param);

    /**
     * 删除
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    void delete(IntegralParam param);

    /**
     * 更新
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    void update(IntegralParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    IntegralResult findBySpec(IntegralParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    List<IntegralResult> findListBySpec(IntegralParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
     LayuiPageInfo findPageBySpec(IntegralParam param);

     DailyPoints selectMeiriAudit(String account);

    void addMeiriAudit(DailyPoints daily);

    WeeklyPoints selectMeizhouAudit(String account);

    void addMeizhouAudit(WeeklyPoints daily);

    MonthlyPoints selectMeiyueAudit(String account);

    void addMeiyueAudit(MonthlyPoints daily);

    int getMonthly(String account);

    int getDay(String account);

    int getWeekly(String account);

    List<MonthlyPoints> listMonthlyPoints(int year, int month);

    void updateMeiyueAudit(MonthlyPoints daily);

    List<MonthlyPoints> QueryMonthlyPoints(int year, int month);

    void addIntegralLog(IntegralLog log);

    List<IntegralLog> listIntegralLog(Long userId, String year);
}
