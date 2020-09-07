package cn.stylefeng.guns.modular.e365.model.result;

import lombok.Data;
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
public class FeedbackResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String feedbackSuggestions;

    private String feedbackType;

    private String feedbackContent;
    private String createTime;


    private String createUser;


    private String updateTime;


    private String updateUser;
}
