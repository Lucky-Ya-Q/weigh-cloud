package com.ruoyi.weigh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.weigh.domain.WcExtendData;
import com.ruoyi.weigh.domain.WcWeighRecord;
import com.ruoyi.weigh.service.IWcExtendDataService;
import com.ruoyi.weigh.service.IWcWeighRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 称重记录Controller
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Api(tags = "称重记录")
@RestController
@RequestMapping("/weigh/record")
public class WcWeighRecordController extends BaseController {
    @Autowired
    private IWcWeighRecordService wcWeighRecordService;
    @Autowired
    private IWcExtendDataService wcExtendDataService;

    /**
     * 查询称重记录列表
     */
    @ApiOperation("查询称重记录列表")
    @PreAuthorize("@ss.hasPermi('weigh:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(WcWeighRecord wcWeighRecord) {
        startPage();
        List<WcWeighRecord> list = wcWeighRecordService.selectWcWeighRecordList(wcWeighRecord);
        return getDataTable(list);
    }

    /**
     * 导出称重记录列表
     */
    @ApiOperation("导出称重记录列表")
    @PreAuthorize("@ss.hasPermi('weigh:record:export')")
    @Log(title = "称重记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WcWeighRecord wcWeighRecord) {
        List<WcWeighRecord> list = wcWeighRecordService.selectWcWeighRecordList(wcWeighRecord);
        ExcelUtil<WcWeighRecord> util = new ExcelUtil<WcWeighRecord>(WcWeighRecord.class);
        util.exportExcel(response, list, "称重记录数据");
    }

    /**
     * 获取称重记录详细信息
     */
    @ApiOperation("获取称重记录详细信息")
    @PreAuthorize("@ss.hasPermi('weigh:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        WcWeighRecord weighRecord = wcWeighRecordService.getById(id);
        LambdaQueryWrapper<WcExtendData> wcExtendDataLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wcExtendDataLambdaQueryWrapper.eq(WcExtendData::getWeighRecordId, weighRecord.getId());
        weighRecord.setWcExtendDataList(wcExtendDataService.list(wcExtendDataLambdaQueryWrapper));
        return AjaxResult.success(weighRecord);
    }

    /**
     * 新增称重记录
     */
    @ApiOperation("新增称重记录")
    @PreAuthorize("@ss.hasPermi('weigh:record:add')")
    @Log(title = "称重记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WcWeighRecord wcWeighRecord) {
        wcWeighRecordService.add(wcWeighRecord);
        return AjaxResult.success();
    }

    /**
     * 修改称重记录
     */
    @ApiOperation("修改称重记录")
    @PreAuthorize("@ss.hasPermi('weigh:record:edit')")
    @Log(title = "称重记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WcWeighRecord wcWeighRecord) {
        wcWeighRecordService.edit(wcWeighRecord);
        return AjaxResult.success();
    }

    /**
     * 删除称重记录
     */
    @ApiOperation("删除称重记录")
    @PreAuthorize("@ss.hasPermi('weigh:record:remove')")
    @Log(title = "称重记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        wcWeighRecordService.delete(ids);
        return AjaxResult.success();
    }
}
