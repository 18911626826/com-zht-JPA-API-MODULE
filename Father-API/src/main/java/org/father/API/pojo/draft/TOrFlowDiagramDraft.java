package org.father.API.pojo.draft;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Description TODO
 * 流程图
 * @author linjian
 * @create 2018-10-24 10:34
 * @Version 1.0
 */

@Entity
@Table(name = "t_or_flow_diagram_draft", schema = "dbo")
public class TOrFlowDiagramDraft {
    private String id;
    private String pimsModelId;
    private String caseId;
    private String periodId;
    private String unitName;
    private String inOutType;
    private String mtrlName;
    private BigDecimal quantity;
    private Timestamp createTime;
    private String createEmpId;
    private String createEmpName;
    private Timestamp modifyTime;
    private String modifyEmpId;
    private String modifyEmpName;
    private String dataSourceCode;
    private String resultDraftId;
    private String unitId;
    private String mtrlCode;
    private BigDecimal price;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pims_model_id")
    public String getPimsModelId() {
        return pimsModelId;
    }

    public void setPimsModelId(String pimsModelId) {
        this.pimsModelId = pimsModelId;
    }

    @Basic
    @Column(name = "case_id")
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    @Basic
    @Column(name = "period_id")
    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    @Basic
    @Column(name = "unit_name")
    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Basic
    @Column(name = "in_out_type")
    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    @Basic
    @Column(name = "mtrl_name")
    public String getMtrlName() {
        return mtrlName;
    }

    public void setMtrlName(String mtrlName) {
        this.mtrlName = mtrlName;
    }

    @Basic
    @Column(name = "quantity")
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

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

    @Basic
    @Column(name = "data_source_code")
    public String getDataSourceCode() {
        return dataSourceCode;
    }

    public void setDataSourceCode(String dataSourceCode) {
        this.dataSourceCode = dataSourceCode;
    }

    @Basic
    @Column(name = "result_draft_id")
    public String getResultDraftId() {
        return resultDraftId;
    }

    public void setResultDraftId(String resultDraftId) {
        this.resultDraftId = resultDraftId;
    }

    @Basic
    @Column(name = "unit_id")
    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "mtrl_code")
    public String getMtrlCode() {
        return mtrlCode;
    }

    public void setMtrlCode(String mtrlCode) {
        this.mtrlCode = mtrlCode;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
