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
import com.ruoyi.weigh.domain.WcFanyi;
import com.ruoyi.weigh.service.IWcFanyiService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 翻译Controller
 * 
 * @author lucky-ya-q
 * @date 2022-06-19
 */
@Api(tags = "翻译")
@RestController
@RequestMapping("/weigh/fanyi")
public class WcFanyiController extends BaseController
{
    @Autowired
    private IWcFanyiService wcFanyiService;

    /**
     * 查询翻译列表
     */
    @ApiOperation("查询翻译列表")
    @PreAuthorize("@ss.hasPermi('weigh:fanyi:list')")
    @GetMapping("/list")
    public TableDataInfo list(WcFanyi wcFanyi)
    {
        startPage();
        List<WcFanyi> list = wcFanyiService.selectWcFanyiList(wcFanyi);
        return getDataTable(list);
    }

    /**
     * 导出翻译列表
     */
    @ApiOperation("导出翻译列表")
    @PreAuthorize("@ss.hasPermi('weigh:fanyi:export')")
    @Log(title = "翻译", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WcFanyi wcFanyi)
    {
        List<WcFanyi> list = wcFanyiService.selectWcFanyiList(wcFanyi);
        ExcelUtil<WcFanyi> util = new ExcelUtil<WcFanyi>(WcFanyi.class);
        util.exportExcel(response, list, "翻译数据");
    }

    /**
     * 获取翻译详细信息
     */
    @ApiOperation("获取翻译详细信息")
    @PreAuthorize("@ss.hasPermi('weigh:fanyi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(wcFanyiService.getById(id));
    }

    /**
     * 新增翻译
     */
    @ApiOperation("新增翻译")
    @PreAuthorize("@ss.hasPermi('weigh:fanyi:add')")
    @Log(title = "翻译", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WcFanyi wcFanyi)
    {
        return toAjax(wcFanyiService.save(wcFanyi));
    }

    /**
     * 修改翻译
     */
    @ApiOperation("修改翻译")
    @PreAuthorize("@ss.hasPermi('weigh:fanyi:edit')")
    @Log(title = "翻译", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WcFanyi wcFanyi)
    {
        return toAjax(wcFanyiService.updateById(wcFanyi));
    }

    /**
     * 删除翻译
     */
    @ApiOperation("删除翻译")
    @PreAuthorize("@ss.hasPermi('weigh:fanyi:remove')")
    @Log(title = "翻译", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wcFanyiService.removeBatchByIds(Arrays.asList(ids)));
    }
}
