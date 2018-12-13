package org.father.API.pojo.MasterData;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "T_BC_Org", schema = "dbo")
public class TBcOrg {
	/**
	 * ID
	 */
	@Id
	@Column(name = "id")
	private Integer id;

	/**
	 * 企业code
	 */
	@Column(name = "Org_Code")
	private String orgCode;
	/**
	 * 企业名称
	 */
	@Column(name = "Org_Name")
	private String orgName;
	/**
	 * 企业简称
	 */
	@Column(name = "Org_SName")
	private String orgSname;
	/**
	 * 企业联系人code
	 */
	@Column(name = "User_Code")
	private String userCode;
	/**
	 * 企业联系人名
	 */
	@Column(name = "User_Name")
	private String userName;
	/**
	 * 企业联系人手机
	 */
	@Column(name = "User_Phone")
	private String userPhone;
	/**
	 * 是否启用
	 */
	@Column(name = "In_Use")
	private String inUse;
	/**
	 * 序号
	 */
	@Column(name = "Sort_Num")
	private int sortNum;
	/**
	 * 删除标识
	 */
	@Column(name = "Is_Delete")
	private int isDelete;
	/**
	 * 创建人
	 */
	@Column(name = "Crt_User_Id")
	private String crtUserId;
	/**
	 * 创建时间
	 */
	@Column(name = "Crt_Date")
	private Timestamp crtDate;
	/**
	 * 修改人
	 */
	@Column(name = "Mnt_User_Id")
	private String mntUserId;
	/**
	 * 修改时间
	 */
	@Column(name = "Mnt_Date")
	private Timestamp mntDate;

}
