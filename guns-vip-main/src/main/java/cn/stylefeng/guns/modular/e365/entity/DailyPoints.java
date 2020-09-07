package cn.stylefeng.guns.modular.e365.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.models.auth.In;
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
@TableName("e365_dailyPoints")
public class DailyPoints implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("unit")
    private String unit;

    @TableField("name")
    private String name;
    @TableField("totel")
    private Integer totel;
    @TableField(value = "date", fill = FieldFill.INSERT)
    private Date date;

    @TableField(exist = false)
    private String account;
}
