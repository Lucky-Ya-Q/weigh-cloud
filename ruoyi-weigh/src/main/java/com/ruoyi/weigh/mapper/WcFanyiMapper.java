package com.ruoyi.weigh.mapper;

import java.util.List;
import com.ruoyi.weigh.domain.WcFanyi;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 翻译Mapper接口
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
public interface WcFanyiMapper extends BaseMapper<WcFanyi>
{/**
 * 查询翻译列表
 *
 * @param wcFanyi 翻译
 * @return 翻译集合
 */
    List<WcFanyi> selectWcFanyiList(WcFanyi wcFanyi);
}
