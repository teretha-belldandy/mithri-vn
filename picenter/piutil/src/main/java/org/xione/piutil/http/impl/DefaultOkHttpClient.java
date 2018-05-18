package org.xione.piutil.http.impl;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.xione.piutil.http.HttpResp;
import org.xione.piutil.http.config.HttpConst;

import okhttp3.OkHttpClient;
import okhttp3.Response;

@Component
public class DefaultOkHttpClient extends AbstractOkHttpClient implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultOkHttpClient.class);

	@Override
	public void afterPropertiesSet() throws Exception {
		if (null != DefaultOkHttpClient.okhttpClient) {
			return;
		}

		X509TrustManager xtm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				X509Certificate[] xcert = new X509Certificate[0];
				return xcert;
			}
		};

		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { xtm }, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}

		HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		};

		DefaultOkHttpClient.okhttpClient = new OkHttpClient.Builder()
				.sslSocketFactory(sslContext.getSocketFactory(), xtm).hostnameVerifier(DO_NOT_VERIFY).build();
	}

	@Override
	protected HttpResp processHttpResponse(Response httpResp) {
		HttpResp response = new HttpResp();
		response.setHttpStatus(200); // 默认返回成功
		if (!httpResp.isSuccessful()) {
			LOG.error("Http_response_status_error, statusCode={}", httpResp.code());
			response.setHttpStatus(httpResp.code());
		}
		String rspBody;
		try {
			rspBody = httpResp.body().string();
			response.setEncoding(HttpConst.ENCODE_UTF_8);
			response.setHttpContent(rspBody.getBytes(HttpConst.ENCODE_UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
