package cn.stylefeng.guns.modular.message.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.message.entity.MessageManagement;
import cn.stylefeng.guns.modular.message.mapper.MessageManagementMapper;
import cn.stylefeng.guns.modular.message.model.params.MessageManagementParam;
import cn.stylefeng.guns.modular.message.model.result.MessageManagementResult;
import  cn.stylefeng.guns.modular.message.service.MessageManagementService;
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
 * @since 2020-09-12
 */
@Service
public class MessageManagementServiceImpl extends ServiceImpl<MessageManagementMapper, MessageManagement> implements MessageManagementService {

    @Override
    public void add(MessageManagementParam param){
        MessageManagement entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(MessageManagementParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(MessageManagementParam param){
        MessageManagement oldEntity = getOldEntity(param);
        MessageManagement newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public MessageManagementResult findBySpec(MessageManagementParam param){
        return null;
    }

    @Override
    public List<MessageManagementResult> findListBySpec(MessageManagementParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(MessageManagementParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(MessageManagementParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private MessageManagement getOldEntity(MessageManagementParam param) {
        return this.getById(getKey(param));
    }

    private MessageManagement getEntity(MessageManagementParam param) {
        MessageManagement entity = new MessageManagement();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
