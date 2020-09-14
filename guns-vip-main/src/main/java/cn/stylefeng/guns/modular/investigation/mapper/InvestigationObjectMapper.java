package cn.stylefeng.guns.modular.investigation.mapper;

import cn.stylefeng.guns.modular.investigation.entity.InvestigationObject;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationObjectParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationObjectResult;
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
 * @since 2020-09-12
 */
public interface InvestigationObjectMapper extends BaseMapper<InvestigationObject> {

    /**
     * 获取列表
     *
     * @author hujt
     * @Date 2020-09-12
     */
    List<InvestigationObjectResult> customList(@Param("paramCondition") InvestigationObjectParam paramCondition);

    /**
     * 获取map列表
     *
     * @author hujt
     * @Date 2020-09-12
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") InvestigationObjectParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author hujt
     * @Date 2020-09-12
     */
    Page<InvestigationObjectResult> customPageList(@Param("page") Page page, @Param("paramCondition") InvestigationObjectParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author hujt
     * @Date 2020-09-12
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") InvestigationObjectParam paramCondition);

    /**
     * 任务受理
     * @param param
     * @return
     */
    List<Map<String,Object>> investigationObjectListByCompId(@Param("unit_id")String param);
    /**
     * 任务受理
     * @param param
     * @return
     */
    List<Map<String,Object>> getInvestigationObjectInfoByid(@Param("map")Map<String,Object> map);

}
