package org.xione.piutil.http;

public class HttpConfig {
	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getSubPath() {
		return subPath;
	}

	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Integer getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(Integer requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxPerRoute() {
		return maxPerRoute;
	}

	public void setMaxPerRoute(Integer maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}

	public Integer getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(Integer soTimeout) {
		this.soTimeout = soTimeout;
	}

	public Integer getInActivityTime() {
		return inActivityTime;
	}

	public void setInActivityTime(Integer inActivityTime) {
		this.inActivityTime = inActivityTime;
	}

	private String schemeType;
	private String host;
	private Integer port;
	private String basePath;
	private String subPath;
	private String charset;
	private Integer socketTimeout = 8000;
	private Integer connectTimeout = 10000;
	private Integer requestTimeout = 15000;
	private Integer maxTotal;
	private Integer maxPerRoute;
	private Integer soTimeout;
	private Integer inActivityTime;

	public String toString() {
		return String.format("%s://%s:%s/%s/%s", schemeType, host, port, basePath, subPath);
	}
}