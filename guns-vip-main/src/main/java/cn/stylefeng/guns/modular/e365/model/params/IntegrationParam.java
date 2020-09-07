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
 * @author Alan-孔
 * @since 2020-04-25
 */
@Data
public class IntegrationParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private Integer mrcs;

    private Integer mrjf;

    private Integer mrdk;

    private Integer mzcs;

    private Integer mzjf;

    private Integer mzdk;

    private Integer mycs;

    private Integer myjf;

    private Integer mydk;

    private Date createTime;

    private String createUser;

    @Override
    public String checkParam() {
        return null;
    }

}
