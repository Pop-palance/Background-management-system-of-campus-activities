package com.ruoyi.web.controller.system;

import java.util.List;
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
import com.ruoyi.system.domain.SysStuAct;
import com.ruoyi.system.service.ISysStuActService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生活动成绩Controller
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@RestController
@RequestMapping("/system/act")
public class SysStuActController extends BaseController
{
    @Autowired
    private ISysStuActService sysStuActService;

    /**
     * 查询学生活动成绩列表
     */
    @PreAuthorize("@ss.hasPermi('system:act:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStuAct sysStuAct)
    {
        startPage();
        List<SysStuAct> list = sysStuActService.selectSysStuActList(sysStuAct);
        return getDataTable(list);
    }

    /**
     * 导出学生活动成绩列表
     */
    @PreAuthorize("@ss.hasPermi('system:act:export')")
    @Log(title = "学生活动成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStuAct sysStuAct)
    {
        List<SysStuAct> list = sysStuActService.selectSysStuActList(sysStuAct);
        ExcelUtil<SysStuAct> util = new ExcelUtil<SysStuAct>(SysStuAct.class);
        return util.exportExcel(list, "act");
    }

    /**
     * 获取学生活动成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:act:query')")
    @GetMapping(value = "/{sno}")
    public AjaxResult getInfo(@PathVariable("sno") String sno)
    {
        return AjaxResult.success(sysStuActService.selectSysStuActById(sno));
    }

    /**
     * 新增学生活动成绩
     */
    @PreAuthorize("@ss.hasPermi('system:act:add')")
    @Log(title = "学生活动成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStuAct sysStuAct)
    {
        return toAjax(sysStuActService.insertSysStuAct(sysStuAct));
    }

    /**
     * 修改学生活动成绩
     */
    @PreAuthorize("@ss.hasPermi('system:act:edit')")
    @Log(title = "学生活动成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStuAct sysStuAct)
    {
        return toAjax(sysStuActService.updateSysStuAct(sysStuAct));
    }

    /**
     * 删除学生活动成绩
     */
    @PreAuthorize("@ss.hasPermi('system:act:remove')")
    @Log(title = "学生活动成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{snos}")
    public AjaxResult remove(@PathVariable String[] snos)
    {
        return toAjax(sysStuActService.deleteSysStuActByIds(snos));
    }
}
