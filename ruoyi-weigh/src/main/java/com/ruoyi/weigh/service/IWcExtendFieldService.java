package com.ruoyi.weigh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.weigh.domain.WcExtendField;

import java.util.List;

/**
 * 扩展字段Service接口
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
public interface IWcExtendFieldService extends IService<WcExtendField> {
    /**
     * 查询扩展字段列表
     *
     * @param wcExtendField 扩展字段
     * @return 扩展字段集合
     */
    List<WcExtendField> selectWcExtendFieldList(WcExtendField wcExtendField);

    void delete(Long[] ids);
}
