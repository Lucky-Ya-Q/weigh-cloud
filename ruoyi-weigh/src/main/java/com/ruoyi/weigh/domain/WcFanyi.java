package com.ruoyi.weigh.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 翻译对象 wc_fanyi
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WcFanyi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 字段键值 */
    @Excel(name = "字段键值")
    private String fieldValue;

    /** 字段标签 */
    @Excel(name = "字段标签")
    private String fieldLabel;

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
