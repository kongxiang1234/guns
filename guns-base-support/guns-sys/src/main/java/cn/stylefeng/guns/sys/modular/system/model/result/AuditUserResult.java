package cn.stylefeng.guns.sys.modular.system.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan
 * @since 2020-09-17
 */
@Data
public class AuditUserResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private Long userId;
    /**
     * 类型
     */
    private String type;

    /**
     * 单位id
     */
    private Long unitId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 级别
     */
    private String rank;

    /**
     * 审核人
     */
    private String auditBy;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 审核内容
     */
    private String auditContent;

    /**
     * 审核状态
     */
    private String auditStatus;

}
