package com.sys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



/**
 * HttpClient的应用
 * @author diyong
 *
 */
public class HttpClientUtil {

	private CloseableHttpClient client ;
	private static HttpClientUtil hct = new HttpClientUtil();
	public static HttpClientUtil getInstance(){
		return hct ;
	}
	private HttpClientUtil(){
//		client = HttpClients.createDefault();
		
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(1000);    		//设置连接池线程最大数量  
		connectionManager.setDefaultMaxPerRoute(50);	//设置单个路由最大的连接线程数量  
        
        //创建http request的配置信息  
        RequestConfig requestConfig = RequestConfig.custom()
        		.setConnectionRequestTimeout(3000)
        		.setSocketTimeout(3000)
        		.build();  
        //设置重定向策略  
        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();  
        //初始化httpclient客户端  
        client = HttpClients.custom()
        		.setConnectionManager(connectionManager)
        		.setDefaultRequestConfig(requestConfig)
//        		.setUserAgent(NewsConstant.USER_AGENT)
        		.setRedirectStrategy(redirectStrategy)
        		.build();
//		client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,"UTF-8") ;
	}
	
	/**
	 * get方法获取网页内容
	 * @param url
	 * @param code 网页编码
	 * @param bzip 是否支持压缩
	 * @return
	 */
	public String getUrlContent(String url){
		return this.getUrlContent(url, "utf-8", true) ;
	}
	public String getUrlContent(String url,String code,boolean bzip){
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer("HttpClientUtil|getUrlContent") ;
		buf.append("|").append(url) ;
		
		String html = "" ;
		HttpGet get = new HttpGet(url);  
        CloseableHttpResponse response = null;
		try {
			if(bzip){
				get.setHeader("Accept-Encoding","gzip, deflate") ; 
			}
        	// 执行get请求.
        	response = client.execute(get) ;
        	//获得响应的消息实体  
        	HttpEntity entity = response.getEntity();
        	//获取响应状态码
        	int statuscode = response.getStatusLine().getStatusCode();
        	if (statuscode == HttpStatus.SC_OK) {
        		//InputStream inputStream = entity.getContent();
        		html = EntityUtils.toString(entity,code) ;
        	}
        	//关闭httpEntity流
        	EntityUtils.consume(entity);
	        
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
//			String title = "HttpClientUtil ERROR <br/>"+url ;
//			MailThread.getInstance().sendMail(title, sw.toString()) ;
//			Syslog.info(title+sw.toString()) ;
		} finally{
			if(null != response){
				try {
					//关闭response
					response.close();
				} catch (IOException e) {
                    e.printStackTrace();  
                }  
            }  
        }

		buf.append("|").append(html.length())
		.append("|").append(System.currentTimeMillis()-start) ;
//		Syslog.info(buf.toString()) ;
		
		return html ;
	}
	
	/**
	 * Post方法获取网页内容
	 * @param url
	 * @param map
	 * @param code
	 * @return
	 */
	public String postUrlContent(String url,Map<String,String> map){
		return this.postUrlContent(url, map, "utf-8",true) ;
	}
	public String postUrlContent(String url,Map<String,String> map,String code, boolean bzip){
		long start = System.currentTimeMillis() ;
		StringBuffer buf = new StringBuffer("HttpClientUtil|postUrlContent") ;
		buf.append("|").append(url) ;

		String html = "" ;
		HttpPost post = new HttpPost(url);  
        CloseableHttpResponse response = null;
		try {
			List<NameValuePair> nvps = new ArrayList <NameValuePair>();
			for(String key : map.keySet()) {
				nvps.add(new BasicNameValuePair(key, map.get(key)));
			}
			post.setEntity(new UrlEncodedFormEntity(nvps,code));
			if(bzip){
				post.setHeader("Accept-Encoding","gzip, deflate") ; 
			}
        	// 执行get请求.
        	response = client.execute(post) ;
        	//获得响应的消息实体  
        	HttpEntity entity = response.getEntity();
        	//获取响应状态码
        	int statuscode = response.getStatusLine().getStatusCode();
        	if (statuscode == HttpStatus.SC_OK) {
        		//InputStream inputStream = entity.getContent();
        		html = EntityUtils.toString(entity) ;
        	}
        	//关闭httpEntity流
        	EntityUtils.consume(entity);
	        
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
//			String title = "HttpClientUtil ERROR <br/>"+url;
//			MailThread.getInstance().sendMail(title, sw.toString()) ;
//			Syslog.info(title+sw.toString()) ;
		} finally{
			if(null != response){
				try {
					//关闭response
					response.close();
				} catch (IOException e) {
                    e.printStackTrace();  
                }  
            }  
        }  

		buf.append("|").append(html.length())
		.append("|").append(System.currentTimeMillis()-start) ;
//		Syslog.info(buf.toString()) ;
		
		return html.toString() ;
	}
	
	public String sendRequest(String url,String uuid){
		return this.sendRequest(url, uuid, 3000, 3000);
	}
	public String sendRequest(String url,String uuid, int connectTimeout, int readTimeout) {
		StringBuffer buf = new StringBuffer("HttpClientUtil|sendRequest") ;
		long start = System.currentTimeMillis() ;
		buf.append("|").append(uuid)
		.append("|").append(url) ;
		
		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;
		HttpURLConnection connection = null ;
		try {
			connection = HttpURLConnection.class.cast(new URL(url).openConnection());
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(connectTimeout);
			connection.setUseCaches(false);
			
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
				String s;
				while ( (s = br.readLine()) != null ) {
					builder.append(s).append("\n");
				}
			}
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
//			String title = "HttpClientUtil ERROR <br/>"+url+"&"+uuid ;
//			MailThread.getInstance().sendMail(title, sw.toString()) ;
//			Syslog.info(title+sw.toString()) ;
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != connection) {
				connection.disconnect();
			}
		}
		buf.append("|").append(builder.length())
		.append("|").append(System.currentTimeMillis()-start) ;
//		Syslog.info(buf.toString()) ;
		
		return builder.toString();
	}
	
	public static void main(String[] a){
		HttpClientUtil client = HttpClientUtil.getInstance() ;
		String url = "http://strategy.intra.umessage.com.cn:8180/strategyapi/area_trade.do?pid=u01&pt=2&vt=1&lci=10000000&zip=1&type=0" ;
//		url = "http://strategy.intra.umessage.com.cn:8180/strategyapi/area_leve.do?pid=u01&vt=1&pt=1&leve=1&format=json&zip=1" ;
//		url = "http://wapse.intra.umessage.com.cn:8068/search?keywords=%E8%82%AF%E5%BE%B7%E5%9F%BA&province_id=10&city_id=00&pn=1&rn=5&range=3000&order_by=popular&limit=1000&search_owners=sale,12580,coupon,gis,web,hotel,bunion&use_or=0&uuid=d56488f2-2b94-4263-afb1-80f7f8ffe07c" ;
		String content = client.getUrlContent(url) ;
		long start = System.currentTimeMillis() ;
		content = client.getUrlContent(url) ;
		System.out.println("lenth:"+content.length() +" time:"+ (System.currentTimeMillis()-start));
//		System.out.println(content) ;
		
		
	}
	
}
