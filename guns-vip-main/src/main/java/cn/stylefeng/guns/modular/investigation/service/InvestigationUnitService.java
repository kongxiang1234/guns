package cn.stylefeng.guns.modular.investigation.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationUnit;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationUnitParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationUnitResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
public interface InvestigationUnitService extends IService<InvestigationUnit> {

    /**
     * 新增
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void add(InvestigationUnitParam param);

    /**
     * 删除
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void delete(InvestigationUnitParam param);

    /**
     * 更新
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void update(InvestigationUnitParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
    InvestigationUnitResult findBySpec(InvestigationUnitParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<InvestigationUnitResult> findListBySpec(InvestigationUnitParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
     LayuiPageInfo findPageBySpec(InvestigationUnitParam param);

}
