package com.ruoyi.weigh.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 称重对象 wc_chengzhong
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WcChengzhong extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String chepai;

    /** 进厂时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "进厂时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time1;

    /** 出厂时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出厂时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time2;

    /** 毛重 */
    @Excel(name = "毛重")
    private String mao;

    /** 皮重 */
    @Excel(name = "皮重")
    private String pi;

    /** 净重 */
    @Excel(name = "净重")
    private String jing;

    /** 发货单位 */
    @Excel(name = "发货单位")
    private String fahuo;

    /** 收货单位 */
    @Excel(name = "收货单位")
    private String shouhuo;

    /** 运输单位 */
    @Excel(name = "运输单位")
    private String yunshu;

    /** 照片1 */
    @Excel(name = "照片1")
    private String photo1;

    /** 照片2 */
    @Excel(name = "照片2")
    private String photo2;

    /** 照片3 */
    @Excel(name = "照片3")
    private String photo3;

    /** 照片4 */
    @Excel(name = "照片4")
    private String photo4;

    /** 照片5 */
    @Excel(name = "照片5")
    private String photo5;

    /** 照片6 */
    @Excel(name = "照片6")
    private String photo6;

    /** 照片7 */
    @Excel(name = "照片7")
    private String photo7;

    /** 照片8 */
    @Excel(name = "照片8")
    private String photo8;

    /** 备注1 */
    @Excel(name = "备注1")
    private String remark1;

    /** 备注2 */
    @Excel(name = "备注2")
    private String remark2;

    /** 备注3 */
    @Excel(name = "备注3")
    private String remark3;

    /** 备注4 */
    @Excel(name = "备注4")
    private String remark4;

    /** 备注5 */
    @Excel(name = "备注5")
    private String remark5;

    /** 备注6 */
    @Excel(name = "备注6")
    private String remark6;

    /** 备注7 */
    @Excel(name = "备注7")
    private String remark7;

    /** 备注8 */
    @Excel(name = "备注8")
    private String remark8;

    /** 备注9 */
    @Excel(name = "备注9")
    private String remark9;

    /** 备注10 */
    @Excel(name = "备注10")
    private String remark10;

    /** 备注11 */
    @Excel(name = "备注11")
    private String remark11;

    /** 备注12 */
    @Excel(name = "备注12")
    private String remark12;

    /** 备注13 */
    @Excel(name = "备注13")
    private String remark13;

    /** 备注14 */
    @Excel(name = "备注14")
    private String remark14;

    /** 备注15 */
    @Excel(name = "备注15")
    private String remark15;

    /**
     * 用户ID
     */
    @TableField(exist = true, fill = FieldFill.INSERT)
    private Long userId;

    /**
     * 部门ID
     */
    @TableField(exist = true, fill = FieldFill.INSERT)
    private Long deptId;
}
