package com.bootdo.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @FileName: HttpProtocolHandler.java
 * @Description: HTTP连接管理类
 * @author honggang.li
 * @date 2014年7月21日
 */
public class ApacheHttpHandler implements  HttpHandler {

	Logger LOG = LoggerFactory.getLogger(ApacheHttpHandler.class);

	private static String DEFAULT_CHARSET = "UTF-8";

	/** 连接超时时间，由bean factory设置，缺省为8秒钟 */
	private static int defaultConnectionTimeout = 30000;

	/** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
	private static int defaultSoTimeout = 30000;

	/** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
	private static int defaultIdleConnTimeout = 60000;

	private static int defaultMaxConnPerHost = 30;

	private static int defaultMaxTotalConn = 80;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒 */
	private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

	/**
	 * HTTP连接管理器，该连接管理器必须是线程安全的.
	 */
	private static PoolingHttpClientConnectionManager connectionManager;
	
	private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static SSLContextBuilder builder = null;
	
	static {
		try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            // 创建一个线程安全的HTTP连接池
            connectionManager = new PoolingHttpClientConnectionManager(registry);
    		connectionManager.setMaxTotal(defaultMaxTotalConn);
    		connectionManager.setDefaultMaxPerRoute(defaultMaxConnPerHost);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * 私有的构造方法
	 */
	ApacheHttpHandler() {
		// IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		// ict.addConnectionManager(connectionManager);
		// ict.setConnectionTimeout(defaultIdleConnTimeout);
		//
		// ict.start();
	}

	public HttpResponse post(HttpRequest request) throws Exception {
		return execute(Method.POST, request);
	}

	public HttpResponse get(HttpRequest request) throws Exception {
		return execute(Method.GET, request);
	}
	public HttpResponse stream(HttpRequest request) throws Exception {
		return execute(Method.STREAM, request);
	}
	public HttpResponse postJson(HttpRequest request) throws Exception {
		return execute(Method.JSON, request);
	}
	private enum Method {
		POST, GET, STREAM,JSON
	}

	private HttpResponse execute(Method methodType, HttpRequest request) throws Exception{

//		if (null != request.getParameters()) {
//
//			for (String key : request.getParameters().keySet()) {
//				LOG.debug("{} \t {}", key, request.getParameters().get(key));
//			}
//		}
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf)
				.setConnectionManager(connectionManager)
				.setConnectionManagerShared(true)
				.build();

		// 设置连接超时
		int connectionTimeout = request.getConnectionTimeout() > 0 ? request.getConnectionTimeout() : defaultConnectionTimeout;
//		httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);

		// 设置回应超时
		int soTimeout = request.getTimeout() > 0 ? request.getTimeout() : defaultSoTimeout;
//		httpclient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);

		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(connectionTimeout)
				.setConnectionRequestTimeout(connectionTimeout)//从连接池获取请求的时间
				.setSocketTimeout(soTimeout).build();
		// 设置等待ConnectionManager释放connection的时间

		// 设置字符编码
		String charset = request.getCharset() != null ? request.getCharset() : DEFAULT_CHARSET;

		HttpRequestBase method = null;
		HttpResponse response = new HttpResponse();

		try {
			if (methodType == Method.POST) {
				method = new HttpPost(request.getUrl());
				if (request.getParameters() != null) {
					//20170120被注释掉的代码有bug不能按 指定编码提交
					
					if (DEFAULT_CHARSET.equalsIgnoreCase(charset)) {
						StringBuilder sb = new StringBuilder();
						for (String key : request.getParameters().keySet()) {
							sb.append(key).append("=").append(request.getParameters().get(key)).append("&");
						}
						StringEntity entity = new StringEntity(sb.substring(0, sb.length() - 1),request.isCharsetFalse()?charset:null);
						((HttpPost) method).setEntity(entity);
					} else {
						List<NameValuePair> nvps = new ArrayList<NameValuePair>();
						Map<String, String> parameters = request.getParameters();
						Set<Entry<String,String>> entrySet = parameters.entrySet();
						for (Entry<String, String> entry : entrySet) {
							nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
						}
						((HttpPost) method).setEntity(new UrlEncodedFormEntity(nvps,charset));
					}
				}
			} else if (methodType == Method.GET) {
				if (request.getParameters() != null && request.getParameters().size() > 0) {
					StringBuilder sb = new StringBuilder(request.getUrl()).append("?");
					for (String key : request.getParameters().keySet()) {
						sb.append(key).append("=").append(request.getParameters().get(key)).append("&");
					}
					method = new HttpGet(sb.substring(0, sb.length() - 1));
				} else {
					method = new HttpGet(request.getUrl());
				}
			} else if (methodType == Method.STREAM) {
				method = new HttpPost(request.getUrl());
				byte[] data = request.getParameter().getBytes();
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
				
				((HttpPost) method).setEntity(new InputStreamEntity(bais, data.length));
			} else if (methodType == Method.JSON) {
				method = new HttpPost(request.getUrl());
				String parameter = request.getParameter();
				StringEntity entity = new StringEntity(parameter, DEFAULT_CHARSET);
				entity.setContentType("application/json");
				((HttpPost) method).setEntity(entity);
			}
			
			if (methodType == Method.JSON) {
				method.addHeader("Content-Type", "application/json;charset=" + charset);
			} else {
				method.addHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
			}
			
			// 设置Http Header中的User-Agent属性
			method.addHeader("User-Agent", "Mozilla/4.0");
			//设置请求响应超时时间
			method.setConfig(requestConfig);

			org.apache.http.HttpResponse apacheHttpResponse = httpclient.execute(method);

			HttpEntity entity = apacheHttpResponse.getEntity();

			InputStream inputStream = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
			StringBuilder stringBuffer = new StringBuilder();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			response.setStringResult(stringBuffer.toString());
			response.setResponseHeaders(method.getAllHeaders());
			EntityUtils.consume(entity);
		} catch (UnsupportedEncodingException ex) {
			throw ex;
		} catch (IOException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		} finally {
			if(method != null){
				method.releaseConnection();
			}
			if(null != httpclient){
				try {
					httpclient.close();
				} catch (IOException e1) {
					throw e1;
				}
			}
		}
		return response;
	}
	
	
	
	
	

	public static void main(String[] args) throws Exception {
		HttpRequest r = new HttpRequest("http://192.168.3.102:12080/JFPay/portal/tradeProcessor?token_id=2JDSF99324JHSDF889234KSDF9823&digest=wangdianyong@jia-fu.cn");

		HttpResponse resp = new ApacheHttpHandler().get(r);
		System.out.println(resp.getStringResult());

	}
}
