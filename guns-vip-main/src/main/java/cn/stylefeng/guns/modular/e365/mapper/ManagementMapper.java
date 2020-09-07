package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Management;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.model.result.ManagementResult;
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
 * @author Alan _孔
 * @since 2020-04-17
 */
public interface ManagementMapper extends BaseMapper<Management> {

    /**
     * 获取列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    List<ManagementResult> customList(@Param("paramCondition") ManagementParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ManagementParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    Page<ManagementResult> customPageList(@Param("page") Page page, @Param("paramCondition") ManagementParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ManagementParam paramCondition);

    Map detailManagement(@Param("specialty") String specialty);

    List<Map<String, Object>> getNumber(ManagementParam managementParam);
}
