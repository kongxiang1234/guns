package cn.stylefeng.guns.modular.investigation.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationContentResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * 获取我发出的协查申请（根据登陆用户获取）
     * @author hujt
     * @Date 2020-09-09
     */
     List<Map<String,Object>> getinvestigationInfoListByLoginUser(String userName);
     /**
     * 查询数据，Specification模式
     * @author hujt
     * @Date 2020-09-09
     */
     List<Map<String,Object>> investigationInfoList();
     /**
     * 获取审核数据（非驳回）
     * @author hujt
     * @Date 2020-09-15
     */
     List<Map<String,Object>> getinvestigationInfoListByStatus();
     /**
     * 普通搜索
     * @author hujt
     * @Date 2020-09-09
     */
     List<Map<String,Object>> getInvestigationInfoListBySearch(String param);
     /**
     * 普通搜索
     * @author hujt
     * @Date 2020-09-09
     */
     List<Map<String,Object>> getInvestigationInfoListByHeighSearch(InvestigationContentParam param);
     /**
     * 根据id查询协查申请数据数据
     * @author hujt
     * @Date 2020-09-09
     */
    List<Map<String, Object>> getInvestigationInfoByid(InvestigationContentParam investigationContentParam);

    /**
     * 协查申请审核
     * @param param
     * @return
     */
        void editInvestigationContent(InvestigationContentParam param);
/**
     * 协查申请删除
     * @param param
     * @return
     */
    void deleteinvestigationInfoById(String infoId);


    String uploadFile(HttpServletRequest request, MultipartFile file);

    void downFiles(HttpServletResponse response);
}
