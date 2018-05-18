package org.xione.piutil.http.impl;

import java.util.Map;

import org.xione.piutil.http.HttpClient;
import org.xione.piutil.http.config.HttpConfig;

public class AbstractHttpClient<T> implements HttpClient<T> {

	@Override
	public T processGet(String url, Map<String, String> headers, Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processGet(HttpConfig httpConfig, Map<String, String> headers, Map<String, String> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processPost(String url, Map<String, String> headers, Map<String, String> params,
			Map<String, String> content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processPost(HttpConfig httpConfig, Map<String, String> headers, Map<String, String> params,
			Map<String, String> content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processPost(String url, Map<String, String> headers, Map<String, String> params, byte[] content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processPost(HttpConfig httpConfig, Map<String, String> headers, Map<String, String> params,
			byte[] content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processPut(String url, Map<String, String> headers, byte[] content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T processPut(HttpConfig httpConfig, Map<String, String> headers, byte[] content) {
		// TODO Auto-generated method stub
		return null;
	}

}
