package cn.stylefeng.guns.modular.investigation.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationInfoMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
import  cn.stylefeng.guns.modular.investigation.service.InvestigationInfoService;
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
public class InvestigationInfoServiceImpl extends ServiceImpl<InvestigationInfoMapper, InvestigationInfo> implements InvestigationInfoService {

    @Override
    public void add(InvestigationInfoParam param){
        InvestigationInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InvestigationInfoParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InvestigationInfoParam param){
        InvestigationInfo oldEntity = getOldEntity(param);
        InvestigationInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InvestigationInfoResult findBySpec(InvestigationInfoParam param){
        return null;
    }

    @Override
    public List<InvestigationInfoResult> findListBySpec(InvestigationInfoParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationInfoParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(InvestigationInfoParam param){
        return param.getInfoId();
    }

    private Page getPageContext() {
        String x = "123";
        return LayuiPageFactory.defaultPage();
    }

    private InvestigationInfo getOldEntity(InvestigationInfoParam param) {
        return this.getById(getKey(param));
    }

    private InvestigationInfo getEntity(InvestigationInfoParam param) {
        InvestigationInfo entity = new InvestigationInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
