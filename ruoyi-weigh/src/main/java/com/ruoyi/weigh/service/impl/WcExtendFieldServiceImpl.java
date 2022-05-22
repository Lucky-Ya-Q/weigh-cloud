package com.ruoyi.weigh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.weigh.domain.WcExtendData;
import com.ruoyi.weigh.domain.WcExtendField;
import com.ruoyi.weigh.mapper.WcExtendDataMapper;
import com.ruoyi.weigh.mapper.WcExtendFieldMapper;
import com.ruoyi.weigh.service.IWcExtendFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 扩展字段Service业务层处理
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Service
public class WcExtendFieldServiceImpl extends ServiceImpl<WcExtendFieldMapper, WcExtendField> implements IWcExtendFieldService {
    @Autowired
    private WcExtendFieldMapper wcExtendFieldMapper;
    @Autowired
    private WcExtendDataMapper wcExtendDataMapper;

    /**
     * 查询扩展字段列表
     *
     * @param wcExtendField 扩展字段
     * @return 扩展字段
     */
    @Override
    @DataScope(userAlias = "u", deptAlias = "d")
    public List<WcExtendField> selectWcExtendFieldList(WcExtendField wcExtendField) {
        return wcExtendFieldMapper.selectWcExtendFieldList(wcExtendField);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        for (Long id : idList) {
            WcExtendField wcExtendField = wcExtendFieldMapper.selectById(id);
            LambdaQueryWrapper<WcExtendData> wcExtendDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
            wcExtendDataLambdaQueryWrapper
                    .eq(WcExtendData::getDeptId, wcExtendField.getDeptId())
                    .eq(WcExtendData::getCode, wcExtendField.getCode());
            wcExtendDataMapper.delete(wcExtendDataLambdaQueryWrapper);
        }
        wcExtendFieldMapper.deleteBatchIds(idList);
    }
}
