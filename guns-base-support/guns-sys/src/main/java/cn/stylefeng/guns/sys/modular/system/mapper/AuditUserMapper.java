package cn.stylefeng.guns.sys.modular.system.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.AuditUser;
import cn.stylefeng.guns.sys.modular.system.model.params.AuditUserParam;
import cn.stylefeng.guns.sys.modular.system.model.result.AuditUserResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alan
 * @since 2020-09-17
 */
public interface AuditUserMapper extends BaseMapper<AuditUser> {

    /**
     * 获取列表
     *
     * @author Alan
     * @Date 2020-09-17
     */
    List<AuditUserResult> customList(@Param("paramCondition") AuditUserParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan
     * @Date 2020-09-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") AuditUserParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan
     * @Date 2020-09-17
     */
    Page<AuditUserResult> customPageList(@Param("page") Page page, @Param("paramCondition") AuditUserParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan
     * @Date 2020-09-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") AuditUserParam paramCondition);

    AuditUser getByIdAuditUser(@Param("id")Long id);
}
