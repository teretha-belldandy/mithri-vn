package org.xione.piutil.http;

public enum HttpMethod {

	GET("GET"), POST("POST"), PUT("PUT"), HEAD("HEAD"), DELETE("DELETE");

	private String method;

	public String getMethod() {
		return method;
	}

	HttpMethod(String method) {
		this.method = method;
	}

}
