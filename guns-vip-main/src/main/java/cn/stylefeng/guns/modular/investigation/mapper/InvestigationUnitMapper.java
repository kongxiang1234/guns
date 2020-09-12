package cn.stylefeng.guns.modular.investigation.mapper;

import cn.stylefeng.guns.modular.investigation.entity.InvestigationUnit;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationUnitParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationUnitResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
public interface InvestigationUnitMapper extends BaseMapper<InvestigationUnit> {

    /**
     * 获取列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<InvestigationUnitResult> customList(@Param("paramCondition") InvestigationUnitParam paramCondition);

    /**
     * 获取map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") InvestigationUnitParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    Page<InvestigationUnitResult> customPageList(@Param("page") Page page, @Param("paramCondition") InvestigationUnitParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") InvestigationUnitParam paramCondition);

}
