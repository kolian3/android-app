package com.appshed.appstore.utils;

import java.util.List;

import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.NameValuePair;
import ch.boye.httpclientandroidlib.client.HttpClient;
import ch.boye.httpclientandroidlib.client.methods.HttpGet;
import ch.boye.httpclientandroidlib.client.methods.HttpPost;
import ch.boye.httpclientandroidlib.config.Registry;
import ch.boye.httpclientandroidlib.config.RegistryBuilder;
import ch.boye.httpclientandroidlib.conn.socket.ConnectionSocketFactory;
import ch.boye.httpclientandroidlib.conn.socket.PlainConnectionSocketFactory;
import ch.boye.httpclientandroidlib.impl.client.HttpClientBuilder;
import ch.boye.httpclientandroidlib.impl.conn.PoolingHttpClientConnectionManager;

/**
 * Created by Anton Maniskevich on 8/27/14.
 */
public class SniRequestsUtils {

	private static final String TAG = SniRequestsUtils.class.getSimpleName();
	private final static Registry<ConnectionSocketFactory> socketFactoryRegistry;
	static {
		socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", TlsSniSocketFactory.INSTANCE)
				.build();
	}

	public static HttpResponse postHttpResponse(String url, List<NameValuePair> nameValuePairs) throws Exception {
//		HttpPost post = new HttpPost(url);
////		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
////		DefaultHttpClient httpClient = new DefaultHttpClient();
//		return httpClient.execute(post);
	}

	public static HttpResponse getHttpResponse(String url) throws Exception {
		HttpGet get = new HttpGet(url);
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connectionManager.setMaxTotal(3);
		connectionManager.setDefaultMaxPerRoute(2);


		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager).build();
		return httpClient.execute(get);
	}
}
