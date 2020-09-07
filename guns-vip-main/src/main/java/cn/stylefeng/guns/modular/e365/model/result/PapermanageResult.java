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
 * @author Alan-孔
 * @since 2020-05-01
 */
@Data
public class PapermanageResult implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private Long managementId;

    private String paperNuber;

}
