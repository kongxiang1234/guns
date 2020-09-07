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
public class ManagementResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String number;
    private Long id;
    private String rolename;
    /**
     * 标题
     */
    private String title;

    /**
     * 试题类型 1单选题，2多选题，3判断题
     */
    private String questionsType;

    /**
     * 所属专业 1变电 2非电气 3 输电
     */
    private String specialty;

    /**
     * 难易程度 高 1 中 2 低3
     */
    private String type;

    /**
     * 启用状态 1启用 2禁用
     */
    private String status;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}
