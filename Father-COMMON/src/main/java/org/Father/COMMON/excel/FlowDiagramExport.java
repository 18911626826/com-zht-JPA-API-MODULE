/**  

* <p>Title: FlowDiagramExport.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月17日 上午10:00:42 

* @version 1.0  

*/
package org.Father.COMMON.excel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**  

* <p>Title: FlowDiagramExport</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月17日 上午10:00:42 

*/
public abstract class FlowDiagramExport<T> {
	protected abstract List<T> getList(T t);
	
	private Class<T> entityClass;
	
	public FlowDiagramExport(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
	}
	
	public XSSFWorkbook exportExcel(XSSFWorkbook workbook,T t)throws Exception,InvocationTargetException{
    	//得到列表
    	List<T> diagrams=getList(t);
    	
    	if(diagrams!=null && diagrams.size()>0) {
    		Method method=entityClass.getMethod("getUnitName");
    		String[] unitNames=new String[diagrams.size()];
	    	//去重
    		for(int i=0;i<diagrams.size();i++) {
    			unitNames[i]=(String)entityClass.getMethod("getUnitName").invoke(diagrams.get(i));
    		}
	    	
	    	Set<String> set=new HashSet<>(Arrays.asList(unitNames));
	    	List<String> unitNamesDistinct=new ArrayList<>(set);
	    	
	    	//创建workbook和sheet
	    	//int unitNameNum=0;
	    	//组装excel
	    	
	    	XSSFSheet sheet=workbook.getSheet("流程图");
    		XSSFRow row=sheet.createRow(1);
        	workbook=this.createHeaderRow(workbook,row,sheet,"流程图",4);
	    	
	    	workbook=getOneExcelForm(workbook,3,1,unitNamesDistinct,0,diagrams);
    	}else {
    		//返回列表为空的逻辑
    		XSSFSheet sheet=workbook.getSheet("流程图");
    		XSSFRow row=sheet.createRow(1);
    		workbook=this.createHeaderRow(workbook,row,sheet,"流程图",4);
    	}	
    	//导出
    	return workbook;
    }
	
