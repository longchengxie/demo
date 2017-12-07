package com.lvmama.xcl.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lvmama.xcl.TestBuilder;
import com.lvmama.xcl.TestBuilderFactory;
import com.lvmama.xcl.utils.PdfUtil;


/**
 * 生成pdf 
 * @author xiechenglong
 *
 */
@Controller
@RequestMapping("/pdf")
public class PdfController {
	private static final Logger LOG = LoggerFactory.getLogger(PdfController.class);
	protected static boolean  isDubgPdf = false;//开发的时候设置为true，上线设置为false
	@RequestMapping(value="/show")
	public String pdf(Model model){
		model.addAttribute("content", getContent());
		return "/pdf";
	}

	private String getContent() {
		try {
			TestBuilder builder = TestBuilderFactory.create();
			return builder.makeContent();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping("/downloadPdf")
	public void downloadPdf(HttpServletRequest req, HttpServletResponse res){
		try {
			res.reset();
			res.setContentType("application/pdf");
			res.setHeader("content-disposition",
					"attachment; filename=" + "1234" + ".pdf");
			ByteArrayOutputStream baos = null;
			String content = getContent();
			baos = PdfUtil.createPdfFile(content);
			if (baos == null) {
				baos = new ByteArrayOutputStream();
			}
			//调试时打开
			byte[] fileBytes = baos.toByteArray();
			String fileName = "test";
			this.newContractDebug(fileBytes, fileName);
			baos.writeTo(res.getOutputStream());
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成合同的时候调试打开
	 * @param fileBytes
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void newContractDebug(byte[] fileBytes, String fileName)
			throws FileNotFoundException, IOException {
		if (isDubgPdf) {
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(new File("E:/econtract/", fileName));
				fileOutputStream.write(fileBytes);
			} catch (FileNotFoundException e) {
				LOG.error("{}", e);
				throw e;
			} catch (IOException e) {
				LOG.error("{}", e);
				throw e;
			} finally {
				if(fileOutputStream != null) {
					fileOutputStream.flush();
					fileOutputStream.close();
				}
			} 
			
			/*FileWriter fileWriter = new FileWriter(new File(directioryFile, fileName + ".html"));
			fileWriter.write(htmlString);
			fileWriter.close();*/
		}
	}
}
