package cn.stylefeng.guns.modular.e365.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan-孔
 * @since 2020-04-23
 */
@TableName("e365_break")
@Data
public class Break implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 督查本体
     */
    @TableField("dc_noumenon")
    private String dcNoumenon;
    /**
     * 通知书编号
     */
    @TableField("dc_number")
    private String dcNumber;
    /**
     * 督查日期
     */
    @TableField("dc_time")
    private Date dcTime;
    /**
     * 作业内容
     */
    @TableField("work_content")
    private String workContent;
    /**
     * 违章情况
     */
    @TableField("content")
    private String content;

    /**
     * 违章级别：1   2 Ⅱ 3 Ⅲ
     */
    @TableField("wz_status")
    private String wzStatus;
    /**
     * 违章一级分类
     */
    @TableField("one_secondary")
    private String oneSecondary;
    /**
     * 违章二级分类
     */
    @TableField("secondary")
    private String secondary;
    /**
     * 现场类别
     */
    @TableField("site_category")
    private String siteCategory;
    /**
     * 督查形式
     */
    @TableField("supervision")
    private String supervision;
    /**
     * 施工单位
     */
    @TableField("construction")
    private String construction;
    /**
     * 违章记分
     */
    @TableField("score")
    private Integer score;
    /**
     * 违章人员
     */
    @TableField("wz_people")
    private String wzPeople;
    /**
     * 身份证号码
     */
    @TableField("idcard")
    private String idCard;
    /**
     * 违章人员记分
     */
    @TableField("personnel_score")
    private String personnelScore;
    /**
     * 人员性质
     */
    @TableField("nature")
    private String nature;
    /**
     * 考核金额
     */
    @TableField("money")
    private String money;
    /**
     * 项目管理单位
     */
    @TableField("xm_unit")
    private String xmUnit;
    /**
     * 项目管理单位记分
     */
    @TableField("unit_score")
    private String unitScore;
    /**
     * 是否闭环
     */
    @TableField("closed")
    private String closed;

    /**
     * 配图
     */
    @TableField("pic")
    private String pic;
    /**
     * 计分
     */
    @TableField("deduction")
    private Integer deduction;

    /**
     * 是否考核外包单位
     */
    @TableField("waibao")
    private Integer waibao;
    /**
     * 备注
     */
    @TableField("descs")
    private String descs;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;


}
