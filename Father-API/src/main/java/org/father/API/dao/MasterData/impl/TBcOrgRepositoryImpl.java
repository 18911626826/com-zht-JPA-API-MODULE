package org.father.API.dao.MasterData.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.Father.COMMON.util.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.father.API.dao.MasterData.TBcOrgRepositoryCustom;
import org.father.API.dao.common.BaseRepository;
import org.father.API.pojo.MasterData.TBcOrg;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TBcOrgRepositoryImpl extends BaseRepository<TBcOrg, Long> implements TBcOrgRepositoryCustom {

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CommonResult deleteTBcOrg(Integer[] ids) {
		// 初始化消息结果类
		CommonResult commonResult = new CommonResult();
		try {
			String hql = " from TBcOrg t where t.id in (:ids)";
			Map<String, Object> paramList = new HashMap<String, Object>();
			List<Integer> idsList = Arrays.asList(ids);
			paramList.put("ids", idsList);

			TypedQuery<TBcOrg> query = getEntityManager().createQuery(hql, TBcOrg.class);
			this.setParameterList(query, paramList);
			List<TBcOrg> factoryList = query.getResultList();
			factoryList.forEach(x -> {
				this.getEntityManager().remove(x);
			});

			commonResult.setIsSuccess(true);
			commonResult.setMessage("删除成功！");
		} catch (Exception ex) {
			// 删除出现异常，绑定异常信息在消息结果对象
			commonResult.setIsSuccess(false);
			commonResult.setMessage(ex.getMessage());
		}
		// 返回消息结果对象
		return commonResult;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CommonResult updateTBcOrg(TBcOrg tBcOrg) {
		// 初始化消息结果类
		CommonResult commonResult = new CommonResult();
		try {
			getEntityManager().merge(tBcOrg);
			commonResult.setResult(tBcOrg);
			commonResult.setIsSuccess(true);
			commonResult.setMessage("保存成功！");
		} catch (Exception ex) {
			commonResult.setIsSuccess(false);
			commonResult.setMessage(ex.getMessage());
		}
		// 返回消息结果对象
		return commonResult;
	}

	@Override
	public TBcOrg getSingleTBcOrg(int id) {
		try {
			return getEntityManager().find(TBcOrg.class, id);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<TBcOrg> getTBcOrgList() {
		try {
			// 查询字符串
			String hql = "from TBcOrg t where t.isDelete=0 order by t.sortNum";
			Map<String, Object> paramList = new HashMap<String, Object>();
			TypedQuery<TBcOrg> query = getEntityManager().createQuery(hql, TBcOrg.class);
			this.setParameterList(query, paramList);
			return query.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public PaginationBean<TBcOrg> getTBcOrg(String name, Pagination page) {
		try {
			// 查询字符串
			StringBuilder hql = new StringBuilder("from TBcOrg t where t.isDelete=0 ");
			// 参数集合
			Map<String, Object> paramList = new HashMap<String, Object>();
			// 名称/简称
			if (!StringUtils.isEmpty(name)) {
				hql.append(" and (t.orgCode like :name escape '/' or t.orgName like :name escape '/') ");
				paramList.put("name", "%" + this.sqlLikeReplace(name) + "%");
			}
			hql.append(" order by t.sortNum");
			// 调用基类方法查询返回结果
			return this.findAll(page, hql.toString(), paramList);
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Object[]> getSingleTBcOrgByName(String name) {
		try {
			// 查询字符串
			StringBuffer sql = new StringBuffer("select id from T_BC_Org " + "where Is_Delete = 0");
			if (StringUtils.isNotEmpty(name)) {
				sql.append(" and Org_Name = '" + name + "'");
			}
			Query query = getEntityManager().createNativeQuery(sql.toString());
			return query.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public List<Object[]> getSingleTBcOrgByCode(String code) {
		try {
			// 查询字符串
			StringBuffer sql = new StringBuffer("select id from T_BC_Org " + "where Is_Delete = 0");
			if (StringUtils.isNotEmpty(code)) {
				sql.append(" and Org_Code = '" + code + "'");
			}
			Query query = getEntityManager().createNativeQuery(sql.toString());
			return query.getResultList();
		} catch (Exception ex) {
			throw ex;
		}
	}

}
