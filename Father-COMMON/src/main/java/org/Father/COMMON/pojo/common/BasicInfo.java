package org.Father.COMMON.pojo.common;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/*
 * 创建人修改人相关信息公用实体
 * 模块编号：pcitc_wm_pojo_class_BasicInfo
 * 作    者：pcitc
 * 创建时间：2017/09/10
 * 修改编号：1
 * 描    述：创建人修改人相关信息公用实体
 */
@MappedSuperclass
@DynamicUpdate
public abstract class BasicInfo {
	/**
	 * 创建时间
	 */
	@Column(name = "CRT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date crtDate;

	/**
	 * 创建人ID
	 */
	@Column(name = "CRT_USER_ID")
	private String crtUserId;

	/**
	 * 创建人名称
	 */
	@Column(name = "CRT_USER_NAME")
	private String crtUserName;

	/**
	 * 修改时间
	 */
	@Column(name = "MNT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mntDate;

	/**
	 * 修改人ID
	 */
	@Column(name = "MNT_USER_ID")
	private String mntUserId;

	/**
	 * 修改人
	 */
	@Column(name = "MNT_USER_NAME")
	private String mntUserName;

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUserId() {
		return crtUserId;
	}

	public void setCrtUserId(String crtUserId) {
		this.crtUserId = crtUserId;
	}

	public String getCrtUserName() {
		return crtUserName;
	}

	public void setCrtUserName(String crtUserName) {
		this.crtUserName = crtUserName;
	}

	public Date getMntDate() {
		return mntDate;
	}

	public void setMntDate(Date mntDate) {
		this.mntDate = mntDate;
	}

	public String getMntUserId() {
		return mntUserId;
	}

	public void setMntUserId(String mntUserId) {
		this.mntUserId = mntUserId;
	}

	public String getMntUserName() {
		return mntUserName;
	}

	public void setMntUserName(String mntUserName) {
		this.mntUserName = mntUserName;
	}
}
