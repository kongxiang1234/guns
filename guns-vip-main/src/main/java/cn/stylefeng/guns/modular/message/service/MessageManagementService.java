package cn.stylefeng.guns.modular.message.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.message.entity.MessageManagement;
import cn.stylefeng.guns.modular.message.model.params.MessageManagementParam;
import cn.stylefeng.guns.modular.message.model.result.MessageManagementResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan
 * @since 2020-09-12
 */
public interface MessageManagementService extends IService<MessageManagement> {

    /**
     * 新增
     *
     * @author Alan
     * @Date 2020-09-12
     */
    void add(MessageManagementParam param);

    /**
     * 删除
     *
     * @author Alan
     * @Date 2020-09-12
     */
    void delete(MessageManagementParam param);

    /**
     * 更新
     *
     * @author Alan
     * @Date 2020-09-12
     */
    void update(MessageManagementParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan
     * @Date 2020-09-12
     */
    MessageManagementResult findBySpec(MessageManagementParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan
     * @Date 2020-09-12
     */
    List<MessageManagementResult> findListBySpec(MessageManagementParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan
     * @Date 2020-09-12
     */
     LayuiPageInfo findPageBySpec(MessageManagementParam param);

}
