package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Management;
import cn.stylefeng.guns.modular.e365.mapper.ManagementMapper;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.model.result.ManagementResult;
import  cn.stylefeng.guns.modular.e365.service.ManagementService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
@Service
public class ManagementServiceImpl extends ServiceImpl<ManagementMapper, Management> implements ManagementService {

    @Override
    public long add(ManagementParam param){
        QueryWrapper<Management> wrapper = new QueryWrapper<>();
        wrapper.eq("number", param.getNumber());
        Management management=this.baseMapper.selectOne(wrapper);
        if(management!=null){
            throw new ServiceException(BizExceptionEnum.MANAGE_ALREADY_REG);
        }
        param.setCreateUser(String.valueOf(LoginContextHolder.getContext().getUser().getAccount()));
        param.setCreateTime(new Date());
        Management entity = getEntity(param);
        this.save(entity);
        return entity.getId();
    }

    @Override
    public Map detailManagement(String specialty) {
        return baseMapper.detailManagement(specialty);
    }

    @Override
    public void delete(ManagementParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ManagementParam param){
        Management oldEntity = getOldEntity(param);
        Management newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ManagementResult findBySpec(ManagementParam param){
        return null;
    }

    @Override
    public List<ManagementResult> findListBySpec(ManagementParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(ManagementParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ManagementParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Management getOldEntity(ManagementParam param) {
        return this.getById(getKey(param));
    }

    private Management getEntity(ManagementParam param) {
        Management entity = new Management();
        ToolUtil.copyProperties(param, entity);
        entity.setZtype(Integer.parseInt(param.getZtype()));
        return entity;
    }

}
