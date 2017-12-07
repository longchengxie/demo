package com.lvmama.xcl.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.PDFEncryption;
import org.xhtmlrenderer.simple.ImageRenderer;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfUtil {
	public static final String PDF_FONT_URL = "/WEB-INF/resources/econtractTemplate/";// pdf模板相对路径
	
//	public static final String PDF_FONT_URL = "/econtractTemplate/";// pdf模板相对路径
	
	public static final String PDF_OWNER_PASS_WORD="LvmamaIsTourismsFirst";
	private static final Logger logger = Logger.getLogger(PdfUtil.class);
	
	public static void main(String[] age){
		try {
			File file = new File("C:/Users/yuzhibing/Desktop/ROUTE.html");
			PdfUtil.htmlToImage(file, ".png");
		} catch (IOException e) {
		}
	}
	
	public static void htmlToImage(File f,String imageType) throws IOException{
		if (f.exists()) {
			String output = f.getAbsolutePath();
			output = output.substring(0, output.lastIndexOf(".")) +imageType;
			ImageRenderer.renderToImage(f, output, 700);
		}   
	}
	
	/**
	 * 根据文件路径创建PDF文件
	 * @param parameters
	 * @param toUrl
	 */
	public static void createPdfFile(final String content,final String toUrl) {
		ByteArrayOutputStream baos = null;
		FileOutputStream fs = null;
		try {
			baos = createPdfFile(content);
			if (null != baos) {
				fs = new FileOutputStream(new File(toUrl));
				baos.writeTo(fs);
			}
		} catch (IOException e) {
			logger.error("合同生成PDF文件  IOException:" + e);
		} finally {
			IOUtils.closeQuietly(baos);
			IOUtils.closeQuietly(fs);
		}
	}
	
	/**
	 * 创建PDF文件流
	 * @param parameters
	 * @param toUrl
	 */
	public static ByteArrayOutputStream createPdfFile(final String line) {
		try {
			String content = line.replaceAll("&nbsp;", "");
			if(!content.startsWith("<")){ 
				content = content.substring(content.indexOf('<')); 
			}
//			content = content.replaceAll("&", "");
//			content = content.replaceAll("<br>", "<br/>");
//			content = content.replaceAll("<BR>", "<br/>");
//			content = content.replaceAll("</br>", "<br/>");
//			content = content.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
			//转换html至xhtml
			content = parse2Xhtml(line);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			File ttc = ResourceUtil.getResourceFile(PDF_FONT_URL+"simsun.ttc");
			if (!ttc.exists()) {
				ttc = ResourceUtil.getResourceFile(PDF_FONT_URL+"SIMSUN.TTC");
			}
			String fontPath=ttc.getAbsolutePath();
			ITextRenderer renderer = new ITextRenderer();
			ITextFontResolver fontResolver = renderer.getFontResolver();
			if(null!=fontPath) {
				fontResolver.addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			}
		
			renderer.setDocumentFromString(content);
			PDFEncryption encryption = new PDFEncryption(null, PDF_OWNER_PASS_WORD
					.getBytes(), PdfWriter.ALLOW_PRINTING);
			renderer.setPDFEncryption(encryption);
			renderer.layout();
			renderer.createPDF(baos);
			renderer.finishPDF();
			return baos;
		} catch (Exception e) {
			logger.error(e);
			logger.error(line);
		}
		return null;
	}
	
	
	/**
	 * html页面转换成xhtml
	 * 
	 * @param html
	 * @return xhtml
	 */
	public static String parse2Xhtml(String html) {

		ByteArrayInputStream is = new ByteArrayInputStream(html.getBytes());
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 实例化Tidy对象
		Tidy tidy = new Tidy();
		// 设置输入
		tidy.setInputEncoding("UTF-8");
		// 如果是true 不输出注释，警告和错误信息
		tidy.setQuiet(true);
		// 设置输出
		tidy.setOutputEncoding("UTF-8");
		// 不显示警告信息
		tidy.setShowWarnings(false);
		// 缩进适当的标签内容。
		tidy.setIndentContent(true);
		// 是否节点结束后另起一行
		tidy.setSmartIndent(true);
		// 内容缩进
		tidy.setSmartIndent(true);
		// 属性换行
		tidy.setIndentAttributes(false);
		// 是否br在一行中显示
		tidy.setBreakBeforeBR(true);
		// 输出为xhtml
		tidy.setXHTML(true);
		// 去掉没用的标签
		tidy.setMakeClean(false);
		// 一行有多长
		tidy.setWraplen(1000);
		// 清洗word2000的内容
		tidy.setWord2000(true);
		//清洗空标签
		tidy.setTrimEmptyElements(false);
		// 设置错误输出信息
//		tidy.setErrout(new PrintWriter(System.out));
		try {
			tidy.parse(is, os);
		} catch (Exception e) {
			logger.error("parse2Xhtml Exception:" + e);
		} finally {
			IOUtils.closeQuietly(is);
			IOUtils.closeQuietly(os);
		}
		return os.toString();
	}

	public  static String convertHtml(String str) {
		
		
		/*if(StringUtils.isNotEmpty(str)){
			String tmp =str.replaceAll("\r\n", "").replaceAll("<br.*?>", "================");
			return tmp.replaceAll("<.*?>", "").replaceAll("================", "<br/>");
		}else{
			return str;
		}*/
		
		if(StringUtils.isNotEmpty(str)){
			String tmp =str.replaceAll("\t", "").replaceAll("\r\n", "").replaceAll("<br.*?>", "================");
			tmp =tmp.replaceAll("<.*?>", "").replaceAll("================", "<br/>");
			tmp =tmp.replaceAll("<br/>", "\r");
			return tmp;
		}else{
			return str;
		}
	}
}
