package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysStu;

/**
 * 学生Service接口
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface ISysStuService 
{
    /**
     * 查询学生
     * 
     * @param sno 学生ID
     * @return 学生
     */
    public SysStu selectSysStuById(String sno);

    /**
     * 查询学生列表
     * 
     * @param sysStu 学生
     * @return 学生集合
     */
    public List<SysStu> selectSysStuList(SysStu sysStu);

    /**
     * 新增学生
     * 
     * @param sysStu 学生
     * @return 结果
     */
    public int insertSysStu(SysStu sysStu);

    /**
     * 修改学生
     * 
     * @param sysStu 学生
     * @return 结果
     */
    public int updateSysStu(SysStu sysStu);

    /**
     * 批量删除学生
     * 
     * @param snos 需要删除的学生ID
     * @return 结果
     */
    public int deleteSysStuByIds(String[] snos);

    /**
     * 删除学生信息
     * 
     * @param sno 学生ID
     * @return 结果
     */
    public int deleteSysStuById(String sno);
}
