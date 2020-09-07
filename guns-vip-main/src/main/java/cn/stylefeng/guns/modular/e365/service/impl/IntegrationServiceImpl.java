package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Integration;
import cn.stylefeng.guns.modular.e365.mapper.IntegrationMapper;
import cn.stylefeng.guns.modular.e365.model.params.IntegrationParam;
import cn.stylefeng.guns.modular.e365.model.result.IntegrationResult;
import  cn.stylefeng.guns.modular.e365.service.IntegrationService;
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
 * @since 2020-04-25
 */
@Service("integrationService")
public class IntegrationServiceImpl extends ServiceImpl<IntegrationMapper, Integration> implements IntegrationService {

    @Override
    public void add(IntegrationParam param){
        Integration entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(IntegrationParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(IntegrationParam param){
        Integration oldEntity = getOldEntity(param);
        Integration newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public IntegrationResult findBySpec(IntegrationParam param){
        return null;
    }

    @Override
    public List<IntegrationResult> findListBySpec(IntegrationParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(IntegrationParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(IntegrationParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Integration getOldEntity(IntegrationParam param) {
        return this.getById(getKey(param));
    }

    private Integration getEntity(IntegrationParam param) {
        Integration entity = new Integration();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
