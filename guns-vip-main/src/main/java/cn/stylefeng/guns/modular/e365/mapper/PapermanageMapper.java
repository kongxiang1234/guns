package cn.stylefeng.guns.modular.e365.mapper;

import cn.stylefeng.guns.modular.e365.entity.Papermanage;
import cn.stylefeng.guns.modular.e365.model.params.PapermanageParam;
import cn.stylefeng.guns.modular.e365.model.result.PapermanageResult;
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
 * @since 2020-05-01
 */
public interface PapermanageMapper extends BaseMapper<Papermanage> {

    /**
     * 获取列表
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    List<PapermanageResult> customList(@Param("paramCondition") PapermanageParam paramCondition);

    /**
     * 获取map列表
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    List<Map<String, Object>> customMapList(@Param("paramCondition") PapermanageParam paramCondition);

    /**
     * 获取分页实体列表
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    Page<PapermanageResult> customPageList(@Param("page") Page page, @Param("paramCondition") PapermanageParam paramCondition);

    /**
     * 获取分页map列表
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    Page<Map<String, Object>> customPageMapList(@Param("page") Page page, @Param("paramCondition") PapermanageParam paramCondition);

}
