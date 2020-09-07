package cn.stylefeng.guns.modular.e365.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Feedback;
import cn.stylefeng.guns.modular.e365.mapper.FeedbackMapper;
import cn.stylefeng.guns.modular.e365.model.params.FeedbackParam;
import cn.stylefeng.guns.modular.e365.model.result.FeedbackResult;
import  cn.stylefeng.guns.modular.e365.service.FeedbackService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-16
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

    @Override
    public void add(FeedbackParam param){
        Feedback entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(FeedbackParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(FeedbackParam param){
        Feedback feedback=new Feedback();
        feedback.setId(param.getId());
        feedback.setFeedbackContent(param.getFeedbackContent());
        String name = LoginContextHolder.getContext().getUser().getName();
        feedback.setUpdateTime(new Date());
        feedback.setUpdateUser(name);
        this.updateById(feedback);
    }

    @Override
    public FeedbackResult findBySpec(FeedbackParam param){
        return null;
    }

    @Override
    public List<FeedbackResult> findListBySpec(FeedbackParam param){
        return null;
    }



    @Override
    public LayuiPageInfo findPageBySpec(String name, String beginTime, String endTime, String type) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, name,beginTime,endTime,type);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(FeedbackParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Feedback getOldEntity(FeedbackParam param) {
        return this.getById(getKey(param));
    }

    private Feedback getEntity(FeedbackParam param) {
        Feedback entity = new Feedback();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
