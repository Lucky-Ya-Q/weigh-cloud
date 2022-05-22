package com.ruoyi.weigh.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 称重记录对象 wc_weigh_record
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WcWeighRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 货物名称
     */
    @Excel(name = "货物名称")
    private String goodsName;

    /**
     * 货物类型
     */
    @Excel(name = "货物类型")
    private String goodsType;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String plateNumber;

    /**
     * 称重时间1
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "称重时间1", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date weighTime1;

    /**
     * 称重时间2
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "称重时间2", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date weighTime2;

    /**
     * 发货单位
     */
    @Excel(name = "发货单位")
    private String fahuo;

    /**
     * 运输单位
     */
    @Excel(name = "运输单位")
    private String yunshu;

    /**
     * 收货单位
     */
    @Excel(name = "收货单位")
    private String shouhuo;

    /**
     * 照片
     */
    @Excel(name = "照片")
    private String photos;

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

    @TableField(exist = false)
    private List<WcExtendData> wcExtendDataList;
}
