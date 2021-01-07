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
import com.ruoyi.system.domain.SysStu;
import com.ruoyi.system.service.ISysStuService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生Controller
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@RestController
@RequestMapping("/system/stu")
public class SysStuController extends BaseController
{
    @Autowired
    private ISysStuService sysStuService;

    /**
     * 查询学生列表
     */
    @PreAuthorize("@ss.hasPermi('system:stu:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStu sysStu)
    {
        startPage();
        List<SysStu> list = sysStuService.selectSysStuList(sysStu);
        return getDataTable(list);
    }

    /**
     * 导出学生列表
     */
    @PreAuthorize("@ss.hasPermi('system:stu:export')")
    @Log(title = "学生", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStu sysStu)
    {
        List<SysStu> list = sysStuService.selectSysStuList(sysStu);
        ExcelUtil<SysStu> util = new ExcelUtil<SysStu>(SysStu.class);
        return util.exportExcel(list, "stu");
    }

    /**
     * 获取学生详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping(value = "/{sno}")
    public AjaxResult getInfo(@PathVariable("sno") String sno)
    {
        return AjaxResult.success(sysStuService.selectSysStuById(sno));
    }

    /**
     * 新增学生
     */
    @PreAuthorize("@ss.hasPermi('system:stu:add')")
    @Log(title = "学生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStu sysStu)
    {
        return toAjax(sysStuService.insertSysStu(sysStu));
    }

    /**
     * 修改学生
     */
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStu sysStu)
    {
        return toAjax(sysStuService.updateSysStu(sysStu));
    }

    /**
     * 删除学生
     */
    @PreAuthorize("@ss.hasPermi('system:stu:remove')")
    @Log(title = "学生", businessType = BusinessType.DELETE)
	@DeleteMapping("/{snos}")
    public AjaxResult remove(@PathVariable String[] snos)
    {
        return toAjax(sysStuService.deleteSysStuByIds(snos));
    }
}
