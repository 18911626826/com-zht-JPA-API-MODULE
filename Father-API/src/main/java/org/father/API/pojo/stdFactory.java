package org.father.API.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**  

* <p>Title: stdFactory</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月6日 下午2:53:21 

*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class stdFactory {
	//facId  Description:分厂id
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer facId;
	
	//facCode  Description:分厂编码
	private String facCode;
	
	//facName  Description:分厂名称
	private String facName;

	//facNo  Description:分厂排序
	private Integer facNo;

	//facIsDelete  Description:分厂删除标记   默认：0
	private Integer facIsDelete;

	//facCreateUser  Description:分厂信息创建者
	private String facCreateUser;

	//facCreateTime  Description:分厂信息创建时间
	private Timestamp facCreateTime;

	//facUpdateUser  Description:分厂信息修改者
	private String facUpdateUser;

	//facUpdateTime  Description:分厂信息修改时间
	private Timestamp facUpdateTime;

	//orgId  Description:企业ID
	private Integer orgId;
	
	//orgName  Description:企业名称
	@Transient
	private String orgName;
	
	public stdFactory(String facCode, String facName, Integer facNO, String orgName) {
		super();
		this.facCode = facCode;
		this.facName = facName;
		this.facNo = facNO;
		this.orgName = orgName;
	}
	
	public stdFactory(Integer facID, String facCode, String facName, Integer facNO, String orgName,Integer orgId) {
		super();
		this.facId = facID;
		this.facCode = facCode;
		this.facName = facName;
		this.facNo = facNO;
		this.orgName = orgName;
		this.orgId=orgId;
	}
}
