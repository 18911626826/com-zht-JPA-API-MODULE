package org.father.API.service.factory;

import java.util.List;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.Father.COMMON.util.CommonResult;
import org.father.API.service.factory.entity.StdFactoryEntity;

/**
 * 分厂 业务层自定义 接口
 * 
 * @author haitao.zhang 2018-11-8
 * @version 1.0
 */
public interface StdFactoryService {

	/**
	 * 分厂信息 添加（业务层） @return @exception
	 */
	CommonResult insert(StdFactoryEntity stdFactoryEntity) throws Exception;

	/**
	 * 分厂信息 更新（业务层） @return @exception
	 */
	CommonResult updateStdFactory(StdFactoryEntity stdFactoryEntity) throws Exception;

	/**
	 * 分厂信息 删除（业务层） @param facIDs 分厂id数组 @return @exception
	 */
	CommonResult deleteStdFactory(Long[] facIDs) throws Exception;

	/**
	 * 分厂信息 得到某个分厂（业务层） @param facID 分厂id @return @exception
	 */
	StdFactoryEntity getSingleStdFactory(Long facID) throws Exception;

	/**
	 * 分厂信息 得到多个分厂（业务层） @param facIDs 分厂id数组 @return @exception
	 */
	List<StdFactoryEntity> getStdFactoryList(Long[] facIDs) throws Exception;

	/**
	 * 分厂信息 分页获得分厂列表（业务层） @param page 分页对象 @param name 模糊查询条件 分厂编码
	 * 分厂名称 @return @exception
	 */
	PaginationBean<StdFactoryEntity> getStdFactory(Pagination page, String name) throws Exception;

	/**
	 * 分厂信息 根据分厂编码校验（业务层）
	 * 
	 * @param facCode 分厂编码
	 * @return
	 */
	/*
	 * List<Object[]> getSingleStdFactoryByCode(String facCode);
	 * 
	 *//**
		 * 分厂信息 根据分厂名称校验（业务层）
		 * 
		 * @param facName 分厂名称
		 * @return
		 *//*
			 * List<Object[]> getSingleStdFactoryByName(String facName);
			 */

	/**
	 * 分厂信息 得到多个分厂（业务层） 用于excel导出
	 * 
	 * @return
	 */
	List<StdFactoryEntity> getStdFactoryListForExport(String orgId) throws Exception;

	/**
	 * 分厂excel批量导入
	 * 
	 * <p>
	 * Title: saveStdFactoryforImport
	 * </p>
	 * 
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param facs
	 */
	CommonResult saveStdFactoryforImport(List<StdFactoryEntity> facs) throws Exception;
}
