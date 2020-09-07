package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Feedback;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.model.result.FeedbackResult;
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
 * @since 2020-04-16
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 获取列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    List<FeedbackResult> customList(@Param("paramCondition") FeedbackParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") FeedbackParam paramCondition);


    /**
     * 获取分页map列表
     *
     * @author Alan-孔
     * @Date 2020-04-16
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") FeedbackParam paramCondition);

    Page<Map<String, Object>> customPageList(@Param("page") Page pageContext, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("type") String type);
}
