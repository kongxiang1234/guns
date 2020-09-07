package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Break;
import cn.stylefeng.guns.modular.e365.model.params.BreakParam;
import cn.stylefeng.guns.modular.e365.model.result.BreakResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-23
 */
public interface BreakService extends IService<Break> {

    /**
     * 新增
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    void add(BreakParam param);

    /**
     * 删除
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    void delete(BreakParam param);

    /**
     * 更新
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    void update(BreakParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    BreakResult findBySpec(BreakParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    List<BreakResult> findListBySpec(BreakParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
     LayuiPageInfo findPageBySpec(String dcTime,String etime,String dcType,String wzTitle,String wzType,String wzStatus);

    LayuiPageInfo findPageListTotal(String beginTime, String endTime, String wzUnit);
}
