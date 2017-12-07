package com.lvmama.xcl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lvmama.xcl.threadlocal.HttpServletLocalThread;
import com.lvmama.xcl.utils.DateUtil;
import com.lvmama.xcl.utils.ExportHelperUtil;
import com.lvmama.xcl.utils.ResourceUtil;
import com.lvmama.xcl.utils.StringUtil;
import com.lvmama.xcl.vo.PermUser;
import com.lvmama.xcl.vo.ProdProduct;
import com.lvmama.xcl.vo.VisaDocTransfer;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * 文件上传 上传文件批量导入数据
 * 
 * @author xiechenglong
 *
 */
@RequestMapping("file")
@Controller
public class FileUploadController extends BaseActionSupport {

	private static final Log LOG = LogFactory.getLog(FileUploadController.class);
	
	/**
	 * 导出excel对应模板文件地址
	 */
	public static final String EXCEL_TEMPLATE_PATH = "/WEB-INF/resources/excel/excelTemplate.xlsx";
	
	/**
	 * 上传excel格式  存储位置
	 */
	public static final String EXCEL_IMPORT_PATH = "/WEB-INF/resources/excel/excelImport.xlsx";
	
	//签证材料交接表
	public static final String VISA_DOC_TRANSFER_TABLE = "/WEB-INF/resources/excel/template/visaDocTransferTable.xls";

	/**
	 * 
	 * @param multipartFile
	 * @return 返回视图名
	 * @throws Exception
	 */
	@RequestMapping("upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws Exception {
		if (multipartFile != null) {
			String originalFilename = multipartFile.getOriginalFilename(); // 获取文件的原始名称
			System.out.println(originalFilename);
			multipartFile.transferTo(new File("D:\\tmp\\" + multipartFile.getOriginalFilename()));
		}
		// 在SpringMVC中如果视图名以redirect开头就会重定向
		return "redirect:/html/success.html";
	}
	
	/**
	 * 上传页面
	 * @return
	 */
	@RequestMapping(value = "/showImport")
	public String showImport(){
		return "showImport";
	}

