/**  

* <p>Title: ServiceTestDB.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月13日 下午6:39:48 

* @version 1.0  

*/
package org.father.API.service.testDB;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.father.API.pojo.TestDBOperationSpeed.testDbOperationSpeed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

/**  

* <p>Title: ServiceTestDB</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月13日 下午6:39:48 

*/
@Service
public class ServiceTestDB {
	
	@PersistenceContext(name = "EntityManagerFactory")
	private EntityManager entityManager;
	
	@Transactional//service层开启事务    如果放在controller层  依然会报错
	public void testEntityManager(List<testDbOperationSpeed> list) {
		/*EntityTransaction et=entityManager.getTransaction();//此种开启事务方法   行不通   报错
		if(et!=null) {
				et.begin();*/
			for (int i = 0; i < list.size(); i++) {
				testDbOperationSpeed speed = list.get(i);
				entityManager.persist(speed);
				if (i % 1000 == 0) {
					entityManager.flush();
					entityManager.clear();
				}
			}
			/*et.commit();
		}*/
	}
}
