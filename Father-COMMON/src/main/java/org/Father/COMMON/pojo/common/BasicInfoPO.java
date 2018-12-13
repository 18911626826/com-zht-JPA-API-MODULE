package org.Father.COMMON.pojo.common;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/*
 * 计划优化 创建人修改人相关信息公用实体
 * 模块编号：pcitc_wm_pojo_class_BasicInfoPO
 * 作    者：linjian
 * 创建时间：2018/08/29
 * 修改编号：1
 * 描    述：创建人修改人相关信息公用实体
 */
@MappedSuperclass
@DynamicUpdate
public class BasicInfoPO {

    private Timestamp createTime;
    private String createEmpId;
    private String createEmpName;
    private Timestamp modifyTime;
    private String modifyEmpId;
    private String modifyEmpName;

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_emp_id")
    public String getCreateEmpId() {
        return createEmpId;
    }

    public void setCreateEmpId(String createEmpId) {
        this.createEmpId = createEmpId;
    }

    @Basic
    @Column(name = "create_emp_name")
    public String getCreateEmpName() {
        return createEmpName;
    }

    public void setCreateEmpName(String createEmpName) {
        this.createEmpName = createEmpName;
    }

    @Basic
    @Column(name = "modify_time")
    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Basic
    @Column(name = "modify_emp_id")
    public String getModifyEmpId() {
        return modifyEmpId;
    }

    public void setModifyEmpId(String modifyEmpId) {
        this.modifyEmpId = modifyEmpId;
    }

    @Basic
    @Column(name = "modify_emp_name")
    public String getModifyEmpName() {
        return modifyEmpName;
    }

    public void setModifyEmpName(String modifyEmpName) {
        this.modifyEmpName = modifyEmpName;
    }


}
