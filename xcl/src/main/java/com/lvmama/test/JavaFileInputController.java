package com.lvmama.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lvmama.test.vo.SuppContract;
import com.lvmama.test.vo.SuppSupplier;
/**
 * java流方式读取Excel转换成List<T>
 * @author xiechenglong
 *
 */
public class JavaFileInputController {
	public static void main(String[] args) throws IOException {
		test2();

	}
	
	
	private static void test2() throws FileNotFoundException, IOException {
		InputStream instream = new FileInputStream("C:/Users/xiechenglong/Desktop/供应商数据/newfile2/1.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(instream);
		XSSFSheet sheet = workBook.getSheetAt(0);
		ArrayList<SuppContract> list1 = new ArrayList<SuppContract>();

		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			SuppContract suppContract = new SuppContract();
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			suppContract.setContractId(Long.valueOf(row.getCell(0).getStringCellValue().toString().trim()));
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			suppContract.setSupplierId(Long.valueOf(row.getCell(1).getStringCellValue().toString().trim()));
			suppContract.setSupplierName(row.getCell(2).toString());
			suppContract.setContractName(row.getCell(3).toString());
			if(row.getCell(4) != null){
				suppContract.setContractNo(row.getCell(4).toString());
			}
			if(row.getCell(5) != null){
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				suppContract.setOperator(Long.valueOf(row.getCell(5).getStringCellValue().toString().trim()));
			}
			if(row.getCell(6) != null){
				row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
				suppContract.setManagerId(Long.valueOf(row.getCell(6).getStringCellValue().toString().trim()));
			}
			list1.add(suppContract);
		}

		InputStream instream2 = new FileInputStream("C:/Users/xiechenglong/Desktop/供应商数据/newfile2/2.xlsx");
		XSSFWorkbook workBook2 = new XSSFWorkbook(instream2);
		XSSFSheet sheet2 = workBook2.getSheetAt(0);
		ArrayList<SuppContract> list2 = new ArrayList<SuppContract>();
		for (int i = 1; i < sheet2.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet2.getRow(i);
			SuppContract suppContract = new SuppContract();
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			suppContract.setContractId(Long.valueOf(row.getCell(0).getStringCellValue().toString().trim()));
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			suppContract.setSupplierId(Long.valueOf(row.getCell(1).getStringCellValue().toString().trim()));
			suppContract.setSupplierName(row.getCell(2).toString());
			suppContract.setContractName(row.getCell(3).toString());
			if(row.getCell(4) != null){
				suppContract.setContractNo(row.getCell(4).toString());
			}
			if(row.getCell(5) != null){
				row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
				suppContract.setOperator(Long.valueOf(row.getCell(5).getStringCellValue().toString().trim()));
			}
			if(row.getCell(6) != null){
				suppContract.setOperatorName(row.getCell(6).toString());
			}
			if(row.getCell(7) != null){
				row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
				suppContract.setManagerId(Long.valueOf(row.getCell(7).getStringCellValue().toString().trim()));
			}
			if(row.getCell(8) != null){
				suppContract.setManagerName(row.getCell(8).toString());
			}
			list2.add(suppContract);
		}
		Iterator<SuppContract> iterator = list1.iterator();
		while (iterator.hasNext()) {
			SuppContract next = iterator.next();
			if (list2.contains(next)) {
				iterator.remove();
			}
		}

		File file = new File("C:/Users/xiechenglong/Desktop/供应商数据/有效合同数据file/无效供应商对应合同数据.xls");
		HSSFWorkbook wb = new HSSFWorkbook();
		creatListSheet3(wb, list1);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		wb.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();

		File file2 = new File("C:/Users/xiechenglong/Desktop/供应商数据/有效合同数据file/无效供应商对应合同数据对应BU.xls");
		HSSFWorkbook wb2 = new HSSFWorkbook();
		creatListSheet4(wb2, list2);
		FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
		wb2.write(fileOutputStream2);
		fileOutputStream2.flush();
		fileOutputStream2.close();
		System.out.println(list1.size());
		System.out.println(list2.size());
	}
	

