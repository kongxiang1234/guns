package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Break;
import cn.stylefeng.guns.modular.e365.mapper.BreakMapper;
import cn.stylefeng.guns.modular.e365.model.params.BreakParam;
import cn.stylefeng.guns.modular.e365.model.result.BreakResult;
import cn.stylefeng.guns.modular.e365.model.result.ContentResult;
import  cn.stylefeng.guns.modular.e365.service.BreakService;
import cn.stylefeng.guns.sys.modular.rest.entity.RestFileInfo;
import cn.stylefeng.guns.sys.modular.rest.mapper.RestFileInfoMapper;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-23
 */
@Service
public class BreakServiceImpl extends ServiceImpl<BreakMapper, Break> implements BreakService {
    @Autowired
    RestFileInfoMapper restFileInfoMapper;
    @Override
    public void add(BreakParam param){
        Break entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(BreakParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(BreakParam param){
        Break oldEntity = getOldEntity(param);
        Break newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public BreakResult findBySpec(BreakParam param){
        return null;
    }

    @Override
    public List<BreakResult> findListBySpec(BreakParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(String dcTime,String etime,String dcType,String wzTitle,String wzType,String wzStatus){
        Page pageContext = getPageContext();

        Page<BreakResult> page=this.baseMapper.customPageList(pageContext,dcTime,etime ,dcType,wzTitle,wzType,wzStatus);

        /*for (BreakResult contentResult:page.getRecords()){
           *//* QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("file_id",contentResult.getPic());
            RestFileInfo restFileInfo= restFileInfoMapper.selectOne(queryWrapper);
            if(restFileInfo==null){
                contentResult.setPic("");
            }else {
                contentResult.setPic(restFileInfo.getFileId());
            }*//*

        }*/
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public LayuiPageInfo findPageListTotal(String beginTime, String endTime, String wzUnit) {
        Page pageContext = getPageContext();
        Page<Map> page=this.baseMapper.findPageListTotal(pageContext,beginTime,endTime ,wzUnit);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(BreakParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Break getOldEntity(BreakParam param) {
        return this.getById(getKey(param));
    }

    private Break getEntity(BreakParam param) {
        Break entity = new Break();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
