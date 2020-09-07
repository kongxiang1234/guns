package cn.stylefeng.guns.modular.e365.model.result;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
 * @since 2020-04-23
 */
@Data
public class BreakResult implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 配图
     */
    private String pic;
    private String wzTypeV;
    private String createTime;

    private String createUser;

    private String updateTime;

    private String updateUser;

    /**
     * 督查本体
     */

    private String dcNoumenon;
    /**
     * 通知书编号
     */

    private String dcNumber;
    /**
     * 督查日期
     */


    private String dcTime;

    private String bdcTime;

    private String edcTime;
    /**
     * 作业内容
     */

    private String workContent;

    private String content;
    /**
     * 违章类别：1   2 Ⅱ 3 Ⅲ
     */

    private String wzStatus;

    /**
     * 违章一级分类
     */

    private String oneSecondary;
    /**
     * 违章二级分类
     */

    private String secondary;
    /**
     * 现场类别
     */

    private String siteCategory;
    /**
     * 督查形式
     */

    private String supervision;
    /**
     * 施工单位
     */

    private String construction;
    /**
     * 违章记分
     */

    private Integer score;
    /**
     * 违章人员
     */

    private String wzPeople;

    private Integer waibao;
    /**
     * 身份证号码
     */

    private String idCard;
    /**
     * 违章人员记分
     */

    private String personnelScore;
    /**
     * 人员性质
     */

    private String nature;
    /**
     * 考核金额
     */

    private String money;
    /**
     * 项目管理单位
     */

    private String xmUnit;
    /**
     * 项目管理单位记分
     */

    private String unitScore;

    /**
     * 是否闭环
     */

    private String closed;


    /**
     * 备注
     */

    private String descs;
}
