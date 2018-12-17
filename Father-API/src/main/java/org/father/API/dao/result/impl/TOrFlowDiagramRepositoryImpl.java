package org.father.API.dao.result.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.apache.commons.lang3.StringUtils;
import org.father.API.dao.common.BaseRepository;
import org.father.API.dao.result.TOrFlowDiagramRepositoryCustom;
import org.father.API.pojo.result.TOrFlowDiagram;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-11-02 14:27
 * @Version 1.0
 */

public class TOrFlowDiagramRepositoryImpl extends BaseRepository<TOrFlowDiagram,String> implements TOrFlowDiagramRepositoryCustom {
    @Override
    public PaginationBean<TOrFlowDiagram> getPage(String resultId, Pagination page, String dataSourceCode, String pimsModelId, String caseId, String periodId) throws Exception {
        // 查询字符串
        StringBuilder hql = new StringBuilder(" from TOrFlowDiagram t where 1 = 1 ");
        // 参数集合
        Map<String, Object> paramList = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(resultId)) {
            hql.append(" and t.resultId = :resultId ");
            paramList.put("resultId", resultId);
        }
		if (StringUtils.isNoneEmpty(pimsModelId)) {
			hql.append(" and t.pimsModelId = :pimsModelId ");
			paramList.put("pimsModelId", pimsModelId);
		}
		if (StringUtils.isNoneEmpty(caseId)) {
			hql.append(" and t.caseId = :caseId ");
			paramList.put("caseId", caseId);
		}
		if (StringUtils.isNoneEmpty(periodId)) {
			hql.append(" and t.periodId = :periodId ");
			paramList.put("periodId", periodId);
		}
		hql.append(" order by t.unitId");
        // 调用基类方法查询返回结果
        return this.findAll(page, hql.toString(), paramList);
    }

	@Override
	public List<TOrFlowDiagram> getByResultId(String result_id) {
			String hql = "FROM TOrFlowDiagram t WHERE 1=1 ";
		    Map<String, Object> paramList = new HashMap<String, Object>();
		    if (StringUtils.isNotEmpty(result_id)) {
		        hql += " and t.resultId = :resultId";
		        paramList.put("resultId", result_id);
		    }
		    TypedQuery<TOrFlowDiagram> query = getEntityManager().createQuery(hql, TOrFlowDiagram.class);
		    this.setParameterList(query, paramList);
		    return query.getResultList();
	}

	@PersistenceUnit(unitName="entityManagerFactory")
	private EntityManagerFactory emf;  //注入EntityManagerFactory

	@Override
	public void add(List<TOrFlowDiagram> listFlow) throws Exception {
		EntityManager em = emf.createEntityManager();
		//,create_time,create_emp_id,create_emp_name
		String insertSql = "insert into t_or_flow_diagram(id,result_id,pims_model_id,case_id,period_id,unit_name," +
				"in_out_type,mtrl_name,quantity,unit_id,mtrl_code,data_source_code,price) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Session session1 = (Session) em.getDelegate();
		Connection conn = em.unwrap(SessionImpl.class).connection();
		PreparedStatement stmt = conn.prepareStatement(insertSql);
		// 方式2：批量提交
		conn.setAutoCommit(false);
		int i = 0;
		for(TOrFlowDiagram e : listFlow) {
			stmt.setString(1, e.getId());
			stmt.setString(2, e.getResultId());
			stmt.setString(3, e.getPimsModelId());
			stmt.setString(4,e.getCaseId());
			stmt.setString(5,e.getPeriodId());
			stmt.setString(6, e.getUnitName());
			stmt.setString(7, e.getInOutType());
			stmt.setString(8, e.getMtrlName());
			stmt.setBigDecimal(9, e.getQuantity());
			stmt.setString(10, e.getUnitId());
			stmt.setString(11, e.getMtrlCode());
			stmt.setString(12, e.getDataSourceCode());
			stmt.setBigDecimal(13, e.getPrice());
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
	public Map<String, String> getQuantitySum(String dataSourceCode) {
		Map<String, String> resultMap = new HashMap<>();
		String sql = "SELECT in_out_type, SUM(quantity) FROM t_or_flow_diagram WHERE " +
				"data_source_code = '"+dataSourceCode+"' GROUP BY in_out_type";
		Query query = getEntityManager().createNativeQuery(sql);
		List<Object[]> objects = query.getResultList();
		for (int i = 0; i < objects.size() ; i++) {
			resultMap.put(objects.get(i)[0].toString(), objects.get(i)[1].toString());
		}
		return resultMap;
	}

}
