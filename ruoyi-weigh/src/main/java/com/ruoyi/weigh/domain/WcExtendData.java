package com.ruoyi.weigh.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 扩展数据对象 wc_extend_data
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WcExtendData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 称重记录ID
     */
    @Excel(name = "称重记录ID")
    private Long weighRecordId;

    /**
     * 字段代码
     */
    @Excel(name = "字段代码")
    private String code;

    /**
     * 字段值
     */
    @Excel(name = "字段值")
    private String value;

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
