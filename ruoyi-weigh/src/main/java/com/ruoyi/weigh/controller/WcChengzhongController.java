package com.ruoyi.weigh.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.weigh.domain.WcChengzhong;
import com.ruoyi.weigh.service.IWcChengzhongService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 称重Controller
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
@Api(tags = "称重")
@RestController
@RequestMapping("/weigh/chengzhong")
public class WcChengzhongController extends BaseController
{
    @Autowired
    private IWcChengzhongService wcChengzhongService;

    /**
     * 查询称重列表
     */
    @ApiOperation("查询称重列表")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:list')")
    @GetMapping("/list")
    public TableDataInfo list(WcChengzhong wcChengzhong)
    {
        startPage();
        List<WcChengzhong> list = wcChengzhongService.selectWcChengzhongList(wcChengzhong);
        return getDataTable(list);
    }

    /**
     * 导出称重列表
     */
    @ApiOperation("导出称重列表")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:export')")
    @Log(title = "称重", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WcChengzhong wcChengzhong)
    {
        List<WcChengzhong> list = wcChengzhongService.selectWcChengzhongList(wcChengzhong);
        ExcelUtil<WcChengzhong> util = new ExcelUtil<WcChengzhong>(WcChengzhong.class);
        util.exportExcel(response, list, "称重数据");
    }

    /**
     * 获取称重详细信息
     */
    @ApiOperation("获取称重详细信息")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wcChengzhongService.getById(id));
    }

    /**
     * 新增称重
     */
    @ApiOperation("新增称重")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:add')")
    @Log(title = "称重", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WcChengzhong wcChengzhong)
    {
        return toAjax(wcChengzhongService.save(wcChengzhong));
    }

    /**
     * 修改称重
     */
    @ApiOperation("修改称重")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:edit')")
    @Log(title = "称重", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WcChengzhong wcChengzhong)
    {
        return toAjax(wcChengzhongService.updateById(wcChengzhong));
    }

    /**
     * 删除称重
     */
    @ApiOperation("删除称重")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:remove')")
    @Log(title = "称重", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wcChengzhongService.removeBatchByIds(Arrays.asList(ids)));
    }

    /**
     * 插入称重
     */
    @ApiOperation("插入称重")
    @PreAuthorize("@ss.hasPermi('weigh:chengzhong:add')")
    @Log(title = "称重", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult insert(WcChengzhong wcChengzhong)
    {
        return toAjax(wcChengzhongService.save(wcChengzhong));
    }
}
