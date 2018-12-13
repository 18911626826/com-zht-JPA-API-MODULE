package org.father.API.controller.factory;

import java.util.List;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.Father.COMMON.util.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.father.API.service.factory.StdFactoryService;
import org.father.API.service.factory.entity.StdFactoryEntity;
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

/*import com.pcitc.dm.common.excel.domain.SubheadingVo;
import com.pcitc.dm.common.excel.export.ExportConfigFactory;
import com.pcitc.dm.common.excel.export.FileExportor;
import com.pcitc.dm.common.excel.export.domain.common.ExportConfig;
import com.pcitc.dm.common.excel.export.domain.common.ExportResult;*/

/**
 * 分厂 控制层自定义
 * 
 * @author haitao.zhang 2018-11-8
 * @version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping(value = "api/pims/factory", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StdFactoryController {

	@Autowired
	private StdFactoryService stdFactoryService;

	/**
	 * 分厂信息 添加（ 控制层）
	 * 
	 * @return
	 */
	@PostMapping
	private CommonResult addStdFactory(StdFactoryEntity factoryEntity) {
		CommonResult cr = new CommonResult();
		try {
			return stdFactoryService.insert(factoryEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cr.setIsSuccess(false);
			cr.setMessage("插入异常！");
			e.printStackTrace();
		}
		return cr;
	}

	/**
	 * 分厂信息 删除（ 控制层）
	 * 
	 * @return
	 */
	@DeleteMapping
	private String deleteStdFactory(@RequestBody Long[] ids) {
		CommonResult cr = new CommonResult();
		if (ids != null && ids.length > 0) {
			try {
				return stdFactoryService.deleteStdFactory(ids).getMessage();
			} catch (Exception e) {
				cr.setIsSuccess(false);
				cr.setMessage("删除异常");
				e.printStackTrace();
			}
		}
		return cr.getMessage();
	}

	/**
	 * 分厂信息 修改（ 控制层）
	 * 
	 * @return
	 */
	@PutMapping
	private CommonResult updateStdFactory(StdFactoryEntity entity) {
		CommonResult cr = new CommonResult();
		if (entity != null) {
			try {
				return stdFactoryService.updateStdFactory(entity);
			} catch (Exception e) {
				cr.setIsSuccess(false);
				cr.setMessage("修改异常");
				e.printStackTrace();
			}
		}
		return cr;
	}

	/**
	 * 分厂信息 获得一条信息（ 控制层）
	 * 
	 * @param id 分厂id
	 * @return
	 */
	@GetMapping("/{id}")
	private StdFactoryEntity getSingleStdFactory(@PathVariable Long id) {
		StdFactoryEntity entity = new StdFactoryEntity();
		try {
			return stdFactoryService.getSingleStdFactory(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * 分厂信息 获得一条信息（ 控制层）
	 * 
	 * @return
	 */
	@GetMapping()
	private PaginationBean<StdFactoryEntity> getStdFactory(Pagination page, String name) {
		PaginationBean<StdFactoryEntity> pageFactory = new PaginationBean<>();
		if (StringUtils.isNotEmpty(name))
			name = name.trim();// 不等于空就去掉空格
		try {
			return stdFactoryService.getStdFactory(page, name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return pageFactory;
		}
	}

	/**
	 * 分厂信息 导出（ 控制层）
	 * 
	 * @return
	 */
	/*@GetMapping("/export")
	private void export(HttpServletResponse response) {
		List<StdFactoryEntity> facEntitys = null;
		SubheadingVo subheadingVo = new SubheadingVo();
		try {
			facEntitys = stdFactoryService.getStdFactoryListForExport(null);
			subheadingVo.setHeader("分厂");
			subheadingVo.setSheetName("分厂");
			ExportConfig exportDeviceConfig = ExportConfigFactory
					.getExportConfig(StdFactoryController.class.getResourceAsStream("/factory/StdFactoryConfig.xml"));
			ExportResult exportDeviceResult = FileExportor.getExportResult(exportDeviceConfig, facEntitys,
					subheadingVo);

			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition", "attachment;filename=" + "StdFactory.xls");// Excel文件名

			try {
				response.flushBuffer();
			} catch (IOException e) {
				e.printStackTrace();
			}
			exportDeviceResult.export(response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	/**
	 * 分厂信息 查询分厂ID和分厂名称（ 控制层）
	 * 
	 * @return
	 */
	@GetMapping("/getStdFactoryList")
	public List<StdFactoryEntity> getStdFactoryList(String orgId) throws Exception {
		List<StdFactoryEntity> stdFactoryEntityList = stdFactoryService.getStdFactoryListForExport(orgId);
		return stdFactoryEntityList;
	}

	/**
	 * 月计划数数据导入
	 * 
	 * @param filePath 文件绝对路径
	 * @return
	 * @throws Exception
	 */
	/*@PostMapping("/importStdFactoryExcel")
	public CommonResult importStdFactoryExcel(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();

		CommonResult commonResult = new CommonResult();

		// FileInputStream is = new FileInputStream();
		Workbook wookbook = null;

		// 判断是否为excel类型文件
		if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
			commonResult.setIsSuccess(false);
			commonResult.setMessage("文件不是excel类型");
			System.out.println("文件不是excel类型");
			return commonResult;
		} else {
			if (fileName.endsWith(".xls")) {
				// 2003版本的excel，用.xls结尾
				wookbook = new HSSFWorkbook(file.getInputStream());// 得到工作簿
			}
			if (fileName.endsWith(".xlsx")) {
				// 2007版本的excel，用.xlsx结尾
				wookbook = new XSSFWorkbook(file.getInputStream());// 得到工作簿
			}
		}

		// XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);
		StdFactoryEntity monthPlan = null;
		List<StdFactoryEntity> list = new ArrayList<StdFactoryEntity>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < wookbook.getNumberOfSheets(); numSheet++) {
			Sheet sheet = wookbook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row != null) {
					monthPlan = new StdFactoryEntity();
					Cell facCode = row.getCell(0);
					Cell facName = row.getCell(1);
					Cell facNO = row.getCell(2);

					monthPlan.setFacCode(facCode.toString());
					;
					monthPlan.setFacName(facName.toString());
					monthPlan.setFacNO(Integer.parseInt(facNO.toString()));

					list.add(monthPlan);
				}
			}
		}
		if (list != null && list.size() > 0) {
			return stdFactoryService.saveStdFactoryforImport(list);
		}

		return commonResult;
	}*/

}
