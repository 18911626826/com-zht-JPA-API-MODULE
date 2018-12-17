package org.father.API.dao.draft.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.apache.commons.lang3.StringUtils;
import org.father.API.dao.common.BaseRepository;
import org.father.API.dao.draft.TOrFlowDiagramDraftRepositoryCustom;
import org.father.API.pojo.draft.TOrFlowDiagramDraft;
import org.hibernate.Session;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-10-31 17:28
 * @Version 1.0
 */

public class TOrFlowDiagramDraftRepositoryImpl extends BaseRepository<TOrFlowDiagramDraft,String> implements TOrFlowDiagramDraftRepositoryCustom {

    @PersistenceUnit(unitName="entityManagerFactory")
    private EntityManagerFactory emf;

    @Override
    public PaginationBean<TOrFlowDiagramDraft> getFlowPage(String pimsModelId, String caseId, String periodId, Pagination page, String dataSourceCode) throws Exception {
        // 查询字符串
        StringBuilder hql = new StringBuilder(" from TOrFlowDiagramDraft t where 1 = 1 ");
        // 参数集合
        Map<String, Object> paramList = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(pimsModelId)) {
            hql.append(" and t.pimsModelId = :pimsModelId ");
            paramList.put("pimsModelId", pimsModelId);
        }
        if (StringUtils.isNotEmpty(caseId)) {
            hql.append(" and t.caseId = :caseId ");
            paramList.put("caseId", caseId);
        }
        if (StringUtils.isNotEmpty(periodId)) {
            hql.append(" and t.periodId = :periodId ");
            paramList.put("periodId", periodId);
        }
        if (StringUtils.isNotEmpty(dataSourceCode)) {
            hql.append(" and t.dataSourceCode = :dataSourceCode ");
            paramList.put("dataSourceCode", dataSourceCode);
        }
        hql.append(" order by t.unitId");
        // 调用基类方法查询返回结果
        return this.findAll(page, hql.toString(), paramList);
    }

    @Override
    public void addOrFlowDiagramDraft(List<TOrFlowDiagramDraft> tOrFlowDiagramDraftList, Connection conn) throws SQLException {
        EntityManager em = emf.createEntityManager();
        String insertSql = "insert into t_or_flow_diagram_draft(id,pims_model_id,case_id,period_id,unit_name," +
                "in_out_type,mtrl_name,quantity,data_source_code,unit_id,mtrl_code,price) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        Session session1 = (Session) em.getDelegate();
        PreparedStatement stmt = conn.prepareStatement(insertSql);
        // 方式2：批量提交
        conn.setAutoCommit(false);
        int i = 0;
        for(TOrFlowDiagramDraft e : tOrFlowDiagramDraftList) {
            stmt.setString(1, UUID.randomUUID().toString().replaceAll("-",""));
            stmt.setString(2, e.getPimsModelId());
            stmt.setString(3, e.getCaseId());
            stmt.setString(4, e.getPeriodId());
            stmt.setString(5, e.getUnitName());
            stmt.setString(6, e.getInOutType());
            stmt.setString(7, e.getMtrlName());
            stmt.setBigDecimal(8, e.getQuantity());
            stmt.setString(9, e.getDataSourceCode());
            stmt.setString(10, e.getUnitId());
            stmt.setString(11, e.getMtrlCode());
            stmt.setBigDecimal(12, e.getPrice());
            stmt.addBatch();
            i++;
            if (i % 100 == 0) {
                stmt.executeBatch();
                conn.commit();
            }
        }
        stmt.executeBatch();
        conn.commit();
        // 关闭session
        session1.close();
    }

	@Override
	public List<TOrFlowDiagramDraft> getBypimsModelIdAndCaseIdAndPeriodIdAndDataSourceCode(String pimsId, String caseId,
			String periodId, String dataSourceCode) {
		String hql = "FROM TOrFlowDiagramDraft t WHERE 1=1 ";
	    Map<String, Object> paramList = new HashMap<String, Object>();
	    if (StringUtils.isNotEmpty(pimsId)) {
	        hql += " and t.pimsModelId = :pimsModelId";
	        paramList.put("pimsModelId", pimsId);
	    }
	    if (StringUtils.isNotEmpty(caseId)) {
	        hql += " and t.caseId =:caseId";
	        paramList.put("caseId", caseId);
	    }
	    if (StringUtils.isNotEmpty(periodId)) {
	        hql += " and t.periodId =:periodId";
	        paramList.put("periodId", periodId);
	    }
	    if (StringUtils.isNotEmpty(dataSourceCode)) {
	        hql += " and t.dataSourceCode =:dataSourceCode";
	        paramList.put("dataSourceCode", dataSourceCode);
	    }
	    TypedQuery<TOrFlowDiagramDraft> query = getEntityManager().createQuery(hql, TOrFlowDiagramDraft.class);
	    this.setParameterList(query, paramList);
	    return query.getResultList();
	}

    @Override
    public Map<String, String> getQuantitySum(String dataSourceCode) {
        Map<String, String> resultMap = new HashMap<>();
        String sql = "SELECT in_out_type, SUM(quantity) FROM t_or_flow_diagram_draft WHERE " +
                "data_source_code = '"+dataSourceCode+"' GROUP BY in_out_type";
        Query query = getEntityManager().createNativeQuery(sql);
        List<Object[]> objects = query.getResultList();
        for (int i = 0; i < objects.size() ; i++) {
            resultMap.put(objects.get(i)[0].toString(), objects.get(i)[1].toString());
        }
        return resultMap;
    }

}
