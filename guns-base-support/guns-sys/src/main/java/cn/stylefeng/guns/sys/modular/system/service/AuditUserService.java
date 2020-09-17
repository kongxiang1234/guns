package cn.stylefeng.guns.sys.modular.system.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.AuditUser;
import cn.stylefeng.guns.sys.modular.system.model.params.AuditUserParam;
import cn.stylefeng.guns.sys.modular.system.model.result.AuditUserResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan
 * @since 2020-09-17
 */
public interface AuditUserService extends IService<AuditUser> {

    /**
     * 新增
     *
     * @author Alan
     * @Date 2020-09-17
     */
    void add(AuditUserParam param);

    /**
     * 删除
     *
     * @author Alan
     * @Date 2020-09-17
     */
    void delete(AuditUserParam param);

    /**
     * 更新
     *
     * @author Alan
     * @Date 2020-09-17
     */
    void update(AuditUserParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan
     * @Date 2020-09-17
     */
    AuditUserResult findBySpec(AuditUserParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan
     * @Date 2020-09-17
     */
    List<AuditUserResult> findListBySpec(AuditUserParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan
     * @Date 2020-09-17
     */
     LayuiPageInfo findPageBySpec(AuditUserParam param);

    AuditUser getByIdAuditUser(Long id);
}
