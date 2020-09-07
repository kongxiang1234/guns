package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Papermanage;
import cn.stylefeng.guns.modular.e365.mapper.PapermanageMapper;
import cn.stylefeng.guns.modular.e365.model.params.PapermanageParam;
import cn.stylefeng.guns.modular.e365.model.result.PapermanageResult;
import  cn.stylefeng.guns.modular.e365.service.PapermanageService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-05-01
 */
@Service
public class PapermanageServiceImpl extends ServiceImpl<PapermanageMapper, Papermanage> implements PapermanageService {

    @Override
    public void add(PapermanageParam param){
        Papermanage entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PapermanageParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PapermanageParam param){
        Papermanage oldEntity = getOldEntity(param);
        Papermanage newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PapermanageResult findBySpec(PapermanageParam param){
        return null;
    }

    @Override
    public List<PapermanageResult> findListBySpec(PapermanageParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(PapermanageParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PapermanageParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Papermanage getOldEntity(PapermanageParam param) {
        return this.getById(getKey(param));
    }

    private Papermanage getEntity(PapermanageParam param) {
        Papermanage entity = new Papermanage();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
