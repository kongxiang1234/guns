package cn.stylefeng.guns.modular.investigation.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationObject;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationObjectMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationObjectParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationObjectResult;
import  cn.stylefeng.guns.modular.investigation.service.InvestigationObjectService;
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
 * @author hujuntao
 * @since 2020-09-12
 */
@Service
public class InvestigationObjectServiceImpl extends ServiceImpl<InvestigationObjectMapper, InvestigationObject> implements InvestigationObjectService {

    @Override
    public void add(InvestigationObjectParam param){
        InvestigationObject entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InvestigationObjectParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InvestigationObjectParam param){
        InvestigationObject oldEntity = getOldEntity(param);
        InvestigationObject newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InvestigationObjectResult findBySpec(InvestigationObjectParam param){
        return null;
    }

    @Override
    public List<InvestigationObjectResult> findListBySpec(InvestigationObjectParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationObjectParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(InvestigationObjectParam param){
        return param.getObjectId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InvestigationObject getOldEntity(InvestigationObjectParam param) {
        return this.getById(getKey(param));
    }

    private InvestigationObject getEntity(InvestigationObjectParam param) {
        InvestigationObject entity = new InvestigationObject();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
