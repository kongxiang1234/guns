package cn.stylefeng.guns.modular.investigation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@TableName("investigation_info")
public class InvestigationInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "info_id", type = IdType.ID_WORKER)
    private Long infoId;

    /**
     * 协查文书号
     */
    @TableField("Documents_number")
    private String documentsNumber;

    /**
     * 协查申请状态
     */
    @TableField("stauts")
    private String stauts;

    /**
     * 承办人
     */
    @TableField("undertaker")
    private String undertaker;

    /**
     * 最迟反馈时间
     */
    @TableField("deadLine")
    private Date deadLine;

    /**
     * 申请人
     */
    @TableField("user_id")
    private String userId;

    /**
     * 申请时间
     */
    @TableField("apply_time")
    private Date applyTime;

    /**
     * 操作时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 操作人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新时间
     */
      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField("update_by")
    private String updateBy;


    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getDocumentsNumber() {
        return documentsNumber;
    }

    public void setDocumentsNumber(String documentsNumber) {
        this.documentsNumber = documentsNumber;
    }

    public String getStauts() {
        return stauts;
    }

    public void setStauts(String stauts) {
        this.stauts = stauts;
    }

    public String getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "InvestigationInfo{" +
        "infoId=" + infoId +
        ", documentsNumber=" + documentsNumber +
        ", stauts=" + stauts +
        ", undertaker=" + undertaker +
        ", deadLine=" + deadLine +
        ", userId=" + userId +
        ", applyTime=" + applyTime +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
