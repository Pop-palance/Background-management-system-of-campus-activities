package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrgMapper;
import com.ruoyi.system.domain.SysOrg;
import com.ruoyi.system.service.ISysOrgService;

/**
 * 组织Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@Service
public class SysOrgServiceImpl implements ISysOrgService 
{
    @Autowired
    private SysOrgMapper sysOrgMapper;

    /**
     * 查询组织
     * 
     * @param oid 组织ID
     * @return 组织
     */
    @Override
    public SysOrg selectSysOrgById(Long oid)
    {
        return sysOrgMapper.selectSysOrgById(oid);
    }

    /**
     * 查询组织列表
     * 
     * @param sysOrg 组织
     * @return 组织
     */
    @Override
    public List<SysOrg> selectSysOrgList(SysOrg sysOrg)
    {
        return sysOrgMapper.selectSysOrgList(sysOrg);
    }

    /**
     * 新增组织
     * 
     * @param sysOrg 组织
     * @return 结果
     */
    @Override
    public int insertSysOrg(SysOrg sysOrg)
    {
        return sysOrgMapper.insertSysOrg(sysOrg);
    }

    /**
     * 修改组织
     * 
     * @param sysOrg 组织
     * @return 结果
     */
    @Override
    public int updateSysOrg(SysOrg sysOrg)
    {
        return sysOrgMapper.updateSysOrg(sysOrg);
    }

    /**
     * 批量删除组织
     * 
     * @param oids 需要删除的组织ID
     * @return 结果
     */
    @Override
    public int deleteSysOrgByIds(Long[] oids)
    {
        return sysOrgMapper.deleteSysOrgByIds(oids);
    }

    /**
     * 删除组织信息
     * 
     * @param oid 组织ID
     * @return 结果
     */
    @Override
    public int deleteSysOrgById(Long oid)
    {
        return sysOrgMapper.deleteSysOrgById(oid);
    }

//    @Override
//    public String checkOrgNameUnique(SysOrg org) {
//        return null;
//    }

//    @Override
//    public String checkOrgNameUnique(SysOrg org) {
//        Long oid = StringUtils.isNull(org.getOid()) ? -1L : org.getOid();
//        SysOrg info = sysOrgMapper.checkOrgNameUnique(org.getOname(), org.getParentid());
//        if (StringUtils.isNotNull(info) && info.getOid().longValue() != oid.longValue())
//        {
//            return UserConstants.NOT_UNIQUE;
//        }
//        return UserConstants.UNIQUE;
//    }
}
