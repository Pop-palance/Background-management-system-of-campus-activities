package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysStuActMapper;
import com.ruoyi.system.domain.SysStuAct;
import com.ruoyi.system.service.ISysStuActService;

/**
 * 学生活动成绩Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class SysStuActServiceImpl implements ISysStuActService 
{
    @Autowired
    public SysStuActMapper sysStuActMapper;

    /**
     * 查询学生活动成绩
     * 
     * @param sno 学生活动成绩ID
     * @return 学生活动成绩
     */
    @Override
    public SysStuAct selectSysStuActById(String sno)
    {
        return sysStuActMapper.selectSysStuActById(sno);
    }

    /**
     * 查询学生活动成绩列表
     * 
     * @param sysStuAct 学生活动成绩
     * @return 学生活动成绩
     */
    @Override
    public List<SysStuAct> selectSysStuActList(SysStuAct sysStuAct)
    {
        return sysStuActMapper.selectSysStuActList(sysStuAct);
    }

    /**
     * 新增学生活动成绩
     * 
     * @param sysStuAct 学生活动成绩
     * @return 结果
     */
    @Override
    public int insertSysStuAct(SysStuAct sysStuAct)
    {
        return sysStuActMapper.insertSysStuAct(sysStuAct);
    }

    /**
     * 修改学生活动成绩
     * 
     * @param sysStuAct 学生活动成绩
     * @return 结果
     */
    @Override
    public int updateSysStuAct(SysStuAct sysStuAct)
    {
        return sysStuActMapper.updateSysStuAct(sysStuAct);
    }

    /**
     * 批量删除学生活动成绩
     * 
     * @param snos 需要删除的学生活动成绩ID
     * @return 结果
     */
    @Override
    public int deleteSysStuActByIds(String[] snos)
    {
        return sysStuActMapper.deleteSysStuActByIds(snos);
    }

    /**
     * 删除学生活动成绩信息
     * 
     * @param sno 学生活动成绩ID
     * @return 结果
     */
    @Override
    public int deleteSysStuActById(String sno)
    {
        return sysStuActMapper.deleteSysStuActById(sno);
    }
}
