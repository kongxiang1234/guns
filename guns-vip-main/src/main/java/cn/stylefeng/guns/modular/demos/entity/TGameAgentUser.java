package cn.stylefeng.guns.modular.demos.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 代理员信息
 * </p>
 *
 * @author Alan
 * @since 2020-04-15
 */
@TableName("t_game_agent_user")
public class TGameAgentUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField("RoleId")
    private Long RoleId;

    /**
     * 账号
     */
    @TableField("Account")
    private String Account;

    /**
     * 密码
     */
    @TableField("Password")
    private String Password;

    /**
     * 微信号
     */
    @TableField("WeiXin")
    private String WeiXin;

    /**
     * 姓名
     */
    @TableField("RealName")
    private String RealName;

    /**
     * 代理管理员ID
     */
    @TableField("ManagerId")
    private Integer ManagerId;

    /**
     * 代理推广员ID
     */
    @TableField("PromoterId")
    private Integer PromoterId;

    /**
     * 房卡
     */
    @TableField("RoomCard")
    private Integer RoomCard;

    /**
     * 亲友圈房卡
     */
    @TableField("RelativeRoomCard")
    private Integer RelativeRoomCard;

    /**
     * 0=禁止开房,1=正常收费,2=全部免费,3=代理免费（房卡免费，钻石全归代理）
     */
    @TableField("RelativeCheckStatus")
    private Integer RelativeCheckStatus;

    /**
     * 手机号
     */
    @TableField("Mobile")
    private String Mobile;

    /**
     * 地址
     */
    @TableField("Address")
    private String Address;

    /**
     * 身份证号码
     */
    @TableField("IdCard")
    private String IdCard;

    /**
     * 描述
     */
    @TableField("Desc")
    private String Desc;

    /**
     * 创建时间
     */
    @TableField("Ctime")
    private Date Ctime;

    /**
     * 密码盐
     */
    @TableField("Salt")
    private String Salt;

    /**
     * 活动状态 0不参与 1联通活动
     */
    @TableField("ActivityStatus")
    private Integer ActivityStatus;

    /**
     * 状态
     */
    @TableField("Status")
    private Integer Status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long RoleId) {
        this.RoleId = RoleId;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String Account) {
        this.Account = Account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getWeiXin() {
        return WeiXin;
    }

    public void setWeiXin(String WeiXin) {
        this.WeiXin = WeiXin;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public Integer getManagerId() {
        return ManagerId;
    }

    public void setManagerId(Integer ManagerId) {
        this.ManagerId = ManagerId;
    }

    public Integer getPromoterId() {
        return PromoterId;
    }

    public void setPromoterId(Integer PromoterId) {
        this.PromoterId = PromoterId;
    }

    public Integer getRoomCard() {
        return RoomCard;
    }

    public void setRoomCard(Integer RoomCard) {
        this.RoomCard = RoomCard;
    }

    public Integer getRelativeRoomCard() {
        return RelativeRoomCard;
    }

    public void setRelativeRoomCard(Integer RelativeRoomCard) {
        this.RelativeRoomCard = RelativeRoomCard;
    }

    public Integer getRelativeCheckStatus() {
        return RelativeCheckStatus;
    }

    public void setRelativeCheckStatus(Integer RelativeCheckStatus) {
        this.RelativeCheckStatus = RelativeCheckStatus;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public Date getCtime() {
        return Ctime;
    }

    public void setCtime(Date Ctime) {
        this.Ctime = Ctime;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }

    public Integer getActivityStatus() {
        return ActivityStatus;
    }

    public void setActivityStatus(Integer ActivityStatus) {
        this.ActivityStatus = ActivityStatus;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "TGameAgentUser{" +
        "id=" + id +
        ", RoleId=" + RoleId +
        ", Account=" + Account +
        ", Password=" + Password +
        ", WeiXin=" + WeiXin +
        ", RealName=" + RealName +
        ", ManagerId=" + ManagerId +
        ", PromoterId=" + PromoterId +
        ", RoomCard=" + RoomCard +
        ", RelativeRoomCard=" + RelativeRoomCard +
        ", RelativeCheckStatus=" + RelativeCheckStatus +
        ", Mobile=" + Mobile +
        ", Address=" + Address +
        ", IdCard=" + IdCard +
        ", Desc=" + Desc +
        ", Ctime=" + Ctime +
        ", Salt=" + Salt +
        ", ActivityStatus=" + ActivityStatus +
        ", Status=" + Status +
        "}";
    }
}
