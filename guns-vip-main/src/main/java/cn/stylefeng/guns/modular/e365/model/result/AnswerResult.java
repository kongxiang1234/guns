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
 * @author Alan _孔
 * @since 2020-04-17
 */
@Data
public class AnswerResult implements Serializable {

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

    /**
     * 多选答案
     */
    private String Multiple;

    /**
     * 判断题答案
     */
    private String flag;

}
