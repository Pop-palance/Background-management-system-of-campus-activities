package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 组织对象 sys_org
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public class SysOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 组织id */
    private Long oid;

    /** 密码 */
    @Excel(name = "密码")
    private String opwd;

    /** 名称 */
    @Excel(name = "名称")
    private String oname;

    /** 父部门id */
    @Excel(name = "父部门id")
    private Long parentid;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String ancestors;

    private Date createtime;

    /** 手机号 */
    @Excel(name = "手机号")
    private String onum;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String oemail;

    /** 状态 */
    @Excel(name = "状态")
    private String ostatus;

    /** 职务 */
    @Excel(name = "职务")
    private String ojob;

    /** 角色 */
    @Excel(name = "角色")
    private String orole;

    /** 备注 */
    @Excel(name = "备注")
    private String onote;

    public void setOid(Long oid) 
    {
        this.oid = oid;
    }

    public Long getOid() 
    {
        return oid;
    }
    public void setOpwd(String opwd) 
    {
        this.opwd = opwd;
    }

    public String getOpwd() 
    {
        return opwd;
    }
    public void setOname(String oname) 
    {
        this.oname = oname;
    }

    public String getOname() 
    {
        return oname;
    }
    public void setParentid(Long parentid) 
    {
        this.parentid = parentid;
    }

    public Long getParentid() 
    {
        return parentid;
    }
    public void setAncestors(String ancestors) 
    {
        this.ancestors = ancestors;
    }

    public String getAncestors() 
    {
        return ancestors;
    }
    public void setOnum(String onum) 
    {
        this.onum = onum;
    }

    public String getOnum() 
    {
        return onum;
    }
    public void setOemail(String oemail) 
    {
        this.oemail = oemail;
    }

    public String getOemail() 
    {
        return oemail;
    }
    public void setOstatus(String ostatus) 
    {
        this.ostatus = ostatus;
    }

    public String getOstatus() 
    {
        return ostatus;
    }
    public void setOjob(String ojob) 
    {
        this.ojob = ojob;
    }

    public String getOjob() 
    {
        return ojob;
    }
    public void setOrole(String orole) 
    {
        this.orole = orole;
    }

    public String getOrole() 
    {
        return orole;
    }
    public void setOnote(String onote) 
    {
        this.onote = onote;
    }

    public String getOnote() 
    {
        return onote;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("oid", getOid())
            .append("opwd", getOpwd())
            .append("oname", getOname())
            .append("parentid", getParentid())
            .append("ancestors", getAncestors())
            .append("createtime", getCreatetime())
            .append("onum", getOnum())
            .append("oemail", getOemail())
            .append("ostatus", getOstatus())
            .append("ojob", getOjob())
            .append("orole", getOrole())
            .append("onote", getOnote())
            .toString();
    }

    private Date getCreatetime() {
        return createtime;

    }
}
