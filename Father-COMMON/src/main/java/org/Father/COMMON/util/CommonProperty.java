package org.Father.COMMON.util;

import java.util.Date;

/*
 * 用户属性公共方法
 * 模块编号：pcitc_wm_common_class_CommonProperty
 * 作	者：pcitc
 * 创建时间：2017/09/25
 * 修改编号：1
 * 描	述：用户属性公共方法
 */
public class CommonProperty {

    private volatile String userId;

    private volatile String userName;

    private Date systemDateTime = new Date();

    /**
     * 获取数据库服务器系统时间
     */
    public Date getSystemDateTime() {
        //todo：根据具体的数据库类型，调整为获取数据库服务器时间
        return systemDateTime;
    }

    /**
     * 获取当前用户ID
     */
	public String getUserId() {
		// todo：根据具体登录方案来确定
		return "admin";
	}

    /**
     * 获取当前用户名称
     */
	public String getUserName() {
		//todo： 根据具体的登录方案来确定
		return "admin";
	}
}
