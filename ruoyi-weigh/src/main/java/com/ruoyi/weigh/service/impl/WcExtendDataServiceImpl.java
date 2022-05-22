package com.ruoyi.weigh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.weigh.domain.WcExtendData;
import com.ruoyi.weigh.mapper.WcExtendDataMapper;
import com.ruoyi.weigh.service.IWcExtendDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 扩展数据Service业务层处理
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Service
public class WcExtendDataServiceImpl extends ServiceImpl<WcExtendDataMapper, WcExtendData> implements IWcExtendDataService {
    @Autowired
    private WcExtendDataMapper wcExtendDataMapper;
}
