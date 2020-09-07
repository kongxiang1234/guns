package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Integration;
import cn.stylefeng.guns.modular.e365.model.params.IntegrationParam;
import cn.stylefeng.guns.modular.e365.model.result.IntegrationResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-25
 */
public interface IntegrationMapper extends BaseMapper<Integration> {

    /**
     * 获取列表
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    List<IntegrationResult> customList(@Param("paramCondition") IntegrationParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") IntegrationParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    Page<IntegrationResult> customPageList(@Param("page") Page page, @Param("paramCondition") IntegrationParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan-孔
     * @Date 2020-04-25
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") IntegrationParam paramCondition);

}
