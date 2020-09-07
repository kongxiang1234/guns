package cn.stylefeng.guns.modular.e365.model.result;

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

public class IntegralLogResuLt implements Serializable {

    private static final long serialVersionUID=1L;


    private Integer id;



    private Long userId;

    private String name;


    private String score;


    private String date;

}
