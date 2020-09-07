package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Answer;
import cn.stylefeng.guns.modular.e365.model.params.AnswerParam;
import cn.stylefeng.guns.modular.e365.model.result.AnswerResult;
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
public interface AnswerMapper extends BaseMapper<Answer> {

    /**
     * 获取列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    List<AnswerResult> customList(@Param("paramCondition") AnswerParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") AnswerParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    Page<AnswerResult> customPageList(@Param("page") Page page, @Param("paramCondition") AnswerParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan _孔
     * @Date 2020-04-17
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") AnswerParam paramCondition);

}
