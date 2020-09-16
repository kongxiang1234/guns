package cn.stylefeng.guns.modular.investigation.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

@TableName("investigation_pdf")
public class InvestigationPdf implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    /** 协查信息表ID */
    @TableField("info_id")
    private Long infoId;

    /**
     * 协查机构ID
     */
    @TableField("unit_id")
    private Long unitId;

    /**
     * 文件ID
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 操作时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 操作人
     */
    @TableField("create_by")
    private Long createBy;

    public InvestigationPdf() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "InvestigationPdf{" +
                "id=" + id +
                ", infoId='" + infoId + '\'' +
                ", unitId=" + unitId +
                ", fileId=" + fileId +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
