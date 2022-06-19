package com.ruoyi.weigh.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weigh.mapper.WcChengzhongMapper;
import com.ruoyi.weigh.domain.WcChengzhong;
import com.ruoyi.weigh.service.IWcChengzhongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 称重Service业务层处理
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
@Service
public class WcChengzhongServiceImpl extends ServiceImpl<WcChengzhongMapper, WcChengzhong> implements IWcChengzhongService
{
    @Autowired
    private WcChengzhongMapper wcChengzhongMapper;

    /**
     * 查询称重列表
     *
     * @param wcChengzhong 称重
     * @return 称重
     */
    @Override
    @DataScope(userAlias = "u", deptAlias = "d")
    public List<WcChengzhong> selectWcChengzhongList(WcChengzhong wcChengzhong)
    {
        return wcChengzhongMapper.selectWcChengzhongList(wcChengzhong);
    }
}
