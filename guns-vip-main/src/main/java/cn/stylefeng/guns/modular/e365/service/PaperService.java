package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Paper;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.model.params.PaperParam;
import cn.stylefeng.guns.modular.e365.model.result.PaperResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan
 * @since 2020-04-18
 */
public interface PaperService extends IService<Paper> {

    /**
     * 新增
     *
     * @author Alan
     * @Date 2020-04-18
     */
    void add(PaperParam param);

    /**
     * 删除
     *
     * @author Alan
     * @Date 2020-04-18
     */
    void delete(PaperParam param);

    /**
     * 更新
     *
     * @author Alan
     * @Date 2020-04-18
     */
    void update(PaperParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan
     * @Date 2020-04-18
     */
    PaperResult findBySpec(PaperParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan
     * @Date 2020-04-18
     */
    List<PaperResult> findListBySpec(PaperParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan
     * @Date 2020-04-18
     */
    LayuiPageInfo findPageBySpec(String beginTime, String endTime, String sjbh, String specialty, String type);


    LayuiPageInfo listdanxtg(ManagementParam specialty);

    List<Map> selectQuary(String specialty);

    List<Map> selectZhouQuary(String type);

    List<Map> selectYueQuary(String number, List<String> specialty, String role);
}
