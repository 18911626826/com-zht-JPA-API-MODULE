package org.Father.COMMON.util;

import java.rmi.RemoteException;
import java.sql.Timestamp;

import org.Father.COMMON.pojo.common.BasicInfo;
import org.Father.COMMON.pojo.common.BasicInfoPO;
import org.springframework.beans.BeanUtils;

/*
 * 公共转换实现类
 * 模块编号：pcitc_wm_common_class_CommonUtil
 * 作    者：pcitc
 * 创建时间：2017/09/17
 * 修改编号：1
 * 描    述：公共转换实现类
 */
public class CommonUtil {

	static String[] ignoreList = { "crtUserId", "crtDate", "crtUserName", "mntUserId", "mntDate", "mntUserName" };

	/**
	 * 两个实体相互赋值使用(创建人、创建时间等字段不进行拷贝)
	 * 
	 * @author pcitc 2018/5/15
	 * @param sourceObj 源实体
	 * @param targetObj 目标实体
	 * @throws Exception
	 */
	public static <S, T> void objectExchange(S sourceObj, T targetObj) throws Exception {
		BeanUtils.copyProperties(sourceObj, targetObj, ignoreList);
	}

	public static <S, T> void objectExchange(S sourceObj, T targetObj, String[] ignoreLists) throws Exception {
		BeanUtils.copyProperties(sourceObj, targetObj, ignoreLists);
	}

	/**
	 * 统一处理创建人、修改人的信息、时间
	 * 
	 * @author pcitc 2018/5/15
	 * @param t    泛型
	 * @param type 新增还是修改（1：新增；2：修改）
	 * @return 处理完创建和修改人信息的实体
	 */
	public static <T extends BasicInfo> T returnValue(T t, int type) throws RemoteException {
		CommonProperty commonProperty = new CommonProperty();
		t.setMntUserId(commonProperty.getUserId());
		t.setMntUserName(commonProperty.getUserName());
		t.setMntDate(commonProperty.getSystemDateTime());
		if (type == 1) {
			t.setCrtUserId(commonProperty.getUserId());
			t.setCrtUserName(commonProperty.getUserName());
			t.setCrtDate(commonProperty.getSystemDateTime());
		}
		return t;
	}

	/**
	 * 计划优化 统一处理创建人、修改人的信息、时间
	 * 
	 * @author linjian 2018/8/29
	 * @param t    泛型
	 * @param type 新增还是修改（1：新增；2：修改）
	 * @return 处理完创建和修改人信息的实体
	 */
	public static <T extends BasicInfoPO> T returnValue(T t, int type) throws RemoteException {
		CommonProperty commonProperty = new CommonProperty();
		if (type == 1) {
			t.setCreateEmpId(commonProperty.getUserId());
			t.setCreateEmpName(commonProperty.getUserName());
			t.setCreateTime(new Timestamp(commonProperty.getSystemDateTime().getTime()));
		}
		if (type == 2) {
			t.setModifyEmpId(commonProperty.getUserId());
			t.setModifyEmpName(commonProperty.getUserName());
			t.setModifyTime(new Timestamp(commonProperty.getSystemDateTime().getTime()));
		}
		return t;
	}
}
