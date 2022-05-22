package com.ruoyi.weigh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.weigh.domain.WcWeighRecord;

import java.util.List;

/**
 * 称重记录Mapper接口
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
public interface WcWeighRecordMapper extends BaseMapper<WcWeighRecord> {
    /**
     * 查询称重记录列表
     *
     * @param wcWeighRecord 称重记录
     * @return 称重记录集合
     */
    List<WcWeighRecord> selectWcWeighRecordList(WcWeighRecord wcWeighRecord);
}
