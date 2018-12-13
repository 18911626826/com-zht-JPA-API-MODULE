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
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.father.API.pojo.TestDBOperationSpeed.testDbOperationSpeed;
import org.springframework.stereotype.Service;

/**  

* <p>Title: ServiceTestDB</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月13日 下午6:39:48 

*/
@Service
public class ServiceTestDB {
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@PersistenceContext(name = "EntityManagerFactory")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Transactional
	public void testEntityManager(List<testDbOperationSpeed> list) {

		for (int i = 0; i < list.size(); i++) {
			testDbOperationSpeed speed = list.get(i);
			entityManager.persist(speed);
			if (i % 1000 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
	}
}