	/**
	 * excel批量导入数据
	 * 
	 * 注意请求一定要是  enctype="multipart/form-data"  请求，不然报错
	 * 
	 * @param file
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/importFile")
	@ResponseBody
	public Object importFile(@RequestParam(value = "file", required = false) MultipartFile file, MultipartHttpServletRequest req, HttpServletResponse res) {
		// 保存导入的数据
		List<Map<String, Object>> exportMsgs = new ArrayList<Map<String, Object>>();
		String message = "";
		String filename = file.getOriginalFilename();
		if (filename == null || "".equals(filename)) {
			this.sendAjaxMsg("导入的文件名为空!", HttpServletLocalThread.getRequest(), res);
			return null;
		} else if (!(filename.endsWith(".xls") || filename.endsWith(".xlsx"))) {
			this.sendAjaxMsg("导入的文件格式错误,只能是xls和xlsx!", HttpServletLocalThread.getRequest(), res);
			return null;
		}
		try {
			// 创建工作薄
			InputStream input = file.getInputStream();
			Workbook workBook = ExportHelperUtil.create(input);
			Sheet sheet = workBook.getSheetAt(0);

			// 查看行
			if (sheet.getPhysicalNumberOfRows() > 1001) {
				this.sendAjaxMsg("数据条数超过1000条，请缩减条数!", HttpServletLocalThread.getRequest(), res);
				return null;
			}

			if (sheet.getRow(0) != null && !(sheet.getRow(0).getCell(0).toString().trim().equalsIgnoreCase("id"))) {
				this.sendAjaxMsg("导入文件的格式不正确,首列列名必须是id");
				return null;
			}

			// 导入成功条数
			int hasImport = 0;
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				// 记录每条数据的导入情况
				Map<String, Object> record = new HashMap<String, Object>();
				Row row = sheet.getRow(i);
				Long PGId = null;

				exportMsgs.add(record);
				record.put("ID", row.getCell(0));

				if (row.getCell(0) != null && StringUtil.isNotEmptyString(row.getCell(0).toString().trim())) {
					try {
						row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						PGId = Double.valueOf(row.getCell(0).getStringCellValue().toString().trim()).longValue();

					} catch (Exception e) {
						// TODO: handle exception
						// log 商品编号ID错误 ——行号：" + (i + 1);
						record.put("描述", "导入失败：ID必须为数字类型");
						continue;
					}

				} else {
					record.put("描述", "导入失败：ID必须为数字类型");
					continue;
				}
				record.put("描述","导入成功");
			}
			if(hasImport > 0 ){
                message = "导入成功数据"+hasImport+"条 。";
            }else{
                message = "导入失败,文件不存在可导入的数据 ";
            }
		} catch (Exception e) {
			e.printStackTrace();
            this.sendAjaxMsg("导入出现异常,请重新导入!", HttpServletLocalThread.getRequest(), res);
		}
		//导出报表信
		//头部信息
        String[] headers = new String[]{"ID","描述"};
        ExportHelperUtil.downExl(res, exportMsgs, headers);
		return message;
	}
	
	
	/**
	 * 导出对账单明细或导出疑问单明细
	 */
	@RequestMapping(value = "exportExcel")
	public void exportExcel(HttpServletRequest req, HttpServletResponse res, Long reconId) {
		try {

			List<Object> itemList = new ArrayList<Object>();

			String templatePath = "/WEB-INF/resources/excel/template/settlement_detail.xls";
			String fileName = "set_order_detail_" + DateUtil.getFormatDate(new Date(), "yyyyMMddHHmmss") + ".xls";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", itemList);
			this.exportXLS(map, templatePath, fileName);
		} catch (Exception e) {
			LOG.error("Call exportExcel occurs exception, caused by:", e);
			e.printStackTrace();
		}
	}
	
	
	
