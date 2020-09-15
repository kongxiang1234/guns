package cn.stylefeng.guns.modular.investigation.mapper;

import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationContentResult;
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
public interface InvestigationContentMapper extends BaseMapper<InvestigationContent> {

    /**
     * 获取列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<InvestigationContentResult> customList(@Param("paramCondition") InvestigationContentParam paramCondition);

    /**
     * 获取map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") InvestigationContentParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    Page<InvestigationContentResult> customPageList(@Param("page") Page page, @Param("paramCondition") InvestigationContentParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") InvestigationContentParam paramCondition);
    /**
     * 获取分页map列表
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<Map<String, Object>> investigationInfoList();


    /**
     * 协查申请根据id获取详情
     * @param investigationContentParam
     * @return
     */
    List<Map<String, Object>> getInvestigationInfoByid(InvestigationContentParam investigationContentParam);
    /**
     * 协查申请普通搜索
     * @param
     * @return
     */
    List<Map<String, Object>> getInvestigationInfoListBySearch(@Param("queryParam")String param);

    /**
     * 协查申请普通搜索
     * @param
     * @return
     */
    List<Map<String, Object>> getInvestigationInfoListByHeighSearch(@Param("paramCondition")InvestigationContentParam param);
    /**
     * 协查申请审核列表（非驳回）
     * @param
     * @return
     */
    List<Map<String, Object>> getinvestigationInfoListByStatus();

    /**
     * 协查申请审核
     * @param param
     * @return
     */
    void editInvestigationContent(@Param("param") InvestigationContentParam param);

    String getUnitByDeptId(Long deptId);
}
