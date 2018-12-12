package org.father.API.dao.compareInitial;

import org.father.API.pojo.compareInitial.stdCompareInitial;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 

* <p>Title: StdCompareInitialRepository</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月5日 上午11:10:16
 */
public interface StdCompareInitialRepository extends JpaRepository<stdCompareInitial,Integer>,StdCompareInitialRepositoryCustom{

}
