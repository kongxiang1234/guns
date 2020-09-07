package cn.stylefeng.guns.modular.demos.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 代理员信息
 * </p>
 *
 * @author Alan
 * @since 2020-04-15
 */
@Data
public class TGameAgentUserParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    private Integer id;

    /**
     * 角色ID
     */
    private Long RoleId;

    /**
     * 账号
     */
    private String Account;

    /**
     * 密码
     */
    private String Password;

    /**
     * 微信号
     */
    private String WeiXin;

    /**
     * 姓名
     */
    private String RealName;

    /**
     * 代理管理员ID
     */
    private Integer ManagerId;

    /**
     * 代理推广员ID
     */
    private Integer PromoterId;

    /**
     * 房卡
     */
    private Integer RoomCard;

    /**
     * 亲友圈房卡
     */
    private Integer RelativeRoomCard;

    /**
     * 0=禁止开房,1=正常收费,2=全部免费,3=代理免费（房卡免费，钻石全归代理）
     */
    private Integer RelativeCheckStatus;

    /**
     * 手机号
     */
    private String Mobile;

    /**
     * 地址
     */
    private String Address;

    /**
     * 身份证号码
     */
    private String IdCard;

    /**
     * 描述
     */
    private String Desc;

    /**
     * 创建时间
     */
    private Date Ctime;

    /**
     * 密码盐
     */
    private String Salt;

    /**
     * 活动状态 0不参与 1联通活动
     */
    private Integer ActivityStatus;

    /**
     * 状态
     */
    private Integer Status;

    @Override
    public String checkParam() {
        return null;
    }

}
