package org.father.API.dao.compareInitial.impl;

import org.father.API.dao.common.BaseRepository;
import org.father.API.dao.compareInitial.StdCompareInitialRepositoryCustom;
import org.father.API.pojo.compareInitial.stdCompareInitial;

/**
 * 

* <p>Title: StdCompareInitialRepositoryImpl</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月5日 上午11:23:18
 */
public class StdCompareInitialRepositoryImpl extends BaseRepository<stdCompareInitial, Integer> implements StdCompareInitialRepositoryCustom{

	/*@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	@Override
	public stdCompareInitial update(stdCompareInitial entity) {
		return getEntityManager().merge(entity);
	}*/
	
}
