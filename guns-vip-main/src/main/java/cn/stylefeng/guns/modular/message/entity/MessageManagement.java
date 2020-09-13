package cn.stylefeng.guns.modular.message.entity;

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
 * @author Alan
 * @since 2020-09-12
 */
@TableName("message_management")
public class MessageManagement implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 发送人
     */
      @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 发送时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 状态
     */
    @TableField("status")
    private String status;

    /**
     * 类型
     */
    @TableField("type")
    private String type;

    /**
     * 类型
     */
    @TableField("sid")
    private String sid;


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageManagement{" +
        "id=" + id +
        ", title=" + title +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", status=" + status +
        ", type=" + type +
        "}";
    }
}
