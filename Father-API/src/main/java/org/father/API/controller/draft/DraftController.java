package org.father.API.controller.draft;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.Father.COMMON.excel.FlowDiagramExport;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.father.API.dao.draft.TOrFlowDiagramDraftRepository;
import org.father.API.pojo.draft.TOrFlowDiagramDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description TODO
 * 草稿控制类
 * @author linjian
 * @create 2018-10-24 11:08
 * @Version 1.0
 */

@CrossOrigin
@RestController
@RequestMapping(value = "api/pims/draft", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DraftController{
    
	@Autowired
	private Draft util;
    /**
     * 导出
     * @param pimsId
     * @param caseId
     * @param periodId
     * @return
     * @throws Exception
     */
    @PostMapping("/exportDraft")
    public void exportManageExcel(HttpServletResponse response,TOrFlowDiagramDraft obj) throws Exception {
    	ServletOutputStream sos=response.getOutputStream();
        //单独导出流程图
    	XSSFWorkbook workbook =new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("流程图");
        
        workbook=util.exportExcel(workbook,obj);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename="+"export.xlsx");//Excel文件名
       
        	workbook.write(sos);
        	sos.flush();
        	sos.close();
    }
    
    /**  

    * <p>Title: Draft</p>  

    * <p>Description: </p>  

    * @author haitao.zhang  

    * @date 2018年12月17日 下午3:02:34 

    */
    @Component
    private class Draft extends FlowDiagramExport<TOrFlowDiagramDraft>{
    	
    	@Autowired
    	private TOrFlowDiagramDraftRepository TOrFlowDiagramDraftRepository;

    	@Override
    	protected List<TOrFlowDiagramDraft> getList(TOrFlowDiagramDraft t) {
    		return TOrFlowDiagramDraftRepository.getBypimsModelIdAndCaseIdAndPeriodIdAndDataSourceCode(t.getPimsModelId(),t.getCaseId(),t.getPeriodId(),t.getDataSourceCode());
    	}

    }
}
