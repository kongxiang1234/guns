package cn.stylefeng.guns.modular.investigation.service.impl;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationPdf;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationInfoMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationInfoParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationInfoResult;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class InvestigationInfoServiceImpl extends ServiceImpl<InvestigationInfoMapper, InvestigationInfo> implements InvestigationInfoService {

    private static final String basrDir = "investigations_pdf";

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    InvestigationPdfService investigationPdfService;

    @Override
    public void add(InvestigationInfoParam param) {
        InvestigationInfo entity = getEntity(param);
        this.save(entity);
    }

    @Override
    public void delete(InvestigationInfoParam param) {
        this.removeById(getKey(param));
    }

    @Override
    public void update(InvestigationInfoParam param) {
        InvestigationInfo oldEntity = getOldEntity(param);
        InvestigationInfo newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InvestigationInfoResult findBySpec(InvestigationInfoParam param) {
        return null;
    }

    @Override
    public List<InvestigationInfoResult> findListBySpec(InvestigationInfoParam param) {
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationInfoParam param) {
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }

    @Override
    public Integer getDocumentNum(String name) {
        return this.baseMapper.getDocumentNum(name);
    }

    @Override
    @Transactional
    public void uploadPdf(HttpServletRequest request, MultipartFile file) {
        String docNum = request.getParameter("docNum");

        if (Objects.isNull(file) || StringUtils.isBlank(docNum)) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        docNum = docNum.trim();
        Long documentId = this.baseMapper.getIdByDocumentNum(docNum);

        //生成文件的唯一id
        String fileId = IdWorker.getIdStr();
        //获取文件后缀
        String fileSuffix = ToolUtil.getFileSuffix(file.getOriginalFilename());
        //生成文件的最终名称
        String finalName = file.getOriginalFilename();
        String fileSavePath = ConstantsContext.getFileUploadPath();

        fileSavePath = fileSavePath.endsWith(File.separator) ? fileSavePath : fileSavePath + File.separator;
        fileSavePath = fileSavePath + basrDir + File.separator + documentId + File.separator;

        String filenameWithoutSuffix = finalName.substring(0, finalName.length() - 4);
        Long unitId = this.baseMapper.getUnitIdByName(filenameWithoutSuffix);

        try {
            //保存文件到指定目录
            File newFile = FileUtils.getFile(fileSavePath + finalName);
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdir();
            }

            FileUtils.writeByteArrayToFile(newFile, file.getBytes(), false);

            //保存文件信息
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileId(fileId);
            fileInfo.setFileName(finalName);
            fileInfo.setFileSuffix(fileSuffix);
            fileInfo.setFilePath(fileSavePath + finalName);
            fileInfo.setFinalName(finalName);
            //计算文件大小kb
            long kb = new BigDecimal(file.getSize())
                    .divide(BigDecimal.valueOf(1024))
                    .setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
            fileInfo.setFileSizeKb(kb);
            fileInfoService.save(fileInfo);

            InvestigationPdf investigationPdf = new InvestigationPdf();
            investigationPdf.setUnitId(unitId);
            investigationPdf.setInfoId(documentId);
            investigationPdf.setFileId(fileId);
            investigationPdf.setFileName(filenameWithoutSuffix);
            investigationPdf.setCreateBy(currentUser.getId());
            // 保存协查通知书信息
            investigationPdfService.save(investigationPdf);
        } catch (Exception e) {
            log.error("上传文件错误！", e);
            FileUtils.deleteQuietly(FileUtils.getFile(fileSavePath));
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

    }

    private Serializable getKey(InvestigationInfoParam param) {
        return param.getInfoId();
    }

    private Page getPageContext() {
        String x = "123";
        return LayuiPageFactory.defaultPage();
    }

    private InvestigationInfo getOldEntity(InvestigationInfoParam param) {
        return this.getById(getKey(param));
    }


    private InvestigationInfo getEntity(InvestigationInfoParam param) {
        InvestigationInfo entity = new InvestigationInfo();
        ToolUtil.copyProperties(param, entity);
        return entity;
    }

}
