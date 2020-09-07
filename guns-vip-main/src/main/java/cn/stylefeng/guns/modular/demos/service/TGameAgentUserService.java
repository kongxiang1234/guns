package cn.stylefeng.guns.modular.demos.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.demos.entity.TGameAgentUser;
import cn.stylefeng.guns.modular.demos.model.params.TGameAgentUserParam;
import cn.stylefeng.guns.modular.demos.model.result.TGameAgentUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 代理员信息 服务类
 * </p>
 *
 * @author Alan
 * @since 2020-04-15
 */
public interface TGameAgentUserService extends IService<TGameAgentUser> {

    /**
     * 新增
     *
     * @author Alan
     * @Date 2020-04-15
     */
    void add(TGameAgentUserParam param);

    /**
     * 删除
     *
     * @author Alan
     * @Date 2020-04-15
     */
    void delete(TGameAgentUserParam param);

    /**
     * 更新
     *
     * @author Alan
     * @Date 2020-04-15
     */
    void update(TGameAgentUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan
     * @Date 2020-04-15
     */
    TGameAgentUserResult findBySpec(TGameAgentUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan
     * @Date 2020-04-15
     */
    List<TGameAgentUserResult> findListBySpec(TGameAgentUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan
     * @Date 2020-04-15
     */
     LayuiPageInfo findPageBySpec(TGameAgentUserParam param);

}
