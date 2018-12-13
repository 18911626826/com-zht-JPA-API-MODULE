package org.father.API.service.factory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 分厂业务层实体类
 * 
 * @author haitao.zhang 2018-11-8
 * @version 1.0
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StdFactoryEntity {

	/**
	 * 分厂id
	 */
	private Long facID;

	/**
	 * 分厂编码
	 */
	private String facCode;

	/**
	 * 分厂名称
	 */
	private String facName;

	/**
	 * 分厂排序
	 */
	private Integer facNO;

	/**
	 * 企业名称
	 */
	private String orgName;

	/**
	 * 企业ID
	 */
	private Integer orgId;

	// batchInsertFailMessage Description:批处理失败消息
	private String batchInsertFailMessage;

	public StdFactoryEntity(Long facID, String facCode, String facName, Integer facNO, String orgName, Integer orgId) {
		super();
		this.facID = facID;
		this.facCode = facCode;
		this.facName = facName;
		this.facNO = facNO;
		this.orgName = orgName;
		this.orgId = orgId;
	}

}
