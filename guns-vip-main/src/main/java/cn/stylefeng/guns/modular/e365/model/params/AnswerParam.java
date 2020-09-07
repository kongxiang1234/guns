package cn.stylefeng.guns.modular.e365.model.params;

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
 * @author Alan _孔
 * @since 2020-04-17
 */
@Data
public class AnswerParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 试题ID
     */
    private Long managementId;

    /**
     * 单选A题目
     */
    private String asubjectSingle;

    /**
     * 单选B题目
     */
    private String bsubjectSingle;

    /**
     * 单选C题目
     */
    private String csubjectSingle;

    /**
     * 单选D题目
     */
    private String dsubjectSingle;
    private String esubjectSingle;
    private String fsubjectSingle;
    /**
     * 单选选择答案
     */
    private String single;

    /**
     * 多选A题目
     */
    private String asubjectMultiple;

    /**
     * 多选B题目
     */
    private String bsubjectMultiple;

    /**
     * 多选C题目
     */
    private String csubjectMultiple;

    /**
     * 多选D题目
     */
    private String dsubjectMultiple;
    private String esubjectMultiple;
    private String fsubjectMultiple;

    /**
     * 多选答案
     */
    private String Multiple;

    /**
     * 判断题答案
     */
    private String flag;
    private Date createTime;
    private String createUser;

    @Override
    public String checkParam() {
        return null;
    }

}
