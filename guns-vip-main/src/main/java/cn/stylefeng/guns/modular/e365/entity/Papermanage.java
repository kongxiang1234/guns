package cn.stylefeng.guns.modular.e365.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan-孔
 * @since 2020-05-01
 */
@TableName("e365_papermanage")
public class Papermanage implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("managementId")
    private Long managementId;

    @TableField("paperNuber")
    private String paperNuber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getManagementId() {
        return managementId;
    }

    public void setManagementId(Long managementId) {
        this.managementId = managementId;
    }

    public String getPaperNuber() {
        return paperNuber;
    }

    public void setPaperNuber(String paperNuber) {
        this.paperNuber = paperNuber;
    }

    @Override
    public String toString() {
        return "Papermanage{" +
        "id=" + id +
        ", managementId=" + managementId +
        ", paperNuber=" + paperNuber +
        "}";
    }
}
