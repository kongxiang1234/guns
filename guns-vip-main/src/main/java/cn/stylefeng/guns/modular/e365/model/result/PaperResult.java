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
 * @author Alan
 * @since 2020-04-18
 */
@Data
public class PaperResult implements Serializable {

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
    private Integer danxtd;

    /**
     * 单中
     */
    private Integer danxtz;

    /**
     * 单选题高
     */
    private Integer danxtg;

    /**
     * 单选题分数
     */
    private Integer danxtValue;

    /**
     * 多低
     */
    private Integer duoxtd;

    /**
     * 多中
     */
    private Integer duoxtz;

    /**
     * 多选题高
     */
    private Integer duoxtg;

    /**
     * 多选题分数
     */
    private Integer duoxtValue;

    /**
     * 判低
     */
    private Integer pdtd;

    /**
     * 判中
     */
    private Integer pdtz;

    /**
     * 判断题高
     */
    private Integer pdtg;

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

    private String danxt;
    private String duoxt;
    private String pdt;
}
