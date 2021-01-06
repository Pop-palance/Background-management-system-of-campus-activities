package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysOrg;

/**
 * 组织Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public interface SysOrgMapper 
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
     * 删除组织
     * 
     * @param oid 组织ID
     * @return 结果
     */
    public int deleteSysOrgById(Long oid);

    /**
     * 批量删除组织
     * 
     * @param oids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysOrgByIds(Long[] oids);
}
