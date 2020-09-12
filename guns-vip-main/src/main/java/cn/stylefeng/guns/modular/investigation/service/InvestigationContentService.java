package cn.stylefeng.guns.modular.investigation.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationContentResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
public interface InvestigationContentService extends IService<InvestigationContent> {
    /**
     * 新增
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void add(InvestigationContentParam param);

    /**
     * 删除
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void delete(InvestigationContentParam param);

    /**
     * 更新
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void update(InvestigationContentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
    InvestigationContentResult findBySpec(InvestigationContentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<InvestigationContentResult> findListBySpec(InvestigationContentParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
     LayuiPageInfo findPageBySpec(InvestigationContentParam param);

     /**
     * 查询数据，Specification模式
     * @author hujt
     * @Date 2020-09-09
     */
     List<Map<String,Object>> investigationInfoList();

}
