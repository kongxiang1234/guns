package cn.stylefeng.guns.modular.investigation.mapper;

import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationPdf;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationPdfPageParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationPdfPageResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InvestigationPdfMapper extends BaseMapper<InvestigationPdf> {

    Page<InvestigationPdfPageResult> listPage(@Param("page") Page page, @Param("investigationPdfPageParam") InvestigationPdfPageParam investigationPdfPageParam);
}
