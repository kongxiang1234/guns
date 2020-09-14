package cn.stylefeng.guns.modular.investigation.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationObject;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationObjectParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationObjectResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hujt
 * @since 2020-09-12
 */
public interface InvestigationObjectService extends IService<InvestigationObject> {

    /**
     * 新增
     *
     * @author hujt
     * @Date 2020-09-12
     */
    void add(InvestigationObjectParam param);

    /**
     * 删除
     *
     * @author hujt
     * @Date 2020-09-12
     */
    void delete(InvestigationObjectParam param);

    /**
     * 更新
     *
     * @author hujt
     * @Date 2020-09-12
     */
    void update(InvestigationObjectParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-12
     */
    InvestigationObjectResult findBySpec(InvestigationObjectParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author hujt
     * @Date 2020-09-12
     */
    List<InvestigationObjectResult> findListBySpec(InvestigationObjectParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-12
     */
     LayuiPageInfo findPageBySpec(InvestigationObjectParam param);

    /**
     * 任务受理
     * @author hujt
     * @Date 2020-09-12
     */
     List<Map<String,Object>> investigationObjectListByCompId(String param);
     /**
     * 任务受理明细
     * @author hujt
     * @Date 2020-09-12
     */
     List<Map<String,Object>> getInvestigationObjectInfoByid(Map<String,Object> map);

}
