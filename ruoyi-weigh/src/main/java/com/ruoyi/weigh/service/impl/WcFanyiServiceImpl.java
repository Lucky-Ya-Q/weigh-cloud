package com.ruoyi.weigh.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.weigh.mapper.WcFanyiMapper;
import com.ruoyi.weigh.domain.WcFanyi;
import com.ruoyi.weigh.service.IWcFanyiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 翻译Service业务层处理
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
@Service
public class WcFanyiServiceImpl extends ServiceImpl<WcFanyiMapper, WcFanyi> implements IWcFanyiService
{
    @Autowired
    private WcFanyiMapper wcFanyiMapper;


    /**
     * 查询翻译列表
     *
     * @param wcFanyi 翻译
     * @return 翻译
     */
    @Override
    @DataScope(userAlias = "u", deptAlias = "d")
    public List<WcFanyi> selectWcFanyiList(WcFanyi wcFanyi)
    {
        return wcFanyiMapper.selectWcFanyiList(wcFanyi);
    }
}
