package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
//import com.ruoyi.common.core.domain.entity.SysOrg;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.ruoyi.system.domain.SysOrg;
import com.ruoyi.system.service.ISysOrgService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 组织Controller
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/system/org")
public class SysOrgController extends BaseController
{
    @Autowired
    private ISysOrgService sysOrgService;

    /**
     * 查询组织列表
     */
    @PreAuthorize("@ss.hasPermi('system:org:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrg sysOrg)
    {
        startPage();
        List<SysOrg> list = sysOrgService.selectSysOrgList(sysOrg);
        return getDataTable(list);
    }

    /**
     * 导出组织列表
     */
    @PreAuthorize("@ss.hasPermi('system:org:export')")
    @Log(title = "组织", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOrg sysOrg)
    {
        List<SysOrg> list = sysOrgService.selectSysOrgList(sysOrg);
        ExcelUtil<SysOrg> util = new ExcelUtil<SysOrg>(SysOrg.class);
        return util.exportExcel(list, "org");
    }

    /**
     * 获取组织详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:org:query')")
    @GetMapping(value = "/{oid}")
    public AjaxResult getInfo(@PathVariable("oid") Long oid)
    {
        return AjaxResult.success(sysOrgService.selectSysOrgById(oid));
    }

    /**
     * 新增组织
     */
    @PreAuthorize("@ss.hasPermi('system:org:add')")
    @Log(title = "组织", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrg sysOrg)
    {
        return toAjax(sysOrgService.insertSysOrg(sysOrg));
    }


//    @PreAuthorize("@ss.hasPermi('system:org:add')")
//    @Log(title = "组织", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@Validated @RequestBody SysOrg org)
//    {
//        if (UserConstants.NOT_UNIQUE.equals(sysOrgService.checkOrgNameUnique(org)))
//        {
//            return AjaxResult.error("新增组织'" +org.getOname() + "'失败，组织名称已存在");
//        }
//        org.setCreateBy(SecurityUtils.getUsername());
//        return toAjax(sysOrgService.insertSysOrg(org));
//    }

    /**
     * 修改组织
     */
    @PreAuthorize("@ss.hasPermi('system:org:edit')")
    @Log(title = "组织", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrg sysOrg)
    {
        return toAjax(sysOrgService.updateSysOrg(sysOrg));
    }

    /**
     * 删除组织
     */
    @PreAuthorize("@ss.hasPermi('system:org:remove')")
    @Log(title = "组织", businessType = BusinessType.DELETE)
	@DeleteMapping("/{oids}")
    public AjaxResult remove(@PathVariable Long[] oids)
    {
        return toAjax(sysOrgService.deleteSysOrgByIds(oids));
    }
}
