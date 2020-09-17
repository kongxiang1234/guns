package cn.stylefeng.guns.modular.message.model.params;

import cn.stylefeng.roses.kernel.model.validator.BaseValidatingParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan
 * @since 2020-09-12
 */
@Data
public class MessageManagementParam implements Serializable, BaseValidatingParam {

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
     * 发送对象
     */
    private String urgeObject;

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

    @Override
    public String checkParam() {
        return null;
    }

}