	private XSSFWorkbook getOneExcelForm(XSSFWorkbook workbook,int row2,int count,List<String> unitNamesDistinct,int unitNameNum,List<T> drafts) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	if(unitNameNum>=unitNamesDistinct.size()) {
    		return workbook;
    	}else {
    		List<T> draftsByUnitName=null;
			try {
				draftsByUnitName=new ArrayList<>();
				for(T draft:drafts) {
					String unitName=(String)entityClass.getMethod("getUnitName").invoke(draft);
					if(unitName.equals(unitNamesDistinct.get(unitNameNum))) {
						draftsByUnitName.add(draft);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        		unitNameNum++;
        		if(count>4) {
        			count=1;
        			row2=workbook.getSheet("流程图").getLastRowNum()+2;
            		workbook=process(workbook,row2,count,draftsByUnitName);
            		getOneExcelForm(workbook,row2,count+1,unitNamesDistinct,unitNameNum,drafts);
            		return workbook;
        		}else {
        			workbook= process(workbook,row2,count,draftsByUnitName);
            		count++;
            		getOneExcelForm(workbook,row2,count,unitNamesDistinct,unitNameNum,drafts);
            		return workbook;
        		}
        	}
    	}
	
	private XSSFWorkbook process(XSSFWorkbook workbook,int rowNum,int count,List<T> data) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
    	XSSFSheet sheet=workbook.getSheet("流程图");
    	XSSFRow row=null;
    	if(sheet.getRow(rowNum)!=null)
    		row=sheet.getRow(rowNum);
    	else
    		row=sheet.createRow(rowNum);
    	
		XSSFCell celltitle2=row.createCell(count==1?2:(count==2?9:(count==3?16:23)));
    	XSSFCell celltitle3=row.createCell(count==1?3:(count==2?10:(count==3?17:24)));
    	XSSFCell celltitle4=row.createCell(count==1?4:(count==2?11:(count==3?18:25)));
    	XSSFCell celltitle5=row.createCell(count==1?5:(count==2?12:(count==3?19:26)));
    	
    	XSSFCell celltitle6=row.createCell(count==1?6:(count==2?13:(count==3?20:27)));
    	XSSFCell celltitle7=row.createCell(count==1?7:(count==2?14:(count==3?21:28)));
	
    	celltitle2.setCellValue("进料");
    	celltitle3.setCellValue("加工量");
    	
    	celltitle5.setCellValue("产品/侧线");
    	celltitle6.setCellValue("收率(%)");
    	celltitle7.setCellValue("产量");
        
    	//分成两个集合
    	if (!data.isEmpty()) {
	      	List<T> type0=new ArrayList<>();
	      	List<T> type1=new ArrayList<>();
	      	
	      	for(T t:data) {
	      		if("投入".equals((String)(entityClass.getMethod("getInOutType").invoke(t)))) {
	          		type0.add(t);
	          	}else {
	          		type1.add(t);
	          	}
	      	}
	      	int type0Num=type0.size();
	      	int type1Num=type1.size();
	      	
	      	XSSFRow rowtype1=null;
	      	XSSFRow rowtype0=null;
	      	
	      	if(type1Num>=type0Num) {
	      		for(int i=0;i<type1Num+5;i++) {
	      			rowtype1=sheet.getRow(rowNum+i+1)!=null?sheet.getRow(rowNum+i+1):sheet.createRow(rowNum+i+1);//产品对象数据多，以此创建row
	      		}
	      		
	      	}else {
	      		for(int i=0;i<type0Num+5;i++) {
	      			rowtype0=sheet.getRow(rowNum+i+1)!=null?sheet.getRow(rowNum+i+1):sheet.createRow(rowNum+i+1);//进料对象数据多，以此创建row
	      		}
	      		
	      	}
	      	//设置表格尾部的格式
	      	sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5-4:rowNum+type0Num+5-4).createCell(count==1?2:(count==2?9:(count==3?16:23))).setCellValue("合计:");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5-3:rowNum+type0Num+5-3).createCell(count==1?2:(count==2?9:(count==3?16:23))).setCellValue("性质:");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5-3:rowNum+type0Num+5-3).createCell(count==1?3:(count==2?10:(count==3?17:24))).setCellValue("数值");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5-2:rowNum+type0Num+5-2).createCell(count==1?2:(count==2?9:(count==3?16:23))).setCellValue("平均API");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5-1:rowNum+type0Num+5-1).createCell(count==1?2:(count==2?9:(count==3?16:23))).setCellValue("平均硫含量");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).createCell(count==1?2:(count==2?9:(count==3?16:23))).setCellValue("平均酸值");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).createCell(count==1?5:(count==2?12:(count==3?19:26))).setCellValue("合计:");
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).createCell(count==1?6:(count==2?13:(count==3?20:27))).setCellValue(100);
      		
      		double sumQtype0=0;//加工量合计
      		double sumQtype1=0;//产量合计
      		//循环存储   进
      		for(int i=0;i<type0.size();i++) {
      			T draftType0=type0.get(i);
      			sheet.getRow(rowNum+1+i).createCell(count==1?2:(count==2?9:(count==3?16:23))).setCellValue((String)(entityClass.getMethod("getMtrlName").invoke(draftType0)));//进料
      			
      			double q=((BigDecimal)(entityClass.getMethod("getQuantity").invoke(draftType0))).doubleValue();
      			sheet.getRow(rowNum+1+i).createCell(count==1?3:(count==2?10:(count==3?17:24))).setCellValue(q);//加工量
      			sumQtype0+=q;//加工量合计
      		}
      		//循环存储  出
      		for(int i=0;i<type1.size();i++) {
      			T draftType1=type1.get(i);
      			sheet.getRow(rowNum+1+i).createCell(count==1?5:(count==2?12:(count==3?19:26))).setCellValue((String)(entityClass.getMethod("getMtrlName").invoke(draftType1)));//产品/侧线
      			sheet.getRow(rowNum+1+i).createCell(count==1?6:(count==2?13:(count==3?20:27)));//收率draftType1.getQuantity().doubleValue()
      			double q=((BigDecimal)(entityClass.getMethod("getQuantity").invoke(draftType1))).doubleValue();
      			sheet.getRow(rowNum+1+i).createCell(count==1?7:(count==2?14:(count==3?21:28))).setCellValue(q);//产量
      			sumQtype1+=q;//产量合计
      		}
      		
      		//计算收率（%）
      		for(int i=0;i<type1.size();i++) {
      			T draftType1=type1.get(i);
      			
      			if(((BigDecimal)(entityClass.getMethod("getQuantity").invoke(draftType1))).doubleValue()==0E-15) {//处理异常值   quantity = .000000000000000  对应  0E-15  问题
      				sheet.getRow(rowNum+i+1).createCell(count==1?6:(count==2?13:(count==3?20:27))).setCellValue(0);//收率
      			}else {
      				try {
      				sheet.getRow(rowNum+i+1).createCell(count==1?6:(count==2?13:(count==3?20:27))).setCellValue(new BigDecimal(((BigDecimal)entityClass.getMethod("getQuantity").invoke(draftType1)).doubleValue()/sumQtype1*100).setScale(2, RoundingMode.HALF_UP).doubleValue());//收率
      				}catch(Exception e) {
      					//e.printStackTrace(s);
      					sheet.getRow(rowNum+i+1).getCell(count==1?6:(count==2?13:(count==3?20:27))).setCellValue("NULL");
      				}
      			}
      		}
      		
      		//设置合计  以及  装置名称
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5-4:rowNum+type0Num+5-4).createCell(count==1?3:(count==2?10:(count==3?17:24))).setCellValue(sumQtype0);//加工量合计
      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).createCell(count==1?7:(count==2?14:(count==3?21:28))).setCellValue(sumQtype1);//产量合计
      		
      	//设置合并后的单元格背景颜色  以及  字体居中   垂直显示   设置   装置名称字段
      		XSSFCellStyle styleRegion=workbook.createCellStyle();
      		styleRegion.setWrapText(true);
      		styleRegion.setAlignment(XSSFCellStyle.ALIGN_CENTER);//字体居中
      		styleRegion.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
      		styleRegion.setFillBackgroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);//背景颜色
      		styleRegion.setFillPattern(FillPatternType.LESS_DOTS);
      		styleRegion.setBorderBottom(BorderStyle.THICK);
      		styleRegion.setBorderTop(BorderStyle.THICK);
      		
      		celltitle4.setCellStyle(styleRegion);
      		String unitName=(String)entityClass.getMethod("getUnitName").invoke(data.get(0));//data.get(0).getUnitName()
      		char[] chars=unitName.toCharArray();
            StringBuilder sb=new StringBuilder();
            for(char c:chars) {
                sb.append(c+"\n");
            }
      		celltitle4.setCellValue(new XSSFRichTextString(sb.toString()));
      		
      		//合并单元格   装置名称字段使用
      		sheet.addMergedRegion(new CellRangeAddress(rowNum,type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5,count==1?4:(count==2?11:(count==3?18:25)),count==1?4:(count==2?11:(count==3?18:25))));
      		
      		//设置单元格边框样式    顶部 
        	XSSFCellStyle style=workbook.createCellStyle();
    			style.setBorderTop(BorderStyle.THICK);
    			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
    			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//字体居中
    			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
    			
    	        
			//设置单元格边框样式    底部
    		XSSFCellStyle style2=workbook.createCellStyle();	
    			style2.setBorderBottom(BorderStyle.THICK);
    			style2.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    			style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);//字体居中
    			style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
    			
    			celltitle2.setCellStyle(style);
    			celltitle3.setCellStyle(style);
    			celltitle5.setCellStyle(style);
    			celltitle6.setCellStyle(style);
    			celltitle7.setCellStyle(style);
      		
    			sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).getCell(count==1?2:(count==2?9:(count==3?16:23))).setCellStyle(style2);
	      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).createCell(count==1?3:(count==2?10:(count==3?17:24))).setCellStyle(style2);
	      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).createCell(count==1?4:(count==2?11:(count==3?18:25))).setCellStyle(style2);
	      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).getCell(count==1?5:(count==2?12:(count==3?19:26))).setCellStyle(style2);
	      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).getCell(count==1?6:(count==2?13:(count==3?20:27))).setCellStyle(style2);
	      		sheet.getRow(type1Num>=type0Num?rowNum+type1Num+5:rowNum+type0Num+5).getCell(count==1?7:(count==2?14:(count==3?21:28))).setCellStyle(style2);
	      	
    	}
    	return workbook;
    }
	
	private XSSFWorkbook createHeaderRow(XSSFWorkbook workbook, Row row, Sheet sheet, String header,Integer mergedNum) {
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor((short) 0);
        font.setBoldweight((short) 700);
        font.setFontHeight((short) 340);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平
        Cell cell = row.createCell((short) 3);
        cell.setCellValue(header);
        cell.setCellStyle(cellStyle);
        CellRangeAddress region1 = new CellRangeAddress(1, 1, (short) 3, (short) 2+mergedNum);
        sheet.addMergedRegion(region1);
        return workbook;
    }
}
