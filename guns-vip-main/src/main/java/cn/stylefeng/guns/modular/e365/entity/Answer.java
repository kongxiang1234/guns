package cn.stylefeng.guns.modular.e365.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan _孔
 * @since 2020-04-17
 */
@TableName("e365_answer")
public class Answer implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 试题ID
     */
    @TableField("managementId")
    private Long managementId;

    /**
     * 单选A题目
     */
    @TableField("Asubject_Single")
    private String asubjectSingle;

    /**
     * 单选B题目
     */
    @TableField("Bsubject_Single")
    private String bsubjectSingle;

    /**
     * 单选C题目
     */
    @TableField("Csubject_Single")
    private String csubjectSingle;

    /**
     * 单选D题目
     */
    @TableField("Dsubject_Single")
    private String dsubjectSingle;
    /**
     * 单选D题目
     */
    @TableField("Esubject_Single")
    private String esubjectSingle;
    /**
     * 单选D题目
     */
    @TableField("Fsubject_Single")
    private String fsubjectSingle;

    /**
     * 单选选择答案
     */
    @TableField("single")
    private String single;

    /**
     * 多选A题目
     */
    @TableField("Asubject_Multiple")
    private String asubjectMultiple;

    /**
     * 多选B题目
     */
    @TableField("Bsubject_Multiple")
    private String bsubjectMultiple;

    /**
     * 多选C题目
     */
    @TableField("Csubject_Multiple")
    private String csubjectMultiple;

    /**
     * 多选D题目
     */
    @TableField("Dsubject_Multiple")
    private String dsubjectMultiple;
    /**
     * 多选D题目
     */
    @TableField("Esubject_Multiple")
    private String esubjectMultiple;
    /**
     * 多选D题目
     */
    @TableField("Fsubject_Multiple")
    private String fsubjectMultiple;

    /**
     * 多选答案
     */
    @TableField("Multiple")
    private String Multiple;

    /**
     * 判断题答案
     */
    @TableField("flag")
    private String flag;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    public String getEsubjectSingle() {
        return esubjectSingle;
    }

    public void setEsubjectSingle(String esubjectSingle) {
        this.esubjectSingle = esubjectSingle;
    }

    public String getFsubjectSingle() {
        return fsubjectSingle;
    }

    public void setFsubjectSingle(String fsubjectSingle) {
        this.fsubjectSingle = fsubjectSingle;
    }

    public String getEsubjectMultiple() {
        return esubjectMultiple;
    }

    public void setEsubjectMultiple(String esubjectMultiple) {
        this.esubjectMultiple = esubjectMultiple;
    }

    public String getFsubjectMultiple() {
        return fsubjectMultiple;
    }

    public void setFsubjectMultiple(String fsubjectMultiple) {
        this.fsubjectMultiple = fsubjectMultiple;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagementId() {
        return managementId;
    }

    public void setManagementId(Long managementId) {
        this.managementId = managementId;
    }

    public String getAsubjectSingle() {
        return asubjectSingle;
    }

    public void setAsubjectSingle(String asubjectSingle) {
        this.asubjectSingle = asubjectSingle;
    }

    public String getBsubjectSingle() {
        return bsubjectSingle;
    }

    public void setBsubjectSingle(String bsubjectSingle) {
        this.bsubjectSingle = bsubjectSingle;
    }

    public String getCsubjectSingle() {
        return csubjectSingle;
    }

    public void setCsubjectSingle(String csubjectSingle) {
        this.csubjectSingle = csubjectSingle;
    }

    public String getDsubjectSingle() {
        return dsubjectSingle;
    }

    public void setDsubjectSingle(String dsubjectSingle) {
        this.dsubjectSingle = dsubjectSingle;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getAsubjectMultiple() {
        return asubjectMultiple;
    }

    public void setAsubjectMultiple(String asubjectMultiple) {
        this.asubjectMultiple = asubjectMultiple;
    }

    public String getBsubjectMultiple() {
        return bsubjectMultiple;
    }

    public void setBsubjectMultiple(String bsubjectMultiple) {
        this.bsubjectMultiple = bsubjectMultiple;
    }

    public String getCsubjectMultiple() {
        return csubjectMultiple;
    }

    public void setCsubjectMultiple(String csubjectMultiple) {
        this.csubjectMultiple = csubjectMultiple;
    }

    public String getDsubjectMultiple() {
        return dsubjectMultiple;
    }

    public void setDsubjectMultiple(String dsubjectMultiple) {
        this.dsubjectMultiple = dsubjectMultiple;
    }

    public String getMultiple() {
        return Multiple;
    }

    public void setMultiple(String Multiple) {
        this.Multiple = Multiple;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Answer{" +
        "id=" + id +
        ", managementId=" + managementId +
        ", asubjectSingle=" + asubjectSingle +
        ", bsubjectSingle=" + bsubjectSingle +
        ", csubjectSingle=" + csubjectSingle +
        ", dsubjectSingle=" + dsubjectSingle +
        ", single=" + single +
        ", asubjectMultiple=" + asubjectMultiple +
        ", bsubjectMultiple=" + bsubjectMultiple +
        ", csubjectMultiple=" + csubjectMultiple +
        ", dsubjectMultiple=" + dsubjectMultiple +
        ", Multiple=" + Multiple +
        ", flag=" + flag +
        "}";
    }
}
