package cn.stylefeng.guns.modular.e365.service.impl;

import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.e365.entity.Paper;
import cn.stylefeng.guns.modular.e365.mapper.ManagementMapper;
import cn.stylefeng.guns.modular.e365.mapper.PaperMapper;
import cn.stylefeng.guns.modular.e365.model.params.ManagementParam;
import cn.stylefeng.guns.modular.e365.model.params.PaperParam;
import cn.stylefeng.guns.modular.e365.model.result.PaperResult;
import  cn.stylefeng.guns.modular.e365.service.PaperService;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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
 * @author Alan
 * @since 2020-04-18
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {

    @Autowired
    private ManagementMapper managementMapper;


    @Override
    public List<Map> selectQuary(String specialty) {
        return baseMapper.selectQuary(specialty);
    }

    @Override
    public List<Map> selectZhouQuary(String type) {
        return baseMapper.selectZhouQuary(type);
    }

    @Override
    public List<Map> selectYueQuary(String number,List<String> specialty, String role) {
        return baseMapper.selectYueQuary(number,specialty,role);
    }

    @Override
    public LayuiPageInfo listdanxtg(ManagementParam managementParam) {
        LayuiPageInfo layuiPageInfo = new LayuiPageInfo();
        List<Map<String, Object>> management=managementMapper.getNumber(managementParam);
        if (StringUtils.isNotBlank(managementParam.getSelectValue())) {
            String[] strList=managementParam.getSelectValue().split(",");
            for (String str:strList){
                for (Map<String, Object> positionMap : management) {
                    String positionId = positionMap.get("id").toString();
                    if (str.equals(positionId)) {
                        positionMap.put("selected", true);
                    }
                }
            }

        }
        layuiPageInfo.setData(management);
        return layuiPageInfo;
    }

    @Override
    public void add(PaperParam param){
        Paper entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(PaperParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(PaperParam param){
        Paper oldEntity = getOldEntity(param);
        Paper newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public PaperResult findBySpec(PaperParam param){
        return null;
    }

    @Override
    public List<PaperResult> findListBySpec(PaperParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(String beginTime, String endTime, String sjbh, String specialty, String type){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, beginTime,endTime,sjbh,specialty,type);
        return LayuiPageFactory.createPageInfo(page);
    }

    private Serializable getKey(PaperParam param){
        return param.getId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private Paper getOldEntity(PaperParam param) {
        return this.getById(getKey(param));
    }

    private Paper getEntity(PaperParam param) {
        Paper entity = new Paper();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
