package org.father.API.service.compareInitial;

import org.Father.COMMON.exception.MyException;
import org.Father.COMMON.util.CommonResult;
import org.father.API.pojo.compareInitial.stdCompareInitial;

 /**
  * 
 
 * <p>Title: stdCompareInitialService</p>  
 
 * <p>Description: </p>  
 
 * @author haitao.zhang  
 
 * @date 2018年12月5日 下午1:36:42
  */
public interface stdCompareInitialService{
	
	/**
	 * 	添加初始化
	
	 * <p>Title: add</p>  
	
	 * <p>Description: </p>  
	
	 * @param entity
	 * @return
	 */
	CommonResult add(stdCompareInitial entity)throws Exception;
	
	/**
	 *       更新初始化
	
	 * <p>Title: update</p>  
	
	 * <p>Description: </p>  
	
	 * @param entity
	 * @return
	 */
	CommonResult update(stdCompareInitial entity)throws Exception;
	
	/**
	 * 删除初始化
	
	 * <p>Title: delete</p>  
	
	 * <p>Description: </p>  
	
	 * @param ID
	 * @return
	 */
	CommonResult delete(Integer ID)throws Exception;
	
	/**
	 * 获取初始化   根据ID
	
	 * <p>Title: getOne</p>  
	
	 * <p>Description: </p>  
	
	 * @param ID
	 * @return
	 */
	stdCompareInitial getOne(Integer ID)throws MyException;
}
