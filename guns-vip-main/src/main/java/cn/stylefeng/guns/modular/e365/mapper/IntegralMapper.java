package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Integral;
import cn.stylefeng.guns.modular.e365.model.params.IntegralParam;
import cn.stylefeng.guns.modular.e365.model.result.IntegralResult;
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
 * @since 2020-04-24
 */
public interface IntegralMapper extends BaseMapper<Integral> {

    /**
     * 获取列表
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    List<IntegralResult> customList(@Param("paramCondition") IntegralParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") IntegralParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    Page<IntegralResult> customPageList(@Param("page") Page page, @Param("name") String name);

    /**
     * 获取分页map列表
     *
     * @author Alan-孔
     * @Date 2020-04-24
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") IntegralParam paramCondition);

}
