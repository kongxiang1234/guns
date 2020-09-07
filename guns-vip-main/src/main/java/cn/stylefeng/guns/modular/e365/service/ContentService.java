package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Content;
import cn.stylefeng.guns.modular.e365.model.params.ContentParam;
import cn.stylefeng.guns.modular.e365.model.result.ContentResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan_孔
 * @since 2020-04-22
 */
public interface ContentService extends IService<Content> {

    /**
     * 新增
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    void add(ContentParam param);

    /**
     * 删除
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    void delete(ContentParam param);

    /**
     * 更新
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    void update(ContentParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    ContentResult findBySpec(ContentParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */
    List<ContentResult> findListBySpec(ContentParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan_孔
     * @Date 2020-04-22
     */


    LayuiPageInfo findPageBySpec(String beginTime, String endTime, String title, String status, String type);
}
