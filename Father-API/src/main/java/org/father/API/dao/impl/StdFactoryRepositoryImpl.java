package org.father.API.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.Father.COMMON.util.CommonResult;
import org.father.API.dao.StdFactoryRepositoryCustom;
import org.father.API.dao.common.BaseRepository;
import org.father.API.pojo.stdFactory;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StdFactoryRepositoryImpl extends BaseRepository<stdFactory, Integer> implements StdFactoryRepositoryCustom{
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public CommonResult updateStdFactory(stdFactory stdFactory) {
		CommonResult cr=new CommonResult();
		try {
			stdFactory=getEntityManager().merge(stdFactory);
			
			cr.setIsSuccess(true);
			cr.setMessage("更新成功!");
			cr.setResult(stdFactory);
		}catch(Exception e) {
			cr.setIsSuccess(false);
			cr.setMessage("更新失败!");
		}
		return cr;
	}
	
	@Transactional(readOnly=false ,propagation=Propagation.REQUIRED)
	@Override
	public CommonResult deleteStdFactory(Long[] facIDs) {
		CommonResult cr=new CommonResult();
		try {
			List<stdFactory> stdFactorys=getStdFactoryList(facIDs);
			stdFactorys.forEach(x->{getEntityManager().remove(x);});
			
			cr.setIsSuccess(true);
			cr.setMessage("删除成功!");
		}catch(Exception e) {
			cr.setIsSuccess(false);
			cr.setMessage("删除失败!");
		}
		return cr;
	}

	@Override
	public stdFactory getSingleStdFactory(Long facID) {
		
		return this.getEntityManager().find(stdFactory.class, facID);
	}

	@Override
	public List<stdFactory> getStdFactoryList(Long[] facIDs) {
		StringBuilder sb=new StringBuilder("from stdFactory f where 1=1");
		Map<String,Object> map=new HashMap<>();
		if(facIDs!=null && facIDs.length>0) {
			sb.append(" and f.facId in (:facIDs)");
		}
		TypedQuery<stdFactory> query=getEntityManager().createQuery(sb.toString(), stdFactory.class);
		if(facIDs!=null && facIDs.length>0) {
			query.setParameter("facIDs",Arrays.asList(facIDs));
		}
		return query.getResultList();
	}

	@Override
	public PaginationBean<stdFactory> getStdFactory(Pagination page, String name) {
		StringBuilder sb=new StringBuilder("from stdFactory f where f.facIsDelete=0");
		Map<String,Object> map=new HashMap<>();
		if(name!=null && !"".equals(name)) {
			name=name.trim();
			sb.append(" and (f.facCode like :name escape '/' or f.facName like :name escape '/')");
			map.put("name", "%"+this.sqlLikeReplace(name)+"%");
		}
		sb.append(" order by f.facNo");
		return this.findAll(page, sb.toString(), map);
	}

	@Override
	public List<Object[]> getSingleStdFactoryByCode(String facCode) {
		StringBuilder sb=new StringBuilder("select f.FAC_ID from STD_FACTORY f where 1=1 and f.FAC_IS_DELETE=0");

		if(facCode!=null) {
			sb.append(" and f.FAC_CODE=:facCode");
		}
		Query query=getEntityManager().createNativeQuery(sb.toString());
		query.setParameter("facCode", facCode);
		return query.getResultList();
	}

	@Override
	public List<Object[]> getSingleStdFactoryByName(String facName) {
		StringBuilder sb=new StringBuilder("select f.FAC_ID from STD_FACTORY f where 1=1 and f.FAC_IS_DELETE=0");

		if(facName!=null) {
			sb.append(" and f.FAC_NAME=:facName");
		}
		Query query=getEntityManager().createNativeQuery(sb.toString());
		query.setParameter("facName", facName);
		return query.getResultList();

	}

	@Override
	public List<stdFactory> getStdFactoryListForExport() {
		
		StringBuilder sb=new StringBuilder("select f.FAC_ID facId,f.FAC_CODE facCode,f.FAC_NAME facName,f.FAC_NO facNO,t.id orgId,t.Org_Name orgName from STD_FACTORY f LEFT JOIN T_BC_Org t on f.ORG_ID=t.id and f.FAC_IS_DELETE=0");
		
		sb.append(" order by f.FAC_NO");
		
		Query query=getEntityManager().createNativeQuery(sb.toString());
		// 将结果转化为 Map<tableKey, keyValue>
		query.unwrap(org.hibernate.SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List<Map<String, Object>> list = query.getResultList();
		List<stdFactory> stdFactoryList=new ArrayList<>();
		for (Map<String, Object> m : list) {
			stdFactory stdFactory=new stdFactory();
			stdFactory.setFacId((Integer)m.get("facId"));
			
			stdFactory.setFacCode((String)m.get("facCode"));
			stdFactory.setFacName((String)m.get("facName"));
			stdFactory.setFacNo((Integer)m.get("facNO"));
			stdFactory.setOrgId((Integer)m.get("orgId"));
			stdFactory.setOrgName((String)m.get("orgName"));
			
			stdFactoryList.add(stdFactory);
		}
		return stdFactoryList;
	}
	
}
