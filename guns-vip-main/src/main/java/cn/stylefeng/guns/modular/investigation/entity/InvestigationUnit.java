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
@TableName("investigation_unit")
public class InvestigationUnit implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
      @TableId(value = "unit_id", type = IdType.ID_WORKER)
    private Long unitId;

    /**
     * 单位名称
     */
    @TableField("unit_name")
    private String unitName;

    /**
     * 单位类型
     */
    @TableField("unit_type")
    private String unitType;

    /**
     * 单位地址
     */
    @TableField("unit_addr")
    private String unitAddr;

    /**
     * 单位logo
     */
    @TableField("unit_logo")
    private String unitLogo;

    /**
     * 负责人姓名
     */
    @TableField("unit_leader_name")
    private String unitLeaderName;

    /**
     * 身份证号
     */
    @TableField("unit_leader_idCard")
    private String unitLeaderIdcard;

    /**
     * 职位
     */
    @TableField("unit_leader_position")
    private String unitLeaderPosition;

    /**
     * 手机号码
     */
    @TableField("unit_leader_phoneNum")
    private String unitLeaderPhonenum;

    /**
     * 邮箱
     */
    @TableField("unit_leader_email")
    private String unitLeaderEmail;

      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField("create_by")
    private String createBy;

      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField("update_by")
    private String updateBy;


    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitAddr() {
        return unitAddr;
    }

    public void setUnitAddr(String unitAddr) {
        this.unitAddr = unitAddr;
    }

    public String getUnitLogo() {
        return unitLogo;
    }

    public void setUnitLogo(String unitLogo) {
        this.unitLogo = unitLogo;
    }

    public String getUnitLeaderName() {
        return unitLeaderName;
    }

    public void setUnitLeaderName(String unitLeaderName) {
        this.unitLeaderName = unitLeaderName;
    }

    public String getUnitLeaderIdcard() {
        return unitLeaderIdcard;
    }

    public void setUnitLeaderIdcard(String unitLeaderIdcard) {
        this.unitLeaderIdcard = unitLeaderIdcard;
    }

    public String getUnitLeaderPosition() {
        return unitLeaderPosition;
    }

    public void setUnitLeaderPosition(String unitLeaderPosition) {
        this.unitLeaderPosition = unitLeaderPosition;
    }

    public String getUnitLeaderPhonenum() {
        return unitLeaderPhonenum;
    }

    public void setUnitLeaderPhonenum(String unitLeaderPhonenum) {
        this.unitLeaderPhonenum = unitLeaderPhonenum;
    }

    public String getUnitLeaderEmail() {
        return unitLeaderEmail;
    }

    public void setUnitLeaderEmail(String unitLeaderEmail) {
        this.unitLeaderEmail = unitLeaderEmail;
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
        return "InvestigationUnit{" +
        "unitId=" + unitId +
        ", unitName=" + unitName +
        ", unitType=" + unitType +
        ", unitAddr=" + unitAddr +
        ", unitLogo=" + unitLogo +
        ", unitLeaderName=" + unitLeaderName +
        ", unitLeaderIdcard=" + unitLeaderIdcard +
        ", unitLeaderPosition=" + unitLeaderPosition +
        ", unitLeaderPhonenum=" + unitLeaderPhonenum +
        ", unitLeaderEmail=" + unitLeaderEmail +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        ", updateTime=" + updateTime +
        ", updateBy=" + updateBy +
        "}";
    }
}
