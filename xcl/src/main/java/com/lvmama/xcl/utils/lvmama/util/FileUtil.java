package com.lvmama.xcl.utils.lvmama.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lvmama.xcl.utils.ExceptionFormatUtil;

/**
 * 文件工具类.
 * @author Libo Wang
 */
public class FileUtil {

	private static Log LOG = LogFactory.getLog(FileUtil.class);
	private final static int BUFFER_SIZE = 4096;
	
	public static void main(String[] args) throws IOException {
		//makeDirectory("D:/IdeaCode/branche2/study/study_web/target/study_web/xie/long/1.txt");
		 String path = "D:\\IdeaCode\\branche2\\study\\study_web\\target\\study_web\\xie\\long\\1.txt";
	        File file = new File(path);
	        if(!file.exists()){
	            file.getParentFile().mkdirs();          
	        }
	        file.createNewFile();

	        // write
	        FileWriter fw = new FileWriter(file, true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write("xxxaffdf");
	        bw.flush();
	        bw.close();
	        fw.close();

	        // read
	        FileReader fr = new FileReader(file);
	        BufferedReader br = new BufferedReader(fr);
	        String str = br.readLine();
	        System.out.println(str);
	}
	

	/**
	 * 创建文件夹.
	 * @param directoryPath 文件夹路径.
	 */
	public static void makeDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	/**
	 * 读取二进制文件中的字节.
	 * @param file 文件路径.
	 * @return 字节数组.
	 * @throws Exception
	 */
	public static byte[] getBytesFromFile(File file) throws Exception {
		if (file != null && file.exists()) {
			FileInputStream in = new FileInputStream(file);
			int fileSize = Integer.valueOf(String.valueOf(file.length()));
			ByteArrayOutputStream bout = new ByteArrayOutputStream(fileSize);
			copy(in, bout);
			return bout.toByteArray();
		} else {
			LOG.info(file + " does not exist");
			return null;
		}
	}

	/**
	 * 读取二进制文件中的字节. 
	 * @param filePath 文件路径.
	 * @return 字节数组.
	 * @throws Exception
	 */
	public static byte[] getBytesFromFilePath(String filePath) throws Exception {
		if (filePath != null && !"".equals(filePath)) {
			File file = new File(filePath);
			return getBytesFromFile(file);
		} else {
			return null;
		}
	}

	/**
	 * 生成二进制文件.
	 * @param bytes 字节数组.
	 * @param filePath 生成文件的路径.
	 * @throws Exception
	 */
	public static File writeBytesToFile(byte[] bytes, String filePath)
			throws Exception {
		if(filePath!=null && !"".equals(filePath)){
			File file = new File(filePath);
			if (!file.exists()) {
				FileOutputStream out = new FileOutputStream(file);
				out.write(bytes);
				out.flush();
				out.close();
			}
			return file;
		}else{
			return null;
		}
	}


	public static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}
	
	
	public static void ZipFiles(java.io.File[] srcfile, String zipFilePath) {
	    byte[] buf = new byte[1024];
	    try {
	      // Create the ZIP file
	      ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFilePath));
	      // Compress the files
	      for (int i = 0; i < srcfile.length; i++) {
	        FileInputStream in = new FileInputStream(srcfile[i]);
	        // Add ZIP entry to output stream.
	        out.putNextEntry(new ZipEntry(srcfile[i].getName()));
	        // Transfer bytes from the file to the ZIP file
	        int len;
	        while ( (len = in.read(buf)) > 0) {
	          out.write(buf, 0, len);
	        }
	        // Complete the entry
	        out.closeEntry();
	        in.close();
	      }
	      // Complete the ZIP file
	      out.close();
	    }
	    catch (IOException e) {
			LOG.error(ExceptionFormatUtil.getTrace(e));
	    }
	}

	 // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

	/**
	 * 解压缩文件.
	 * 
	 * @param filePath
	 *            gz文件路径.
	 */
	public static void gunzipFile(String filePath) throws Exception {
		File file = new File(filePath);
		if (file.exists()) {
			String os = System.getProperty("os.name");
			if (os.toLowerCase().startsWith("win")) {
				LOG.info(os + " can't gunzip " + filePath);
			} else {
				Runtime.getRuntime().exec("gunzip " + filePath);
			}
		} else {
			LOG.info(filePath + " does not exist");
		}
	}
	
	public ByteArrayOutputStream parse(InputStream in) throws Exception  
	{  
	    ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	    int ch;  
	    while ((ch = in.read()) != -1) {     
	        swapStream.write(ch);     
	    }  
	       return swapStream;  
	}  
	
	public ByteArrayInputStream parse(OutputStream out) throws Exception  
	{  
	    ByteArrayOutputStream   baos=new   ByteArrayOutputStream();  
	    baos=(ByteArrayOutputStream) out;  
	    ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());  
	    return swapStream;  
	}  

	public static void writeFile(String fileName, String filePath, String content) {
		try {
			// 判断文件是否存在，不存在创建
			File f = new File(filePath + fileName);
			if (!f.exists()) {
				File f2 = new File(filePath);
				f2.mkdirs();
				f.createNewFile();
			}
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			outputStreamWriter.write(content);
			outputStreamWriter.flush();
			outputStreamWriter.close();
		} catch (Exception e) {
			LOG.error(ExceptionFormatUtil.getTrace(e));
		}

	}

	/**
	 * 文件删除原来的目录.并创建一个新的目录.
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public static void deleteDirectory(String filePath) throws Exception {
		File dirfile = new File(filePath);
		File[] files = dirfile.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				// 删除子文件
				if (files[i].isFile() && files[i].exists()) {
					files[i].delete();
				}
			}
		}
		// 删除子目录
		if (dirfile.exists() & dirfile.isDirectory()) {
			FileUtils.deleteDirectory(dirfile);
		}
		File f = new File(filePath);
		f.mkdirs();
	}

	/**
	 * 将正文写入指定文件(.txt,.xml等),如果有存在的目录，先进行目录删除.
	 * 
	 * @param fileName
	 * @param filePath
	 * @param content
	 */
	public static void writeNewFile(String fileName, String filePath, String content) throws Exception {
		File file = new File(filePath + File.separator + fileName);
		OutputStreamWriter outWrite = null;
		try {
			outWrite = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			outWrite.write(content);
			outWrite.flush();
			outWrite.close();
		} catch (Exception e) {
			throw e;
		} finally {
			if (outWrite != null) {
				outWrite.close();
			}
		}
	}

	/**
	 * 文件内容是否为空
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExistContentForFile(String path) throws IOException {
		File f = new File(path);
		if (f.exists()) {
 			if (f.length()>0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


	/**
	 * 往文件里写入字符串
	 *
	 * @param filePath 文件路径
	 * @param content 写入内容
	 */
	public static void writeStrToFile(String filePath, String content) {
		try {
			File file = new File(filePath);
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			ps.println(content);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 往文件里写入字符串
	 *
	 * @param filePath 文件路径
	 * @return  返回文件字符串内容
	 */
	public static String readFile(String filePath) {
		StringBuffer content = new StringBuffer();
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { //判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					content.append(lineTxt + "\n");
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		} finally {
			return content.toString();
		}
	}
}
