package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Content;
import cn.stylefeng.guns.modular.e365.mapper.ContentMapper;
import cn.stylefeng.guns.modular.e365.model.params.ContentParam;
import cn.stylefeng.guns.modular.e365.model.result.ContentResult;
import  cn.stylefeng.guns.modular.e365.service.ContentService;
import cn.stylefeng.guns.sys.modular.rest.entity.RestFileInfo;
import cn.stylefeng.guns.sys.modular.rest.mapper.RestFileInfoMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan_孔
 * @since 2020-04-22
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    RestFileInfoMapper restFileInfoMapper;

    @Override
    public void add(ContentParam param){
        Content entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(ContentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(ContentParam param){
        Content oldEntity = getOldEntity(param);
        Content newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public ContentResult findBySpec(ContentParam param){
        return null;
    }

    @Override
    public List<ContentResult> findListBySpec(ContentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(String beginTime, String endTime, String title, String status, String type){
        Page pageContext = getPageContext();
        Page<ContentResult> page= this.baseMapper.customPageList(pageContext,beginTime,endTime,title,status,type);
       // IPage page1 = this.baseMapper.customPageList(pageContext, param);
        for (ContentResult contentResult:page.getRecords()){
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("file_id",contentResult.getPic());
            RestFileInfo restFileInfo= restFileInfoMapper.selectOne(queryWrapper);
            if(restFileInfo!=null){
                contentResult.setPic(restFileInfo.getFileId());
            }
        }
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(ContentParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Content getOldEntity(ContentParam param) {
        return this.getById(getKey(param));
    }

    private Content getEntity(ContentParam param) {
        Content entity = new Content();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
