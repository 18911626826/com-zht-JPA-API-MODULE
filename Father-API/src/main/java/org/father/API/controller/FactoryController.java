package org.father.API.controller;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.apache.commons.lang3.StringUtils;
import org.father.API.dao.StdFactoryRepository;
import org.father.API.pojo.stdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="com/zht/factory")
public class FactoryController {
	
	@Autowired
	private StdFactoryRepository stdFactoryRepository;
	
	@GetMapping()
	public PaginationBean<stdFactory> getStdFactory(Pagination page,String name){
		PaginationBean<stdFactory> pageFactory=new PaginationBean<>();
		if(StringUtils.isNotBlank(name)) name=StringUtils.trimToNull(name.trim());//不等于空就去掉空格
			try {
				return stdFactoryRepository.getStdFactory(page, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return pageFactory;
			}
	}
	
	@GetMapping("/getPageJPA")
	public PaginationBean<stdFactory> getStdFactoryJPA(Pagination page,String name){
		PageRequest pr=new PageRequest(page.getPageIndex(), page.getPageSize());
		
		Page<stdFactory> stdsPage=stdFactoryRepository.findByFacIsDeleteNotNullAndFacCodeLikeOrFacNameLikeOrderByFacNo("%"+name+"%","%"+name+"%",pr);
		
		PaginationBean<stdFactory> pageff=new PaginationBean<>(page,stdsPage.getTotalElements());
		pageff.setPageList(stdsPage.getContent());
		return pageff;
	}

}
