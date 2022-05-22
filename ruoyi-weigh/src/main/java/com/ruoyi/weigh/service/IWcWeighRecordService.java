package com.ruoyi.weigh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.weigh.domain.WcWeighRecord;

import java.util.List;

/**
 * 称重记录Service接口
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
public interface IWcWeighRecordService extends IService<WcWeighRecord> {
    /**
     * 查询称重记录列表
     *
     * @param wcWeighRecord 称重记录
     * @return 称重记录集合
     */
    List<WcWeighRecord> selectWcWeighRecordList(WcWeighRecord wcWeighRecord);

    void add(WcWeighRecord wcWeighRecord);

    void edit(WcWeighRecord wcWeighRecord);

    void delete(Long[] ids);
}
