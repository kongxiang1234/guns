package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Break;
import cn.stylefeng.guns.modular.e365.model.params.BreakParam;
import cn.stylefeng.guns.modular.e365.model.result.BreakResult;
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
 * @since 2020-04-23
 */
public interface BreakMapper extends BaseMapper<Break> {

    /**
     * 获取列表
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    List<BreakResult> customList(@Param("paramCondition") BreakParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") BreakParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    Page<BreakResult> customPageList(@Param("page") Page page,@Param("btime") String btime,@Param("etime") String etime,@Param("dcType") String dcType,@Param("wzTitle") String wzTitle,@Param("wzType") String wzType,@Param("wzStatus") String wzStatus);

    /**
     * 获取分页map列表
     *
     * @author Alan-孔
     * @Date 2020-04-23
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") BreakParam paramCondition);

    Page<Map> findPageListTotal(@Param("page") Page page,@Param("btime") String beginTime,@Param("etime") String endTime,@Param("wzUnit") String wzUnit);
}
