package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Paper;
import cn.stylefeng.guns.modular.e365.model.params.PaperParam;
import cn.stylefeng.guns.modular.e365.model.result.PaperResult;
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
 * @since 2020-04-18
 */
public interface PaperMapper extends BaseMapper<Paper> {

    /**
     * 获取列表
     *
     * @author Alan
     * @Date 2020-04-18
     */
    List<PaperResult> customList(@Param("paramCondition") PaperParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan
     * @Date 2020-04-18
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PaperParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan
     * @Date 2020-04-18
     */
    Page<Map> customPageList(@Param("page") Page page,@Param("beginTime") String beginTime,@Param("endTime") String endTime,@Param("sjbh") String sjbh,@Param("specialty") String specialty,@Param("type") String type);

    /**
     * 获取分页map列表
     *
     * @author Alan
     * @Date 2020-04-18
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PaperParam paramCondition);

    List<Map> selectQuary(@Param("specialty") String specialty);

    List<Map> selectZhouQuary(@Param("type") String type);

    List<Map> selectYueQuary(@Param("number") String number, @Param("specialty") List<String> specialty,@Param("role") String role);
}
