package cn.stylefeng.guns.sys.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author stylefeng
 * @since 2019-04-01
 */
@Data
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /**
     * 父部门id
     */
    @TableField("pid")
    private Long pid;

    /**
     * 父级ids
     */
    @TableField("pids")
    private String pids;
    @TableField(exist = false)
    private String pidsV;
    /**
     * 简称
     */
    @TableField("simple_name")
    @NotBlank
    private String simpleName;

    /**
     * 全称
     */
    @TableField("full_name")
    @NotBlank
    private String fullName;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 版本（乐观锁保留字段）
     */
    @TableField("version")
    private Integer version;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;


    /**
     * 负责人
     */
    @TableField("person_charge")
    private String personCharge;

    /**
     * 部门电话
     */
    @TableField("dept_mobile")
    private String deptMobile;

    /**
     * 分机号
     */
    @TableField("extension_number")
    private String extensionNumber;






    @Override
    public String toString() {
        return "Dept{" +
        "deptId=" + deptId +
        ", pid=" + pid +
        ", pids=" + pids +
        ", simpleName=" + simpleName +
        ", fullName=" + fullName +
        ", description=" + description +
        ", version=" + version +
        ", sort=" + sort +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
