package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Management;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.model.result.ManagementResult;
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
public interface ManagementService extends IService<Management> {

    /**
     * 新增
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    long add(ManagementParam param);

    /**
     * 删除
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    void delete(ManagementParam param);

    /**
     * 更新
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    void update(ManagementParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    ManagementResult findBySpec(ManagementParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    List<ManagementResult> findListBySpec(ManagementParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
     LayuiPageInfo findPageBySpec(ManagementParam param);

    Map detailManagement(String specialty);
}
