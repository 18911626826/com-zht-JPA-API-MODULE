package org.father.API.pojo.factory;

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
 * 分厂持久层映射实体类
 * @author haitao.zhang 2018-11-8
 * @version 1.0
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STD_FACTORY",schema="DBO")
public class StdFactory {
	
	/**
	 * 分厂id
	 */
	@Id
	@Column(name="FAC_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long facID;
	
	/**
	 * 分厂编码
	 */
	@Column(name="FAC_CODE")
	private String facCode;
	
	/**
	 * 分厂名称
	 */
	@Column(name="FAC_NAME")
	private String facName;
	
	/**
	 * 分厂排序
	 */
	@Column(name="FAC_NO")
	private Integer facNO;

	/**
	 * 分厂删除标记   默认：0
	 */
	@Column(name="FAC_IS_DELETE")
	private Integer facIsDelete;
	
	/**
	 * 分厂信息创建者
	 */
	@Column(name="FAC_CREATE_USER")
	private String facCreateUser;
	
	/**
	 * 分厂信息创建时间
	 */
	@Column(name="FAC_CREATE_TIME")
	private Timestamp facCreateTime;
	
	/**
	 * 分厂信息修改者
	 */
	@Column(name="FAC_UPDATE_USER")
	private String facUpdateUser;
	
	/**
	 * 分厂信息修改时间
	 */
	@Column(name="FAC_UPDATE_TIME")
	private Timestamp facUpdateTime;
	
	/**
	 * 分厂信息  所属企业ID
	 */
	@Column(name="ORG_ID")
	//企业ID
	private Integer orgId;
	
	//企业名称
	@Transient
	private String orgName;
	
	public StdFactory(String facCode, String facName, Integer facNO, String orgName) {
		super();
		this.facCode = facCode;
		this.facName = facName;
		this.facNO = facNO;
		this.orgName = orgName;
	}
	
	public StdFactory(Long facID, String facCode, String facName, Integer facNO, String orgName,Integer orgId) {
		super();
		this.facID = facID;
		this.facCode = facCode;
		this.facName = facName;
		this.facNO = facNO;
		this.orgName = orgName;
		this.orgId=orgId;
	}
}
