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
 * @since 2020-09-12
 */
@Data
public class InvestigationObjectResult implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long objectId;

    /**
     * 协查通知书
     */
    private String objectNotice;

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
    private String remarks;

    /**
     * 工作证_操作人
     */
    private String employeeCard;

    /**
     * 工作证_选择
     */
    private String employeeCardSelect;

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

}
