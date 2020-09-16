package cn.stylefeng.guns.modular.investigation.model.result;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InvestigationPdfPageResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private long infoId;

    private String documentName;

    private long undertaker;

    private String undertakerName;

    private String applyTime;

    private int fileNum;

}
