package cn.stylefeng.guns.modular.investigation.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationUnit;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationUnitMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationUnitParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationUnitResult;
import  cn.stylefeng.guns.modular.investigation.service.InvestigationUnitService;
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
 * @author hujt
 * @since 2020-09-09
 */
@Service
public class InvestigationUnitServiceImpl extends ServiceImpl<InvestigationUnitMapper, InvestigationUnit> implements InvestigationUnitService {

    @Override
    public void add(InvestigationUnitParam param){
        InvestigationUnit entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InvestigationUnitParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InvestigationUnitParam param){
        InvestigationUnit oldEntity = getOldEntity(param);
        InvestigationUnit newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InvestigationUnitResult findBySpec(InvestigationUnitParam param){
        return null;
    }

    @Override
    public List<InvestigationUnitResult> findListBySpec(InvestigationUnitParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationUnitParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(InvestigationUnitParam param){
        return param.getUnitId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InvestigationUnit getOldEntity(InvestigationUnitParam param) {
        return this.getById(getKey(param));
    }

    private InvestigationUnit getEntity(InvestigationUnitParam param) {
        InvestigationUnit entity = new InvestigationUnit();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
