package com.ruoyi.weigh.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.weigh.domain.WcExtendData;
import com.ruoyi.weigh.service.IWcExtendDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 扩展数据Controller
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Api(tags = "扩展数据")
@RestController
@RequestMapping("/weigh/data")
public class WcExtendDataController extends BaseController {
    @Autowired
    private IWcExtendDataService wcExtendDataService;

    /**
     * 查询扩展数据列表
     */
    @ApiOperation("查询扩展数据列表")
    @PreAuthorize("@ss.hasPermi('weigh:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(WcExtendData wcExtendData) {
        startPage();
        List<WcExtendData> list = wcExtendDataService.list(null);
        return getDataTable(list);
    }

    /**
     * 导出扩展数据列表
     */
    @ApiOperation("导出扩展数据列表")
    @PreAuthorize("@ss.hasPermi('weigh:data:export')")
    @Log(title = "扩展数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WcExtendData wcExtendData) {
        List<WcExtendData> list = wcExtendDataService.list(null);
        ExcelUtil<WcExtendData> util = new ExcelUtil<WcExtendData>(WcExtendData.class);
        util.exportExcel(response, list, "扩展数据数据");
    }

    /**
     * 获取扩展数据详细信息
     */
    @ApiOperation("获取扩展数据详细信息")
    @PreAuthorize("@ss.hasPermi('weigh:data:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wcExtendDataService.getById(id));
    }

    /**
     * 新增扩展数据
     */
    @ApiOperation("新增扩展数据")
    @PreAuthorize("@ss.hasPermi('weigh:data:add')")
    @Log(title = "扩展数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WcExtendData wcExtendData) {
        return toAjax(wcExtendDataService.save(wcExtendData));
    }

    /**
     * 修改扩展数据
     */
    @ApiOperation("修改扩展数据")
    @PreAuthorize("@ss.hasPermi('weigh:data:edit')")
    @Log(title = "扩展数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WcExtendData wcExtendData) {
        return toAjax(wcExtendDataService.updateById(wcExtendData));
    }

    /**
     * 删除扩展数据
     */
    @ApiOperation("删除扩展数据")
    @PreAuthorize("@ss.hasPermi('weigh:data:remove')")
    @Log(title = "扩展数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(wcExtendDataService.removeByIds(Arrays.asList(ids)));
    }
}
