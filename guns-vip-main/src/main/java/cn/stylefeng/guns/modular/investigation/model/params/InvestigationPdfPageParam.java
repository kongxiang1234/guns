package cn.stylefeng.guns.modular.investigation.model.params;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InvestigationPdfPageParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String documentName;

    private String undertakerName;

    private Date applyTimeStart;

    private Date applyTimeEnd;

}
