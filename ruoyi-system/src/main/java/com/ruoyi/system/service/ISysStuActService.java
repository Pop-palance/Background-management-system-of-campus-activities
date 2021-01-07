package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysStuAct;

/**
 * 学生活动成绩Service接口
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface ISysStuActService 
{
    /**
     * 查询学生活动成绩
     * 
     * @param sno 学生活动成绩ID
     * @return 学生活动成绩
     */
    public SysStuAct selectSysStuActById(String sno);

    /**
     * 查询学生活动成绩列表
     * 
     * @param sysStuAct 学生活动成绩
     * @return 学生活动成绩集合
     */
    public List<SysStuAct> selectSysStuActList(SysStuAct sysStuAct);

    /**
     * 新增学生活动成绩
     * 
     * @param sysStuAct 学生活动成绩
     * @return 结果
     */
    public int insertSysStuAct(SysStuAct sysStuAct);

    /**
     * 修改学生活动成绩
     * 
     * @param sysStuAct 学生活动成绩
     * @return 结果
     */
    public int updateSysStuAct(SysStuAct sysStuAct);

    /**
     * 批量删除学生活动成绩
     * 
     * @param snos 需要删除的学生活动成绩ID
     * @return 结果
     */
    public int deleteSysStuActByIds(String[] snos);

    /**
     * 删除学生活动成绩信息
     * 
     * @param sno 学生活动成绩ID
     * @return 结果
     */
    public int deleteSysStuActById(String sno);
}
