package cn.stylefeng.guns.modular.investigation.mapper;

import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
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
 * @author hujt
 * @since 2020-09-09
 */
public interface InvestigationInfoMapper extends BaseMapper<InvestigationInfo> {

    /**
     * 获取列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<InvestigationInfoResult> customList(@Param("paramCondition") InvestigationInfoParam paramCondition);

    /**
     * 获取map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") InvestigationInfoParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    Page<InvestigationInfoResult> customPageList(@Param("page") Page page, @Param("paramCondition") InvestigationInfoParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") InvestigationInfoParam paramCondition);

}
