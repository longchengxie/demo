package com.lvmama.xcl.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpInetSocketAddress;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;


public class HttpsUtil {
	private static final Log logger = LogFactory.getLog(HttpsUtil.class);

	public static final String CHARACTER_ENCODING = "UTF-8";
	public static final String PATH_SIGN = "/";
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	public static final String CONTENT_TYPE = "Content-Type";

	public static final int CONNECTION_TIMEOUT = 10000;
	public static final int SO_TIMEOUT = 60000;
	public static final int SO_TIMEOUT_60S = 60000;

	public static final int BUFFER = 1024;

	/**
	 * HttpResponse包装类
	 * 
	 * @author qiuguobin
	 */
	public static class HttpResponseWrapper {
		private HttpResponse httpResponse;
		private HttpClient httpClient;
		private HttpRequestBase httpRequest;

		public HttpResponseWrapper(HttpClient httpClient) {
			this.httpClient = httpClient;
		}

		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse) {
			this.httpClient = httpClient;
			this.httpResponse = httpResponse;
		}

		public HttpResponseWrapper(HttpClient httpClient, HttpResponse httpResponse, HttpRequestBase httpRequest) {
			this(httpClient, httpResponse);
			this.httpRequest = httpRequest;
		}

		public HttpRequestBase getHttpRequest() {
			return httpRequest;
		}

		public void setHttpRequest(HttpRequestBase httpRequest) {
			this.httpRequest = httpRequest;
		}

		public HttpResponse getHttpResponse() {
			return httpResponse;
		}

		public void setHttpResponse(HttpResponse httpResponse) {
			this.httpResponse = httpResponse;
		}

		/**
		 * 获得流类型的响应
		 */
		public InputStream getResponseStream() throws IllegalStateException, IOException {
			return httpResponse.getEntity().getContent();
		}

		/**
		 * 获得字符串类型的响应
		 */
		public String getResponseString(String responseCharacter) throws ParseException, IOException {
			HttpEntity entity = getEntity();
			String responseStr = EntityUtils.toString(entity, responseCharacter);
			if (entity.getContentType() == null) {
				responseStr = new String(responseStr.getBytes("iso-8859-1"), responseCharacter);
			}
			EntityUtils.consume(entity);
			return responseStr;
		}

		public String getResponseString() throws ParseException, IOException {
			return getResponseString(CHARACTER_ENCODING);
		}

		/**
		 * 获得响应状态码
		 */
		public int getStatusCode() {
			return httpResponse.getStatusLine().getStatusCode();
		}

		/**
		 * 获得响应状态码并释放资源
		 */
		public int getStatusCodeAndClose() {
			close();
			return getStatusCode();
		}

		public HttpEntity getEntity() {
			return httpResponse.getEntity();
		}

