package cn.stylefeng.guns.modular.e365.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-16
 */
@TableName("e365_feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("feedback_suggestions")
    private String feedbackSuggestions;
    @TableField("userId")
    private Long userId;
    @TableField("feedback_type")
    private String feedbackType;

    @TableField("feedback_content")
    private String feedbackContent;


    @TableField(value = "create_Time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField("create_user")
    private String createUser;
    @TableField(value = "update_Time", fill = FieldFill.INSERT)
    private Date updateTime;
    @TableField("update_user")
    private String updateUser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }



    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeedbackSuggestions() {
        return feedbackSuggestions;
    }

    public void setFeedbackSuggestions(String feedbackSuggestions) {
        this.feedbackSuggestions = feedbackSuggestions;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    @Override
    public String toString() {
        return "Feedback{" +
        "id=" + id +
        ", feedbackSuggestions=" + feedbackSuggestions +
        ", feedbackType=" + feedbackType +
        ", feedbackContent=" + feedbackContent +
        "}";
    }
}
