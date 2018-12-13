package org.father.API.dao.MasterData;

import java.util.List;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.Father.COMMON.util.CommonResult;
import org.father.API.pojo.MasterData.TBcOrg;

public interface TBcOrgRepositoryCustom {

	CommonResult deleteTBcOrg(Integer[] ids);

	CommonResult updateTBcOrg(TBcOrg tBcOrg);

	TBcOrg getSingleTBcOrg(int id);

	List<TBcOrg> getTBcOrgList();

	PaginationBean<TBcOrg> getTBcOrg(String name, Pagination page);

	List<Object[]> getSingleTBcOrgByName(String name);

	List<Object[]> getSingleTBcOrgByCode(String code);

}