package cn.stylefeng.guns.modular.investigation.service;

import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationPdf;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationPdfPageParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
public interface InvestigationPdfService extends IService<InvestigationPdf> {
    LayuiPageInfo findPageBySpec(InvestigationPdfPageParam investigationPdfPageParam);
}
