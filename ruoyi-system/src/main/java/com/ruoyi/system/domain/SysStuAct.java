package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生活动成绩对象 sys_stu_act
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public class SysStuAct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学号 */
    private String sno;

    /** 活动id */
    private Long aid;

    /** 活动分数 */
    @Excel(name = "活动分数")
    private Long grade;

    public void setSno(String sno) 
    {
        this.sno = sno;
    }

    public String getSno() 
    {
        return sno;
    }
    public void setAid(Long aid) 
    {
        this.aid = aid;
    }

    public Long getAid() 
    {
        return aid;
    }
    public void setGrade(Long grade) 
    {
        this.grade = grade;
    }

    public Long getGrade() 
    {
        return grade;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sno", getSno())
            .append("aid", getAid())
            .append("grade", getGrade())
            .toString();
    }
}
