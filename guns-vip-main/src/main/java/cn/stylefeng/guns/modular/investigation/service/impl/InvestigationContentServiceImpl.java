package cn.stylefeng.guns.modular.investigation.service.impl;
import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.auth.model.LoginUser;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.base.pojo.page.LayuiPageFactory;
import cn.stylefeng.guns.base.pojo.page.LayuiPageInfo;
import cn.stylefeng.guns.modular.investigation.entity.InvestigationContent;
import cn.stylefeng.guns.modular.investigation.mapper.InvestigationContentMapper;
import cn.stylefeng.guns.modular.investigation.model.params.InvestigationContentParam;
import cn.stylefeng.guns.modular.investigation.model.result.InvestigationContentResult;
import cn.stylefeng.guns.modular.investigation.service.InvestigationContentService;
import cn.stylefeng.guns.sys.core.exception.enums.BizExceptionEnum;
import cn.stylefeng.guns.sys.modular.system.entity.FileInfo;
import cn.stylefeng.guns.sys.modular.system.service.FileInfoService;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import cn.stylefeng.roses.kernel.model.exception.enums.CoreExceptionEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.postgresql.util.URLCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@Service
public class InvestigationContentServiceImpl extends ServiceImpl<InvestigationContentMapper, InvestigationContent> implements InvestigationContentService {

    private static String baseDir = "investigations";
    @Autowired
    FileInfoService fileInfoService;

    @Override
    public void add(InvestigationContentParam param){
        InvestigationContent entity = getEntity(param);
        this.save(entity);
        entity.getInfoId();
    }

    @Override
    public void delete(InvestigationContentParam param){
        this.removeById(getKey(param));
    }

    @Override
    public void update(InvestigationContentParam param){
        InvestigationContent oldEntity = getOldEntity(param);
        InvestigationContent newEntity = getEntity(param);
        ToolUtil.copyProperties(newEntity, oldEntity);
        this.updateById(newEntity);
    }

    @Override
    public InvestigationContentResult findBySpec(InvestigationContentParam param){
        return null;
    }

    @Override
    public List<InvestigationContentResult> findListBySpec(InvestigationContentParam param){
        return null;
    }

    @Override
    public LayuiPageInfo findPageBySpec(InvestigationContentParam param){
        Page pageContext = getPageContext();
        IPage page = this.baseMapper.customPageList(pageContext, param);
        return LayuiPageFactory.createPageInfo(page);
    }
    /**
     * 获取我发出的协查申请（根据登陆用户获取）
     * @author hujt
     * @Date 2020-09-09
     */
    @Override
    public List<Map<String, Object>> getinvestigationInfoListByLoginUser(String userName) {
        return this.baseMapper.getinvestigationInfoListByLoginUser(userName);
    }

    @Override
    public List<Map<String, Object>> investigationInfoList() {
        return this.baseMapper.investigationInfoList();
    }

    @Override
    public List<Map<String, Object>> getinvestigationInfoListByStatus() {
        return this.baseMapper.getinvestigationInfoListByStatus();
    }

    private Serializable getKey(InvestigationContentParam param){
        return param.getContentId();
    }

    private Page getPageContext() {
        return LayuiPageFactory.defaultPage();
    }

    private InvestigationContent getOldEntity(InvestigationContentParam param) {
        return this.getById(getKey(param));
    }

    private InvestigationContent getEntity(InvestigationContentParam param) {
        InvestigationContent entity = new InvestigationContent();
        ToolUtil.copyProperties(param, entity);
//        LoginUser currentUser = LoginContextHolder.getContext().getUser();
//        currentUser.getName()
        return entity;
    }
    @Override
    public List<Map<String, Object>> getInvestigationInfoByid(InvestigationContentParam investigationContentParam){
        return this.baseMapper.getInvestigationInfoByid(investigationContentParam);
    }

    /**
     * 协查申请审核
     * @param param
     * @return
     */
    @Override
    public void editInvestigationContent(InvestigationContentParam param) {
        this.baseMapper.editInvestigationContent(param);
    }

    /**
     * 协查申请删除
     * @param infoId
     */
    @Override
    public void deleteinvestigationInfoById(String infoId) {
        this.baseMapper.deleteinvestigationInfoById(infoId);
        this.baseMapper.deleteinvestigationObjectById(infoId);
    }


    /**
     * 上传文件
     *
     * @author fengshuonan
     * @Date 2019-05-04 17:18
     */
    @Override
    @Transactional
    public String uploadFile(HttpServletRequest request, MultipartFile file) {

        if (Objects.isNull(file)) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }

        LoginUser currentUser = LoginContextHolder.getContext().getUser();
        if (currentUser == null) {
            throw new ServiceException(CoreExceptionEnum.NO_CURRENT_USER);
        }

        String unitName = this.baseMapper.getUnitByDeptId(currentUser.getDeptId());
        if (StringUtils.isBlank(unitName)) {
            throw new ServiceException(1001, "未能在协查机构查询到您的机构！");
        }


