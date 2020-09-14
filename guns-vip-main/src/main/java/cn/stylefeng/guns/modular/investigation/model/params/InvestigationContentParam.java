package cn.stylefeng.guns.modular.investigation.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
     * 协查文书号
     */
    private String DocumentsNumber;

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
     *申请人
     */
    private String userId;
    /**
     *申请时间
     */
    private String applyTime;

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
     * 状态
     */
    private String stauts;

    /**
     * 更新人
     */
    private String updateBy;

    @Override
    public String checkParam() {
        return null;
    }

}
