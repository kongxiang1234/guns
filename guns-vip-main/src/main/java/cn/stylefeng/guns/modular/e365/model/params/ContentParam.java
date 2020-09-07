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
 * @author Alan_孔
 * @since 2020-04-22
 */
@Data
public class ContentParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片
     */
    private String pic;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    /**
     * 1:未发布  2：已发布
     */
    private Integer status;

    /**
     * 1：警告教育 2：安全释义3：安全发布 4：电力设施保护5：违章通报
     */
    private Integer type;

    /**
     * 阅读量
     */
    private String reading;

    /**
     *
     */
    private Integer ztype;

    @Override
    public String checkParam() {
        return null;
    }

}
