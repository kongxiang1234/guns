package cn.stylefeng.guns.modular.message.mapper;

import cn.stylefeng.guns.modular.message.entity.MessageManagement;
import cn.stylefeng.guns.modular.message.model.params.MessageManagementParam;
import cn.stylefeng.guns.modular.message.model.result.MessageManagementResult;
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
 * @author Alan
 * @since 2020-09-12
 */
public interface MessageManagementMapper extends BaseMapper<MessageManagement> {

    /**
     * 获取列表
     *
     * @author Alan
     * @Date 2020-09-12
     */
    List<MessageManagementResult> customList(@Param("paramCondition") MessageManagementParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan
     * @Date 2020-09-12
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") MessageManagementParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan
     * @Date 2020-09-12
     */
    Page<MessageManagementResult> customPageList(@Param("page") Page page, @Param("paramCondition") MessageManagementParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan
     * @Date 2020-09-12
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") MessageManagementParam paramCondition);

}
