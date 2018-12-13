/**  

* <p>Title: DBOperationSpeed.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月13日 下午5:22:54 

* @version 1.0  

*/
package org.father.API.controller.testDBOperationSpeed;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.father.API.pojo.TestDBOperationSpeed.testDbOperationSpeed;
import org.father.API.service.testDB.ServiceTestDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.print.resources.serviceui;

/**
 * 
 * <p>
 * Title: DBOperationSpeed
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author haitao.zhang
 * 
 * @date 2018年12月13日 下午5:22:54
 * 
 */
@Controller
@RequestMapping(value = "zht/api/testDBOperation")
public class DBOperationSpeed {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private ServiceTestDB serviceTestDB;
	@Transactional
	public void testJdbcTemplate(List<testDbOperationSpeed> list) {

		String sql = "insert into TEST_DB_OPERATION_SPEED(NAME) VALUES(?);";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				ps.setString(1, list.get(i).getName());
			}

			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return list.size();
			}
		});
	}
	
	@GetMapping
	public void test() {
		List<testDbOperationSpeed> speeds = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			testDbOperationSpeed speed = new testDbOperationSpeed();
			speed.setName(new StringBuilder().append("学生").append(i).toString());
			speeds.add(speed);
		}
		long start = System.currentTimeMillis();
		testJdbcTemplate(speeds);
		long end = System.currentTimeMillis();

		System.out.println("jdbc:" + (end - start));
			System.out.println("****************************************");
		long start2 = System.currentTimeMillis();
		serviceTestDB.testEntityManager(speeds);
		long end2 = System.currentTimeMillis();

		System.out.println("entity:" + (end2 - start2));
	}
}
