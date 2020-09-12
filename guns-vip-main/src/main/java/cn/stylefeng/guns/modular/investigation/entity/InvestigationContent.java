package cn.stylefeng.guns.modular.investigation.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@TableName("investigation_content")
public class InvestigationContent implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "content_id", type = IdType.ID_WORKER)
    private Long contentId;

    /**
     * 姓名（单位）
     */
    @TableField("name_company")
    private String nameCompany;

    /**
     * 身份证号（信用代码，银行卡号） 
     */
    @TableField("card_number")
    private String cardNumber;

    /**
     * 备注
     */
    @TableField("undertaker")
    private String undertaker;

    /**
     * 协查单位id
     */
    @TableField("unit_id")
    private String unitId;

    /**
     * 协查信息表id
     */
    @TableField("info_id")
    private String infoId;

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


    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUndertaker() {
        return undertaker;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
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
        return "InvestigationContent{" +
        "contentId=" + contentId +
        ", nameCompany=" + nameCompany +
        ", cardNumber=" + cardNumber +
        ", undertaker=" + undertaker +
        ", unitId=" + unitId +
        ", infoId=" + infoId +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
