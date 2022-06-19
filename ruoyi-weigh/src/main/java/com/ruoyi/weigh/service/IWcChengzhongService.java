package com.ruoyi.weigh.service;

import java.util.List;
import com.ruoyi.weigh.domain.WcChengzhong;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 称重Service接口
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
public interface IWcChengzhongService extends IService<WcChengzhong>
{    /**
 * 查询称重列表
 *
 * @param wcChengzhong 称重
 * @return 称重集合
 */
List<WcChengzhong> selectWcChengzhongList(WcChengzhong wcChengzhong);
}
