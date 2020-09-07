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
 * @author Alan_孔
 * @since 2020-04-22
 */
@Data
@TableName("e365_Integral_log")
public class IntegralLog implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField("userId")
    private Long userId;

    /**
     * 内容
     */
    @TableField("name")
    private String name;

    /**
     * 图片
     */
    @TableField("score")
    private String score;

    @TableField(value = "date", fill = FieldFill.INSERT)
    private Date date;

}
