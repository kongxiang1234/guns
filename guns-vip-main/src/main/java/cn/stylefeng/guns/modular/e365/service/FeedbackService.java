package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Feedback;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.model.result.FeedbackResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-16
 */
public interface FeedbackService extends IService<Feedback> {

    /**
     * 新增
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    void add(FeedbackParam param);

    /**
     * 删除
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    void delete(FeedbackParam param);

    /**
     * 更新
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    void update(FeedbackParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    FeedbackResult findBySpec(FeedbackParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    List<FeedbackResult> findListBySpec(FeedbackParam param);



    LayuiPageInfo findPageBySpec(String name, String beginTime, String endTime, String type);
}
