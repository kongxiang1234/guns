package cn.stylefeng.guns.modular.e365.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Alan
 * @since 2020-04-18
 */
@TableName("e365_paper")
public class Paper implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷编号
     */
    @TableField("sjbh")
    private String sjbh;

    /**
     * 试卷名称
     */
    @TableField("sjname")
    private String sjname;

    /**
     * 所属专业
     */
    @TableField("sszy")
    private String sszy;

    /**
     * 单低
     */
    @TableField("danxtd")
    private String danxtd;

    /**
     * 单中
     */
    @TableField("danxtz")
    private String danxtz;

    /**
     * 单选题高
     */
    @TableField("danxtg")
    private String danxtg;

    /**
     * 单选题分数
     */
    @TableField("danxtValue")
    private Integer danxtValue;

    /**
     * 多低
     */
    @TableField("duoxtd")
    private String duoxtd;

    /**
     * 多中
     */
    @TableField("duoxtz")
    private String duoxtz;

    /**
     * 多选题高
     */
    @TableField("duoxtg")
    private String duoxtg;

    /**
     * 多选题分数
     */
    @TableField("duoxtValue")
    private Integer duoxtValue;

    /**
     * 判低
     */
    @TableField("pdtd")
    private String pdtd;

    /**
     * 判中
     */
    @TableField("pdtz")
    private String pdtz;

    /**
     * 判断题高
     */
    @TableField("pdtg")
    private String pdtg;

    /**
     * 判断题分数
     */
    @TableField("pdtValue")
    private Integer pdtValue;

    /**
     * 合计题目数
     */
    @TableField("tmtotle")
    private Integer tmtotle;

    /**
     * 题目分数
     */
    @TableField("tmfs")
    private Integer tmfs;

    /**
     * 考试开始时间
     */
    @TableField("btime")
    private Date btime;

    /**
     * 考试结束时间
     */
    @TableField("etime")
    private Date etime;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 1：未发布 2：已发布3考试中4已结束
     */
    @TableField("type")
    private String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSjbh() {
        return sjbh;
    }

    public void setSjbh(String sjbh) {
        this.sjbh = sjbh;
    }

    public String getSjname() {
        return sjname;
    }

    public void setSjname(String sjname) {
        this.sjname = sjname;
    }

    public String getSszy() {
        return sszy;
    }

    public void setSszy(String sszy) {
        this.sszy = sszy;
    }

    public String getDanxtd() {
        return danxtd;
    }

    public void setDanxtd(String danxtd) {
        this.danxtd = danxtd;
    }

    public String getDanxtz() {
        return danxtz;
    }

    public void setDanxtz(String danxtz) {
        this.danxtz = danxtz;
    }

    public String getDanxtg() {
        return danxtg;
    }

    public void setDanxtg(String danxtg) {
        this.danxtg = danxtg;
    }

    public Integer getDanxtValue() {
        return danxtValue;
    }

    public void setDanxtValue(Integer danxtValue) {
        this.danxtValue = danxtValue;
    }

    public String getDuoxtd() {
        return duoxtd;
    }

    public void setDuoxtd(String duoxtd) {
        this.duoxtd = duoxtd;
    }

    public String getDuoxtz() {
        return duoxtz;
    }

    public void setDuoxtz(String duoxtz) {
        this.duoxtz = duoxtz;
    }

    public String getDuoxtg() {
        return duoxtg;
    }

    public void setDuoxtg(String duoxtg) {
        this.duoxtg = duoxtg;
    }

    public Integer getDuoxtValue() {
        return duoxtValue;
    }

    public void setDuoxtValue(Integer duoxtValue) {
        this.duoxtValue = duoxtValue;
    }

    public String getPdtd() {
        return pdtd;
    }

    public void setPdtd(String pdtd) {
        this.pdtd = pdtd;
    }

    public String getPdtz() {
        return pdtz;
    }

    public void setPdtz(String pdtz) {
        this.pdtz = pdtz;
    }

    public String getPdtg() {
        return pdtg;
    }

    public void setPdtg(String pdtg) {
        this.pdtg = pdtg;
    }

    public Integer getPdtValue() {
        return pdtValue;
    }

    public void setPdtValue(Integer pdtValue) {
        this.pdtValue = pdtValue;
    }

    public Integer getTmtotle() {
        return tmtotle;
    }

    public void setTmtotle(Integer tmtotle) {
        this.tmtotle = tmtotle;
    }

    public Integer getTmfs() {
        return tmfs;
    }

    public void setTmfs(Integer tmfs) {
        this.tmfs = tmfs;
    }

    public Date getBtime() {
        return btime;
    }

    public void setBtime(Date btime) {
        this.btime = btime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id=" + id +
                ", sjbh=" + sjbh +
                ", sjname=" + sjname +
                ", sszy=" + sszy +
                ", danxtd=" + danxtd +
                ", danxtz=" + danxtz +
                ", danxtg=" + danxtg +
                ", danxtValue=" + danxtValue +
                ", duoxtd=" + duoxtd +
                ", duoxtz=" + duoxtz +
                ", duoxtg=" + duoxtg +
                ", duoxtValue=" + duoxtValue +
                ", pdtd=" + pdtd +
                ", pdtz=" + pdtz +
                ", pdtg=" + pdtg +
                ", pdtValue=" + pdtValue +
                ", tmtotle=" + tmtotle +
                ", tmfs=" + tmfs +
                ", btime=" + btime +
                ", etime=" + etime +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", type=" + type +
                "}";
    }
}
