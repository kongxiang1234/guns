package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Integration;
import cn.stylefeng.guns.modular.e365.model.params.IntegrationParam;
import cn.stylefeng.guns.modular.e365.model.result.IntegrationResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-25
 */
public interface IntegrationService extends IService<Integration> {

    /**
     * 新增
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    void add(IntegrationParam param);

    /**
     * 删除
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    void delete(IntegrationParam param);

    /**
     * 更新
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    void update(IntegrationParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    IntegrationResult findBySpec(IntegrationParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    List<IntegrationResult> findListBySpec(IntegrationParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
     LayuiPageInfo findPageBySpec(IntegrationParam param);

}
