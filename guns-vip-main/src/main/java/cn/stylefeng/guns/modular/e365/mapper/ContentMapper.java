package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Content;
import cn.stylefeng.guns.modular.e365.model.params.ContentParam;
import cn.stylefeng.guns.modular.e365.model.result.ContentResult;
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
 * @author Alan_孔
 * @since 2020-04-22
 */
public interface ContentMapper extends BaseMapper<Content> {

    /**
     * 获取列表
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    List<ContentResult> customList(@Param("paramCondition") ContentParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") ContentParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    Page<ContentResult> customPageList(@Param("page") Page page,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("title") String title,@Param("status") String status,@Param("type") String type);

    /**
     * 获取分页map列表
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") ContentParam paramCondition);

}
