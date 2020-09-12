package cn.stylefeng.guns.modular.investigation.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author hujt
 * @since 2020-09-09
 */
@Data
public class InvestigationInfoParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    /**
     * 主键id
     */
    private Long infoId;

    /**
     * 协查文书号
     */
    private String documentsNumber;

    /**
     * 协查申请状态
     */
    private String stauts;

    /**
     * 承办人
     */
    private String undertaker;

    /**
     * 最迟反馈时间
     */
    private Date deadLine;

    /**
     * 申请人
     */
    private String userId;

    /**
     * 申请时间
     */
    private Date applyTime;

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

    /**
     * 数据
     */
    private Map<String,Object> data;

    @Override
    public String checkParam() {
        return null;
    }

}
