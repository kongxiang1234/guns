package cn.stylefeng.guns.modular.investigation.model.params;

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
 * @author hujt
 * @since 2020-09-09
 */
@Data
public class InvestigationContentParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long contentId;

    /**
     * 姓名（单位）
     */
    private String nameCompany;

    /**
     * 身份证号（信用代码，银行卡号） 
     */
    private String cardNumber;

    /**
     * 备注
     */
    private String undertaker;

    /**
     * 协查单位id
     */
    private String unitId;

    /**
     * 协查信息表id
     */
    private String infoId;

    /**
     * 操作时间
     */
    private Date createTime;

    /**
     * 操作人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    @Override
    public String checkParam() {
        return null;
    }

}
