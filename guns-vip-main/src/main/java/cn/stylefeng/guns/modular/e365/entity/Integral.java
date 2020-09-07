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
 * @since 2020-04-24
 */
@TableName("e365_integral")
public class Integral implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工名字
     */
    @TableField("name")
    private String name;

    /**
     * 员工id
     */
    @TableField("userId")
    private Long userId;

    /**
     * 所属单位
     */
    @TableField("ssdw")
    private String ssdw;

    /**
     * 有奖积分
     */
    @TableField("yj_integral")
    private Integer yjIntegral;

    /**
     * 每日积分
     */
    @TableField("mr_integral")
    private Integer mrIntegral;

    /**
     * 每周积分
     */
    @TableField("mz_integral")
    private Integer mzIntegral;

    /**
     * 每月积分
     */
    @TableField("my_integral")
    private Integer myIntegral;

    /**
     * 可抵扣违章记分
     */
    @TableField("dkwz_integral")
    private Integer dkwzIntegral;

    /**
         * 实际抵扣违章记分
     */
    @TableField("sdkwz_integral")
    private Integer sdkwzIntegral;

    /**
     * 剩余积分
     */
    @TableField("sy_integral")
    private Integer syIntegral;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSsdw() {
        return ssdw;
    }

    public void setSsdw(String ssdw) {
        this.ssdw = ssdw;
    }

    public Integer getYjIntegral() {
        return yjIntegral;
    }

    public void setYjIntegral(Integer yjIntegral) {
        this.yjIntegral = yjIntegral;
    }

    public Integer getMrIntegral() {
        return mrIntegral;
    }

    public void setMrIntegral(Integer mrIntegral) {
        this.mrIntegral = mrIntegral;
    }

    public Integer getMzIntegral() {
        return mzIntegral;
    }

    public void setMzIntegral(Integer mzIntegral) {
        this.mzIntegral = mzIntegral;
    }

    public Integer getMyIntegral() {
        return myIntegral;
    }

    public void setMyIntegral(Integer myIntegral) {
        this.myIntegral = myIntegral;
    }

    public Integer getDkwzIntegral() {
        return dkwzIntegral;
    }

    public void setDkwzIntegral(Integer dkwzIntegral) {
        this.dkwzIntegral = dkwzIntegral;
    }

    public Integer getSdkwzIntegral() {
        return sdkwzIntegral;
    }

    public void setSdkwzIntegral(Integer sdkwzIntegral) {
        this.sdkwzIntegral = sdkwzIntegral;
    }

    public Integer getSyIntegral() {
        return syIntegral;
    }

    public void setSyIntegral(Integer syIntegral) {
        this.syIntegral = syIntegral;
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
        return "Integral{" +
        "id=" + id +
        ", name=" + name +
        ", userId=" + userId +
        ", ssdw=" + ssdw +
        ", yjIntegral=" + yjIntegral +
        ", mrIntegral=" + mrIntegral +
        ", mzIntegral=" + mzIntegral +
        ", myIntegral=" + myIntegral +
        ", dkwzIntegral=" + dkwzIntegral +
        ", sdkwzIntegral=" + sdkwzIntegral +
        ", syIntegral=" + syIntegral +
        ", createTime=" + createTime +
        ", createUser=" + createUser +
        "}";
    }
}
