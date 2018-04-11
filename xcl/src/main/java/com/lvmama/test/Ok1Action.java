package com.lvmama.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;


/**
 * 查询影院相关信息
 * 
 * @author zhoufanyang
 * @date 2017年10月23日 下午2:46:57
 */
@Controller
public class Ok1Action {

	public static void main(String args[]) throws Exception {
		File file = new File("D:/fuck/qqqq.txt");
		txt2String(file);
	}
	
	/**
	 * 读取txt文件的内容
	 * @param file 想要读取的文件对象
	 * @return 返回文件内容
	 */
	public static void txt2String(File file){
		try{
			//BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "gb2312");
			BufferedReader br = new BufferedReader(isr);
			String s = null;
			while((s = br.readLine())!=null){//使用readLine方法，一次读一行
				String line = (s);
				System.out.println(line);
				String[] array = StringUtils.split(line, ",");
				forline(array[3], array[0], array[4]);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static void forline(String id, String dirName, String fileName) throws Exception {
		String lianzaiUrl = "http://super.lvmama.com/vst_back/pet/ajax/file/downLoad.do?fileId="+id;
		//String loginAction = "http://super.lvmama.com/pet_back/login.do?";
		// 取cookie
		//String cookie = getCookie("lv16139", "1234567!", loginAction);
		//System.out.println("---------------cookie="+cookie);
		//if (!cookie.contains("USERID=")) {
		//	System.out.println("登录失败");
		//	System.exit(1);
		//}
		String cookie = "JSESSIONID=EA3787781A53171629DDA7663A5BDA43;lvsessionid=76220d33-ad46-43f2-83da-195ff7dc51d5_14517631;";
		for (int i = 1; i <= 1; i++) {
			getUrl(lianzaiUrl, cookie, dirName, fileName);
		}
	}

	/**
	 * 获得一连载贴内容中的所有超链接
	 * 
	 * @param lianzaiUrl
	 * @return
	 * @throws Exception
	 */
	public static void getUrl(String lianzaiUrl, String cookie, String dirName, String fileName) throws Exception {
		URL url = new URL(lianzaiUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置超时间为3秒
		conn.setConnectTimeout(600 * 1000);
		// 防止屏蔽程序抓取而返回403错误
		//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		//String cookie =  getCookie("test","test", loginAction);  
		conn.setRequestProperty("Cookie", cookie);
		//conn.setRequestProperty("Cookie", cookie);  
		
		// 得到输入流
		InputStream inputStream = conn.getInputStream();
		// 获取自己数组
		byte[] getData = readInputStream(inputStream);

		// 文件保存位置
		File saveDir = new File("D:/supplier" + File.separator + dirName);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		File file = new File(saveDir  + File.separator + fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(getData);
		if (fos != null) {
			fos.close();
		}
		if (inputStream != null) {
			inputStream.close();
		}

		System.out.println("info:" + url + " download success");
	}
	
	/**
	 * 从输入流中获取字节数组
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * post方式登录
	 * 
	 * @param username
	 * @param password
	 * @param loginAction
	 * @return
	 * @throws Exception
	 */
	public static String getCookie(String username, String password, String loginAction) throws Exception {
		// 登录
		URL url = new URL(loginAction);
		String param = "user.userName=" + username + "&user.password=" +"&oldVersion=false";
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		OutputStream out = conn.getOutputStream();
		out.write(param.getBytes());
		out.flush();
		out.close();
		String sessionId = "";
		String cookieVal = "";
		String key = null;
		// 取cookie
		for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
			if (key.equalsIgnoreCase("set-cookie")) {
				cookieVal = conn.getHeaderField(i);
				cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
				sessionId = sessionId + cookieVal + ";";
			}
		}
		return sessionId;
	}
}
