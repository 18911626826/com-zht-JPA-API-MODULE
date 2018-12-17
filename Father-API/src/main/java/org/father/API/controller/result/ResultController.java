package org.father.API.controller.result;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.father.API.dao.result.TOrFlowDiagramRepository;
import org.father.API.pojo.result.TOrFlowDiagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-11-02 14:15
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = "api/pims/orResult", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResultController {

    @Autowired
    private TOrFlowDiagramRepository tOrFlowDiagramRepository;
    
    @Autowired
    private Result result;

    @GetMapping("/exportResult")
    public void exportManageExcel(HttpServletResponse response,String resultId, String name, String dataSourceCode) throws Exception {
    	
    	//单独导出流程图
    	XSSFWorkbook workbook =new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("流程图");
        
        //调用封装的  导出工具类
//        Result result=new Result();//自己new出来的对象   里面的属性 @Autowired 也是获取不到  因为不在spring的管理范围内，故此在本类中（也就是Controller中， 用@Autowired注入）
        TOrFlowDiagram diagram=new TOrFlowDiagram();
        diagram.setResultId(resultId);
        workbook=result.exportExcel(workbook,diagram);
        
        /*ExportExcelResult exportResult=new ExportExcelResult();
        exportResult.setWorkbook(workbook);
        exportDeviceResult=exportResult;*/

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+"export.xlsx");//Excel文件名
        try {
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if(exportDeviceResult!=null) {
        	exportDeviceResult.export(response.getOutputStream());
        }*/
        workbook.write(response.getOutputStream());
    }
    
}
