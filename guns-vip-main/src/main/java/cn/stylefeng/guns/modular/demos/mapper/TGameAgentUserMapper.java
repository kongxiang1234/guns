package cn.stylefeng.guns.modular.demos.mapper;

import cn.stylefeng.guns.modular.demos.entity.TGameAgentUser;
import cn.stylefeng.guns.modular.demos.model.params.TGameAgentUserParam;
import cn.stylefeng.guns.modular.demos.model.result.TGameAgentUserResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代理员信息 Mapper 接口
 * </p>
 *
 * @author Alan
 * @since 2020-04-15
 */
public interface TGameAgentUserMapper extends BaseMapper<TGameAgentUser> {

    /**
     * 获取列表
     *
     * @author Alan
     * @Date 2020-04-15
     */
    List<TGameAgentUserResult> customList(@Param("paramCondition") TGameAgentUserParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan
     * @Date 2020-04-15
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") TGameAgentUserParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan
     * @Date 2020-04-15
     */
    Page<TGameAgentUserResult> customPageList(@Param("page") Page page, @Param("paramCondition") TGameAgentUserParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan
     * @Date 2020-04-15
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") TGameAgentUserParam paramCondition);

}
