package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.domain.SysOrg;

/**
 * 组织Service接口
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public interface ISysOrgService 
{
    /**
     * 查询组织
     * 
     * @param oid 组织ID
     * @return 组织
     */
    public SysOrg selectSysOrgById(Long oid);

    /**
     * 查询组织列表
     * 
     * @param sysOrg 组织
     * @return 组织集合
     */
    public List<SysOrg> selectSysOrgList(SysOrg sysOrg);

    /**
     * 新增组织
     * 
     * @param sysOrg 组织
     * @return 结果
     */
    public int insertSysOrg(SysOrg sysOrg);

    /**
     * 修改组织
     * 
     * @param sysOrg 组织
     * @return 结果
     */
    public int updateSysOrg(SysOrg sysOrg);

    /**
     * 批量删除组织
     * 
     * @param oids 需要删除的组织ID
     * @return 结果
     */
    public int deleteSysOrgByIds(Long[] oids);

    /**
     * 删除组织信息
     * 
     * @param oid 组织ID
     * @return 结果
     */
    public int deleteSysOrgById(Long oid);

//    /**
//     * 校验部门名称是否唯一
//     *
//     * @param org 组织信息
//     * @return 结果
//     */
//    public String checkOrgNameUnique(SysOrg org);


}
