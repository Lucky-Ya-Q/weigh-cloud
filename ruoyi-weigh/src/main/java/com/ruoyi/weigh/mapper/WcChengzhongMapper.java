package com.ruoyi.weigh.mapper;

import java.util.List;
import com.ruoyi.weigh.domain.WcChengzhong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 称重Mapper接口
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
public interface WcChengzhongMapper extends BaseMapper<WcChengzhong>
{
    /**
     * 查询称重列表
     *
     * @param wcChengzhong 称重
     * @return 称重集合
     */
    public List<WcChengzhong> selectWcChengzhongList(WcChengzhong wcChengzhong);
}
