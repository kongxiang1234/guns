package cn.stylefeng.guns.modular.e365.model.params;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-04-16
 */
@Data
public class FeedbackParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String feedbackSuggestions;

    private String feedbackType;

    private String feedbackContent;

    private Date createTime;

    private Long userId;

    private String createUser;


    private Date updateTime;


    private String updateUser;
    @Override
    public String checkParam() {
        return null;
    }

}
