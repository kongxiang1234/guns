package cn.stylefeng.guns.modular.message.model.result;

import lombok.Data;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan
 * @since 2020-09-12
 */
@Data
public class MessageManagementResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发送人
     */
    private String createUser;

    /**
     * 发送时间
     */
    private Date createTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 类型
     */
    private String type;

}
