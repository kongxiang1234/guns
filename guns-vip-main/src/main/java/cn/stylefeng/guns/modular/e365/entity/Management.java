package cn.stylefeng.guns.modular.e365.entity;

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
 * @author Alan _孔
 * @since 2020-04-17
 */
@TableName("e365_management")
public class Management implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    @TableField("number")
    private String number;
    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 试题类型 1单选题，2多选题，3判断题
     */
    @TableField("questions_type")
    private String questionsType;

    /**
     * 所属专业 1变电 2非电气 3 输电
     */
    @TableField("specialty")
    private String specialty;
    @TableField("ztype")
    private Integer ztype;
    /**
     * 难易程度 高 1 中 2 低3
     */
    @TableField("type")
    private String type;

    @TableField("rolename")
    private String rolename;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * 启用状态 1启用 2禁用
     */
    @TableField("status")
    private String status;

      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

      @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

      @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

    public Integer getZtype() {
        return ztype;
    }

    public void setZtype(Integer ztype) {
        this.ztype = ztype;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionsType() {
        return questionsType;
    }

    public void setQuestionsType(String questionsType) {
        this.questionsType = questionsType;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public String toString() {
        return "Management{" +
        "id=" + id +
        ", title=" + title +
        ", questionsType=" + questionsType +
        ", specialty=" + specialty +
        ", type=" + type +
        ", status=" + status +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        ", updateTime=" + updateTime +
        ", updateUser=" + updateUser +
                ", number=" + number +
        "}";
    }
}
