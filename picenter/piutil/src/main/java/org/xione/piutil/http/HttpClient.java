package org.xione.piutil.http;

import java.util.Map;

import org.xione.piutil.http.config.HttpConfig;

public interface HttpClient<T> {

	T processGet(String url, Map<String, String> headers, Map<String, String> params);

	T processGet(HttpConfig httpConfig, Map<String, String> headers, Map<String, String> params);

	T processPost(String url, Map<String, String> headers, Map<String, String> params, Map<String, String> content);

	T processPost(HttpConfig httpConfig, Map<String, String> headers, Map<String, String> params,
			Map<String, String> content);

	T processPost(String url, Map<String, String> headers, Map<String, String> params, byte[] content);

	T processPost(HttpConfig httpConfig, Map<String, String> headers, Map<String, String> params, byte[] content);

	T processPut(String url, Map<String, String> headers, byte[] content);

	T processPut(HttpConfig httpConfig, Map<String, String> headers, byte[] content);

}
