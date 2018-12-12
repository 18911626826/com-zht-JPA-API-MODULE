package org.father.API.controller.compareInitial;

import org.Father.COMMON.exception.MyException;
import org.Father.COMMON.util.CommonResult;
import org.father.API.pojo.compareInitial.stdCompareInitial;
import org.father.API.service.compareInitial.stdCompareInitialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 	初始化页面    控制层

* <p>Title: StdCompareInitialController</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月5日 下午1:37:04
 */
@CrossOrigin
@RestController
@RequestMapping(value="api/pims/compareInitial",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StdCompareInitialController {
	
	@Autowired
	private stdCompareInitialService service;
	
	/**
	 * 添加初始化
	
	 * <p>Title: add</p>  
	
	 * <p>Description: </p>  
	
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@PostMapping
	public CommonResult add(stdCompareInitial entity)throws Exception{
		CommonResult cr=new CommonResult();
		cr.setIsSuccess(false);
		
		if(entity.getCompInitId()==null) {	
			return service.add(entity);
		}else {
			cr.setMessage("添加记录不能指定ID！");
		    return cr;
		}
	}
	
	/**
	 * 删除初始化
	
	 * <p>Title: delete</p>  
	
	 * <p>Description: </p>  
	
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping
	public CommonResult delete(@RequestBody Integer ID)throws Exception{
		return service.delete(ID);
	}
	
	/**
	 * 更新初始化
	
	 * <p>Title: update</p>  
	
	 * <p>Description: </p>  
	
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@PutMapping
	public CommonResult update(stdCompareInitial entity)throws Exception{
		CommonResult cr=new CommonResult();
		cr.setIsSuccess(false);
		
		if(entity!=null && entity.getCompInitId()!=null) {
			if(null!=service.getOne(entity.getCompInitId())) {
				return service.update(entity);
			}else {
				cr.setMessage("更新的记录不存在！");
			    return cr;
			}
		}else {
		    cr.setMessage("更新记录的ID不能为空！");
		    return cr;
		}
	}
	
	/**
	 * 获取初始化   根据ID
	
	 * <p>Title: getOne</p>  
	
	 * <p>Description: </p>  
	
	 * @param ID
	 * @return
	 */
	@GetMapping("/{id}")
	public stdCompareInitial getOne(@PathVariable(value="id") Integer ID)throws MyException{
			return service.getOne(ID);
	}
	
}
