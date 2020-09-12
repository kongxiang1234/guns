package cn.stylefeng.guns.modular.investigation.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@Data
public class InvestigationUnitResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long unitId;

    /**
     * 单位名称
     */
    private String unitName;

    /**
     * 单位类型
     */
    private String unitType;

    /**
     * 单位地址
     */
    private String unitAddr;

    /**
     * 单位logo
     */
    private String unitLogo;

    /**
     * 负责人姓名
     */
    private String unitLeaderName;

    /**
     * 身份证号
     */
    private String unitLeaderIdcard;

    /**
     * 职位
     */
    private String unitLeaderPosition;

    /**
     * 手机号码
     */
    private String unitLeaderPhonenum;

    /**
     * 邮箱
     */
    private String unitLeaderEmail;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}
