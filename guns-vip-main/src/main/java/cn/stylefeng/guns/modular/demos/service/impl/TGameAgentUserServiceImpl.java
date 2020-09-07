package cn.stylefeng.guns.modular.demos.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.demos.entity.TGameAgentUser;
import cn.stylefeng.guns.modular.demos.mapper.TGameAgentUserMapper;
import cn.stylefeng.guns.modular.demos.model.params.TGameAgentUserParam;
import cn.stylefeng.guns.modular.demos.model.result.TGameAgentUserResult;
import  cn.stylefeng.guns.modular.demos.service.TGameAgentUserService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 代理员信息 服务实现类
 * </p>
 *
 * @author Alan
 * @since 2020-04-15
 */
@Service
public class TGameAgentUserServiceImpl extends ServiceImpl<TGameAgentUserMapper, TGameAgentUser> implements TGameAgentUserService {

    @Override
    public void add(TGameAgentUserParam param){
        TGameAgentUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(TGameAgentUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(TGameAgentUserParam param){
        TGameAgentUser oldEntity = getOldEntity(param);
        TGameAgentUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public TGameAgentUserResult findBySpec(TGameAgentUserParam param){
        return null;
    }

    @Override
    public List<TGameAgentUserResult> findListBySpec(TGameAgentUserParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(TGameAgentUserParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(TGameAgentUserParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private TGameAgentUser getOldEntity(TGameAgentUserParam param) {
        return this.getById(getKey(param));
    }

    private TGameAgentUser getEntity(TGameAgentUserParam param) {
        TGameAgentUser entity = new TGameAgentUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
