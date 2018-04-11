package com.lvmama.xcl.utils;

import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author zhoufy
 * @date 2016-5-3
 */
public class FileUtil {
	
	private static final Log logger = LogFactory.getLog(FileUtil.class);
	
	/**
	 * saveFileToServer 上传文件保存到服务器
	 * @param filePath 为上传文件的名称
	 * @param saveFilePathName 为文件保存全路径
	 * @param saveFileName 为保存的文件
	 * @param extendes 为允许的文件扩展名
	 * @return 返回一个map，map中有4个值，第一个为保存的文件名fileName,第二个为保存的文件大小fileSize,,
	 *         第三个为保存文件时错误信息errors,如果生成缩略图则map中保存smallFileName，表示缩略图的全路径
	 */
	public static File getFileFromRequest(HttpServletRequest request,
			String filePath, String saveFilePathName, String saveFileName,
			String[] extendes) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(filePath);
		
		// 为了配合uploadify获取文件
		if (null == file) {
			logger.warn("---图片上传 file=null");
			Map<String, MultipartFile> map = multipartRequest.getFileMap();

			MultipartFile mfile = map.get("Filedata");

			file = (CommonsMultipartFile)mfile;
		}

		if (file != null && !file.isEmpty()) {
			// logger.info("文件名为：" + file.getOriginalFilename());
			String extend = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if (saveFileName == null || saveFileName.trim().equals("")) {
				saveFileName = UUID.randomUUID().toString() + "." + extend;
			}
			if (saveFileName.lastIndexOf(".") < 0) {
				saveFileName = saveFileName + "." + extend;
			}
			float fileSize = Float.valueOf(file.getSize());// 返回文件大小，单位为k
			List<String> errors = new java.util.ArrayList<String>();
			boolean flag = true;
			if (extendes == null) {
				extendes = new String[] { "jpg", "jpeg", "gif", "bmp", "tbi",
						"png","mp4" };
			}
			for (String s : extendes) {
				if (extend.toLowerCase().equals(s))
					flag = true;
			}
			if (flag) {
				File path = new File(saveFilePathName);
				if (!path.exists()) {
					path.mkdir();
				}
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(saveFilePathName + File.separator
								+ saveFileName));
				InputStream is = null;
				try {
					is = file.getInputStream();
					int size = (int) (fileSize);
					byte[] buffer = new byte[size];
					while (is.read(buffer) > 0) {
						out.write(buffer);
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (out != null) {
						out.close();
					}
				}
				if (isImg(extend)) {
					File img = new File(saveFilePathName + File.separator + saveFileName);
					try {
						BufferedImage bis = ImageIO.read(img);
						int w = bis.getWidth();
						int h = bis.getHeight();
						logger.info("----上传图片，w="+w+", h="+h);
					} catch (Exception e) {
						// map.put("width", 200);
						// map.put("heigh", 100);
					}
					return img;
				}
				logger.info("----上传图片，mime="+extend+", fileName="+saveFileName+", oldName="+file.getOriginalFilename()+"fileSize="+fileSize);
				// logger.info("上传结束，生成的文件名为:" + fileName);
			} else {
				// logger.info("不允许的扩展名");
				errors.add("不允许的扩展名");
			}
		}  
		return null;
	}
	
	/**
	 * saveFileToServer 上传文件保存到服务器
	 * @param filePath 为上传文件的名称
	 * @param saveFilePathName 为文件保存全路径
	 * @param saveFileName 为保存的文件
	 * @param extendes 为允许的文件扩展名
	 * @return 返回一个map，map中有4个值，第一个为保存的文件名fileName,第二个为保存的文件大小fileSize,,
	 *         第三个为保存文件时错误信息errors,如果生成缩略图则map中保存smallFileName，表示缩略图的全路径
	 */
	public static File createWxypFile(HttpServletRequest request,
			String key, String saveFilePath, String saveFileName) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(key);
		
		// 为了配合uploadify获取文件
		if (null == file) {
			logger.warn("---图片上传 file=null");
			Map<String, MultipartFile> map = multipartRequest.getFileMap();

			MultipartFile mfile = map.get("Filedata");

			file = (CommonsMultipartFile)mfile;
		}

		if (file != null && !file.isEmpty()) {
			// logger.info("文件名为：" + file.getOriginalFilename());
			String extend = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if (saveFileName == null || saveFileName.trim().equals("")) {
				saveFileName = UUID.randomUUID().toString() + "." + extend;
			}
			if (saveFileName.lastIndexOf(".") < 0) {
				saveFileName = saveFileName + "." + extend;
			}
			float fileSize = Float.valueOf(file.getSize());// 返回文件大小，单位为k
			List<String> errors = new java.util.ArrayList<String>();
			boolean flag = true;
			if (flag) {
				File path = new File(saveFilePath);
				if (!path.exists()) {
					path.mkdir();
				}
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(saveFilePath + File.separator
								+ saveFileName));
				InputStream is = null;
				try {
					is = file.getInputStream();
					int size = (int) (fileSize);
					byte[] buffer = new byte[size];
					while (is.read(buffer) > 0) {
						out.write(buffer);
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (out != null) {
						out.close();
					}
				}
				File img = new File(saveFilePath + File.separator + saveFileName);
				try {
					BufferedImage bis = ImageIO.read(img);
					int w = bis.getWidth();
					int h = bis.getHeight();
					logger.info("----上传图片，w="+w+", h="+h);
				} catch (Exception e) {
				}
				return img;
			} else {
				errors.add("不允许的扩展名");
			}
		}  
		return null;
	}
	

	/**
	 * saveFileToServer 上传文件保存到服务器
	 * @param filePath 为上传文件的名称
	 * @param saveFilePathName 为文件保存全路径
	 * @param saveFileName 为保存的文件
	 * @param extendes 为允许的文件扩展名
	 * @return 返回一个map，map中有4个值，第一个为保存的文件名fileName,第二个为保存的文件大小fileSize,,
	 *         第三个为保存文件时错误信息errors,如果生成缩略图则map中保存smallFileName，表示缩略图的全路径
	 */
	public static File getUploadFileFromRequest(HttpServletRequest request,
			String filePath, String saveFilePathName, String saveFileName,
			String[] extendes) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile(filePath);
		
		// 为了配合uploadify获取文件
		if (null == file) {
			logger.warn("---附件上传 file=null");
			Map<String, MultipartFile> map = multipartRequest.getFileMap();

			MultipartFile mfile = map.get("Filedata");

			file = (CommonsMultipartFile)mfile;
		}

		if (file != null && !file.isEmpty()) {
			String extend = file.getOriginalFilename()
					.substring(file.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if (saveFileName == null || saveFileName.trim().equals("")) {
				saveFileName = UUID.randomUUID().toString() + "." + extend;
			}
			if (saveFileName.lastIndexOf(".") < 0) {
				saveFileName = saveFileName + "." + extend;
			}
			float fileSize = Float.valueOf(file.getSize());// 返回文件大小，单位为k
			List<String> errors = new java.util.ArrayList<String>();
			boolean flag = false;
			if (extendes == null) {
				extendes = new String[] { "jpg", "jpeg", "gif", "bmp", "tbi",
						"png","mp4","xlsx","xls","docx","doc","pdf" };
			}
			for (String s : extendes) {
				if (extend.toLowerCase().equals(s)){
					flag = true;
				}
			}
			if (flag) {
				File path = new File(saveFilePathName);
				if (!path.exists()) {
					path.mkdir();
				}
				DataOutputStream out = new DataOutputStream(
						new FileOutputStream(saveFilePathName + File.separator
								+ saveFileName));
				InputStream is = null;
				try {
					is = file.getInputStream();
					int size = (int) (fileSize);
					byte[] buffer = new byte[size];
					while (is.read(buffer) > 0) {
						out.write(buffer);
					}
				} catch (IOException exception) {
					exception.printStackTrace();
				} finally {
					if (is != null) {
						is.close();
					}
					if (out != null) {
						out.close();
					}
				}
				File attach = new File(saveFilePathName + File.separator + saveFileName);
				
				logger.info("----上传附件，mime="+extend+", fileName="+saveFileName+", oldName="+file.getOriginalFilename()+"fileSize="+fileSize);
				
				return attach;
			} else {
				errors.add("不允许的扩展名");
			}
		}  
		return null;
	}
	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new java.util.ArrayList<String>();
		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		list.add("tbi");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}
	
	/**
	 * 前台判定是否存在文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean fileExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public static boolean createFolder(String folderPath) {
		boolean ret = true;
		try {
			java.io.File myFilePath = new java.io.File(folderPath);
			if (!myFilePath.exists() && !myFilePath.isDirectory()) {
				ret = myFilePath.mkdirs();
				if (!ret) {
					logger.info("创建文件夹出错");
				}
			}
		} catch (Exception e) {
			logger.info("创建文件夹出错");
			ret = false;
		}
		return ret;
	}
}
