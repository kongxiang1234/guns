package cn.stylefeng.guns.modular.e365.model.params;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @author Alan _孔
 * @since 2020-04-17
 */
@Data
public class ManagementParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;



    private Long id;

    /**
     * 标题
     */
    @Excel(name = "题干")
    private String title;

    private String selectValue;
    private String content;
    @Excel(name = "试题编码")
    private String number;
    /**
     * 试题类型 1单选题，2多选题，3判断题
     */
    @Excel(name = "试题类型")
    private String questionsType;

    /**
     * 所属专业 1变电 2非电气 3 输电
     */
    @Excel(name = "所属专业")
    private String specialty;

    /**
     * 难易程度 高 1 中 2 低3
     */
    @Excel(name = "高低程度")
    private String type;
    @Excel(name = "试题等级")
    private String rolename;
    /**
     * 启用状态 1启用 2禁用
     */
    private String status;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;
    @Excel(name = "专题类型")
    private String ztype;
    @Override
    public String checkParam() {
        return null;
    }


    @Excel(name = "选项")
    private String option;

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

    @Excel(name = "答案")
    private String single;
}