	private void exportXLS(Map<String, Object> map, String path, String fileName) {
		try {
			File templateResource = ResourceUtil.getResourceFile(path);
			String templateFileName = templateResource.getAbsolutePath();
			String destFileName = getTempDir() + "/" + fileName;
			XLSTransformer transformer = new XLSTransformer();
			transformer.transformXLS(templateFileName, map, destFileName);
			HttpServletResponse response = getResponse();
			if (fileName.indexOf(".xlsx") != -1) {
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			} else {
				response.setContentType("application/vnd.ms-excel");
			}
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			File file = new File(destFileName);
			InputStream inputStream = new FileInputStream(destFileName);
			if (file != null && file.exists()) {
				OutputStream os = response.getOutputStream();
				byte[] b = new byte[1024];
				int length;
				while ((length = inputStream.read(b)) > 0) {
					os.write(b, 0, length);
				}
				inputStream.close();
			} else {
				org.apache.commons.io.IOUtils.closeQuietly(inputStream);
				throw new RuntimeException("下载失败");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * 普通导出excel
	 */
	@RequestMapping(value = "/export1")
	public void export1(HttpServletResponse res){
		try{
			List<ProdProduct> prodProductVOList = new ArrayList<ProdProduct>();
			HashMap<Long, PermUser> permUsersMap = new HashMap<Long, PermUser>();
			HSSFWorkbook wb = new HSSFWorkbook();
			creatProductListSheet(wb,prodProductVOList,permUsersMap);
			String filename = DateUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ".xls";
			//这里调用reset()因为我在别的代码中调用了response.getWriter();
            res.reset();
			res.setHeader("Content-Disposition", "attachment; filename=ebkProductList" + filename);
			res.setContentType("application/vnd.ms-excel");
			OutputStream ouputStream = res.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
		}catch(Exception e) {
			LOG.error(e);
		}
	}
	
	
	/**
	 * 通过模板导出excel
	 */
	@RequestMapping(value = "/export2")
	public void export2(HttpServletResponse res){
		List<VisaDocTransfer> visaDocTransferList = new ArrayList<VisaDocTransfer>();
		for(int i = 0;i<3;i++){
			VisaDocTransfer visaDocTransfer = new VisaDocTransfer();
			visaDocTransfer.setSerialNumber(i);
			visaDocTransfer.setOrderId(1111L);
			visaDocTransfer.setExportTime("2017-11-09");
			visaDocTransfer.setUserName("aqaqa");
			visaDocTransferList.add(visaDocTransfer);
		}
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("visaDocTransferList", visaDocTransferList);

		String destFileName = writeExcelByjXls(beans, VISA_DOC_TRANSFER_TABLE);
		//设定输出文件名
		String outPutName="visaDocTransfer";
		outPutName=outPutName + DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		writeAttachment(destFileName, outPutName, res);
	}
	
	
	/**
	 * 将文件写入附件
	 * 
	 * @param destFileName
	 * @param attachmentName
	 */
	public void writeAttachment(String destFileName, String attachmentName, HttpServletResponse response) {
		FileInputStream fin = null;
		OutputStream os = null;
		try {
			File f = new File(destFileName);
			if ((attachmentName == null) || "".equals(attachmentName)) {
				attachmentName = f.getName();
			}
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + attachmentName + ".xls");
			os = response.getOutputStream();
			fin = new FileInputStream(f);
			IOUtils.copy(fin, os);
			os.flush();
 			os.close();
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			org.apache.commons.io.IOUtils.closeQuietly(fin); 
			org.apache.commons.io.IOUtils.closeQuietly(os);
		}
 
	}
	
	/**
  	 * 写excel通过模板 bean
  	 */
	private static String writeExcelByjXls(Map<String,Object> beans, String template){
		try {
			File templateResource = ResourceUtil.getResourceFile(template);
			XLSTransformer transformer = new XLSTransformer();
			String destFileName = getTempDir() + "/visa_doc_" + new Date().getTime()+".xls";
			transformer.transformXLS(templateResource.getAbsolutePath(), beans, destFileName);
			return destFileName;
		}catch(Exception e){
			LOG.error(e);
		}
		return null;
	}
	
	private static String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}	

	
	
	@SuppressWarnings("deprecation")
	private void creatProductListSheet(HSSFWorkbook wb, List<ProdProduct> prodProductVOList, HashMap<Long, PermUser> permUsersMap) {
		HSSFSheet sheet = wb.createSheet("产品列表");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("产品编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("驴妈妈产品名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("供应商产品名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("产品类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("子类型");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("所属账号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("是否可售");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("产品状态");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("审核状态");
        cell.setCellStyle(style);
        cell = row.createCell((short) 9);
        cell.setCellValue("出发地");
        cell.setCellStyle(style);
        cell = row.createCell((short) 10);
        cell.setCellValue("目的地");
        cell.setCellStyle(style);
        cell = row.createCell((short) 11);
        cell.setCellValue("驴妈妈产品经理");
        cell.setCellStyle(style);
        cell = row.createCell((short) 12);
        cell.setCellValue("驴妈妈联系电话");
        cell.setCellStyle(style);
        if(CollectionUtils.isNotEmpty(prodProductVOList)){
        	for(int i = 0; i < prodProductVOList.size(); i++){
        		row = sheet.createRow(i + 1);
        		ProdProduct prodProduct = prodProductVOList.get(i);
        		if(prodProduct.getProductId() != null){
        			row.createCell((short) 0).setCellValue(prodProduct.getProductId());
        		}else{
        			row.createCell((short) 0).setCellValue("");
        		}
        		if(prodProduct.getProductName() != null){
        			row.createCell((short) 1).setCellValue(prodProduct.getProductName());
        		}else{
        			row.createCell((short) 1).setCellValue("");
        		}
        		if(prodProduct.getSuppProductName() != null){
        			row.createCell((short) 2).setCellValue(prodProduct.getSuppProductName());
        		}else{
        			row.createCell((short) 2).setCellValue("");
        		}
        		//产品类型
        		if("" != null){
        			
        		}else{
        			row.createCell((short) 3).setCellValue("");
        		}
        		
        		if("" != null){
        			row.createCell((short) 4).setCellValue("");
        		}else{
        			row.createCell((short) 4).setCellValue("");
        		}
        		if("" !=null){
        			row.createCell((short) 5).setCellValue("");
        		}else{
        			row.createCell((short) 5).setCellValue("");
        		}
        		if(prodProduct.getSaleFlag()!=null && "Y".equalsIgnoreCase(prodProduct.getSaleFlag())){
        			row.createCell((short) 6).setCellValue("是");
        		}else if(prodProduct.getSaleFlag()!=null && "N".equalsIgnoreCase(prodProduct.getSaleFlag())){
        			row.createCell((short) 6).setCellValue("否");
        		}else{
        			row.createCell((short) 6).setCellValue("");
        		}
        		if(prodProduct.getCancelFlag()!=null && "Y".equalsIgnoreCase(prodProduct.getCancelFlag())){
        			row.createCell((short) 7).setCellValue("有效");
        		}else if(prodProduct.getCancelFlag()!=null && "N".equalsIgnoreCase(prodProduct.getCancelFlag())){
        			row.createCell((short) 7).setCellValue("无效");
        		}else{
        			row.createCell((short) 7).setCellValue("");
        		}
        		if(prodProduct.getAuditStatus()!=null && "AUDITTYPE_NO_SUBMIT".equalsIgnoreCase(prodProduct.getAuditStatus())){
        			row.createCell((short) 8).setCellValue("待提交");
        		}else if(prodProduct.getAuditStatus()!=null && ("AUDITTYPE_TO_PM".equalsIgnoreCase(prodProduct.getAuditStatus()) || "AUDITTYPE_TO_QA".equalsIgnoreCase(prodProduct.getAuditStatus()) || "AUDITTYPE_TO_BUSINESS".equalsIgnoreCase(prodProduct.getAuditStatus()) || "AUDITTYPE_BACK_QA".equalsIgnoreCase(prodProduct.getAuditStatus()) || "AUDITTYPE_BACK_BUSINESS".equalsIgnoreCase(prodProduct.getAuditStatus()))){
        			row.createCell((short) 8).setCellValue("审核中");
        		}else if(prodProduct.getAuditStatus()!=null && "AUDITTYPE_BACK_PM".equalsIgnoreCase(prodProduct.getAuditStatus())){
        			row.createCell((short) 8).setCellValue("已驳回");
        		}else if(prodProduct.getAuditStatus()!=null && "AUDITTYPE_PASS".equalsIgnoreCase(prodProduct.getAuditStatus())){
        			row.createCell((short) 8).setCellValue("已通过");
        		}else{
        			row.createCell((short) 8).setCellValue("");
        		}
        		if("" != null){
        			if("" !=null){
        				row.createCell((short) 9).setCellValue("");
        			}else{
        				row.createCell((short) 9).setCellValue("");
        			}
        		}else{
        			row.createCell((short) 9).setCellValue("");
        		}
        		if(""!=null ){
        			row.createCell((short) 10).setCellValue("");
        		}else{
        			row.createCell((short) 10).setCellValue("");
        		}
        		PermUser permUser = permUsersMap.get(prodProduct.getProductId());
        		if(permUser!=null && permUser.getRealName()!=null){
        			row.createCell((short) 11).setCellValue(permUser.getRealName());
        		}else{
        			row.createCell((short) 11).setCellValue("");
        		}
        		if(permUser!=null && permUser.getMobile()!=null){
        			row.createCell((short) 12).setCellValue(permUser.getMobile());
        		}else{
        			row.createCell((short) 12).setCellValue("");
        		}
        	}
        }
	}
}