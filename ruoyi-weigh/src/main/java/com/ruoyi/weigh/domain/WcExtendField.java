package com.ruoyi.weigh.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 扩展字段对象 wc_extend_field
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WcExtendField extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 字段代码
     */
    @Excel(name = "字段代码")
    private String code;

    /**
     * 显示名称
     */
    @Excel(name = "显示名称")
    private String name;

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
