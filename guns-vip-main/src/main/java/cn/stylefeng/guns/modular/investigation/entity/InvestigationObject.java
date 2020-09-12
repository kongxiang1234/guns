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
 * @author hujuntao
 * @since 2020-09-12
 */
@TableName("investigation_object")
public class InvestigationObject implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "object_id", type = IdType.ID_WORKER)
    private Long objectId;

    /**
     * 协查通知书
     */
    @TableField("object_notice")
    private String objectNotice;

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
     * 工作证_操作人
     */
    @TableField("employee_card")
    private String employeeCard;

    /**
     * 工作证_选择
     */
    @TableField("employee_card_select")
    private String employeeCardSelect;

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


    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectNotice() {
        return objectNotice;
    }

    public void setObjectNotice(String objectNotice) {
        this.objectNotice = objectNotice;
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

    public String getEmployeeCard() {
        return employeeCard;
    }

    public void setEmployeeCard(String employeeCard) {
        this.employeeCard = employeeCard;
    }

    public String getEmployeeCardSelect() {
        return employeeCardSelect;
    }

    public void setEmployeeCardSelect(String employeeCardSelect) {
        this.employeeCardSelect = employeeCardSelect;
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
        return "InvestigationObject{" +
        "objectId=" + objectId +
        ", objectNotice=" + objectNotice +
        ", nameCompany=" + nameCompany +
        ", cardNumber=" + cardNumber +
        ", employeeCard=" + employeeCard +
        ", employeeCardSelect=" + employeeCardSelect +
        ", infoId=" + infoId +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
