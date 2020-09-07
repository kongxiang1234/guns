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
 * @author Alan
 * @since 2020-04-18
 */
@Data
public class PaperParam implements Serializable, BaseValidatingParam {


    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 试卷编号
     */
    private String sjbh;

    /**
     * 试卷名称
     */
    private String sjname;

    /**
     * 所属专业
     */
    private String sszy;

    /**
     * 单低
     */
    private Integer danxtdValue;
    private Integer  danxtd;
    /**
     * 单中
     */

    private Integer danxtzValue;
    private Integer  danxtz;
    /**
     * 单选题高
     */
    private Integer danxtgValue;
    private Integer  danxtg;


    /**
     * 单选题分数
     */
    private Integer danxtValue;

    /**
     * 多低
     */
    private Integer duoxtdValue;
    private Integer  duoxtd;


    /**
     * 多中
     */
    private Integer duoxtzValue;
    private Integer  duoxtz;



    /**
     * 多选题高
     */
    private Integer duoxtgValue;
    private Integer  duoxtg;

    /**
     * 多选题分数
     */
    private Integer duoxtValue;

    /**
     * 判低
     */
    private Integer pdtdValue;
    private Integer  pdtd;


    /**
     * 判中
     */
    private Integer pdtzValue;
    private Integer  pdtz;



    /**
     * 判断题高
     */
    private Integer pdtgValue;
    private Integer  pdtg;



    /**
     * 判断题分数
     */
    private Integer pdtValue;

    /**
     * 合计题目数
     */
    private Integer tmtotle;

    /**
     * 题目分数
     */
    private Integer tmfs;

    /**
     * 考试开始时间
     */
    private Date btime;

    /**
     * 考试结束时间
     */
    private Date etime;

    private Date createTime;

    private String createUser;

    /**
     * 1：未发布 2：已发布3考试中4已结束
     */
    private String type;

    @Override
    public String checkParam() {
        return null;
    }

}
