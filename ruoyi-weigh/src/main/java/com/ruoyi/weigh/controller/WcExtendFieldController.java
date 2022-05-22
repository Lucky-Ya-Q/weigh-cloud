package com.ruoyi.weigh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.weigh.domain.WcExtendField;
import com.ruoyi.weigh.service.IWcExtendFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 扩展字段Controller
 *
 * @author lucky-ya-q
 * @date 2022-02-14
 */
@Api(tags = "扩展字段")
@RestController
@RequestMapping("/weigh/field")
public class WcExtendFieldController extends BaseController {
    @Autowired
    private IWcExtendFieldService wcExtendFieldService;

    /**
     * 查询扩展字段列表
     */
    @ApiOperation("查询扩展字段列表")
    @PreAuthorize("@ss.hasPermi('weigh:field:list')")
    @GetMapping("/list")
    public TableDataInfo list(WcExtendField wcExtendField) {
        startPage();
        List<WcExtendField> list = wcExtendFieldService.selectWcExtendFieldList(wcExtendField);
        return getDataTable(list);
    }

    /**
     * 导出扩展字段列表
     */
    @ApiOperation("导出扩展字段列表")
    @PreAuthorize("@ss.hasPermi('weigh:field:export')")
    @Log(title = "扩展字段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WcExtendField wcExtendField) {
        List<WcExtendField> list = wcExtendFieldService.selectWcExtendFieldList(wcExtendField);
        ExcelUtil<WcExtendField> util = new ExcelUtil<WcExtendField>(WcExtendField.class);
        util.exportExcel(response, list, "扩展字段数据");
    }

    /**
     * 获取扩展字段详细信息
     */
    @ApiOperation("获取扩展字段详细信息")
    @PreAuthorize("@ss.hasPermi('weigh:field:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(wcExtendFieldService.getById(id));
    }

    /**
     * 新增扩展字段
     */
    @ApiOperation("新增扩展字段")
    @PreAuthorize("@ss.hasPermi('weigh:field:add')")
    @Log(title = "扩展字段", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WcExtendField wcExtendField) {
        Long deptId = SecurityUtils.getDeptId();
        LambdaQueryWrapper<WcExtendField> wcExtendFieldLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wcExtendFieldLambdaQueryWrapper
                .eq(WcExtendField::getDeptId, deptId)
                .eq(WcExtendField::getCode, wcExtendField.getCode());
        long count = wcExtendFieldService.count(wcExtendFieldLambdaQueryWrapper);
        if (count > 0) {
            return AjaxResult.error("字段代码已存在");
        }
        return toAjax(wcExtendFieldService.save(wcExtendField));
    }

    /**
     * 修改扩展字段
     */
    @ApiOperation("修改扩展字段")
    @PreAuthorize("@ss.hasPermi('weigh:field:edit')")
    @Log(title = "扩展字段", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WcExtendField wcExtendField) {
        return toAjax(wcExtendFieldService.updateById(wcExtendField));
    }

    /**
     * 删除扩展字段
     */
    @ApiOperation("删除扩展字段")
    @PreAuthorize("@ss.hasPermi('weigh:field:remove')")
    @Log(title = "扩展字段", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        wcExtendFieldService.delete(ids);
        return AjaxResult.success();
    }

    /**
     * 查询所有扩展字段列表
     */
    @ApiOperation("查询所有扩展字段列表")
    @PreAuthorize("@ss.hasPermi('weigh:field:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(Long deptId) {
        boolean admin = SecurityUtils.isAdmin(SecurityUtils.getUserId());
        if (admin) {
            if (deptId == null) {
                deptId = SecurityUtils.getDeptId();
            }
        } else {
            deptId = SecurityUtils.getDeptId();
        }
        LambdaQueryWrapper<WcExtendField> wcExtendFieldLambdaQueryWrapper = new LambdaQueryWrapper<>();
        wcExtendFieldLambdaQueryWrapper.eq(WcExtendField::getDeptId, deptId);
        return AjaxResult.success(wcExtendFieldService.list(wcExtendFieldLambdaQueryWrapper));
    }
}
