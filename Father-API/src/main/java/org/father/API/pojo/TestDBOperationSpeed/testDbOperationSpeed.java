/**  

* <p>Title: testDbOperationSpeed.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月13日 下午5:31:16 

* @version 1.0  

*/
package org.father.API.pojo.TestDBOperationSpeed;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**  

* <p>Title: testDbOperationSpeed</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月13日 下午5:31:16 

*/
@Getter
@Setter
@Entity
public class testDbOperationSpeed {
	@Id
	private String name;
}
