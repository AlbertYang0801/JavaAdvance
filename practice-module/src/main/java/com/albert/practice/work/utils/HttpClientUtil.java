package com.albert.practice.work.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtil {

	public static CloseableHttpClient getClient(){
		CookieStore cookieStore  = new BasicCookieStore();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(25000)
						.setConnectTimeout(25000).setSocketTimeout(25000).build())
				.setDefaultCookieStore(cookieStore).build();
		return httpClient;
	}
	public static String get(CloseableHttpClient httpClient, String url, Map<String, String> headers) throws ClientProtocolException, IOException {
		HttpGet httpGet = new HttpGet(url);
		if (headers != null) {
			headers.entrySet().stream().forEach(
				entry -> {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			);
		}
		HttpResponse httpResponse = httpClient.execute(httpGet);
		int code = httpResponse.getStatusLine().getStatusCode();
		if (code >= 299) {
			log.error("status code is {}", code);
		}
		return EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
	}
	public static String post(CloseableHttpClient httpClient, String url, Map<String, Object> params, Map<String, String> headers)
			throws ParseException, IOException {
		HttpPost httpPost = new HttpPost(url);
		String str;
		if (params == null) {
			Map<String, Object> map = Maps.newHashMap();
			str = JSON.toJSONString(map);
		}else {
			if (params.containsKey(" ")) {
				Object object = params.get(" ");
				str = JSON.toJSONString(object);
			}else {
				str = JSON.toJSONString(params);
			}
		}
		
		StringEntity postEntity = new StringEntity(str, "UTF-8");
		httpPost.setEntity(postEntity);
		if (headers != null) {
			addHeaders(httpPost, headers);
		}
		HttpResponse httpResponse = httpClient.execute(httpPost);
		int code = httpResponse.getStatusLine().getStatusCode();
		if (code >= 299) {
			log.error("status code is {}", code);
		}
		return EntityUtils.toString(httpResponse.getEntity());
	}
	public static String post(CloseableHttpClient httpClient, String url, Object params, Map<String, String> headers)
			throws ParseException, IOException {
		HttpPost httpPost = new HttpPost(url);
		String str;
		if (params == null) {
			Map<String, Object> map = Maps.newHashMap();
			str = JSON.toJSONString(map);
		}else {
			str = JSON.toJSONString(params);
		}
		
		StringEntity postEntity = new StringEntity(str, "UTF-8");
		httpPost.setEntity(postEntity);
		if (headers != null) {
			addHeaders(httpPost, headers);
		}
		HttpResponse httpResponse = httpClient.execute(httpPost);
		int code = httpResponse.getStatusLine().getStatusCode();
		if (code >= 299) {
			log.error("status code is {}", code);
		}
		return EntityUtils.toString(httpResponse.getEntity());
	}
	public static String postByForm(CloseableHttpClient httpClient, String url, Map<String, Object> params, Map<String, String> headers)
			throws ParseException, IOException {
		HttpPost httpPost = new HttpPost(url);
		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(params), "UTF-8");
		httpPost.setEntity(postEntity);
		if (headers != null) {
			addHeaders(httpPost, headers);
		}
		HttpResponse httpResponse = httpClient.execute(httpPost);
		int code = httpResponse.getStatusLine().getStatusCode();
		if (code >= 299) {
			log.error("status code is {}", code);
		}
		return EntityUtils.toString(httpResponse.getEntity());
	}
	
	public static String put(CloseableHttpClient httpClient, String url, Map<String, Object> params, Map<String, String> headers)
			throws ParseException, IOException {
		HttpPut httpPut = new HttpPut(url);
		String str = JSON.toJSONString(params);
		StringEntity postEntity = new StringEntity(str, "UTF-8");
		httpPut.setEntity(postEntity);
		if (headers != null) {
			addHeaders(httpPut, headers);
		}
		HttpResponse httpResponse = httpClient.execute(httpPut);
//		int code = httpResponse.getStatusLine().getStatusCode();
		return EntityUtils.toString(httpResponse.getEntity());
	}
	private static SSLConnectionSocketFactory createSSLsocket() {
		SSLContext sslcontext = null;
		try {
			sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, null, null,
					new NoopHostnameVerifier());
			return sslsf;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static CloseableHttpClient getNoCookieClient(){
		SSLConnectionSocketFactory sslsf = createSSLsocket();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000).setSocketTimeout(10000).build())
		.build();
		
//		CloseableHttpClient httpClient = HttpClients.custom()
//				.setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(30000)
//						.setConnectTimeout(30000).setSocketTimeout(30000).build())
//				.build();
		return httpClient;
	}
	public static CloseableHttpClient getNoCookieClientLongTime(){
		SSLConnectionSocketFactory sslsf = createSSLsocket();
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(RequestConfig.custom().setConnectionRequestTimeout(30000)
				.setConnectTimeout(30000).setSocketTimeout(30000).build())
				.build();
		
		return httpClient;
	}
	
	public static String getSpecifiedUrl(String host, String uri, Map<String, String> param){
		StringBuilder urlBuilder = new StringBuilder(host);
		urlBuilder.append(uri).append("?");
		param.entrySet().stream().forEach(entry -> {
			urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		});
		if (urlBuilder.lastIndexOf("&") == urlBuilder.length()-1) {
			urlBuilder.deleteCharAt(urlBuilder.length()-1);
		}
		return urlBuilder.toString();
	}
	
	public static void closeClient(CloseableHttpClient client) {
		try {
			client.close();
		} catch (IOException e) {
			log.error("close RestClient error",e);
		}
	}
//	public static void encodeStringWithBuffer(StringBuffer buffer, String param) throws UnsupportedEncodingException {
//		buffer.append("contentSelector=");
//		String encode = URLEncoder.encode("[\""+param+"\"]", "utf-8");
//		buffer.append(encode);
//	}
	
//	public static String getSpecifiedUrlWithoutParam(Config conf, String uri, String uriParam) {
//		StringBuilder urlBuilder = new StringBuilder();
//		String uriThuth = conf.getString(uri);
//		if (uriThuth.indexOf(",") >= 0) {
//			String[] uriArr = uriThuth.split(",");
//			urlBuilder.append(conf.getString("ocsystem.host")).append(":")
//				.append(conf.getString("ocsystem.api.port"))
//				.append(uriArr[0]).append(uriParam)
//				.append(uriArr[1]);
//		}else {
//			urlBuilder.append(conf.getString("ocsystem.host")).append(":")
//			.append(conf.getString("ocsystem.api.port"))
//			.append(uriThuth);
//		}
//		return urlBuilder.toString();
//	}
	
	/**
	 * 参数
	 * 
	 * @param parameterMap
	 * @return
	 */
	private static List<NameValuePair> getParam(Map<String, Object> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		if(parameterMap != null)
		for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
			param.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
		}
		return param;
	}

	public static String buildGetParams(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		if (map.size() > 0) {
			for (String key : map.keySet()) {
				sb.append(key + "=");
				if (StringUtils.isEmpty(map.get(key))) {
					sb.append("&");
				} else {
					String value = map.get(key);
					try {
						value = URLEncoder.encode(value, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					sb.append(value + "&");
				}
			}
		}
		return sb.toString();
	}


	private static void addHeaders(HttpEntityEnclosingRequestBase httpRequest, Map<String, String> headerMap) {
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpRequest.addHeader(entry.getKey(), entry.getValue());
		}
	}



}
