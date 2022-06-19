package com.ruoyi.weigh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.weigh.domain.WcChengzhong;
import com.ruoyi.weigh.domain.WcFanyi;

import java.util.List;

/**
 * 翻译Service接口
 *
 * @author lucky-ya-q
 * @date 2022-06-19
 */
public interface IWcFanyiService extends IService<WcFanyi> {
    /**
     * 查询翻译列表
     *
     * @param wcFanyi 翻译
     * @return 翻译集合
     */
     List<WcFanyi> selectWcFanyiList(WcFanyi wcFanyi);
}
