package org.xione.piutil.http.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xione.piutil.http.HttpConfig;
import org.xione.piutil.http.HttpMethod;
import org.xione.piutil.http.HttpResp;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class AbstractOkHttpClient extends AbstractHttpClient<HttpResp> {

	public static final Logger LOG = LoggerFactory.getLogger(AbstractOkHttpClient.class);

	public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	public static final MediaType MEDIA_TYPE_OCTET = MediaType.parse("application/octet-stream");

	public static OkHttpClient okhttpClient;

	@Override
	public synchronized HttpResp processPost(HttpConfig httpConfig, Map<String, String> headers,
			Map<String, String> params, Map<String, String> content) {
		HttpUrl url = genHttpUrl(httpConfig, params);
		Headers hds = Headers.of(headers);
		FormBody.Builder requestBodyBuilder = new FormBody.Builder();
		for (String key : content.keySet()) {
			requestBodyBuilder.add(key, content.get(key));
		}
		RequestBody formBody = requestBodyBuilder.build();
		return processHttpRequest(HttpMethod.POST, url, hds, formBody);
	}

	@Override
	public synchronized HttpResp processPost(HttpConfig httpConfig, Map<String, String> headers,
			Map<String, String> params, byte[] content) {
		HttpUrl url = genHttpUrl(httpConfig, params);
		Headers hds = Headers.of(headers);
		RequestBody jsonBody = RequestBody.create(MEDIA_TYPE_JSON, content);
		return processHttpRequest(HttpMethod.POST, url, hds, jsonBody);
	}

	@Override
	public synchronized HttpResp processPut(String url, Map<String, String> headerMap, byte[] content) {
		HttpUrl httpUrl = HttpUrl.parse(url);
		Headers headers = Headers.of(headerMap);
		RequestBody jsonBody = RequestBody.create(MEDIA_TYPE_OCTET, content);
		return processHttpRequest(HttpMethod.PUT, httpUrl, headers, jsonBody);
	}

	private HttpUrl genHttpUrl(HttpConfig httpConfig, Map<String, String> params) {
		if (StringUtils.isBlank(httpConfig.getHost())) {
			return null;
		}
		String schemeType = StringUtils.isNotBlank(httpConfig.getSchemeType()) ? httpConfig.getSchemeType() : "http";
		HttpUrl.Builder urlBuilder = new HttpUrl.Builder().scheme(schemeType).host(httpConfig.getHost());
		if (null != httpConfig.getPort()) {
			urlBuilder = urlBuilder.port(httpConfig.getPort().intValue());
		}
		String fullPath = conbineFullPath(httpConfig.getBasePath(), httpConfig.getSubPath());
		if (StringUtils.isNotBlank(fullPath)) {
			String[] pathSegs = fullPath.split("/");
			for (String seg : pathSegs) {
				if (StringUtils.isNotBlank(seg)) {
					urlBuilder = urlBuilder.addPathSegment(seg);
				}
			}
		}
		if (null != params && !params.isEmpty()) {
			for (Map.Entry<String, String> param : params.entrySet()) {
				urlBuilder.addQueryParameter(param.getKey(), param.getValue());
			}
		}
		return urlBuilder.build();
	}

	private String conbineFullPath(String basePath, String subPath) {
		if (StringUtils.isBlank(basePath) || StringUtils.isBlank(subPath)) {
			return String.format("%s%s", basePath, subPath);
		}
		if (basePath.endsWith("/") && subPath.startsWith("/")) {
			return String.format("%s%s", basePath, subPath.substring(1));
		} else if (!basePath.endsWith("/") && subPath.startsWith("/")) {
			return String.format("%s/%s", basePath, subPath);
		} else {
			return String.format("%s%s", basePath, subPath);
		}
	}

	protected HttpResp processHttpRequest(HttpMethod method, HttpUrl url, Headers headers, RequestBody body) {
		LOG.debug("{}_processHttpRequest, method is {}, HttpUrl is {}, Headers is {}, RequestBody is {}",
				this.getClass(), method, String.valueOf(url), String.valueOf(headers), String.valueOf(body));
		if (null == url || null == body) {
			return null;
		}
		Request.Builder requestBuilder = new Request.Builder().url(url).headers(headers);
		if (HttpMethod.GET.equals(method)) {
			requestBuilder = requestBuilder.get();
		} else if (HttpMethod.POST.equals(method)) {
			requestBuilder = requestBuilder.post(body); // 默认post方法
		} else if (HttpMethod.PUT.equals(method)) {
			requestBuilder = requestBuilder.put(body);
		}
		Request httpRequest = requestBuilder.build();
		if (null == AbstractOkHttpClient.okhttpClient) {
			AbstractOkHttpClient.okhttpClient = new OkHttpClient();
		}
		try {
			Response response = AbstractOkHttpClient.okhttpClient.newCall(httpRequest).execute();
			return processHttpResponse(response);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected abstract HttpResp processHttpResponse(Response httpResp);

}
