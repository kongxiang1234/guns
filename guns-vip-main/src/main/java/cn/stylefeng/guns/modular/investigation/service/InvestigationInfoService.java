package cn.stylefeng.guns.modular.investigation.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
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
public interface InvestigationInfoService extends IService<InvestigationInfo> {

    /**
     * 新增
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void add(InvestigationInfoParam param);

    /**
     * 删除
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void delete(InvestigationInfoParam param);

    /**
     * 更新
     *
     * @author hujt
     * @Date 2020-09-09
     */
    void update(InvestigationInfoParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
    InvestigationInfoResult findBySpec(InvestigationInfoParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
    List<InvestigationInfoResult> findListBySpec(InvestigationInfoParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author hujt
     * @Date 2020-09-09
     */
     LayuiPageInfo findPageBySpec(InvestigationInfoParam param);

     //获取协查文书号编号
     Integer getDocumentNum(String name);

}
