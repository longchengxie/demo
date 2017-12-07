package com.lvmama.xcl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * excel 创建，导出
 * @author xiechenglong
 *
 */
public class ExportHelperUtil {

	//创建Exl类型文档
	 public static Workbook create(InputStream inp) throws IOException,InvalidFormatException {
			if (!inp.markSupported()) {
				inp = new PushbackInputStream(inp, 8);
			}
			if (POIFSFileSystem.hasPOIFSHeader(inp)) {
				return new HSSFWorkbook(inp);
			}
			if (POIXMLDocument.hasOOXMLHeader(inp)) {
				return new XSSFWorkbook(OPCPackage.open(inp));
			}
			throw new IllegalArgumentException("你的excel版本目前poi解析不了");
	    }
	 
	//下载文件
    public static void downExl(HttpServletResponse response,List<Map<String,Object>> exportMsgs,String[]headers){       
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition","attachment;filename=exportExl.xlsx");
		response.setContentType("application/msexcel");
	    OutputStream os = null;
	   try {
		   os = response.getOutputStream();
		   if(CollectionUtils.isNotEmpty(exportMsgs)){        	
				XSSFWorkbook wb = new XSSFWorkbook();
				XSSFSheet sheet = wb.createSheet();
				sheet.autoSizeColumn(0); // 自动寬度
				XSSFRow rowTitle = sheet.createRow(0);
				for(int i =0; i < headers.length; i++){
					rowTitle.createCell(i).setCellValue(headers[i]);
				}
				
				int j = 0 ; 
				for(Map<String,Object> record:exportMsgs){
					XSSFRow rowTitleTmp = sheet.createRow(j+1);
					for(int c =0; c < headers.length; c++){
						XSSFCell cell = rowTitleTmp.createCell(c); 
						cell.setCellType(XSSFCell.CELL_TYPE_STRING );
						Object value = record.get(headers[c]);
						cell.setCellValue(String.valueOf(value));
					}
					j++ ; 
				}
				wb.write(os);
				os.flush();
		   }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
}
