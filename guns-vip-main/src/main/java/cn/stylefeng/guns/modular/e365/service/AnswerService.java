package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Answer;
import cn.stylefeng.guns.modular.e365.model.params.AnswerParam;
import cn.stylefeng.guns.modular.e365.model.result.AnswerResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
public interface AnswerService extends IService<Answer> {

    /**
     * 新增
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    void add(AnswerParam param);

    /**
     * 删除
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    void delete(AnswerParam param);

    /**
     * 更新
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    void update(AnswerParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    AnswerResult findBySpec(AnswerParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    List<AnswerResult> findListBySpec(AnswerParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
     LayuiPageInfo findPageBySpec(AnswerParam param);

}
