package cn.stylefeng.guns.sys.modular.system.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.sys.modular.system.entity.AuditUser;
import cn.stylefeng.guns.sys.modular.system.mapper.AuditUserMapper;
import cn.stylefeng.guns.sys.modular.system.model.params.AuditUserParam;
import cn.stylefeng.guns.sys.modular.system.model.result.AuditUserResult;
import cn.stylefeng.guns.sys.modular.system.service.AuditUserService;
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
 * @author Alan
 * @since 2020-09-17
 */
@Service
public class AuditUserServiceImpl extends ServiceImpl<AuditUserMapper, AuditUser> implements AuditUserService {

    @Override
    public void add(AuditUserParam param){
        AuditUser entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(AuditUserParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(AuditUserParam param){
        AuditUser oldEntity = getOldEntity(param);
        AuditUser newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        newEntity.setIsdel("1");
        this.updateById(newEntity);
    }

    @Override
    public AuditUserResult findBySpec(AuditUserParam param){
        return null;
    }

    @Override
    public List<AuditUserResult> findListBySpec(AuditUserParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(AuditUserParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageMapList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(AuditUserParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private AuditUser getOldEntity(AuditUserParam param) {
        return this.getById(getKey(param));
    }

    private AuditUser getEntity(AuditUserParam param) {
        AuditUser entity = new AuditUser();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

    @Override
    public AuditUser getByIdAuditUser(Long id) {
        return this.baseMapper.getByIdAuditUser(id);
    }

    @Override
    public AuditUser getByIdAuditUsers(Long id) {
        return this.baseMapper.getByIdAuditUsers(id);
    }
}
