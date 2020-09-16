package cn.stylefeng.guns.modular.investigation.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationPdf;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationInfoMapper;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationPdfMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationPdfPageParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationPdfPageResult;
import cn.stylefeng.guns.modular.investigation.service.InvestigationInfoService;
import cn.stylefeng.guns.modular.investigation.service.InvestigationPdfService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@Service
public class InvestigationPdfServiceImpl extends ServiceImpl<InvestigationPdfMapper, InvestigationPdf> implements InvestigationPdfService {

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationPdfPageParam investigationPdfPageParam) {
        Page pageContext = LayuiPageFactory.defaultPage();;
        Page<InvestigationPdfPageResult> listPage = this.baseMapper.listPage(pageContext, investigationPdfPageParam);
        return LayuiPageFactory.createPageInfo(listPage);
    }
}
