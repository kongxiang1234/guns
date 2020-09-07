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
 * @since 2020-05-01
 */
@Data
public class PapermanageParam implements Serializable, BaseValidatingParam {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private Long managementId;

    private String paperNuber;

    @Override
    public String checkParam() {
        return null;
    }

}
