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
 * @author Alan-孔
 * @since 2020-04-25
 */
@TableName("e365_integration")
public class Integration implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mrcs")
    private Integer mrcs;

    @TableField("mrjf")
    private Integer mrjf;

    @TableField("mrdk")
    private Integer mrdk;

    @TableField("mzcs")
    private Integer mzcs;

    @TableField("mzjf")
    private Integer mzjf;

    @TableField("mzdk")
    private Integer mzdk;

    @TableField("mycs")
    private Integer mycs;

    @TableField("myjf")
    private Integer myjf;

    @TableField("mydk")
    private Integer mydk;

      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

      @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMrcs() {
        return mrcs;
    }

    public void setMrcs(Integer mrcs) {
        this.mrcs = mrcs;
    }

    public Integer getMrjf() {
        return mrjf;
    }

    public void setMrjf(Integer mrjf) {
        this.mrjf = mrjf;
    }

    public Integer getMrdk() {
        return mrdk;
    }

    public void setMrdk(Integer mrdk) {
        this.mrdk = mrdk;
    }

    public Integer getMzcs() {
        return mzcs;
    }

    public void setMzcs(Integer mzcs) {
        this.mzcs = mzcs;
    }

    public Integer getMzjf() {
        return mzjf;
    }

    public void setMzjf(Integer mzjf) {
        this.mzjf = mzjf;
    }

    public Integer getMzdk() {
        return mzdk;
    }

    public void setMzdk(Integer mzdk) {
        this.mzdk = mzdk;
    }

    public Integer getMycs() {
        return mycs;
    }

    public void setMycs(Integer mycs) {
        this.mycs = mycs;
    }

    public Integer getMyjf() {
        return myjf;
    }

    public void setMyjf(Integer myjf) {
        this.myjf = myjf;
    }

    public Integer getMydk() {
        return mydk;
    }

    public void setMydk(Integer mydk) {
        this.mydk = mydk;
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

    @Override
    public String toString() {
        return "Integration{" +
        "id=" + id +
        ", mrcs=" + mrcs +
        ", mrjf=" + mrjf +
        ", mrdk=" + mrdk +
        ", mzcs=" + mzcs +
        ", mzjf=" + mzjf +
        ", mzdk=" + mzdk +
        ", mycs=" + mycs +
        ", myjf=" + myjf +
        ", mydk=" + mydk +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        "}";
    }
}
