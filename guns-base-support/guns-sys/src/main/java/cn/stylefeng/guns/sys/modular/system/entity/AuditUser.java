package cn.stylefeng.guns.sys.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan
 * @since 2020-09-17
 */
@TableName("audit_user")
public class AuditUser implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    @TableField("isdel")
    private String isdel;


    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    /**
     * 单位id
     */
    @TableField("unit_id")
    private Long unitId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 级别
     */
   /* @TableField("ranks")*/
   /* private String rank;*/

    /**
     * 审核人
     */
    @TableField("audit_by")
    private String auditBy;

    /**
     * 审核时间
     */
    @TableField("audit_time")
    private Date auditTime;

    /**
     * 审核内容
     */
    @TableField("audit_content")
    private String auditContent;

    /**
     * 审核状态
     */
    @TableField("audit_status")
    private String auditStatus;
    @TableField("userId")
    private Long userId;
     @TableField(exist = false)
    private String unitName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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



    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditContent() {
        return auditContent;
    }

    public void setAuditContent(String auditContent) {
        this.auditContent = auditContent;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "AuditUser{" +
        "id=" + id +
        ", type=" + type +
        ", unitId=" + unitId +
        ", name=" + name +
        ", mobile=" + mobile +
        ", createTime=" + createTime +
        ", createBy=" + createBy +

        ", auditBy=" + auditBy +
        ", auditTime=" + auditTime +
        ", auditContent=" + auditContent +
        ", auditStatus=" + auditStatus +
        "}";
    }
}
