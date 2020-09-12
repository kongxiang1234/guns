package cn.stylefeng.guns.modular.investigation.service.impl;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationContentMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationContentResult;
import cn.stylefeng.guns.modular.investigation.service.InvestigationContentService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@Service
public class InvestigationContentServiceImpl extends ServiceImpl<InvestigationContentMapper, InvestigationContent> implements InvestigationContentService {

    @Override
    public void add(InvestigationContentParam param){
        InvestigationContent entity = getEntity(param);
        this.save(entity);
        entity.getInfoId();


    }

//    alt ins
//    @Override
//    public void add1(InvestigationContentParam param) {
//
//    }

    @Override
    public void delete(InvestigationContentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InvestigationContentParam param){
        InvestigationContent oldEntity = getOldEntity(param);
        InvestigationContent newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InvestigationContentResult findBySpec(InvestigationContentParam param){
        return null;
    }

    @Override
    public List<InvestigationContentResult> findListBySpec(InvestigationContentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationContentParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public List<Map<String, Object>> investigationInfoList() {
        return this.baseMapper.investigationInfoList();
    }

    private Serializable getKey(InvestigationContentParam param){
        return param.getContentId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InvestigationContent getOldEntity(InvestigationContentParam param) {
        return this.getById(getKey(param));
    }

    private InvestigationContent getEntity(InvestigationContentParam param) {
        InvestigationContent entity = new InvestigationContent();
        ToolUtil.copyProperties(param, entity);
//        LoginUser currentUser = LoginContextHolder.getContext().getUser();
//        currentUser.getName()
        return entity;
    }

}
