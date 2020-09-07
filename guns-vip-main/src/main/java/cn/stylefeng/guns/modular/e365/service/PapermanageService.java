package cn.stylefeng.guns.modular.e365.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Papermanage;
import cn.stylefeng.guns.modular.e365.model.params.PapermanageParam;
import cn.stylefeng.guns.modular.e365.model.result.PapermanageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-05-01
 */
public interface PapermanageService extends IService<Papermanage> {

    /**
     * 新增
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    void add(PapermanageParam param);

    /**
     * 删除
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    void delete(PapermanageParam param);

    /**
     * 更新
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    void update(PapermanageParam param);

    /**
     * 查询单条数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    PapermanageResult findBySpec(PapermanageParam param);

    /**
     * 查询列表，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
    List<PapermanageResult> findListBySpec(PapermanageParam param);

    /**
     * 查询分页数据，Specification模式
     *
     * @author Alan-孔
     * @Date 2020-05-01
     */
     LayuiPageInfo findPageBySpec(PapermanageParam param);

}