	private static void creatListSheet4(HSSFWorkbook wb2, ArrayList<SuppContract> list2) {
		HSSFSheet sheet = wb2.createSheet("无效供应商合同数据");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb2.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("合同ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("供应商ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("供应商名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("合同名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("合同编号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("经办人");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("经办人名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("产品经理");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("产品经理名称");
		cell.setCellStyle(style);
		for (int i = 0; i < list2.size(); i++) {
			SuppContract suppContract = list2.get(i);
			row = sheet.createRow(i + 1);
			row.createCell((short) 0).setCellValue(suppContract.getContractId());
			row.createCell((short) 1).setCellValue(suppContract.getContractId());
			row.createCell((short) 2).setCellValue(suppContract.getSupplierName());
			row.createCell((short) 3).setCellValue(suppContract.getContractName());
			if(suppContract.getContractNo() != null){
				row.createCell((short) 4).setCellValue(suppContract.getContractNo());
			}else{
				row.createCell((short) 4).setCellValue("");
			}
			if(suppContract.getOperator() != null){
				row.createCell((short) 5).setCellValue(suppContract.getOperator());
			}else{
				row.createCell((short) 5).setCellValue("");
			}
			if(suppContract.getOperatorName() != null){
				row.createCell((short) 6).setCellValue(suppContract.getOperatorName());
			}else{
				row.createCell((short) 6).setCellValue("");
			}
			if(suppContract.getManagerId() != null){
				row.createCell((short) 7).setCellValue(suppContract.getManagerId());
			}else{
				row.createCell((short) 7).setCellValue("");
			}
			if(suppContract.getManagerName() != null){
				row.createCell((short) 8).setCellValue(suppContract.getManagerName());
			}else{
				row.createCell((short) 8).setCellValue("");
			}
			
		}
		
	}


	private static void creatListSheet3(HSSFWorkbook wb, ArrayList<SuppContract> list1) {
		HSSFSheet sheet = wb.createSheet("无效供应商合同数据");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("合同ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("供应商ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("供应商名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("合同名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("合同编号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("经办人");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("产品经理");
		cell.setCellStyle(style);
		for (int i = 0; i < list1.size(); i++) {
			SuppContract suppContract = list1.get(i);
			row = sheet.createRow(i + 1);
			row.createCell((short) 0).setCellValue(suppContract.getContractId());
			row.createCell((short) 1).setCellValue(suppContract.getContractId());
			row.createCell((short) 2).setCellValue(suppContract.getSupplierName());
			row.createCell((short) 3).setCellValue(suppContract.getContractName());
			if(suppContract.getContractNo() != null){
				row.createCell((short) 4).setCellValue(suppContract.getContractNo());
			}else{
				row.createCell((short) 4).setCellValue("");
			}
			if(suppContract.getOperator() != null){
				row.createCell((short) 5).setCellValue(suppContract.getOperator());
			}else{
				row.createCell((short) 5).setCellValue("");
			}
			if(suppContract.getManagerId() != null){
				row.createCell((short) 6).setCellValue(suppContract.getManagerId());
			}else{
				row.createCell((short) 6).setCellValue("");
			}
		}
		
	}

	private static void test1() throws FileNotFoundException, IOException {
		InputStream instream = new FileInputStream("C:/Users/xiechenglong/Desktop/供应商数据/newfile2/1.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(instream);
		XSSFSheet sheet = workBook.getSheetAt(0);
		ArrayList<SuppSupplier> list1 = new ArrayList<SuppSupplier>();

		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			SuppSupplier suppSupplier = new SuppSupplier();
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			suppSupplier.setSupplierId(Long.valueOf(row.getCell(0).getStringCellValue().toString().trim()));
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			suppSupplier.setSupplierName(row.getCell(1).getStringCellValue().toString().trim());
			// suppSupplier.setBu(row.getCell(2).toString());
			list1.add(suppSupplier);
		}

		InputStream instream2 = new FileInputStream("C:/Users/xiechenglong/Desktop/供应商数据/newfile2/2.xlsx");
		XSSFWorkbook workBook2 = new XSSFWorkbook(instream2);
		XSSFSheet sheet2 = workBook2.getSheetAt(0);
		ArrayList<SuppSupplier> list2 = new ArrayList<SuppSupplier>();
		for (int i = 1; i < sheet2.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet2.getRow(i);
			SuppSupplier suppSupplier = new SuppSupplier();
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			suppSupplier.setSupplierId(Long.valueOf(row.getCell(0).getStringCellValue().toString().trim()));
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			suppSupplier.setSupplierName(row.getCell(1).getStringCellValue().toString().trim());
			row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			suppSupplier.setBu(row.getCell(2).getStringCellValue().toString().trim());

			list2.add(suppSupplier);
		}
		Iterator<SuppSupplier> iterator = list1.iterator();
		while (iterator.hasNext()) {
			SuppSupplier next = iterator.next();
			if (list2.contains(next)) {
				iterator.remove();
			}
		}

		String filename = "资质无效供应商数据";
		File file = new File("C:/Users/xiechenglong/Desktop/供应商数据/有效file3/"+filename+".xls");
		HSSFWorkbook wb = new HSSFWorkbook();
		creatListSheet(wb, list1,filename);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		wb.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();

		File file2 = new File("C:/Users/xiechenglong/Desktop/供应商数据/有效file3/"+filename+"对应BU.xls");
		HSSFWorkbook wb2 = new HSSFWorkbook();
		creatListSheet2(wb2, list2,filename);
		FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
		wb2.write(fileOutputStream2);
		fileOutputStream2.flush();
		fileOutputStream2.close();
		System.out.println(list1.size());
		System.out.println(list2.size());
	}

	private static void creatListSheet2(HSSFWorkbook wb2, ArrayList<SuppSupplier> list2, String filename) {
		HSSFSheet sheet = wb2.createSheet(filename);
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb2.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("供应商ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("供应商名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("对应BU");
		cell.setCellStyle(style);
		for (int i = 0; i < list2.size(); i++) {
			SuppSupplier suppSupplier = list2.get(i);
			row = sheet.createRow(i + 1);
			row.createCell((short) 0).setCellValue(suppSupplier.getSupplierId());
			row.createCell((short) 1).setCellValue(suppSupplier.getSupplierName());
			row.createCell((short) 2).setCellValue(SuppSupplier.CONTRACT_SETTLE_BU.getCnName(suppSupplier.getBu()));
		}

	}

	private static void creatListSheet(HSSFWorkbook wb, ArrayList<SuppSupplier> list1, String filename) {
		HSSFSheet sheet = wb.createSheet(filename+"对应BU");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("供应商ID");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("供应商名称");
		cell.setCellStyle(style);
		for (int i = 0; i < list1.size(); i++) {
			SuppSupplier suppSupplier = list1.get(i);
			row = sheet.createRow(i + 1);
			row.createCell((short) 0).setCellValue(suppSupplier.getSupplierId());
			row.createCell((short) 1).setCellValue(suppSupplier.getSupplierName());
		}
	}
}