        //生成文件的唯一id
        String fileId = IdWorker.getIdStr();
        //获取文件后缀
        String fileSuffix = ToolUtil.getFileSuffix(file.getOriginalFilename());
        //生成文件的最终名称
        String finalName = unitName + "_" + fileId + "." + fileSuffix;
        String fileSavePath = ConstantsContext.getFileUploadPath();
        fileSavePath = fileSavePath.endsWith(File.separator) ? fileSavePath : fileSavePath + File.separator;
        fileSavePath = fileSavePath + baseDir + File.separator;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        fileSavePath = fileSavePath + date + File.separator;


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
        } catch (Exception e) {
            log.error("上传文件错误！", e);
            FileUtils.deleteQuietly(FileUtils.getFile(fileSavePath));
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return fileId;
    }

    @Override
    public void downFiles(HttpServletResponse response) {
        String fileSavePath = ConstantsContext.getFileUploadPath();
        fileSavePath = fileSavePath.endsWith(File.separator) ? fileSavePath : fileSavePath + File.separator;
        fileSavePath = fileSavePath + baseDir + File.separator;

        if (!Files.exists(Paths.get(fileSavePath))) {
            throw new ServiceException(700, "No Files To Download!");
        }

        Collection<File> files = FileUtils.listFiles(FileUtils.getFile(fileSavePath), FileFilterUtils.fileFileFilter(), DirectoryFileFilter.INSTANCE);
        if (CollectionUtils.isEmpty(files)) {
            throw new ServiceException(700, "No Files To Download!");
        }

        String fileName = "";
        String contentType = "";
        Path filePath = null;
        for (File file : files) {
            fileName = file.getName();
            filePath = Paths.get(file.getAbsolutePath());
            try {
                contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
            } catch (Exception e) {
                throw new ServiceException(701, "Download Error!");
            }
        }


        OutputStream outputStream = null;
        SeekableByteChannel seekableByteChannel = null;
        response.setContentType("application/octet-stream");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Expires", "0");

        if (files.size() == 1) {
            response.setHeader("content-type", contentType);
            response.setHeader("Content-Disposition", "attachment; filename=" + URLCoder.encode(fileName));
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                outputStream = new BufferedOutputStream(response.getOutputStream());
                seekableByteChannel = Files.newByteChannel(filePath, StandardOpenOption.READ);
                int len = seekableByteChannel.read(byteBuffer);
                while (len != -1) {
                    byteBuffer.flip();
                    while (byteBuffer.hasRemaining()) {
                        outputStream.write(byteBuffer.get());
                    }
                    byteBuffer.clear();
                    len = seekableByteChannel.read(byteBuffer);
                }
                outputStream.flush();
            } catch (Exception e) {
                throw new ServiceException(701, "Download Error!");
            } finally {
                if (Objects.nonNull(seekableByteChannel)) {
                    try {
                        seekableByteChannel.close();
                    } catch (IOException ignore) {
                    }
                }

                if (Objects.nonNull(outputStream)) {
                    try {
                        outputStream.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        } else {
            response.setHeader("content-type", "application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=investigations.zip");
            ZipOutputStream zipOutputStream = null;
            WritableByteChannel destChannel = null;
            
            try {
                zipOutputStream = new ZipOutputStream(response.getOutputStream());
                destChannel = Channels.newChannel(zipOutputStream);
                
                for (File file : files) {
                    String entryName = file.getAbsolutePath().replace(fileSavePath, "");
                    ZipEntry zipEntry = new ZipEntry(entryName);
                    zipOutputStream.putNextEntry(zipEntry);

                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    seekableByteChannel = Files.newByteChannel(Paths.get(file.getAbsolutePath()), StandardOpenOption.READ);
                    
                    int len = seekableByteChannel.read(byteBuffer);
                    while (len != -1) {
                        byteBuffer.flip();
                        while (byteBuffer.hasRemaining()) {
                            destChannel.write(byteBuffer);
                        }
                        byteBuffer.clear();
                        len = seekableByteChannel.read(byteBuffer);
                    }
                    
                    byteBuffer.clear();
                    seekableByteChannel.close();
                }
                zipOutputStream.flush();
            } catch (Exception e) {
                throw new ServiceException(701, "Download Error!");
            } finally {
    
                if (Objects.nonNull(seekableByteChannel)) {
                    try {
                        seekableByteChannel.close();
                    } catch (IOException ignore) {
                    }
                }
    
                if (Objects.nonNull(destChannel)) {
                    try {
                        destChannel.close();
                    } catch (IOException ignore) {
                    }
                }
                
                if (Objects.nonNull(zipOutputStream)) {
                    try {
                        zipOutputStream.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        }
    }

    /**
     * 普通搜索
     * @param
     * @return
     */
    @Override
    public List<Map<String, Object>> getInvestigationInfoListBySearch(String param){
        return this.baseMapper.getInvestigationInfoListBySearch(param);
    }

    /**
     * 高级搜索
     * @param
     * @return
     */
    @Override
    public List<Map<String, Object>> getInvestigationInfoListByHeighSearch(InvestigationContentParam param) {
        return this.baseMapper.getInvestigationInfoListByHeighSearch(param);
    }

}
