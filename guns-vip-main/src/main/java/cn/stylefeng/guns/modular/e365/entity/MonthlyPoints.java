package cn.stylefeng.guns.modular.e365.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-25
 */
@Data
@TableName("e365_monthlypoints")
public class MonthlyPoints implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("unit")
    private String unit;

    @TableField("name")
    private String name;

    @TableField("paperId")
    private Integer paperId;
    @TableField("score")
    private Integer score;
    @TableField("ranking")
    private Integer ranking;

    @TableField("dateYear")
    private String dateYear;
    @TableField("month")
    private String month;

    @TableField("userId")
    private Long userId;
}