		/**
		 * 释放资源
		 */
		public void close() {
			if (httpRequest != null) {
				httpRequest.releaseConnection();
			}
			httpClient.getConnectionManager().shutdown();
		}
	}

	/**
	 * POST方式提交上传文件请求
	 */
	public static HttpResponseWrapper requestPostUpload(String url, Map<String, File> requestFiles, Map<String, String> requestParas, String requestCharacter)
			throws ClientProtocolException, IOException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClient();
		} else {
			client = createHttpClient();
		}
		HttpPost httpPost = new HttpPost(url);
		MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName(requestCharacter));
		if (requestFiles == null || requestFiles.size() == 0) {
			return null;
		}
		for (Map.Entry<String, File> entry : requestFiles.entrySet()) {
			multipartEntity.addPart(entry.getKey(), new FileBody(entry.getValue(), "application/octet-stream", requestCharacter));
		}
		if (requestParas != null && requestParas.size() > 0) {
			// 对key进行排序
			List<String> keys = new ArrayList<String>(requestParas.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				multipartEntity.addPart(key, new StringBody(requestParas.get(key), Charset.forName(requestCharacter)));
			}
		}
		httpPost.setEntity(multipartEntity);

		try {
			HttpResponse httpResponse = client.execute(httpPost);
			return new HttpResponseWrapper(client, httpResponse, httpPost);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * POST方式提交上传文件请求
	 */
	public static HttpResponseWrapper requestPostUpload(String url, Map<String, File> requestFiles, Map<String, String> requestParas) throws ClientProtocolException, IOException {
		return requestPostUpload(url, requestFiles, requestParas, CHARACTER_ENCODING);
	}

	/**
	 * POST方式提交表单数据，返回响应对象
	 */
	public static HttpResponseWrapper requestPostFormResponse(String url, Map<String, String> requestParas, String requestCharacter) throws ClientProtocolException, IOException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClient();
		} else {
			client = createHttpClient();
		}
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = initNameValuePair(requestParas);
		httpPost.setEntity(new UrlEncodedFormEntity(formParams, requestCharacter));

		try {
			HttpResponse httpResponse = client.execute(httpPost); // 执行POST请求
			return new HttpResponseWrapper(client, httpResponse, httpPost);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 自定义操作表单提交的数据
	 * 
	 * @return
	 */
	public static HttpResponseWrapper requestPostFormResponse2(String url, Map<String, String> requestParas, int timeOut) throws IOException, HttpException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClient(CONNECTION_TIMEOUT, timeOut * 1000);
		} else {
			client = createHttpClient(CONNECTION_TIMEOUT, timeOut * 1000);
		}
		// client.getParams().setParameter("Accept-Encoding", "gzip, deflate");
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = initNameValuePair(requestParas);
		httpPost.setEntity(new HttpParamEncodeEntity(formParams, CHARACTER_ENCODING));

		try {
			HttpResponse httpResponse = client.execute(httpPost); // 执行POST请求
			return new HttpResponseWrapper(client, httpResponse, httpPost);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * POST方式提交表单数据，返回响应对象，utf8编码
	 */
	public static HttpResponseWrapper requestPostFormResponse(String url, Map<String, String> requestParas) throws ClientProtocolException, IOException {
		return requestPostFormResponse(url, requestParas, CHARACTER_ENCODING);
	}

	/**
	 * POST方式提交表单数据，不会自动重定向
	 */
	public static String requestPostForm(String url, Map<String, String> requestParas, String requestCharacter, String responseCharacter) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			httpResponseWrapper = requestPostFormResponse(url, requestParas, requestCharacter);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpResponseWrapper.close();
		}
		return null;
		// HttpClient client = null;
		// String responseStr = null;
		// try{
		// if (url.startsWith("https")) {
		// client =createHttpsClient();
		// }else{
		// client =createHttpClient();
		// }
		// HttpPost httpPost = new HttpPost(url);
		// List<NameValuePair> formParams = initNameValuePair(requestParas);
		// httpPost.setEntity(new UrlEncodedFormEntity(formParams,
		// requestCharacter));
		// HttpResponse httpResponse = client.execute(httpPost); //执行POST请求
		// int statusCode = httpResponse.getStatusLine().getStatusCode();
		// if (isRedirectStatus(statusCode)) {
		// httpPost.abort();
		// Header locationHeader = httpResponse.getFirstHeader("location");
		// if (locationHeader != null) {
		// String location = locationHeader.getValue();
		// logger.info("redirect url=" + location);
		// HttpGet httpGet = new HttpGet(location);
		// HttpResponse response = client.execute(httpGet);
		// HttpEntity entity = response.getEntity();
		// if (entity!=null) {
		// responseStr = EntityUtils.toString(entity, responseCharacter);
		// EntityUtils.consume(entity);
		// }
		// }
		// } else {
		// HttpEntity entity = httpResponse.getEntity(); //获取响应实体
		// if (entity!=null) {
		// responseStr = EntityUtils.toString(entity, responseCharacter);
		// EntityUtils.consume(entity);
		// }
		// }
		// }catch(IOException e) {
		// logger.error(e.getMessage());
		// e.printStackTrace();
		// }finally {
		// client.getConnectionManager().shutdown();
		// }
		// return responseStr;
	}

	/**
	 * POST方式提交表单数据，不会自动重定向
	 */
	public static String requestPostForm(String url, Map<String, String> requestParas) {
		return requestPostForm(url, requestParas, CHARACTER_ENCODING, CHARACTER_ENCODING);
	}

	public static String requestPostFormOwnSetTimeOut(String url, Map<String, String> requestParas, int timeout) {
		return requestPostFormOwnSetTimeOut(url, requestParas, CHARACTER_ENCODING, CHARACTER_ENCODING, timeout);
	}

	public static String requestPostFormOwnSetTimeOut(String url, Map<String, String> requestParas, String requestCharacter, String responseCharacter, int timeout) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			httpResponseWrapper = requestPostFormOwnSetTimeOut(url, requestParas, requestCharacter, timeout);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpResponseWrapper.close();
		}
		return null;
	}

	public static HttpResponseWrapper requestPostFormOwnSetTimeOut(String url, Map<String, String> requestParas, String requestCharacter, int timeout)
			throws ClientProtocolException, IOException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClientOwnSetTimeOut(timeout);
		} else {
			client = createHttpClientOwnSetTimeOut(timeout);
		}
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formParams = initNameValuePair(requestParas);
		httpPost.setEntity(new UrlEncodedFormEntity(formParams, requestCharacter));

		try {
			HttpResponse httpResponse = client.execute(httpPost); // 执行POST请求
			return new HttpResponseWrapper(client, httpResponse, httpPost);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	// private static boolean isRedirectStatus(int statuscode) {
	// return (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) ||
	// (statuscode == HttpStatus.SC_MOVED_TEMPORARILY) ||
	// (statuscode == HttpStatus.SC_SEE_OTHER) ||
	// (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT);
	// }

	public static HttpResponseWrapper requestGetResponse(String url) throws ClientProtocolException, IOException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClient();
		} else {
			client = createHttpClient();
		}
		HttpGet httpGet = new HttpGet(url);

		try {
			HttpResponse httpResponse = client.execute(httpGet);
			return new HttpResponseWrapper(client, httpResponse, httpGet);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public static HttpResponseWrapper requestGetResponse(String url, String xForWardedFor) throws ClientProtocolException, IOException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClient();
		} else {
			client = createHttpClient();
		}
		HttpGet httpGet = new HttpGet(url);
		if (StringUtil.isNotEmptyString(xForWardedFor)) {
			httpGet.addHeader("X-Forwarded-For", xForWardedFor);
		}

		try {
			HttpResponse httpResponse = client.execute(httpGet);
			return new HttpResponseWrapper(client, httpResponse, httpGet);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * GET方式提交URL请求，会自动重定向
	 */
	public static String requestGet(String url, String responseCharacter, String xForWardedFor) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			httpResponseWrapper = requestGetResponse(url, xForWardedFor);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (httpResponseWrapper != null) {
				httpResponseWrapper.close();
			}
		}
		return null;
	}

	/**
	 * GET方式提交URL请求，会自动重定向
	 */
	public static String requestGet(String url) {
		return requestGet(url, CHARACTER_ENCODING, null);
	}

	/**
	 * get请求
	 * 
	 * @param url
	 *            请求url(eg:https://api.lvmama.com/user/getUserInfo)
	 * @param params
	 *            参数
	 * @return 响应信息
	 */
	public static String requestGet(String url, Map<String, Object> params) {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?").append(mapToUrl(params));
		return requestGet(urlBuilder.toString(), CHARACTER_ENCODING, null);
	}

	/**
	 * 将Map中的数据组装成url
	 * 
	 * @param params
	 * @return
	 */
	public static String mapToUrl(Map<String, Object> params) {
		StringBuilder sb = new StringBuilder();
		try {
			boolean isFirst = true;
			for (String key : params.keySet()) {
				String value = (String) params.get(key);
				if (isFirst) {
					sb.append(key + "=" + URLEncoder.encode(value, "utf-8"));
					isFirst = false;
				} else {
					if (value != null) {
						sb.append("&" + key + "=" + URLEncoder.encode(value, "utf-8"));
					} else {
						sb.append("&" + key + "=");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return sb.toString();
	}

	/**
	 * 将Map中的数据组装成url
	 * 
	 * @param params
	 * @return
	 */
	public static String mapToUrl(Map<String, Object> params, String encodeCharacter) {
		StringBuilder sb = new StringBuilder();
		try {
			boolean isFirst = true;
			for (String key : params.keySet()) {
				String value = (String) params.get(key);
				if (isFirst) {
					sb.append(key + "=" + URLEncoder.encode(value, encodeCharacter));
					isFirst = false;
				} else {
					if (value != null) {
						sb.append("&" + key + "=" + URLEncoder.encode(value, encodeCharacter));
					} else {
						sb.append("&" + key + "=");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return sb.toString();
	}

	/**
	 * GET方式提交URL请求，会自动重定向
	 */
	public static String proxyRequestGet(String url, String xForWardedFor) {
		return requestGet(url, CHARACTER_ENCODING, xForWardedFor);
	}

	/**
	 * POST方式提交非表单数据，返回响应对象
	 */
	public static HttpResponseWrapper requestPostData(String url, String data, String contentType, String requestCharacter, int connectionTimeout, int soTimeout)
			throws ClientProtocolException, IOException {
		HttpClient client = null;
		HttpPost httpPost = null;
		if (url.startsWith("https")) {
			client = createHttpsClient(connectionTimeout, soTimeout);
		} else {
			client = createHttpClient(connectionTimeout, soTimeout);
		}
		httpPost = new HttpPost(url);
		httpPost.setHeader(CONTENT_TYPE, contentType);
		httpPost.setEntity(new StringEntity(data, requestCharacter));

		try {
			HttpResponse httpResponse = client.execute(httpPost);
			return new HttpResponseWrapper(client, httpResponse, httpPost);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			// httpPost.releaseConnection();
		}
		return null;
	}

	/**
	 * POST方式提交非表单数据，返回响应对象
	 */
	public static HttpResponseWrapper requestPostData(String url, String data, String contentType, String requestCharacter) throws ClientProtocolException, IOException {
		return requestPostData(url, data, contentType, requestCharacter, CONNECTION_TIMEOUT, SO_TIMEOUT);
	}

	/**
	 * POST非表单方式提交XML数据
	 */
	public static String requestPostXml(String url, String xmlData, String requestCharacter, String responseCharacter) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			String contentType = "text/xml; charset=" + requestCharacter;
			httpResponseWrapper = requestPostData(url, xmlData, contentType, requestCharacter);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpResponseWrapper.close();
		}
		return null;
	}

	/**
	 * POST非表单方式提交XML数据
	 */
	public static String requestPostXml(String url, String xmlData) {
		return requestPostXml(url, xmlData, CHARACTER_ENCODING, CHARACTER_ENCODING);
	}

	public static HttpClient createHttpClient(int connectionTimeout, int soTimeout) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
		HttpConnectionParams.setSoTimeout(params, soTimeout);
		return httpClient;
	}

	public static HttpClient createHttpClient() {
		return createHttpClient(CONNECTION_TIMEOUT, SO_TIMEOUT);
	}

	public static HttpClient createHttpClientOwnSetTimeOut(int timeout) {
		return createHttpClient(timeout, timeout);
	}

	public static HttpClient createHttpsClient(int connectionTimeout, int soTimeout) {
		try {
			HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
			HttpParams params = httpClient.getParams();
			HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
			HttpConnectionParams.setSoTimeout(params, soTimeout);
			// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { new TrustAnyTrustManager() }, null);
			// 创建SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
		  //socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			//SSLSocketFactory socketFactory = new MYSSLSocketFactory(ctx,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			// 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
			
			return httpClient;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	
	/**
	 * 线上证书报错，Certificate for <pay.lvmama.com> doesn't contain CN or DNS subjectAlt javax.net.ssl.SSLException: Certificate for <pay.lvmama.com> doesn't contain CN or DNS subjectAlt
	 * 内部调用去除域名验证
	 * @author liuwei
	 *
	 */
//	 private static class MYSSLSocketFactory extends SSLSocketFactory {
//		private final javax.net.ssl.SSLSocketFactory socketfactory;
//
//		public MYSSLSocketFactory(SSLContext sslContext, X509HostnameVerifier hostnameVerifier) {
//			super(sslContext, hostnameVerifier);
//			this.socketfactory = sslContext.getSocketFactory();
//		}
//
//		@Override
//		public Socket connectSocket(Socket socket, InetSocketAddress remoteAddress, InetSocketAddress localAddress, HttpParams params) throws IOException, UnknownHostException,
//				ConnectTimeoutException {
//			if (remoteAddress == null) {
//				throw new IllegalArgumentException("Remote address may not be null");
//			}
//			if (params == null) {
//				throw new IllegalArgumentException("HTTP parameters may not be null");
//			}
//			Socket sock = (socket != null) ? socket : this.socketfactory.createSocket();
//			if (localAddress != null) {
//				sock.setReuseAddress(HttpConnectionParams.getSoReuseaddr(params));
//				sock.bind(localAddress);
//			}
//
//			int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
//			int soTimeout = HttpConnectionParams.getSoTimeout(params);
//			try {
//				sock.setSoTimeout(soTimeout);
//				sock.connect(remoteAddress, connTimeout);
//			} catch (SocketTimeoutException ex) {
//				throw new ConnectTimeoutException("Connect to " + remoteAddress + " timed out");
//			}
//			String hostname;
//			if (remoteAddress instanceof HttpInetSocketAddress)
//				hostname = ((HttpInetSocketAddress) remoteAddress).getHttpHost().getHostName();
//			else
//				hostname = remoteAddress.getHostName();
//			SSLSocket sslsock;
//			if (sock instanceof SSLSocket) {
//				sslsock = (SSLSocket) sock;
//			} else {
//				int port = remoteAddress.getPort();
//				sslsock = (SSLSocket) this.socketfactory.createSocket(sock, hostname, port, true);
//				prepareSocket(sslsock);
//			}
//
//			// if (this.hostnameVerifier != null) {
//			// try {
//			// this.hostnameVerifier.verify(hostname, sslsock);
//			// } catch (IOException iox) {
//			// try {
//			// sslsock.close();
//			// } catch (Exception x) {
//			// }
//			// throw iox;
//			// }
//			// }
//
//			return sslsock;
//		}
//	}
	 
	public static HttpClient createHttpsClient() {
		return createHttpsClient(CONNECTION_TIMEOUT, SO_TIMEOUT);
	}

	public static HttpClient createHttpsClientOwnSetTimeOut(int timeout) {
		return createHttpsClient(timeout, timeout);
	}

	public static List<NameValuePair> initNameValuePair(Map<String, String> params) {
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		if (params != null && params.size() > 0) {
			// 对key进行排序
			List<String> keys = new ArrayList<String>(params.keySet());
			Collections.sort(keys);
			for (String key : keys) {
				// logger.info(key+" = " +params.get(key));
				formParams.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		return formParams;
	}

	/**
	 * 通过httpclient下载的文件直接转为字节数组
	 * 
	 * @param path
	 * @author yanzhirong
	 * @return
	 */
	public static byte[] getHttpClientResponseByteArray(String path) {
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		GetMethod httpGet = new GetMethod(path);

		byte[] byteArrays = new byte[0];
		InputStream in = null;
		ByteArrayOutputStream bos = null;
		try {
			client.executeMethod(httpGet);
			in = httpGet.getResponseBodyAsStream();
			bos = new ByteArrayOutputStream();

			byte[] b = new byte[BUFFER];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			byteArrays = bos.toByteArray();
			in.close();
			bos.close();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpGet.releaseConnection();
			try {
				if (in != null) {
					in.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e1) {
				logger.error(e1);
			}
		}
		return byteArrays;
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	/******************** 已下为客户端新加 2014-05-29 *****************/
	/**
	 * 客户端调用 POST方式提交表单数据，不会自动重定向
	 * 
	 * @param url
	 *            请求url
	 * @param requestParas
	 *            请求参数
	 * @param xForWardedFor
	 *            请求ip（统计客户端登陆次数）
	 * 
	 */
	public static String requestPostForm(String url, Map<String, String> requestParas, String xForWardedFor) {
		return requestPostForm(url, requestParas, xForWardedFor, CHARACTER_ENCODING, CHARACTER_ENCODING);
	}

	/**
	 * 客户端登陆调用
	 * 
	 * @param url
	 *            请求url
	 * @param requestParas
	 *            请求参数
	 * @param xForWardedFor
	 *            请求ip（统计客户端登陆次数）
	 * @param requestCharacter
	 *            请求编码
	 * @param responseCharacter
	 *            响应编码
	 * @return
	 */
	public static String requestPostForm(String url, Map<String, String> requestParas, String xForWardedFor, String requestCharacter, String responseCharacter) {
		HttpResponseWrapper httpResponseWrapper = null;
		try {
			httpResponseWrapper = requestPostFormResponse(url, requestParas, requestCharacter, xForWardedFor);
			return httpResponseWrapper.getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			httpResponseWrapper.close();
		}
		return null;
	}

	/**
	 * POST方式提交表单数据，返回响应对象
	 * 
	 * @param url
	 *            请求url
	 * @param requestParas
	 *            请求参数
	 * @param xForWardedFor
	 *            请求ip（统计客户端登陆次数）
	 */
	public static HttpResponseWrapper requestPostFormResponse(String url, Map<String, String> requestParas, String requestCharacter, String xForWardedFor)
			throws ClientProtocolException, IOException {
		HttpClient client = null;
		if (url.startsWith("https")) {
			client = createHttpsClient();
		} else {
			client = createHttpClient();
		}
		HttpPost httpPost = new HttpPost(url);
		if (StringUtil.isNotEmptyString(xForWardedFor)) {
			httpPost.addHeader("X-Forwarded-For", xForWardedFor);
		}
		List<NameValuePair> formParams = initNameValuePair(requestParas);
		httpPost.setEntity(new UrlEncodedFormEntity(formParams, requestCharacter));

		try {
			HttpResponse httpResponse = client.execute(httpPost); // 执行POST请求
			return new HttpResponseWrapper(client, httpResponse, httpPost);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			// httpPost.releaseConnection();
		}
		return null;
	}

	public static HttpClient createHttpClient(Map<String, Object> httpParams) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		if (params != null && httpParams != null) {
			for (String key : httpParams.keySet()) {
				params.setParameter(key, httpParams.get(key));
			}
		}
		return httpClient;
	}

	public static HttpClient createHttpsClient(Map<String, Object> httpParams) {
		try {
			HttpClient httpClient = new DefaultHttpClient(); // 创建默认的httpClient实例
			HttpParams params = httpClient.getParams();
			if (params != null && httpParams != null) {
				for (String key : httpParams.keySet()) {
					params.setParameter(key, httpParams.get(key));
				}
			}
			// TLS1.0与SSL3.0基本上没有太大的差别，可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext
			SSLContext ctx = SSLContext.getInstance("TLS");
			// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
			ctx.init(null, new TrustManager[] { new TrustAnyTrustManager() }, null);
			// 创建SSLSocketFactory
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			// 通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
			return httpClient;
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	/**
	 * 
	 * @autor: heyuxing 2014-11-14 下午8:02:50
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @return
	 * @return String
	 */
	public static String requestGet(String url, Map<String, Object> params, Map<String, Object> httpParams) {
		return requestGet(url, params, httpParams, null);
	}

	/**
	 * 
	 * @autor: heyuxing 2014-12-11 下午2:29:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return String
	 */
	public static String requestGet(String url, Map<String, Object> params, Map<String, Object> httpParams, String responseCharacter) {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?").append(mapToUrl(params));

		HttpResponseWrapper httpResponseWrapper = null;
		HttpGet httpGet = null;
		HttpClient client = null;
		try {

			if (url.startsWith("https")) {
				client = createHttpsClient(httpParams);
			} else {
				client = createHttpClient(httpParams);
			}
			httpGet = new HttpGet(urlBuilder.toString());
			HttpResponse httpResponse = client.execute(httpGet);

			if (StringUtil.isEmptyString(responseCharacter)) {
				responseCharacter = CHARACTER_ENCODING;
			}
			return new HttpResponseWrapper(client, httpResponse, httpGet).getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (httpResponseWrapper != null) {
				httpResponseWrapper.close();
			}
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
		}
		return null;
	}

	/**
	 * 
	 * @autor: mahy 2015-10-21 下午2:29:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return String
	 */
	public static String requestGet(String url, Map<String, Object> params, Map<String, Object> httpParams, String requestCharacter, String responseCharacter) {
		StringBuilder urlBuilder = new StringBuilder(url);
		urlBuilder.append("?").append(mapToUrl(params, requestCharacter));

		HttpResponseWrapper httpResponseWrapper = null;
		HttpGet httpGet = null;
		HttpClient client = null;
		try {

			if (url.startsWith("https")) {
				client = createHttpsClient(httpParams);
			} else {
				client = createHttpClient(httpParams);
			}
			httpGet = new HttpGet(urlBuilder.toString());
			HttpResponse httpResponse = client.execute(httpGet);

			if (StringUtil.isEmptyString(responseCharacter)) {
				responseCharacter = CHARACTER_ENCODING;
			}
			return new HttpResponseWrapper(client, httpResponse, httpGet).getResponseString(responseCharacter);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (httpResponseWrapper != null) {
				httpResponseWrapper.close();
			}
			if (httpGet != null) {
				httpGet.releaseConnection();
			}
		}
		return null;
	}

	/**
	 * 
	 * @autor: liuwei 2016-05-23 下午2:29:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object requestRestGet(String url, Class Voclass) {
		try {
			HttpClient httpClient = new DefaultHttpClient();

			HttpGet httpGet = new HttpGet(url);

			httpGet.setHeader(new BasicHeader("Accept", "application/json"));

			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			ObjectMapper mapper = new ObjectMapper();
			
			Object details = mapper.readValue(entity.getContent(), Voclass);
			logger.info(details);
			return details;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 
	 * @autor: liuwei 2016-05-23 下午2:29:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return String
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Object requestRestPost(String url, Class Voclass) {
		try {
			HttpResponseWrapper responseWrapper = requestPostData(url, new JSONObject().toString(), "application/json", "UTF-8");
			if (responseWrapper == null) {
				logger.info("PreSaleRefundApplyServiceTaskImpl.updateStampCode responseWrapper is null");
			}
			if (responseWrapper != null) {
				int statusCode = responseWrapper.getStatusCode();// 请求返回的状态码
				logger.info("preSaleOrder返回状态码：" + statusCode);
				if (statusCode != HttpStatus.SC_OK) {
					logger.info("preSaleOrder返回状态码：" + statusCode + " 返回字符串为" + responseWrapper.getResponseString());
				}
			}

			return JSONObject.toBean(JSONObject.fromObject(responseWrapper.getResponseString()), Voclass);

		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 
	 * @autor: liuwei 2016-05-23 下午2:29:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return String
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Object requestRestPost(String url, JSONObject jsonObject, Class Voclass) {
		try {
			HttpResponseWrapper responseWrapper = requestPostData(url, jsonObject.toString(), "application/json", "UTF-8");
			if (responseWrapper == null) {
				logger.info("PreSaleRefundApplyServiceTaskImpl.updateStampCode responseWrapper is null");
			}
			if (responseWrapper != null) {
				int statusCode = responseWrapper.getStatusCode();// 请求返回的状态码
				logger.info("preSaleOrder返回状态码：" + statusCode);
				if (statusCode != HttpStatus.SC_OK) {
					logger.info("preSaleOrder返回状态码：" + statusCode + " 返回字符串为" + responseWrapper.getResponseString());
				}
			}

			return JSONObject.toBean(JSONObject.fromObject(responseWrapper.getResponseString()), Voclass);

		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 
	 * @autor: mahy 2017-01-09 10:23:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return JSONObject
	 * @throws IOException
	 * @throws ParseException
	 */
	public static JSONObject requestJsonPost(String url, JSONObject jsonObject) {
		try {
			HttpResponseWrapper responseWrapper = requestPostData(url, jsonObject.toString(), "application/json", "UTF-8");
			if (responseWrapper == null) {
				logger.info("responseWrapper is null");
			}
			if (responseWrapper != null) {
				int statusCode = responseWrapper.getStatusCode();// 请求返回的状态码
				logger.info("返回状态码：" + statusCode);
				if (statusCode != HttpStatus.SC_OK) {
					logger.info("返回状态码：" + statusCode + " 返回字符串为" + responseWrapper.getResponseString());
				}
			}
			
			return JSONObject.fromObject(responseWrapper.getResponseString());
			
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 
	 * @autor: liuwei 2016-05-23 下午2:29:01
	 * @param url
	 *            请求url
	 * @param params
	 *            请求参数
	 * @param httpParams
	 *            设置HttpClient.getParams()中的参数
	 * @param responseCharacter
	 *            处理应答response字符串使用的字符集
	 * @return
	 * @return String
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object requestRestPostMapper(String url, Class Voclass) {
		try {
			HttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost = new HttpPost(url);

			httpPost.setHeader(new BasicHeader("Accept", "application/json"));

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			ObjectMapper mapper = new ObjectMapper();
			System.out.println(entity.getContent());

			Object details = mapper.readValue(entity.getContent(), Voclass);
			return details;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * PUT方式提交非表单数据，返回响应对象 add by zoubin
	 */
	public static HttpResponseWrapper requestPutData(String url, String data, String contentType, String requestCharacter, int connectionTimeout, int soTimeout)
			throws ClientProtocolException, IOException {
		HttpClient client = null;
		HttpPut httpPut = null;
		if (url.startsWith("https")) {
			client = createHttpsClient(connectionTimeout, soTimeout);
		} else {
			client = createHttpClient(connectionTimeout, soTimeout);
		}
		httpPut = new HttpPut(url);

		httpPut.setHeader(CONTENT_TYPE, contentType);
		httpPut.setEntity(new StringEntity(data, requestCharacter));

		try {
			HttpResponse httpResponse = client.execute(httpPut);
			return new HttpResponseWrapper(client, httpResponse, httpPut);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			// httpPost.releaseConnection();
		}
		return null;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// String url = Constant.PRE_SELL_SERVICE +
		// "/back/stamp/refund/apply?refundId=355686&orderId=200613652&refundNum=1";
		// System.out.println("hhs/v1/back/stamp/refundByCode/apply,url=" +
		// url);

		HttpResponseWrapper responseWrapper = requestPostData(
				"http://10.112.4.203:8181/hhs/v1/back/stamp/refundByCode/apply?refundId=358763&orderId=200614029&stampCodes=4672951888", new JSONObject().toString(),
				"application/json", "UTF-8");
		if (responseWrapper == null) {
			System.out.println("PreSaleRefundApplyServiceTaskImpl.updateStampCode responseWrapper is null");
		}
		if (responseWrapper != null) {
			int statusCode = responseWrapper.getStatusCode();// 请求返回的状态码
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println("preSaleOrder返回状态码：" + statusCode + " 返回字符串为" + responseWrapper.getResponseString());

			}
		}


	}

	/**
	 * PUT方式提交非表单数据，返回响应对象 add by zoubin
	 */
	public static HttpResponseWrapper requestPutData(String url, String data, String contentType, String requestCharacter) throws ClientProtocolException, IOException {
		return requestPutData(url, data, contentType, requestCharacter, CONNECTION_TIMEOUT, SO_TIMEOUT);
	}
}
