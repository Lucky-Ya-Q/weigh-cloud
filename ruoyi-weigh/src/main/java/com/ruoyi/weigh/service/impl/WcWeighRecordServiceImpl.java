package com.ruoyi.weigh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.weigh.domain.WcExtendData;
import com.ruoyi.weigh.domain.WcWeighRecord;
import com.ruoyi.weigh.mapper.WcExtendDataMapper;
import com.ruoyi.weigh.mapper.WcWeighRecordMapper;
import com.ruoyi.weigh.service.IWcWeighRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 称重记录Service业务层处理
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Service
public class WcWeighRecordServiceImpl extends ServiceImpl<WcWeighRecordMapper, WcWeighRecord> implements IWcWeighRecordService {
    @Autowired
    private WcWeighRecordMapper wcWeighRecordMapper;
    @Autowired
    private WcExtendDataMapper wcExtendDataMapper;

    /**
     * 查询称重记录列表
     *
     * @param wcWeighRecord 称重记录
     * @return 称重记录
     */
    @Override
    @DataScope(userAlias = "u", deptAlias = "d")
    public List<WcWeighRecord> selectWcWeighRecordList(WcWeighRecord wcWeighRecord) {
        List<WcWeighRecord> wcWeighRecordList = wcWeighRecordMapper.selectWcWeighRecordList(wcWeighRecord);
        for (WcWeighRecord weighRecord : wcWeighRecordList) {
            LambdaQueryWrapper<WcExtendData> wcExtendDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
            wcExtendDataLambdaQueryWrapper.eq(WcExtendData::getWeighRecordId, weighRecord.getId());
            weighRecord.setWcExtendDataList(wcExtendDataMapper.selectList(wcExtendDataLambdaQueryWrapper));
        }
        return wcWeighRecordList;
    }

    @Override
    @Transactional
    public void add(WcWeighRecord wcWeighRecord) {
        wcWeighRecordMapper.insert(wcWeighRecord);
        List<WcExtendData> wcExtendDataList = wcWeighRecord.getWcExtendDataList();
        for (WcExtendData wcExtendData : wcExtendDataList) {
            if (StringUtils.isNotEmpty(wcExtendData.getValue())) {
                wcExtendData.setWeighRecordId(wcWeighRecord.getId());
                wcExtendDataMapper.insert(wcExtendData);
            }
        }
    }

    @Override
    @Transactional
    public void edit(WcWeighRecord wcWeighRecord) {
        wcWeighRecordMapper.updateById(wcWeighRecord);
        List<WcExtendData> wcExtendDataList = wcWeighRecord.getWcExtendDataList();

        LambdaQueryWrapper<WcExtendData> wcExtendDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wcExtendDataLambdaQueryWrapper.eq(WcExtendData::getWeighRecordId, wcWeighRecord.getId());
        wcExtendDataMapper.delete(wcExtendDataLambdaQueryWrapper);

        for (WcExtendData wcExtendData : wcExtendDataList) {
            if (StringUtils.isNotEmpty(wcExtendData.getValue())) {
                wcExtendData.setWeighRecordId(wcWeighRecord.getId());
                wcExtendDataMapper.insert(wcExtendData);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        List<Long> idList = Arrays.asList(ids);
        for (Long id : idList) {
            LambdaQueryWrapper<WcExtendData> wcExtendDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
            wcExtendDataLambdaQueryWrapper.eq(WcExtendData::getWeighRecordId, id);
            wcExtendDataMapper.delete(wcExtendDataLambdaQueryWrapper);
        }
        wcWeighRecordMapper.deleteBatchIds(idList);
    }
}
