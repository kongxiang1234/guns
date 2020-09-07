package cn.stylefeng.guns.modular.e365.model.params;

import lombok.Data;
import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-24
 */
@Data
public class IntegralParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 员工名字
     */
    private String name;

    /**
     * 员工id
     */
    private Long userId;

    /**
     * 所属单位
     */
    private String ssdw;

    /**
     * 有奖积分
     */
    private Integer yjIntegral;

    /**
     * 每日积分
     */
    private Integer mrIntegral;

    /**
     * 每周积分
     */
    private Integer mzIntegral;

    /**
     * 每月积分
     */
    private Integer myIntegral;

    /**
     * 可抵扣违章记分
     */
    private Integer dkwzIntegral;

    /**
     * 实际抵扣违章记分
     */
    private Integer sdkwzIntegral;

    /**
     * 剩余积分
     */
    private Integer syIntegral;

    private Date createTime;

    private String createUser;

    @Override
    public String checkParam() {
        return null;
    }

}
