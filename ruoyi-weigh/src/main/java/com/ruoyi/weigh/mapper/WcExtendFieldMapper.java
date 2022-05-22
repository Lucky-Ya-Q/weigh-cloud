package com.ruoyi.weigh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.weigh.domain.WcExtendField;

import java.util.List;

/**
 * 扩展字段Mapper接口
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
public interface WcExtendFieldMapper extends BaseMapper<WcExtendField> {
    /**
     * 查询扩展字段列表
     *
     * @param wcExtendField 扩展字段
     * @return 扩展字段集合
     */
    List<WcExtendField> selectWcExtendFieldList(WcExtendField wcExtendField);
}
